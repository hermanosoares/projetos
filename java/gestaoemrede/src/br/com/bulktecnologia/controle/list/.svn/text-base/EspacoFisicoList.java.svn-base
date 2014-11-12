package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;

import br.com.bulktecnologia.modelo.dao.EspacoFisicoDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.EspacoFisicoEntity;

@Name("EspacoFisicoList")
public class EspacoFisicoList  implements Serializable{
	
	
	@In(required=false)
	private EspacoFisicoDAO EspacoFisicoDAO;
	
	@In
	private CookieSessaoUsuario cookie;
	
	private List<EspacoFisicoEntity> espacos;
	
	
	@Create
	public void aoInicializar(){
		recarregar();
	}
	
	@Observer("EspacoFisicoList.evento.recarregar")
	public void recarregar(){
		espacos = EspacoFisicoDAO.recuperaEspacosFisico(cookie.getIdInstituicao());
	}

	public List<EspacoFisicoEntity> getEspacos() {
		return espacos;
	}


	


	

	

	
	
}
