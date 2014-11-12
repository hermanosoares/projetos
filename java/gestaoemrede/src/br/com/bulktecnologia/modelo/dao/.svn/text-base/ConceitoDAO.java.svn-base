package br.com.bulktecnologia.modelo.dao;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.Conceito;

@Name("ConceitoDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class ConceitoDAO extends BaseDAO {
	
	

	public List<Conceito> recuperaConceitosDaConfiguracao(Long idConfiguracao){
		Query q = super.entityManager.createNamedQuery("Conceito.recuperaConceitoDaConfiguracao");
		q.setParameter("idConfiguracaoEspecifica", idConfiguracao);
		return q.getResultList();
	}
	
	
	
}
