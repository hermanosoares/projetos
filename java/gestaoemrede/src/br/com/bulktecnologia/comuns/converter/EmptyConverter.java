package br.com.bulktecnologia.comuns.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
@Name("EmptyConverter")
@BypassInterceptors
@org.jboss.seam.annotations.faces.Converter
public class EmptyConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value==null || "".equals(value) || "".equals(value.trim())){
			return null;
		}
		return value;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		return (String)o;
	}


}
