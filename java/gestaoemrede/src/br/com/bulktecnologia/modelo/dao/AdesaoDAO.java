package br.com.bulktecnologia.modelo.dao;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.AdesaoEntity;
@Name("AdesaoDAO")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class AdesaoDAO extends BaseDAO{

	public List<AdesaoEntity> recuperaTodasAdesoes(){
		Query q =  this.getEm().createNamedQuery("AdesaoEntity.recuperaTodasAdesoes");
		return q.getResultList();
	}

}
