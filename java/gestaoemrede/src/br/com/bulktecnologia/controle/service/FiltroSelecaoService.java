package br.com.bulktecnologia.controle.service;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;
@Name("FiltroSelecaoService")
@Scope(ScopeType.CONVERSATION)
public class FiltroSelecaoService extends GenericService implements	Serializable {

	private TurnoEntity turnoSelecionado;
	private EnsinoEntity ensinoSelecionado;
	private SerieEntity serieSelecionado;
	private DisciplinaEntity disciplinaSelecionado;
	
	
	

	public TurnoEntity getTurnoSelecionado() {
		return turnoSelecionado;
	}


	public void setTurnoSelecionado(TurnoEntity turnoSelecionado) {
		this.turnoSelecionado = turnoSelecionado;
	}


	public EnsinoEntity getEnsinoSelecionado() {
		return ensinoSelecionado;
	}


	public void setEnsinoSelecionado(EnsinoEntity ensinoSelecionado) {
		this.ensinoSelecionado = ensinoSelecionado;
	}


	public SerieEntity getSerieSelecionado() {
		return serieSelecionado;
	}


	public void setSerieSelecionado(SerieEntity serieSelecionado) {
		this.serieSelecionado = serieSelecionado;
	}


	public DisciplinaEntity getDisciplinaSelecionado() {
		return disciplinaSelecionado;
	}


	public void setDisciplinaSelecionado(DisciplinaEntity disciplinaSelecionado) {
		this.disciplinaSelecionado = disciplinaSelecionado;
	}

}
