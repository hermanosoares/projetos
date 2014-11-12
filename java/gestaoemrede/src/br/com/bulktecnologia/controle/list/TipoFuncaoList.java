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
import br.com.bulktecnologia.modelo.entidade.TipoFuncaoEntity;
@Name("TipoFuncaoList")
@Scope(ScopeType.CONVERSATION)
public class TipoFuncaoList implements Serializable {
	
	@In
	private InstituicaoDAO InstituicaoDAO;
	
	@In
	private CookieSessaoUsuario cookie;
	
	
	private List<TipoFuncaoEntity> tiposFuncao;

	public List<TipoFuncaoEntity> getTiposFuncao() {
		return tiposFuncao;
	}
	
	@Create
	public void aoInicializar(){
		recarregar();
	}

	
	@Observer("TipoFuncaoList.evento.recarregar")
	public void recarregar(){
		this.tiposFuncao = InstituicaoDAO.recuperaTiposFuncoesFuncionarios(cookie.getIdInstituicao());
	}
	
	
}
