package br.com.bulktecnologia.modelo.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.ejb.QueryImpl;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
@Name("BaseDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class BaseDAO{

	protected EntityManager entityManager;

	public BaseDAO(){
		this.entityManager = (EntityManager)Component.getInstance("entityManager");
	}

	protected Logger log = Logger.getLogger(BaseDAO.class);
	
	@SuppressWarnings("unchecked")
	public Object find(Class clazz, Object pk){
		return this.entityManager.find(clazz, pk);
	}

	@Transactional
	public void persist(Object o){
		this.entityManager.persist(o);
	}
	
	@Transactional
	public Object merge(Object o){
		return this.entityManager.merge(o);
	}
	
	@Transactional
	public void remove(Object o){
		try {
			this.entityManager.remove(o);
		} catch (Exception e) {
			this.entityManager.getTransaction().setRollbackOnly();
		}
	}

	
	public void flush(){
		this.entityManager.flush();
	}
	
	
	public void refresh(Object o){
		this.entityManager.refresh(o);
	}
	
	
	public EntityManager getEm() {
		return entityManager;
	}

	public List<Object> getAll(Class entityClazz){
		return this.getEm().createQuery("SELECT x FROM "+entityClazz.getSimpleName()+" x").getResultList();
	}

	
	public void configuraParametrosQuery(Query q, Map<String,Object> parametros){
		 QueryImpl impl = (QueryImpl) q;
		List<String>  namedParameters = Arrays.asList(impl.getHibernateQuery().getNamedParameters());
		
		for (String name : namedParameters){
			Object valor = parametros.get(name);
			q.setParameter(name,valor);
		}
	}

	
	public boolean isManaged(Object o){
		return getEm().contains(o);		
	}

}
