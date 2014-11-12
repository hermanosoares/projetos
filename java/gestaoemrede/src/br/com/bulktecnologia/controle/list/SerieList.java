package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;

@Name("SerieList")
@Scope(ScopeType.CONVERSATION)
public class SerieList extends GenericList implements Serializable{

	private List<SerieEntity> series;
	

	public List<SerieEntity> getSeries(EnsinoEntity ensinoSelecionado){
		this.series = ensinoSelecionado.getSeries();
		return this.series;
	}
	

}
