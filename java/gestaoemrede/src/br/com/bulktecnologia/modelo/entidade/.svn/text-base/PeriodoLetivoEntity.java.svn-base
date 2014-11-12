package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.annotation.CampoObrigatorio;

@Entity
@Table(name="periodoletivo")
@Name("periodoLetivoEntity")
public class PeriodoLetivoEntity implements Serializable {

	@Id
	@SequenceGenerator(name="periodoletivo_gen",sequenceName="periodoletivo_seq")
	@GeneratedValue(generator="periodoletivo_gen")
	private Long id;
	
	@CampoObrigatorio(nomeCampo="Nome")
	@Column(nullable=false)
	private String nome;
	
	@CampoObrigatorio(nomeCampo="Data Início Periodo Letivo")
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dtInicio;
	
	
	@CampoObrigatorio(nomeCampo="Data Término Periodo Letivo")
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dtTermino;

	@CampoObrigatorio(nomeCampo="Data Fechamento Diário")
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dtFechamentoDiario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ensino_id",nullable=false)
	private EnsinoEntity ensino;
	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtTermino() {
		return dtTermino;
	}

	public void setDtTermino(Date dtTermino) {
		this.dtTermino = dtTermino;
	}

	public EnsinoEntity getEnsino() {
		return ensino;
	}

	public void setEnsino(EnsinoEntity ensino) {
		this.ensino = ensino;
	}

	public Date getDtFechamentoDiario() {
		return dtFechamentoDiario;
	}

	public void setDtFechamentoDiario(Date dtFechamentoDiario) {
		this.dtFechamentoDiario = dtFechamentoDiario;
	}
	
	
}
