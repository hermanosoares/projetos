package br.com.bulktecnologia.controle.service;

import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.comuns.util.SendMail;
import br.com.bulktecnologia.controle.exception.EmailDuplicadoException;
import br.com.bulktecnologia.controle.seguranca.PasswordGen;
import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.UsuarioEntity;
@Name("EsqueciSenhaService")
public class EsqueciSenhaService {

	@In(required=false)
	private FacesMessages facesMessages;
	
	@In
	private UsuarioEntity usuarioEntity;
	
	@In
	private SendMail sendMail;
	
	@In
	private UsuarioDAO UsuarioDAO;
	
	@In(required=false)
	private HttpServletRequest httpServletRequest;	
	
	
	public String redefinirSenha(){

		try {
			UsuarioEntity u = UsuarioDAO.recuperaUsuarioByEmail(usuarioEntity.getEmail().trim());

			if (u==null){
				facesMessages.add("login e/ou email inválido(s), verifique os dados digitados.");
				facesMessages.add("dúvidas contate nossa central de suporte.");
				return null;
			}
			else{
				Long redefinicoes = UsuarioDAO.countEsqueciSenha(usuarioEntity.getLogin());

				if ( redefinicoes < ApplicationConstants.Parametros.MAX_REDEFINICOES_ESQUECI_SENHA_POR_DIA ){
					String newPassword = PasswordGen.generateRandomPassword();
					UsuarioDAO.insereNovoEsqueciSenha(u);
					u.setSenha(newPassword);
					UsuarioDAO.merge(u);

					sendMail.enviaMsgRedefinicaoSenha( u.getNome(),
													   u.getEmail(),
													   null,
													   u.getLogin(),
													   newPassword,
													   httpServletRequest.getRemoteAddr() );
					facesMessages.add(" Uma nova senha foi enviada para seu(s) email(s) cadastrado no gestão em rede ");
					facesMessages.add(" verifique na sua caixa de entrada ou caixa de spam do(s) seu(s) email(s).");
					facesMessages.add(" dúvidas contate nossa central de suporte.");
					return "sucesso";
				}
				else{
					facesMessages.add("o máximo de redefinições de senha por dia foi atingido para seu login!");
					facesMessages.add("Redefinição nesta data somente via contato na central de suporte.");
					return null;
				}
			}
		} catch (EmailDuplicadoException e) {
			facesMessages.add(e.getMessage());
			return null;
		}

	}

	
}
