package br.com.bulktecnologia.comuns.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import br.com.bulktecnologia.modelo.enums.TipoSerie;
@Name("TipoSerieConverter")
@BypassInterceptors
@org.jboss.seam.annotations.faces.Converter
public class TipoSerieConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		int posicao = Integer.parseInt(value);
		TipoSerie[] tipos = TipoSerie.values();
		return tipos[posicao];
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		TipoSerie[] tipos = TipoSerie.values();
		for (int i=0;i<tipos.length;i++){
			if (tipos[i].equals(o)){
				return ""+i;
			}
		}
		return null;
	}

}
