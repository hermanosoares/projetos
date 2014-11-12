package br.com.bulktecnologia.controle.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.LockModeType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.modelo.dao.ConfiguracaoDAO;
import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;
import br.com.bulktecnologia.modelo.enums.TipoTurno;

@Name("ConfiguracaoService")
@Scope(ScopeType.CONVERSATION)
public class ConfiguracaoService extends BaseCrudService{
	
	public ConfiguracaoService() {
		super(false);
	}
	
	@In
	private FacesMessages facesMessages;
	
	@In(required=false)
	@Out(required=false)
	private ConfiguracaoEntity ConfiguracaoEntity;

	@In
	private CookieSessaoUsuario cookie;
	
	@In
	private ConfiguracaoDAO ConfiguracaoDAO;
	
	private TipoTurno turno;
	
	
	public String selecionarConfiguracaoGlobal(ConfiguracaoEntity c){
		this.cookie.setIdConfigGlobalSelecionada(c.getId());
		this.cookie.setNomeConfigGlobalSelecionada(c.getNome());
		
		return "intituicoesensino";
	}
	
	@Override
	public String edita(Object o) {
		this.ConfiguracaoEntity = (ConfiguracaoEntity)o;
		return PAGINA_ADD_EDIT;
	}
	
	@Override
	public String cancela() {
		if (cookie.getConfigEspecificaInstituicao()==null){
			return "intituicoesensino";
		}
		else{
			return "cancela";
		}
	}
	
	@Override
	public String insere() {
		this.ConfiguracaoEntity = null;
		return PAGINA_ADD_EDIT;
	}

	
	
/*	*//**
	 * ativa configuracao para um registro de configuracao, e desativa para o que estiver atual.
	 * @param c
	 *//*
	public String ativaConfiguracao(ConfiguracaoEntity c){
		
		ConfiguracaoEntity confAtiva = ConfiguracaoDAO.recuperaConfiguracaoAtiva(c.getInstituicao().getId(),false);

		//verifico se entidade existe no entitymanager
		if (isManaged(confAtiva)){
			//realizo lock para evitar que concorrentemente outro usuario realize manipule este objeto
			ConfiguracaoDAO.getEm().lock(confAtiva, LockModeType.READ);
		}
		
		c.setAtivo(Boolean.TRUE);
		
		if (confAtiva!=null){
			confAtiva.setAtivo(Boolean.FALSE);
			ConfiguracaoDAO.merge(confAtiva);
		}
		ConfiguracaoDAO.merge(c);
		ConfiguracaoDAO.flush();
		this.cookie.setConfigEspecificaInstituicao(c);
		this.ConfiguracaoEntity = c;
		facesMessages.add("Configuracao: "+c.getNome()+" ativada com sucesso!");
		return PAGINA_LIST;
	}*/

	
	public String addTurno(ConfiguracaoEntity c){

		if (turno==null){
			facesMessages.add("selecione um turno");
			return PAGINA_ADD_EDIT;
		}
		
		if ( c.getTurnos() == null ){
			 c.setTurnos(new ArrayList<TurnoEntity>());
		}
		
		for (TurnoEntity te : c.getTurnos()){
			if ( te.getTipoturno().equals(turno) ){
				facesMessages.add("turno: "+turno.name()+" já existente!");
				return PAGINA_ADD_EDIT;
			}
		}
		
		TurnoEntity t = new TurnoEntity(turno);
		t.setConfiguracao(ConfiguracaoEntity);
		
		c.getTurnos().add(t);
		return PAGINA_ADD_EDIT;
	}

	

	
	public TipoTurno getTurno() {
		return turno;
	}

	public void setTurno(TipoTurno turno) {
		this.turno = turno;
	}



	@Override
	protected boolean antesGravar(Object o) {
		
		ConfiguracaoEntity c = (ConfiguracaoEntity)o;
		
		if (cookie.getConfigEspecificaInstituicao()!=null){
			
			if (!isManaged(c)){
				InstituicaoEntity i = (InstituicaoEntity)ConfiguracaoDAO.find(InstituicaoEntity.class,cookie.getIdInstituicao());
				c.setInstituicao(i);
			}
			
		}

		return true;
	}

	
	
	@Override
	protected boolean antesRemover(Object o) {
		return true;
	}

	
	@Override
	protected boolean antesRemoverDetalhe(Collection<Object> collectionMestre,Object detalhe) {
		return true;
	}
	
	
	
	
}
