package br.com.bulktecnologia.modelo.enums;

public enum TipoFormacaoTurma {
	
	Regular("REG"),      	//uma turma formada por apenas um unico turno,tipoensino,serie
	Por_Disciplina("DIS"),   	//uma turma formada por N disciplinas
	Multi_Seriada("MULT"),	//uma turma formada por N turno, N tipoEnsino, N serie.
	Multi_Seriada_E_Por_Disciplina("MULT-D"); //uma turma formada por N Disciplinas, N turno, N tipoensino, N serie.
	
	private String sigla;
	
	private TipoFormacaoTurma(String sigla){
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

}
