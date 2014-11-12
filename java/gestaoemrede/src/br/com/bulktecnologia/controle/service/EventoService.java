package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.EventoDAO;
import br.com.bulktecnologia.modelo.entidade.EventoEntity;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;

@Name("EventoService")
@Scope(ScopeType.CONVERSATION)
public class EventoService extends BaseCrudService implements Serializable{

	public EventoService() {
		super(false);
	}


	@In(required=false)
	@Out(required=false)
	private EventoEntity EventoEntity;

	
	@In
	private EventoDAO EventoDAO;

	
/*	@Override
	public String grava(Object o){
		this.EventoEntity = (EventoEntity) o;
		EventoEntity evento = EventoDAO.validaEventoDuplicado(  getCookie().getIdInstituicao(),
																EventoEntity.getDescricao());
		if(EventoEntity.getDataInicio().compareTo(EventoEntity.getDataTermino()) == 1){
			facesMessages.add("Data inválida: Data de inicio maior que data final.");
			return PAGINA_ADD_EDIT;
		}
		
		
		if(evento != null){
			facesMessages.add("Evento: #{EventoEntity.descricao} já existe!");
			return PAGINA_ADD_EDIT;
		} else {
			InstituicaoEntity instituicao = (InstituicaoEntity) EventoDAO.find(InstituicaoEntity.class, getCookie().getIdInstituicao());
			EventoEntity.setInstituicaoEntity(instituicao);
			facesMessages.add("Evento: #{EventoEntity.descricao} inserido com sucesso!");
			return super.grava(EventoEntity);
		}
	}*/



	@Override
	public String edita(Object o) {
		this.EventoEntity = (EventoEntity)o;
		return PAGINA_ADD_EDIT;
	}
	
	

	@Override
	public String insere() {
		this.EventoEntity = null;
		return PAGINA_ADD_EDIT;
	}
	
/*	@Override
	public String remove(Object o) {
		this.EventoEntity = (EventoEntity) o;
		EventoEntity.setInstituicaoEntity(null);
		return super.remove(EventoEntity);
	}

*/

	@Override
	protected boolean antesGravar(Object o) {
		
		this.EventoEntity = (EventoEntity) o;
		EventoEntity evento = EventoDAO.validaEventoDuplicado(  getCookie().getIdInstituicao(),
																EventoEntity.getDescricao());
		if(EventoEntity.getDataInicio().compareTo(EventoEntity.getDataTermino()) == 1){
			facesMessages.add("Data inválida: Data de inicio maior que data final.");
			return false;
		}
		
		
		if(evento != null){
			facesMessages.add("Evento: #{EventoEntity.descricao} já existe!");
			return false;
		} else {
			InstituicaoEntity instituicao = (InstituicaoEntity) EventoDAO.find(InstituicaoEntity.class, getCookie().getIdInstituicao());
			EventoEntity.setInstituicaoEntity(instituicao);
			facesMessages.add("Evento: #{EventoEntity.descricao} inserido com sucesso!");
		}
		
		return true;
	}
	
	
	@Override
	protected boolean antesRemover(Object o) {
		this.EventoEntity = (EventoEntity) o;
		EventoEntity.setInstituicaoEntity(null);
		return true;
	}

	
	
	@Override
	protected boolean antesRemoverDetalhe(Collection<Object> collectionMestre,Object detalhe) {
		return true;
	}
	

}
