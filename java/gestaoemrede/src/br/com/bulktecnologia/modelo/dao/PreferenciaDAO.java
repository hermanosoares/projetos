package br.com.bulktecnologia.modelo.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.PreferenciaUsuarioEntity;

@Name("preferenciaDAO")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class PreferenciaDAO extends BaseDAO {

	
	
	public PreferenciaUsuarioEntity recuperaPreferencia(Long idUsuario,String chave){
		try {
			Query q = this.getEm().createNamedQuery("PreferenciaUsuarioEntity.recuperaPreferencia");
			q.setParameter("idUsuario", idUsuario);
			q.setParameter("chave", chave);
			return (PreferenciaUsuarioEntity) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
}
