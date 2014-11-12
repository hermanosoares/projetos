package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioInstituicaoEntity;
@Name("usuarioList")
@Scope(ScopeType.CONVERSATION)
public class UsuarioList implements Serializable {

	@In
	private UsuarioDAO UsuarioDAO;
	
	private InstituicaoEntity instituicaoSelecionada;

	private List<UsuarioInstituicaoEntity> usuariosInstituicao;
	
	public void carregaUsuarios(){
		if (this.instituicaoSelecionada!=null){
			this.usuariosInstituicao = this.UsuarioDAO.recuperaTodosUsuarioDaInstituicao(this.instituicaoSelecionada.getId());
		}
		else{
			this.usuariosInstituicao = null;
		}
	}
	
	
	
	
	public InstituicaoEntity getInstituicaoSelecionada() { return instituicaoSelecionada;}
	public void setInstituicaoSelecionada(InstituicaoEntity instituicaoSelecionada){this.instituicaoSelecionada = instituicaoSelecionada;}
	public List<UsuarioInstituicaoEntity> getUsuariosInstituicao() {return usuariosInstituicao;}
	public void setUsuariosInstituicao(List<UsuarioInstituicaoEntity> usuariosInstituicao){this.usuariosInstituicao = usuariosInstituicao;}

}
