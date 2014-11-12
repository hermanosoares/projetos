package br.com.bulktecnologia.comuns.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.util.Conversions.DoubleConverter;
@Name("NotaConverter")
@BypassInterceptors
@org.jboss.seam.annotations.faces.Converter
public class NotaConverter extends DoubleConverter implements javax.faces.convert.Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String s) {
		if ( StringUtils.isBlank(s) ){
			return null;
		}
		s = s.replaceAll(",",".");
		return new Double(s);
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		return o.toString();
	}

}
