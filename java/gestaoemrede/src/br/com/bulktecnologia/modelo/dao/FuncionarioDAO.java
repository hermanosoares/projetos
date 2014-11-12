package br.com.bulktecnologia.modelo.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;

import br.com.bulktecnologia.modelo.entidade.ContratoTrabalhoEntity;
import br.com.bulktecnologia.modelo.entidade.FuncionarioEntity;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;

@Name("FuncionarioDAO")
@Scope(ScopeType.STATELESS)
@AutoCreate
@Transactional
public class FuncionarioDAO extends BaseDAO{

	
	
	public FuncionarioEntity recuperaFuncionaroByCodigoFuncional(String codigoFuncional){
		
		try {
			Query q =  this.getEm().createNamedQuery("FuncionarioEntity.recuperaFuncionarioByCodigoFuncional");
			q.setParameter("codigoFuncional",codigoFuncional);
			return (FuncionarioEntity) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	
	
	/**
	 * recupera todos os vinculos funcionais da pessoa.
	 * @param idPessoa
	 * @return List<FuncionarioEntity>
	 */
	public List<FuncionarioEntity> recuperaRelacoesFuncionaisDaPessoa(Long idPessoa){
		Query q = this.getEm().createNamedQuery("FuncionarioEntity.recuperaRelacoesFuncionaisDaPessoa");
		q.setParameter("idPessoa", idPessoa);
		return q.getResultList();
	}
	
	
	
	public FuncionarioEntity recuperaFuncionarioByIdPessoa(Long idPessoa){
		Query q =  this.getEm().createNamedQuery("FuncionarioEntity.recuperaFuncionarioByIdPessoa");
		q.setParameter("idPessoa", idPessoa);
		try {
			return (FuncionarioEntity) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	
	public ContratoTrabalhoEntity recuperaContratoTrabalhoByFuncionarioInstituicao(Long idFuncionario, Long idInstituicao){
		Query q = this.getEm().createNamedQuery("ContratoTrabalhoEntity.recuperaContratoTrabalhoByFuncionarioInstituicao");
		q.setParameter("idFuncionario", idFuncionario);
		q.setParameter("idInstituicao", idInstituicao);
		try {
			return (ContratoTrabalhoEntity)q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	
	
}
