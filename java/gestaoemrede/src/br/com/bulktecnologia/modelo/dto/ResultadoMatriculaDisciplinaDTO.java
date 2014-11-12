package br.com.bulktecnologia.modelo.dto;

public class ResultadoMatriculaDisciplinaDTO {
	
	private Long idDisciplina;
	private Long idMatricula;
	
	public ResultadoMatriculaDisciplinaDTO(Long idDisciplina,Long idMatricula) {
		this.idDisciplina = idDisciplina;
		this.idMatricula = idMatricula;
	}
	

	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public Long getIdMatricula() {
		return idMatricula;
	}


}
