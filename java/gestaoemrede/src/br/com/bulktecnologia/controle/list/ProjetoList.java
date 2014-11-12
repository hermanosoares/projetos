package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.InstituicaoDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.ProjetoEntity;

@Name("ProjetoList")
@Scope(ScopeType.CONVERSATION)
public class ProjetoList implements Serializable{

	@In
	private CookieSessaoUsuario cookie;
	
	@In
	private InstituicaoDAO InstituicaoDAO;
	
	private List<ProjetoEntity> projetos;
	
	@Create
	public void aoInicializar(){
		this.recarregar();
	}

	@Observer("ProjetoList.evento.recarregar")
	public void recarregar() {
		projetos = InstituicaoDAO.recuperaProjetosByInstituicao(cookie.getIdInstituicao());
	}

	public List<ProjetoEntity> getProjetos() {
		return projetos;
	}
	
}
