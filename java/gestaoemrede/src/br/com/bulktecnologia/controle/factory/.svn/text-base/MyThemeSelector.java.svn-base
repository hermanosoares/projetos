package br.com.bulktecnologia.controle.factory;

import java.io.Serializable;

import org.jboss.seam.Component;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;

@org.jboss.seam.annotations.Scope(value=org.jboss.seam.ScopeType.SESSION)
@org.jboss.seam.annotations.Name(value="org.jboss.seam.theme.myThemeSelector")
@org.jboss.seam.annotations.intercept.BypassInterceptors
@org.jboss.seam.annotations.Install(precedence=(int) 0,classDependencies={"javax.faces.context.FacesContext"})
public class MyThemeSelector implements Serializable{
	
	
	public String getTema() {
		CookieSessaoUsuario cookie =(CookieSessaoUsuario) Component.getInstance("cookie",false);
		if (cookie==null){
			return ApplicationConstants.Parametros.SKIN_PADRAO.name();
		}
		else{
			return cookie.getSkin();
		}
	}
	

	
	
}
