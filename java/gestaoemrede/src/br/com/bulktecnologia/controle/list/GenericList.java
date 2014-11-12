package br.com.bulktecnologia.controle.list;

import org.jboss.seam.Component;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;

public abstract class GenericList{
	
	protected FacesMessages facesMessages;
	protected CookieSessaoUsuario cookie;

	public GenericList(){
		this.cookie = (CookieSessaoUsuario) Component.getInstance("cookie");
		this.facesMessages = FacesMessages.instance();		
	}

}
