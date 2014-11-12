package br.com.bulktecnologia.modelo.dao;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.TurnoEntity;

@Name("TurnoDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class TurnoDAO extends BaseDAO{

/*
	*//**
	 * Recupera os turnos da configuracao ativa da instituicao.
	 * 
	 * @param idInstituicao
	 * @return 
	 *//*
	public List<TurnoEntity> recuperaTurnosByInstituicaoAtiva(Long idInstituicao){
		Query q = this.getEm().createNamedQuery("ConfiguracaoEntity.recuperaTurnosByInstituicaoAtiva");
		q.setParameter("idInstituicao", idInstituicao);
		return q.getResultList();
	}
*/	
	
	public List<TurnoEntity> recuperaTurnosByIdConfiguracao(Long idConfiguracao){
		Query q = this.getEm().createNamedQuery("ConfiguracaoEntity.recuperaTurnosByIdConfiguracao");
		q.setParameter("idConfiguracao", idConfiguracao);
		return q.getResultList();
	}
		
}
