package br.com.bulktecnologia.controle.service;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.security.Identity;

import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;

@Name("SegurancaService")
public class SegurancaService {

	
	@Observer("evento.seguranca.verificaAcessoAdministrativo")
	public void verificaAdministrativo(){
/*		if (  !(Identity.instance().hasRole(PapelUsuarioTecnico.Administrador.name())) &&
			  !(Identity.instance().hasRole(PapelUsuarioTecnico.App.name()))	){ 
			  Events.instance().raiseEvent("evento.seguranca.logoutAndRedirectLogin");
		}*/
		
		throw new UnsupportedOperationException("nao impl!");
	}
	
	
	
	@Observer("evento.seguranca.verificaMultiplasInstituicoes")
	public void verificaMultiplasInstituicoes(){
		CookieSessaoUsuario cookie =  (CookieSessaoUsuario) Component.getInstance("cookie",false);
		
		if (cookie==null || (cookie.getIdInstituicao()==null && !cookie.getGlobal()) ){
			Events.instance().raiseEvent("evento.seguranca.logoutAndRedirectLogin");
		}
	}

	

	@Observer("evento.seguranca.logoutAndRedirectLogin")
	public void logoutAndRedirectLogin(){
		Identity.instance().logout();
		Redirect.instance().setViewId("/home.xhtml");
		Redirect.instance().execute();
	}



/*

	
	@Factory
	public Boolean getPapelApp() {
		return Identity.instance().hasRole(PapelUsuarioTecnico.App.name());
	}
	
	
	@Factory
	public Boolean getPapelAluno() {
		return Identity.instance().hasRole(PapelUsuario.Aluno.name());
	}

	
	@Factory
	public Boolean getPapelAdministrador() {
		return Identity.instance().hasRole(PapelUsuarioTecnico.Administrador.name());
	}

	
	//injetado via Component.getInstance("isPapelTecnico")
	@Factory
	public Boolean getIsPapelTecnico() {
		for (PapelUsuarioTecnico put: PapelUsuarioTecnico.values()){
			if ( Identity.instance().hasRole( put.name() ) ) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}	
	

	@Factory
	public Boolean getPapelDiretor() {
		return Identity.instance().hasRole(PapelUsuario.Diretor.name());
	}



	@Factory
	public Boolean getPapelSecretária() {
		return Identity.instance().hasRole(PapelUsuario.Secretário.name());
	}



	@Factory
	public Boolean getPapelAux_Educação() {
		return Identity.instance().hasRole(PapelUsuario.Aux_Educação.name());
	}



	@Factory
	public Boolean getPapelProfessor() {
		return Identity.instance().hasRole(PapelUsuario.Professor.name());
	}



	@Factory
	public Boolean getPapelSuporte() {
		return Identity.instance().hasRole(PapelUsuarioTecnico.Suporte.name());
	}

	@Factory
	public Boolean getPermiteGerenciarAcesso(){
		return getPapelAdministrador() || getPapelSuporte() || getPapelSecretária() || getPapelDiretor();
	}
*/
	
	
}
