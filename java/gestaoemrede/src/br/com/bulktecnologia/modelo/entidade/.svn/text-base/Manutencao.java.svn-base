package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotNull;
@Entity
@NamedQueries(value={
			@NamedQuery(name="Manutencao.recuperaManutencaoProgramada",query="from Manutencao m where CURRENT_DATE between m.inicio and m.fim")
			})
public class Manutencao implements Serializable {

	@Id
	@GeneratedValue(generator="manutencao_gen")
	@SequenceGenerator(name="manutencao_gen",sequenceName="manutencao_seq")
	private Long id;

	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date inicio;

	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fim;

	@NotNull(message="mensagem de manutenção programada obrigatória! ")
	@Column(nullable=false)
	private String mensagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
