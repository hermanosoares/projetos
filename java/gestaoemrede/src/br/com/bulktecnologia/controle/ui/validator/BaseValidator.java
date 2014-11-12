package br.com.bulktecnologia.controle.ui.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.faces.FacesMessages;

public abstract class BaseValidator implements Validator {
	
	
	protected boolean ok = true;
	
	
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)	throws ValidatorException {
	}

	/**
	 * conclui validacao
	 * @throws ValidatorException
	 */
	public void end()throws ValidatorException{
		if (!ok){
			throw new ValidatorException(new FacesMessage());
		}
	}
	
	
}
