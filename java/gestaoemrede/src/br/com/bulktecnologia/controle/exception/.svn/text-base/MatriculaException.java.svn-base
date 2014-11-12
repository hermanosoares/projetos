package br.com.bulktecnologia.controle.exception;

import java.util.List;

import br.com.bulktecnologia.modelo.entidade.MatriculaEntity;

public class MatriculaException extends BusinessException {
	
	private List<MatriculaEntity> matriculasAtivas;
	
	public MatriculaException(String exceptionCause, Throwable e) {
		super(exceptionCause, e);
	}

	public MatriculaException(String exceptionCause) {
		super(exceptionCause);
	}
	
	public MatriculaException(String exceptionCause,List<MatriculaEntity> matriculasAtivas) {
		super(exceptionCause);
		this.matriculasAtivas = matriculasAtivas;
	}

	public MatriculaException() {
	}

	public MatriculaException(List<MatriculaEntity> matriculasAtivas ) {
		this.matriculasAtivas = matriculasAtivas;
	}
	
	
	
	public List<MatriculaEntity> getMatriculasAtivas() {
		return matriculasAtivas;
	}
	
	
	
	
}
