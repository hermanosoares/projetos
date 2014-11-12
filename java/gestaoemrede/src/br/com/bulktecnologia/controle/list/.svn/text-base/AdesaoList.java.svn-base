package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.AdesaoDAO;
import br.com.bulktecnologia.modelo.entidade.AdesaoEntity;
@Name("AdesaoList")
@Scope(ScopeType.CONVERSATION)
public class AdesaoList implements Serializable {
	
	@In
	private AdesaoDAO AdesaoDAO;
	
	private List<AdesaoEntity> adesoes;



	@Create
	public void aoInicializar(){
		recarregar();
	}

	@Observer("AdesaoList.evento.recarregar")
	public void recarregar(){
		adesoes = AdesaoDAO.recuperaTodasAdesoes();
	}

	public List<AdesaoEntity> getAdesoes() {
		return adesoes;
	}

	public void setAdesoes(List<AdesaoEntity> adesoes) {
		this.adesoes = adesoes;
	}
	
}
