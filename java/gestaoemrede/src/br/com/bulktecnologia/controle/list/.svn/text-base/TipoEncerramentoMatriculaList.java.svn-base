package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.ConfiguracaoDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.TipoEncerramentoMatriculaEntity;

@Name("TipoEncerramentoMatriculaList")
@Scope(ScopeType.CONVERSATION)
public class TipoEncerramentoMatriculaList  implements Serializable{

	@In
	private ConfiguracaoDAO ConfiguracaoDAO;
	
	@In
	private CookieSessaoUsuario cookie;
	
	private List<TipoEncerramentoMatriculaEntity> tiposEncerramentoMatricula;
	
	@Create
	public void aoIniciailizar(){
		this.recarregar();
	}

	@Observer("TipoEncerramentoMatriculaList.evento.recarregar")
	public void recarregar() {
		this.tiposEncerramentoMatricula = ConfiguracaoDAO.recuperaTiposEncerramentoMatricula(cookie.getIdInstituicao());
	}

	public List<TipoEncerramentoMatriculaEntity> getTiposEncerramentoMatricula() {
		return tiposEncerramentoMatricula;
	}
	
	
	
}
