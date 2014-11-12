package br.com.bulktecnologia.modelo.entidade;

public enum TipoOrdenacao {
	
	Alfabetico("Alfabético"),
	
	Masc_Primeiro("Masculino Primeiro"),
	
	Fem_Primeiro("Feminino Primeiro"),
	
	Sequencial("Sequencial");
	
	private String label;
	
	private TipoOrdenacao(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
