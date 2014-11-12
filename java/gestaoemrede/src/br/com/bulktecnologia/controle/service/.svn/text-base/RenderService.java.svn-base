package br.com.bulktecnologia.controle.service;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Identity;

import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.enums.Perfil;

@Name("renderService")
@Scope(ScopeType.STATELESS)
public class RenderService {
	
	@In
	private CookieSessaoUsuario cookie;
	
	private Boolean possuiPerfil(Perfil perfil){
		if (cookie.getGlobal()){
			return Boolean.TRUE;
		}
		return Identity.instance().hasRole(perfil.toString());		
	}


	@Factory
	public Boolean getPermiteExibirMenuAcao(){
		return possuiPerfil(Perfil.ITEM_MENU_ACAO);
	}

	@Factory
	public Boolean getPermiteExibirCadastroAluno(){
		return possuiPerfil(Perfil.ITEM_MENU_CADASTRO_ALUNO);
	}
	
	
	@Factory
	public Boolean getPermiteExibirCadastroFuncionario(){
		return possuiPerfil(Perfil.ITEM_MENU_CADASTRO_FUNCIONARIO);
	}
	
	@Factory
	public Boolean getPermiteExibirPesquisaAlteracao(){
		return possuiPerfil(Perfil.ITEM_MENU_PESQUISA_ALTERACAO);
	}
	
	@Factory
	public Boolean getPermiteExibirMatricula(){
		return possuiPerfil(Perfil.ITEM_MENU_MATRICULA);
	}
	
	@Factory
	public Boolean getPermiteExibirAlocacao(){
		return possuiPerfil(Perfil.ITEM_MENU_ALOCACAO);
	}
	
	
	@Factory
	public Boolean getPermiteExibirGerenciarLayout(){
		return possuiPerfil(Perfil.ITEM_MENU_GERENCIAR_LAYOUT);
	}
	
	@Factory
	public Boolean getPermiteExibirGerenciarSenha(){
		return possuiPerfil(Perfil.ITEM_MENU_GERENCIAR_SENHA);
	}

	@Factory
	public Boolean getPermiteExibirConfiguracao(){
		return possuiPerfil(Perfil.ITEM_MENU_CONFIGURACAO);
	}
	
	@Factory
	public Boolean getPermiteExibirPainelAdmExterno(){
		return possuiPerfil(Perfil.ITEM_PAINEL_ADM_EXTERNO);
	}
	
	@Factory
	public Boolean getPermiteExibirRelatorio(){
		return possuiPerfil(Perfil.ITEM_MENU_RELATORIO);
	}

	@Factory
	public Boolean getPermiteExibirNotaEFalta(){
		return possuiPerfil(Perfil.ITEM_MENU_NOTA_E_FALTA);
	}

}
