package br.com.bulktecnologia.controle.factory;

import java.util.List;

import javax.servlet.http.Cookie;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;

import br.com.bulktecnologia.modelo.dao.EventoDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.EventoEntity;

@Name("EventoFactory")
public class EventoFactory {

	@In
	private EventoDAO EventoDAO;
	
	@In
	private CookieSessaoUsuario cookie;
	
	private List eventos;
	
	@Create
	public void inicializa(){
		this.eventos = EventoDAO.recuperaEventos(cookie.getIdInstituicao());
	}

	public List getEventos() {
		return eventos;
	}

	public void setEventos(List eventos) {
		this.eventos = eventos;
	}
}
