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
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotNull;

import br.com.bulktecnologia.modelo.enums.TipoSerie;
@Entity
@Table(name="serie")
@NamedQueries(value={})
public class SerieEntity implements Serializable {
	
	public SerieEntity(){
	}
	
	public SerieEntity(TipoSerie tipoSerie){
		setTipoSerie(tipoSerie);
	}
	
	@Id
	@GeneratedValue(generator="tiposerie_gen")
	@SequenceGenerator(name="tiposerie_gen",sequenceName="serie_seq")
	private Long id;
	
	@Column(nullable=false)
	@NotNull(message="Nome da série Obrigatória")
	private String nome;
	
	@ManyToMany(mappedBy="series",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<TurmaEntity> turmas;
	
	@OrderBy("tipoDisciplina asc")
	@OneToMany(mappedBy="serie",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<DisciplinaEntity> disciplinas;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false,columnDefinition="INTEGER")
	private TipoSerie tipoSerie;
	
	@OrderBy("id asc")
	@OneToMany(mappedBy="serie",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<EtapaEntity> etapas;

	@OneToMany(mappedBy="serie",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<EtapaRecuperacaoEntity> etapasRecuperacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ensino_id",nullable=false)
	private EnsinoEntity ensino;
	
	
	@Column(nullable=false)
	@NotNull(message="Número de Dependência obrigatória")
	@Min(value=0,message="Mínimo de 0 Dependência.")
	@Max(value=20,message="Máximo de 20 Dependências !")
	private Long numDepenciaAceitavel = 0L;

	@Column(nullable=false)
	private Boolean reprovaPorAvaliacao = Boolean.TRUE;
	
	@Column(nullable=false)
	private Boolean reprovaPorFrequencia = Boolean.TRUE;
	
	
	@Column(nullable=false)
	@NotNull(message="Nota máxima obrigatória")
	@Min(value=1,message="nota máximo mínimo de 1 ponto!.")
	@Max(value=100,message="nota máxima 100 pontos!")
	private Double notaMaxima = 100.00;
	
	@Column
	private Double percentNotaAprov = 60D;
	
	@Column(nullable=false)
	private Double percentFreqAprov = 75D;
	
	@Transient
	private Boolean selecionado = Boolean.FALSE;
	
	
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

	public List<TurmaEntity> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<TurmaEntity> turmas) {
		this.turmas = turmas;
	}

	public List<DisciplinaEntity> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaEntity> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public EnsinoEntity getEnsino() {
		return ensino;
	}

	public void setEnsino(EnsinoEntity ensino) {
		this.ensino = ensino;
	}

	public TipoSerie getTipoSerie() {
		return tipoSerie;
	}

	public void setTipoSerie(TipoSerie tipoSerie) {
		this.tipoSerie = tipoSerie;
	}

	public Long getNumDepenciaAceitavel() {
		return numDepenciaAceitavel;
	}

	public void setNumDepenciaAceitavel(Long numDepenciaAceitavel) {
		this.numDepenciaAceitavel = numDepenciaAceitavel;
	}

	public Boolean getReprovaPorAvaliacao() {
		return reprovaPorAvaliacao;
	}

	public void setReprovaPorAvaliacao(Boolean reprovaPorAvaliacao) {
		this.reprovaPorAvaliacao = reprovaPorAvaliacao;
	}

	public Boolean getReprovaPorFrequencia() {
		return reprovaPorFrequencia;
	}

	public void setReprovaPorFrequencia(Boolean reprovaPorFrequencia) {
		this.reprovaPorFrequencia = reprovaPorFrequencia;
	}

	public Double getNotaMaxima() {
		return notaMaxima;
	}

	public void setNotaMaxima(Double notaMaxima) {
		this.notaMaxima = notaMaxima;
	}

	public Double getPercentNotaAprov() {
		return percentNotaAprov;
	}

	public void setPercentNotaAprov(Double percentNotaAprov) {
		this.percentNotaAprov = percentNotaAprov;
	}

	public Double getPercentFreqAprov() {
		return percentFreqAprov;
	}

	public void setPercentFreqAprov(Double percentFreqAprov) {
		this.percentFreqAprov = percentFreqAprov;
	}

	public List<EtapaEntity> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<EtapaEntity> etapas) {
		this.etapas = etapas;
	}

	public List<EtapaRecuperacaoEntity> getEtapasRecuperacao() {
		return etapasRecuperacao;
	}

	public void setEtapasRecuperacao(List<EtapaRecuperacaoEntity> etapasRecuperacao) {
		this.etapasRecuperacao = etapasRecuperacao;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	
	
}
