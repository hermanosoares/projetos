package br.com.bulktecnologia.controle.audit;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.envers.RevisionListener;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;

public class MyRevisionListener implements RevisionListener{

	public void newRevision(Object o) {
		
		if ( PropertyUtils.isWriteable(o, "login") ){
			try {
				PropertyUtils.setProperty(o,"login",Identity.instance().getUsername());
				CookieSessaoUsuario cookie = (CookieSessaoUsuario) Component.getInstance("cookie",false);
				
				if (cookie!=null && PropertyUtils.isWriteable(o, "auditoriaacessoid")){
					PropertyUtils.setProperty(o,"auditoriaacessoid",cookie.getIdAuditoriaAcesso());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		
	}

}
