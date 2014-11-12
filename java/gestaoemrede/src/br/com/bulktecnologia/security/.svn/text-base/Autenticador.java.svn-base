package br.com.bulktecnologia.security;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.controle.service.AuditoriaService;
import br.com.bulktecnologia.modelo.dao.PreferenciaDAO;
import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.Manutencao;
import br.com.bulktecnologia.modelo.entidade.PreferenciaUsuarioEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioEntity;
import br.com.bulktecnologia.modelo.enums.TipoLogout;

@Name("authenticator")
public class Autenticador{
	
	@In
	private UsuarioDAO  UsuarioDAO;

	@In
	private PreferenciaDAO preferenciaDAO;

	@In(required=false,value="#{identity.username}")
	private String usuario;
	
	@In(required=false,value="#{identity.password}")
	private String senha;
	
	@In(required=false)
	@Out(required=false)
	private CookieSessaoUsuario cookie;
	
	@In
	private FacesMessages facesMessages;
	
	@In(required=false)
	private HttpServletRequest httpServletRequest;
	
	private Boolean permitirAcesso = Boolean.FALSE;
	
	private UsuarioEntity u;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	public boolean login(){
		
		cookie = new CookieSessaoUsuario();

		try {
			Query q = this.UsuarioDAO.getEm().createQuery("select u from UsuarioEntity u where lower(u.login) = lower(#{identity.username}) and u.senha = #{identity.password}");
			u =  (UsuarioEntity)q.getSingleResult();

			if (u!=null){
					
				if ( !u.getGlobal() ){
					List<Manutencao> manutencoes  = this.UsuarioDAO.getEm().createNamedQuery("Manutencao.recuperaManutencaoProgramada").getResultList();
					if (manutencoes.size()>0){
						facesMessages.add(manutencoes.get(0).getMensagem());
						return Boolean.FALSE;
					}
				}
				
				cookie.setIdUsuario(u.getId());
				cookie.setLogin(u.getLogin());
				cookie.setSenha(u.getSenha());
				cookie.setGlobal(u.getGlobal());
				
				PreferenciaUsuarioEntity pu = preferenciaDAO.recuperaPreferencia(u.getId(), "skin");
				if (pu!=null){
					cookie.setSkin(pu.getValor());	
				}
				
				
				if ( u.getLogado() ){
					facesMessages.add("O Login: "+u.getLogin()+", já encontra-se conectado.");
					facesMessages.add("Caso você tenha fechado o browser sem 'sair' da aplicação, em até 10 minutos seu login será liberado.");
					facesMessages.add("Caso contrário, existe outro usuário conectado na aplicação com este Login.");
					cookie = null;
					return Boolean.FALSE;
				}

				permitirAcesso = Boolean.TRUE;

			}

			if (permitirAcesso){

				if (u!=null){
					this.UsuarioDAO.atualizaStatusDeLogado(u.getId(), Boolean.TRUE);
				}

				AuditoriaService auditoriaService = (AuditoriaService) Component.getInstance("AuditoriaService");
				Long idAuditoriaAcesso =  auditoriaService.criaAuditoriaAcesso(usuario, 
						httpServletRequest.getRemoteHost(),
						httpServletRequest.getRemoteAddr(),
						httpServletRequest.getHeader("User-Agent"));

				this.cookie.setIdAuditoriaAcesso(idAuditoriaAcesso);
			}
			
		} catch (NoResultException e) {
			log.debug("usuario/senha invalido");
		}
			
		return permitirAcesso;
	}
	

	

	@Observer(value={"org.jboss.seam.preDestroyContext.SESSION"})
	@Transactional
	public void logout(){
		if (cookie!=null){

			try {
				this.UsuarioDAO.atualizaLogoutAuditoriaAcesso(this.cookie.getIdAuditoriaAcesso(), this.cookie.getTipoLogout());	
				
				//notifica que usuário realizou um logout
				if ( this.cookie.getIdUsuario()!=null ){
					this.UsuarioDAO.atualizaStatusDeLogado(this.cookie.getIdUsuario(), Boolean.FALSE);
				}
				
			} catch (Exception e) {
				//TODO enviar email informado o erro que houve e notificar que login de usuario encontra-se travado.
				log.fatal("erro fatal ao tentar deslogar usuário no 'Autenticador.logout' via evento : org.jboss.seam.preDestroyContext.SESSION");
				log.fatal(e);
			}
		}
	}
	
	
	
	
	
	public void NotificaTipoLogout(){
		this.cookie.setTipoLogout(TipoLogout.REALIZOU_SAIDA);
	}
	
	
}
