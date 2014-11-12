package br.com.bulktecnologia.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class StatusDiarioClasse{

	@Column(columnDefinition="boolean")
	private Boolean diarioAberto = Boolean.TRUE;

	public Boolean getDiarioAberto() {
		return diarioAberto;
	}

	public void setDiarioAberto(Boolean diarioAberto) {
		this.diarioAberto = diarioAberto;
	}
	
	public String getStatusDiario(){
		if (this.diarioAberto){
			return "Aberto";
		}
		else{
			return "Fechado";
		}
	}
	
}
