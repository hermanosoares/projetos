package br.com.bulktecnologia.controle.service;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.UsuarioInstituicaoEntity;

@Name("LoginService")
@Scope(ScopeType.CONVERSATION)
public class LoginService extends GenericService implements Serializable{

	private static final long serialVersionUID = 1788388485657680987L;
	
	@In
	private UsuarioDAO UsuarioDAO;

	
	private UsuarioInstituicaoEntity uiSelecionado;
	private String loginSelecionado;
	
	public String grava(){
		this.UsuarioDAO.flush();
		facesMessages.add("Login de acesso atualizado com sucesso.");
		return "sucesso";
	}
	
	public String edita(Object o) {
/*		UsuarioInstituicaoEntity ui = (UsuarioInstituicaoEntity)o;
		this.loginSelecionado = ui.getAuxLogin();
		this.uiSelecionado = (UsuarioInstituicaoEntity)this.UsuarioDAO.find(UsuarioInstituicaoEntity.class,ui.getId());*/
		return null;
	}
	

	public String getLoginSelecionado() {
		return loginSelecionado;
	}
	
}
