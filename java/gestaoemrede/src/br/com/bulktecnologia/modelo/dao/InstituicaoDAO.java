package br.com.bulktecnologia.modelo.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.ProjetoEntity;
import br.com.bulktecnologia.modelo.entidade.TipoFuncaoEntity;

@Name("InstituicaoDAO")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class InstituicaoDAO extends BaseDAO{
	
	
	public InstituicaoEntity recuperaInstituicaoByCodInstituicao(String codInstituicao){
	 	Query q = this.getEm().createNamedQuery("InstituicaoEntity.recuperaInstituicaoByCodInstituicao");
	 	q.setParameter("codInstituicao", codInstituicao);
	 	try {
			return (InstituicaoEntity) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<ProjetoEntity> recuperaProjetosByInstituicao(Long idInstituicao){
	 	Query q = super.getEm().createNamedQuery("InstituicaoEntity.recuperaProjetosByInstituicao");
	 	q.setParameter("idInstituicao", idInstituicao);
	 	return q.getResultList();
	}
	
	public List<InstituicaoEntity> recuperaTodasInstituicoes(){
		Query q = getEm().createNamedQuery("InstituicaoEntity.recuperaTodasInstituicoes");
		return q.getResultList();
	}
	
	
	public List<ProjetoEntity> recuperaProjetosAtivosByInstituicao(Long idInstituicao){
	 	Query q = super.getEm().createNamedQuery("InstituicaoEntity.recuperaProjetosAtivosByInstituicao");
	 	q.setParameter("idInstituicao", idInstituicao);
	 	return q.getResultList();
	}
	

	
	/**
	 * Recupera os tipos de funcoes dos funcionarios da instituicao
	 * @return List<TipoFuncaoEntity>
	 */
	public List<TipoFuncaoEntity> recuperaTiposFuncoesFuncionarios(Long idInstituicao){
		Query q = getEm().createNamedQuery("TipoFuncaoEntity.recuperaFuncoesByInstituicao");
		q.setParameter("idInstituicao",idInstituicao);
		return q.getResultList();
	}
	
	
	public List<InstituicaoEntity> recuperaInstituicoesByUsuarioId(Long idUsuario){
		Query q0 = getEm().createNamedQuery("UsuarioInstituicao.recuperaInstituicoesDoUsuario");
		q0.setParameter("idusuario", idUsuario);
		q0.setHint("org.hibernate.cacheable", true);
		q0.setHint("org.hibernate.cacheRegion", "sampleCache3");

		return  q0.getResultList();
	}

	
	
	
	
}
