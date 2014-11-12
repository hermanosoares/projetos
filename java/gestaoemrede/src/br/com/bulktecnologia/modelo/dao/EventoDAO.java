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

import br.com.bulktecnologia.modelo.entidade.EventoEntity;

@Name("EventoDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class EventoDAO extends BaseDAO{

	/**
	 * Recupera todos os eventos de acordo com o ID da Instituição
	 * @param id
	 * @return
	 */
	public List<EventoEntity> recuperaEventos(Long id){
		Query q = this.getEm().createNamedQuery("EventoEntity.recuperaEventos");
		q.setParameter("idInstituicao",id);
		return q.getResultList();
	}
	
	
	
	/**
	 * Recupera Evento pela 'descricao do evento' na referida instituicao
	 * caso retorne uma objeto é porque o Evento ja está cadastrado,.
	 * caso retorne null, a gravação ou atualização é permitida.
	 */
	public EventoEntity validaEventoDuplicado(Long idInstituicao, String descricaoEv){
		Query q = this.getEm().createNamedQuery("EventoEntity.validaEventoEntityDuplicado");
		q.setParameter("descricaoEvento", descricaoEv);
		q.setParameter("idInstituicao", idInstituicao);
		
		try {
			List<EventoEntity> eventos = q.getResultList();
			
			for (EventoEntity ev: eventos){
				if (descricaoEv != null && descricaoEv.compareTo(ev.getDescricao())==0){
					return ev;
				}
				else{
					if (descricaoEv==null){
						return ev;
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
