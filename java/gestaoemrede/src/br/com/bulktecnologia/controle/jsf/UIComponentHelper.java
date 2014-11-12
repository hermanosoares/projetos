package br.com.bulktecnologia.controle.jsf;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("UIComponentHelper")
@Scope(ScopeType.STATELESS)
public class UIComponentHelper {

	public String resolveClientId(String id) {
		try {
			FacesContext fctx = FacesContext.getCurrentInstance();
			UIComponent found = getComponentWithId(fctx.getViewRoot(), id);
			if (found!=null)
				return found.getClientId(fctx);                   
			else
				return null;
		} catch (Exception e) {
			return null;
		}     
	}

	private UIComponent getComponentWithId(UIComponent parent, String id) {
		for (Iterator<UIComponent> chs = parent.getFacetsAndChildren(); chs.hasNext();) {
			UIComponent ch = chs.next();
			if (ch.getId().equalsIgnoreCase(id))
				return ch;         
			else {
				UIComponent found = getComponentWithId(ch, id);
				if (found!=null)
					return found;
			}
		}
		return null;
	}

}
