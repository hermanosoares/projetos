package br.com.bulktecnologia.modelo.enums;

public enum TipoAlocacao {
	Por_Turma("Por Turma"),
	Por_Disciplina("Por Disciplina"),
	Multi_Seriada("Multi-Seriada"), 
	Multi_Seriada_Por_Disciplina("Multi-Seriada por Disciplina");
	
	private String label;
	
	private TipoAlocacao(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	
}
