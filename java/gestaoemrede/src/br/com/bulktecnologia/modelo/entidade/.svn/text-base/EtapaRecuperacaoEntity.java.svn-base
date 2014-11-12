package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.enums.TipoRecuperacao;
@Name("etapaRecuperacaoEntity")
@Entity
@Table(name="etapaRecuperacao")
public class EtapaRecuperacaoEntity implements Serializable {

	@Id
	@GeneratedValue(generator="EtapaRecuperacaoEntity_gen")
	@SequenceGenerator(name="EtapaRecuperacaoEntity_gen",sequenceName="EtapaRecuperacaoEntity_seq")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private SerieEntity serie;
	
	@Column(nullable=false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoRecuperacao tipoRecuperacao;

	@ManyToMany(mappedBy="etapasRecuperacao",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<EtapaEntity> etapas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EtapaEntity> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<EtapaEntity> etapas) {
		this.etapas = etapas;
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

	public TipoRecuperacao getTipoRecuperacao() {
		return tipoRecuperacao;
	}

	public void setTipoRecuperacao(TipoRecuperacao tipoRecuperacao) {
		this.tipoRecuperacao = tipoRecuperacao;
	}
	
	
	
}
