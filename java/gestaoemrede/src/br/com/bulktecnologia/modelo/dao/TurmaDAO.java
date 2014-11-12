package br.com.bulktecnologia.modelo.dao;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.comuns.util.TurmaHelper;
import br.com.bulktecnologia.modelo.entidade.TurmaEntity;
import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;
import br.com.bulktecnologia.modelo.enums.TipoSerie;

@Name("TurmaDAO")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class TurmaDAO extends BaseDAO {


	public List<TurmaEntity> recuperaTurmasDaConfiguracaoAtivaByTipoFormacaoTurma(Long idConfiguracaoEspecifica,TipoFormacaoTurma tipoFormacaoTurma, Long idSerie, Long idTurno){
		
		String namedQuery = null;
		
		if ( TurmaHelper.isTurmaPorSerie(tipoFormacaoTurma) ){
			namedQuery = "TurmaEntity.recuperaTurmasByTipoFormacaoTurmaRegularEMultiSeriada";
		}
		else{
			namedQuery = "TurmaEntity.recuperaTurmasByTipoFormacaoTurmaPorDisciplinaEMultiSeriadaPorDisciplina";
		}
		
		Query q = this.getEm().createNamedQuery(namedQuery);
		q.setParameter("tipoFormacaoTurma", tipoFormacaoTurma);
		q.setParameter("idSerie", idSerie);		
		q.setParameter("idTurno", idTurno);
		q.setParameter("idConfiguracaoEspecifica", idConfiguracaoEspecifica);
		
		return q.getResultList();
	}
	
	
	

	public List<TipoFormacaoTurma> recuperaTiposFormacaoTurmaDaConfiguracaoAtiva(Long idConfiguracaoEspecifica){
		 Query q = this.getEm().createNamedQuery("TurmaEntity.recuperaTiposFormacaoTurmaDaConfiguracaoAtiva");
		 q.setParameter("idConfiguracaoEspecifica",idConfiguracaoEspecifica);
		 return q.getResultList();
	}
	

	public List<TurmaEntity> recuperaTodasTurmasDaConfiguracaoAtiva(Long idConfiguracaoEspecifica){
		 Query q = this.getEm().createNamedQuery("TurmaEntity.recuperaTodasTurmasDaConfiguracaoAtiva");
		 q.setParameter("idConfiguracaoEspecifica",idConfiguracaoEspecifica);
		 return q.getResultList();
	}

	
	
	
	public List<TurmaEntity> recuperaTurmasDasSeries(List<Long> idsSeries){
		 Query q = this.getEm().createNamedQuery("TurmaEntity.recuperaTurmasDasSeries");
		 q.setParameter("idsSeries",idsSeries);
		 return q.getResultList();
	}
	
	
}
