package br.com.bulktecnologia.controle.ui.validator;

import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.faces.FacesMessages;
@Name("ValidatorCampoDtNascimento")
@BypassInterceptors
@org.jboss.seam.annotations.faces.Validator
public class ValidatorCampoDtNascimento extends BaseValidator {

	
	public void validate(FacesContext arg0, UIComponent uicomp, Object value)throws ValidatorException {
		
			Date d = (Date)value;
			
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			
			String paramMsg = (String) uicomp.getAttributes().get("paramMsg");
			String paramFuturo = (String) uicomp.getAttributes().get("paramDisableFuture");

			//se nao informou param que disabilita futuro
			if (paramFuturo==null){
				if ( d.after(new Date()) ){
					
					if (paramMsg==null){
						FacesMessages.instance().add("Problema na Data: data de nascimento está no futuro ");
					}
					else{
						FacesMessages.instance().add("Problema na Data: "+paramMsg+" está no futuro ");
					}
					super.ok = false;
				}
			}
			
			int ano = c.get(Calendar.YEAR);
			
			if (ano < 1900 ){
				FacesMessages.instance().add("Problema na Data: Ano inválido, use o formato DD/MM/AAAA. exemplo: 20/01/2009 ");
				super.ok = false;
			}
		end();
	}
	
	
	

}
