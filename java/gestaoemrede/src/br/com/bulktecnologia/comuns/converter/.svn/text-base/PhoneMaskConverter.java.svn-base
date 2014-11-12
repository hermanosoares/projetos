package br.com.bulktecnologia.comuns.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
@Name("PhoneMaskConverter")
@BypassInterceptors
@org.jboss.seam.annotations.faces.Converter
public class PhoneMaskConverter implements Converter{

	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String phone) {
		return decodeTelefone(phone);
	}

	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object phone) {
		return encodeTelefone((Long)phone);
	}


	
	/**
	 * remove mascara (xx)xxxx.xxxx
	 * e devolve a String 
	 * @param phoneWithMask
	 * @return
	 */
	private Long decodeTelefone(String phoneWithMask){
		if ( !StringUtils.isBlank(phoneWithMask) ){
			phoneWithMask =  phoneWithMask.replace("(", "");
			phoneWithMask =  phoneWithMask.replace(")", "");
			phoneWithMask =  phoneWithMask.replace(".", "");
			phoneWithMask =  phoneWithMask.trim();
			return new Long(phoneWithMask);
		}
		return null;
	}

	
	
	/**
	 * aplica mascara usando o padrão
	 * (xx)xxxx.xxxx
	 * 
	 * @param phone
	 * @return String
	 */
	public String encodeTelefone(Long phone){
		String phoneStr = new Long(phone).toString();
		return "("+phoneStr.substring(0, 2)+")"+phoneStr.substring(2,6)+"."+phoneStr.substring(6);
	}

	

}

