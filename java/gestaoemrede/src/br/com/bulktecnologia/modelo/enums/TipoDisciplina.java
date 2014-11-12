package br.com.bulktecnologia.modelo.enums;

public enum TipoDisciplina {
	
	LINGUA_PORTUGUESA("Língua Portuguesa","PORT"),
	MATEMATICA("Matemática","MAT"),
	HISTORIA("História","HIST"),
	GEOGRAFIA("Geografia","GEO"),
	CIENCIAS("Ciências","CIE"),
	FISICA("Física","FIS"),
	QUIMICA("Química","QUI"),
	BIOLOGIA("Biologia","BIO"),
	FILOSOFIA("Filosofia","FIL"),
	SOCIOLOGIA("Sociologia","SOC"),
	EDUCACAO_ARTISTICA("Educação Artística","E.ART"),
	EDUCACAO_FISICA("Educação Física","E.FIS"),
	EDUCACAO_RELIGIOSA("Educação Religiosa","E.REG"),
	LINGUA_ESTRANGEIRA("Língua Estrangeira","L.EST"),
	DISCIPLINA_ISOLADA("Disciplina Isolada","ISOL"),
	TODAS("Todas","TODAS");

	private String label;
	private String sigla;
	
	private TipoDisciplina(String label,String sigla){
		this.label = label;
		this.sigla = sigla;
	}


	public String getLabel() {
		return label;
	}


	public String getSigla() {
		return sigla;
	}
	
	
}
