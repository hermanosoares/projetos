package br.com.bulktecnologia.modelo.dao;



import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.controle.exception.EmailDuplicadoException;
import br.com.bulktecnologia.modelo.entidade.EsqueciSenhaEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioInstituicaoEntity;
import br.com.bulktecnologia.modelo.enums.Perfil;
import br.com.bulktecnologia.modelo.enums.TipoLogout;

@Name("UsuarioDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class UsuarioDAO extends BaseDAO{

	
	
	public Integer atualizaLogoutAuditoriaAcesso(Long idAuditoriaAcesso,TipoLogout tipoLogout){
		Query q1 = this.getEm().createNamedQuery("AuditoriaAcessoEntity.atualizaDtHoraLogout");
		q1.setParameter("dthoralogout", new Date());
		TipoLogout tpLogout = tipoLogout==null?TipoLogout.DESCONECTADO:tipoLogout;
		q1.setParameter("tipoLogout",tpLogout.name());
		q1.setParameter("idAuditoriaAcesso",idAuditoriaAcesso);
		return q1.executeUpdate();
	}
	
	
	
	
	public Integer atualizaStatusDeLogado(Long idUsuario,Boolean statusLogado){
		Query q = this.getEm().createNamedQuery("UsuarioEntity.atualizaStatusUsuarioLogado");
		q.setParameter("statusLogado", statusLogado);
		q.setParameter("idUsuario", idUsuario);
		return q.executeUpdate();
	}
	
	
	
	
	
	/**
	 * devolve a quantidade de vezes que foram redefinidos a senha de acesso do login para o dia atual. 
	 * @param login
	 * @return Long
	 */
	public Long countEsqueciSenha(String login){
		Query q = this.getEm().createNamedQuery("UsuarioEntity.countEsqueciSenha");
		q.setParameter("login", login);
		return (Long)q.getSingleResult();
	}
	
	

	
	
	/**
	 * recupera usuario baseado no login e cpf informado.
	 * usado para redefinicoes de senha.
	 * @param login
	 * @return UsuarioEntity
	 */
	public UsuarioEntity recuperaUsuarioByEmail(String emailPrincipal)throws EmailDuplicadoException{
		Query q = this.getEm().createNamedQuery("UsuarioEntity.recuperaUsuarioByEmail");
		q.setParameter("email",emailPrincipal);
		
		try {
			UsuarioEntity u =  (UsuarioEntity) q.getSingleResult();
			return u;
		} catch (NoResultException e) {
			return null;
		}
		catch (NonUniqueResultException e) {
			throw new EmailDuplicadoException("Entre em contato com a central de suporte, este email está duplicado!");
		}
		
	}

	
	
	/**
	 * Insere um novo registro historico de redefinicao de senha para o usuario informado.
	 * @param u
	 */
	public void insereNovoEsqueciSenha(UsuarioEntity u){
		EsqueciSenhaEntity e = new EsqueciSenhaEntity();
		e.setData(new Date());
		e.setUsuario(u);
		
		this.getEm().persist(e);
	}
	
	
	
	
	public List<UsuarioInstituicaoEntity> recuperaTodosUsuarioDaInstituicao(Long idInstituicao){
		Query q = this.getEm().createNamedQuery("UsuarioInstituicao.recuperaTodosUsuariosDaInstituicao");
		q.setParameter("instituicao",idInstituicao);
		return q.getResultList();
	}
	
	
	
	
	
	
	public UsuarioEntity recuperaUsuarioByCpf(String cpf){
		Query q = this.getEm().createNamedQuery("UsuarioEntity.recuperaUsuarioByCPF");
		q.setParameter("cpf", cpf);
		
		try {
			UsuarioEntity u = (UsuarioEntity) q.getSingleResult();
			return u;
		} catch (NoResultException e) {
			return null;
		} catch(NonUniqueResultException e){
			log.fatal("Erro Fatal Mais de um Usuário encontrado para o CPF: "+cpf);
			return null;
		}
		
		
	}
	
	
	
	public Boolean LoginDisponivel(String login){
		Query q = this.getEm().createNamedQuery("UsuarioEntity.verificaDisponibilidadeLogin");
		q.setParameter("login",login);
		
		try {
			Long encontrado = (Long) q.getSingleResult();
			if (encontrado==0){
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		} catch (NoResultException e) {
			log.fatal(e);
			return Boolean.TRUE;
		} catch (NonUniqueResultException e) {
			log.fatal(e);
			return Boolean.FALSE;
		}
	}
	
	
	
	/**
	 * altera senha do usuario.
	 * 
	 * @param idUsuario
	 * @param novaSenha
	 * @return Integer = registro afetados.
	 */
	public Integer alteraSenha(Long idUsuario, String novaSenha){
		Query q = this.getEm().createNamedQuery("UsuarioEntity.alterasenha");
		q.setParameter("novaSenha",novaSenha);
		q.setParameter("idUsuario",idUsuario);
		return q.executeUpdate();
	}
	

	


	
	/**
	 * recupera usuario apartir de seu login.
	 * @param login
	 * @return UsuarioEntity
	 */
	public UsuarioEntity recuperaUsuarioByLogin(String login){
		
		Query q = this.getEm().createNamedQuery("UsuarioEntity.recuperaUsuarioByLogin");
		q.setParameter("login", login);
		
		try {
			return (UsuarioEntity) q.getSingleResult();
		} catch (NoResultException nre) {
			return null; 
		}
		
	}
	
	
	
	
	
	

	
	public UsuarioInstituicaoEntity recuperaUsuarioInstituicaoByUsuarioEInstituicao(Long idInstituicao, Long idUsuario){
		try {
			Query q = this.getEm().createNamedQuery("UsuarioInstituicao.recuperaUsuarioInstituicaoByUsuarioEInstituicao");
			q.setParameter("idInstituicao",idInstituicao);
			q.setParameter("idUsuario",idUsuario);
			return (UsuarioInstituicaoEntity) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	public List<Perfil> recuperaPerfis(Long idInstituicao,Long idUsuario){
		Query q = this.entityManager.createNamedQuery("UsuarioInstituicao.recuperaPerfisByUsuarioEInstituicao");
		q.setParameter("idInstituicao",idInstituicao);
		q.setParameter("idUsuario",idUsuario);
		return q.getResultList();
	}
	
	
	
	
	
}

