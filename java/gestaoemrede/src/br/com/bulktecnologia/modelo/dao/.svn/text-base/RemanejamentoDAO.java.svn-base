package br.com.bulktecnologia.modelo.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.AlocacaoItemEntity;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.RemanejamentoEntity;
@Name("RemanejamentoDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class RemanejamentoDAO extends BaseDAO{

	public List<RemanejamentoEntity> recuperaRemanejamentosByIdMatricula(List<Long> idMatriculas){
		Query q = this.getEm().createNamedQuery("RemanejamentoEntity.recuperaRemanejamentoByIdMatriculas");
		q.setParameter("idMatriculas", idMatriculas);
		return q.getResultList();
	}
	
	
	public Integer atualizaAlocacaoItemParaNaoRemanejada(List<Long> idsAlocacaoItemOrigem){
		Query q = this.getEm().createNamedQuery("RemanejamentoEntity.atualizaAlocacaoItemOrigemParaNaoRemanejada");
		q.setParameter("idsAlocacaoItemOrigem", idsAlocacaoItemOrigem);
		return q.executeUpdate();
	}
	
	
	public Integer removeRemanejamentos(List<Long> idsRemanejamentos){
	 Query q =this.getEm().createNamedQuery("RemanejamentoEntity.removeHistoricoRemanejamentoByIdsRemanejamentos");
	 q.setParameter("idsRemanejamentos", idsRemanejamentos);
	 return q.executeUpdate();
	}
	
	
	public Integer removeAlocacaItemDestino(List<Long> idsAlocacaoItemDestino){
		
		for (Long idAlocItem: idsAlocacaoItemDestino){
			AlocacaoItemEntity alocItem =  this.getEm().find(AlocacaoItemEntity.class, idAlocItem);

			Iterator<DisciplinaEntity> it = alocItem.getDisciplinas().iterator();
			while (it.hasNext()){
				DisciplinaEntity d  = it.next();
				d.getAlocacaoItems().remove(alocItem);
				it.remove();
			}
			this.getEm().flush();
		}
		
		Query q = this.getEm().createNamedQuery("RemanejamentoEntity.removeAlocacaoItemDestino");
		q.setParameter("idsAlocacaoItemDestino", idsAlocacaoItemDestino);
		return q.executeUpdate();
	}
	
	
	public Integer removeMatriculasRemanejadasEAtualizaMatriculasOrigemParaNaoEncerrada(List<Long> idsMatriculaDestino,List<Long> idsMatriculaOrigem){
		Query q = this.getEm().createNamedQuery("RemanejamentoEntity.removeMatriculaDependenciaRemanejada");
		q.setParameter("idsMatriculaRemanejadas", idsMatriculaDestino);
		Integer affected1 = q.executeUpdate();
		
		Query q2 = this.getEm().createNamedQuery("RemanejamentoEntity.removeMatriculaRemanejada");
		q2.setParameter("idsMatriculaRemanejadas", idsMatriculaDestino);
		Integer affected2 = q2.executeUpdate();
		
		Query q3 = this.getEm().createNamedQuery("RemanejamentoEntity.atualizaMatriculaOrigemParaNaoRemanejada");
		q3.setParameter("idsMatriculaOrigem", idsMatriculaOrigem);
		Integer affected3 = q3.executeUpdate();
		
		return  affected2;
	}
	
	
	
	
	
	
	
	
}
