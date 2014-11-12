package br.com.bulktecnologia.controle.service;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;

@Name("SeletorHelper")
@Scope(ScopeType.STATELESS)
public class SeletorHelper {

	@In
	@Out
	private CookieSessaoUsuario cookie;
	
	public void selectAluno(Long id,String viewIdAlvo){
		this.cookie.setRegistroSelecionado(id, viewIdAlvo,AlunoEntity.class);
	}
	
}
