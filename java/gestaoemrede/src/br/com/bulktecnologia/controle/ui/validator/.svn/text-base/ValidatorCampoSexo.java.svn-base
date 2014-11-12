package br.com.bulktecnologia.controle.ui.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.faces.FacesMessages;
@Name("ValidatorCampoSexo")
@BypassInterceptors
@org.jboss.seam.annotations.faces.Validator
public class ValidatorCampoSexo extends BaseValidator {
	
	
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		
		if (value==null){
			FacesMessages.instance().add("#{messages['campo.sexo.nao.preenchido']}");
			end();
		}
	}
	

}
