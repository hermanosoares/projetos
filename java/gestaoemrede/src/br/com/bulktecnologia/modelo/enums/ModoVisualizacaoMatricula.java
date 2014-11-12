package br.com.bulktecnologia.modelo.enums;

public enum ModoVisualizacaoMatricula {
	
	Matriculados("Alunos Matriculados"),
	Não_Matriculados("Alunos Não Matriculados");
	
	private ModoVisualizacaoMatricula(String label){
		this.label = label;
	}
	
	private String label;

	
	public String getLabel() {
		return label;
	}
	
	
}
