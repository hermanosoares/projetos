package br.com.bulktecnologia.modelo.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.NonUniqueObjectException;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;

import br.com.bulktecnologia.modelo.entidade.EspacoFisicoEntity;


@Name("EspacoFisicoDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class EspacoFisicoDAO extends BaseDAO{

	
	
	/**
	 * recupera todos os espacos fisico da instituicao
	 */
	public List<EspacoFisicoEntity> recuperaEspacosFisico(Long idInstituicao){
		Query q = this.getEm().createNamedQuery("EspacoFisicoEntity.recuperaEspacosFisico");
		q.setParameter("idInstituicao", idInstituicao);
		return q.getResultList();
	}
	
	
	
	
	/**
	 * recupera SalaAulaEntity pelo Id
	 */
	public EspacoFisicoEntity recuperaEspacoFisico(Long idEspacofisico){
		return (EspacoFisicoEntity) this.getEm().find(EspacoFisicoEntity.class, idEspacofisico);
	}
	
	
	
	
	
	/**
	 * Recupera Espaco Fisico pelo 'identificador do espaço' e 'tipo do espaco' na referida instituicao
	 * caso retorne uma objeto é porque o espaco ja está cadastrado,.
	 * caso retorne null, a gravação ou atualização é permitida.
	 */
	public EspacoFisicoEntity validaEspacoFisicoDuplicado(String identificadorEspaco, String tipoEspaco,Long idInstituicao, Long idEspacoFisicoJaExistente){
		
		Query q = this.getEm().createNamedQuery("EspacoFisicoEntity.validaEspacoFisicoDuplicado");
		q.setParameter("identificadorEspaco", identificadorEspaco);
		q.setParameter("tipoEspaco",tipoEspaco);
		q.setParameter("idInstituicao",idInstituicao);
		
		try {
				List<EspacoFisicoEntity> espacos = q.getResultList();
				
				for (EspacoFisicoEntity e: espacos){
					if (idEspacoFisicoJaExistente!=null && idEspacoFisicoJaExistente.compareTo(e.getId())!=0){
						return e;
					}
					else{
						if (idEspacoFisicoJaExistente==null){
							return e;
						}
					}
				}
				
				return null;
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueObjectException e) {
			return null;
		}
	}
	
	
	
}
