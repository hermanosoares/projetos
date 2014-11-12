package br.com.bulktecnologia.controle.ui.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.faces.FacesMessages;

@Name("ValidatorCampoNome")
@BypassInterceptors
@org.jboss.seam.annotations.faces.Validator
public class ValidatorCampoNome extends BaseValidator {
	
	private String nomeDoCampo="";
	
	@Override
	public void validate(FacesContext context, UIComponent uicomp, Object value) throws ValidatorException {
		

		String nome = (String)value;
		
		if ( uicomp.getAttributes().get("nomedocampo")!=null ){
			this.nomeDoCampo = (String)uicomp.getAttributes().get("nomedocampo")+": ";
		}
		
		if ( !StringUtils.isAlphaSpace(nome) ){
			FacesMessages.instance().add(this.getNomeDoCampo()+" #{messages['campo.nome.caracteres.invalidos']}");
			ok = false;
		}
		
		//verifica se o nome � vazio ou nulo
		if ( StringUtils.isBlank( nome ) ){
			FacesMessages.instance().add(this.getNomeDoCampo()+" #{messages['campo.nome.nao.preenchido']}");
			ok = false;
		}
		else{
			nome = nome.trim();
			//verifica se o nome � menor que 4 caracteres.
			if (nome.length() < 4){
				FacesMessages.instance().add(this.getNomeDoCampo()+" #{messages['campo.nome.invalido']}");
				ok = false;
			}
			else{
				String[] splitNome = nome.split(" ");
				//existe sobrenome no nome ?
				if (splitNome.length < 2){
					FacesMessages.instance().add(this.getNomeDoCampo()+" #{messages['campo.sobrenome.invalido']}");
					ok = false;
				}
				else{
					//verifica se o tamanho do ultimo sobrenome � menor de 2 caracteres.
					String ultimoSobrenome = splitNome[splitNome.length-1];
					if ( ultimoSobrenome.trim().length()<2 ){
						FacesMessages.instance().add(this.getNomeDoCampo()+" #{messages['campo.sobrenome.invalido']}");
						ok = false;
					}
				}
			}
		}

		
		super.end();
		
	/*	if ( pessoaEntity.getDtnascimento()==null){
			FacesMessages.instance().add("#{messages['campo.dtnascimento.nao.preenchido']}");
			ok = false;
		}
		
		if ( StringUtils.isBlank(pai.getNome()) && StringUtils.isBlank(mae.getNome())){
			FacesMessages.instance().add("#{messages['campo.requerido.pai.ou.mae']}");
			ok = false;
		}
		
		if (ok){
			this.pessoasProvaveis = recuperaPessoasProvaveis();
			if (this.pessoasProvaveis.size()>0 ){
				if (this.pessoasProvaveis.size()==1){
					FacesMessages.instance().add("#{messages['rotulo.pessoaprovavel.encontrada']}");
				}
				else{
					FacesMessages.instance().add("#{messages['rotulo.pessoasprovaveis.encontrada']}");
				}
				ok = false;
			}
		}

		
		return ok;
		
		*/
		
	}

	public String getNomeDoCampo() {
		return nomeDoCampo;
	}

}
