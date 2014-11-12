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
import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;

@Name("ConfiguracaoList")
@Scope(ScopeType.CONVERSATION)
public class ConfiguracaoList  implements Serializable{
	
	@In
	private ConfiguracaoDAO ConfiguracaoDAO;
	
	
	@In
	private CookieSessaoUsuario cookie;
	
	private List<ConfiguracaoEntity> configuracoes;

	
	@Create
	public void aoInicializar(){
		recarregar();
	}

	@Observer("ConfiguracaoList.evento.recarregar")
	public void recarregar(){
		if (cookie.getConfigEspecificaInstituicao()==null){
			this.configuracoes =   ConfiguracaoDAO.recuperaConfiguracoesGlobais();
		}
		else{
			this.configuracoes =   ConfiguracaoDAO.recuperaConfiguracoesByInstituicao(cookie.getIdInstituicao());
		}
		
	}
	
	public List<ConfiguracaoEntity> getConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(List<ConfiguracaoEntity> configuracoes) {
		this.configuracoes = configuracoes;
	}
	
	
	
}
