package br.com.bulktecnologia.controle.service;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;

@Name("AlteraSenhaService")
@Scope(ScopeType.EVENT)
public class AlteraSenhaService {

	@In
	private FacesMessages facesMessages;
	
	@In
	private UsuarioDAO UsuarioDAO;
	
	@In
	@Out
	private CookieSessaoUsuario cookie;
	
	private String senha;
	private String novaSenha;
	private String confirmacaoNovaSenha;
	
	
	public String alteraSenha(){
		
		//valido senha atual com a senha da sessao.
		if ( cookie.getSenha().equals( senha ) ){
			
			//valido confirmacao de nova senha
			if ( novaSenha.equals(confirmacaoNovaSenha) ){
				
				if ( !senha.equals(novaSenha) ) {
					
					
					if ( novaSenha.length() >= ApplicationConstants.Parametros.MIN_LENGTH_SENHA){
						
						//atualizo a senha da sessao
						cookie.setSenha(novaSenha);
						//atualizo a senha no banco
						UsuarioDAO.alteraSenha(cookie.getIdUsuario(), novaSenha);
						
						facesMessages.add("Senha alterada com sucesso!");
						return "sucesso";
					}
					else{
						facesMessages.add("A Senha precisa ter o mínimo de "+ApplicationConstants.Parametros.MIN_LENGTH_SENHA+" caracteres.");
						return null;
					}
					
				}
				else{
					facesMessages.add("Digite uma senha diferente da atual.");
					return null;
				}
			}
			else{
				facesMessages.add("Sua nova senha digitada  não conferem! ");
			}
			
		}
		else{
			facesMessages.add("senha digitada não confere com sua atual senha!");
		}
		
		return null;	
	}


	public String getNovaSenha() {
		return novaSenha;
	}


	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}


	public String getConfirmacaoNovaSenha() {
		return confirmacaoNovaSenha;
	}


	public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
