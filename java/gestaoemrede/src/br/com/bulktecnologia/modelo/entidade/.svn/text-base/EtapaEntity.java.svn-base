package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name="etapa")
@Name("etapaEntity")
public class EtapaEntity implements Serializable{

	
	@Id
	@SequenceGenerator(name="etapa_gen",sequenceName="etapa_seq")
	@GeneratedValue(generator="etapa_gen")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private SerieEntity serie;

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable
	private List<EtapaRecuperacaoEntity> etapasRecuperacao;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String nomeReduzido;
	
	@Column(nullable=false)
	private Double notaMaximaEtapa;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public SerieEntity getSerie() {
		return serie;
	}

	public void setSerie(SerieEntity serie) {
		this.serie = serie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
	public String getNomeReduzido() {
		return nomeReduzido;
	}

	public void setNomeReduzido(String nomeReduzido) {
		this.nomeReduzido = nomeReduzido;
	}

	
	
	public Double getNotaMaximaEtapa() {
		return notaMaximaEtapa;
	}

	public void setNotaMaximaEtapa(Double notaMaximaEtapa) {
		this.notaMaximaEtapa = notaMaximaEtapa;
	}
	
	public List<EtapaRecuperacaoEntity> getEtapasRecuperacao() {
		return etapasRecuperacao;
	}

	public void setEtapasRecuperacao(List<EtapaRecuperacaoEntity> etapasRecuperacao) {
		this.etapasRecuperacao = etapasRecuperacao;
	}


	
	
	
}
