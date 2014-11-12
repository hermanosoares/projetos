package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.UsuarioInstituicaoEntity;

@Name("UsuariosInstituicaoList")
@Scope(ScopeType.CONVERSATION)
public class UsuariosInstituicaoList  implements Serializable{
	
	@In
	private UsuarioDAO UsuarioDAO;

	@In
	private CookieSessaoUsuario cookie;

	private List<UsuarioInstituicaoEntity> usuariosInstituicao;
	
	@Create
	public void aoInicializar(){
		recarregar();
	}

	@Observer("UsuariosInstituicaoList.evento.recarregar")
	public void recarregar(){
		//usuariosInstituicao = UsuarioDAO.recuperaUsuarioInstituicaoByIdInstituicao( cookie.getIdInstituicao() );
		throw new RuntimeException("nao implementado!");
	}

	public List<UsuarioInstituicaoEntity> getUsuariosInstituicao() {
		return usuariosInstituicao;
	}
	
	
	
}
