package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.EventoDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.EventoEntity;

@Name("EventoList")
@Scope(ScopeType.CONVERSATION)
public class EventoList  implements Serializable{

	@In(required=false)
	private EventoDAO EventoDAO;
	
	@In
	private CookieSessaoUsuario cookie;
	
	private List<EventoEntity> eventos;
	
	@Create
	public void aoInicializar(){
		recarregar();
	}

	@Observer("EventoList.evento.recarregar")
	public void recarregar(){
		eventos = EventoDAO.recuperaEventos(cookie.getIdAuditoriaAcesso());
	}
	
	public List<EventoEntity> getEventos() {
		return eventos;
	}
}
