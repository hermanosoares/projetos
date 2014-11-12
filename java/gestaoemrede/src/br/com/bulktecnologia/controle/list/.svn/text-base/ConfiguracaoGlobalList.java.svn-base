package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Unwrap;

import br.com.bulktecnologia.modelo.dao.ConfiguracaoDAO;
import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
//@Name("configuracaoGlobalList")
public class ConfiguracaoGlobalList implements Serializable {

	//@In
	private ConfiguracaoDAO ConfiguracaoDAO;
	
	private List<ConfiguracaoEntity> configuracoesGlobais;
	
	//@Create
	public void aoInicializar(){
		carregaConfiguracoesGlobais();
	}
	
	public void carregaConfiguracoesGlobais(){
		this.configuracoesGlobais = this.ConfiguracaoDAO.recuperaConfiguracoesGlobais();
	}

	//@Unwrap
	public List<ConfiguracaoEntity> getConfiguracoesGlobais() {return configuracoesGlobais;}

}
