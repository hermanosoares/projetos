package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Unwrap;

import br.com.bulktecnologia.modelo.dao.InstituicaoDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
@Name("InstituicaoList")
@Scope(ScopeType.CONVERSATION)
@AutoCreate
public class InstituicaoList implements Serializable {

	private List<InstituicaoEntity> instituicoesEnsino;

	@In
	private InstituicaoDAO InstituicaoDAO;
	
	@Create
	public void aoInicializarServico(){
		recarregar();
	}


	public void recarregar(){
		
		 CookieSessaoUsuario cookie = (CookieSessaoUsuario) Component.getInstance("cookie");
		 
		 if ( cookie.getGlobal() ){
			 instituicoesEnsino = this.InstituicaoDAO.recuperaTodasInstituicoes();
		 }
		 else{
			 instituicoesEnsino = this.InstituicaoDAO.recuperaInstituicoesByUsuarioId(cookie.getIdUsuario()); 	 
		 }
		
	}

	@Unwrap
	public List<InstituicaoEntity> getInstituicoesEnsino() {
		return instituicoesEnsino;
	}


	
}
