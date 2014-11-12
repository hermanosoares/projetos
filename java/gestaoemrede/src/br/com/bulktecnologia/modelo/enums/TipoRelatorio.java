package br.com.bulktecnologia.modelo.enums;

public enum TipoRelatorio {
	
	Relatorio_Auditoria_Acesso("Relatorio Auditoria de Acessos");
	
	private String label;
	
	private TipoRelatorio(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
}
