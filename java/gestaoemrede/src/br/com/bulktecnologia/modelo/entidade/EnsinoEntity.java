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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.enums.TipoEnsino;
@Entity
@Table(name="ensino")
@Name("ensinoEntity")
@NamedQueries(value={
			@NamedQuery(name="EnsinoEntity.recuperaTiposEnsinoByTurno",query="select e from EnsinoEntity e where e.turno.id = :idTurno")
					})
public class EnsinoEntity implements Serializable {
	public EnsinoEntity(){
	}
	
	public EnsinoEntity(TipoEnsino tipoEnsino){
		setTipoEnsino(tipoEnsino);
	}
	
	@Id
	@GeneratedValue(generator="tipoensino_gen")
	@SequenceGenerator(name="tipoensino_gen",sequenceName="ensino_seq")
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)	
	private TipoEnsino tipoEnsino;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="turno_id",nullable=false)
	private TurnoEntity turno;
	
	@OrderBy("tipoSerie asc")
	@OneToMany(mappedBy="ensino",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<SerieEntity> series;

	@OneToMany(mappedBy="ensino",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<PeriodoLetivoEntity> periodosLetivos;

	
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

	public TurnoEntity getTurno() {
		return turno;
	}

	public void setTurno(TurnoEntity turno) {
		this.turno = turno;
	}

	public List<SerieEntity> getSeries() {
		return series;
	}

	public void setSeries(List<SerieEntity> series) {
		this.series = series;
	}

	public TipoEnsino getTipoEnsino() {
		return tipoEnsino;
	}

	public void setTipoEnsino(TipoEnsino tipoEnsino) {
		this.tipoEnsino = tipoEnsino;
	}

	public List<PeriodoLetivoEntity> getPeriodosLetivos() {
		return periodosLetivos;
	}

	public void setPeriodosLetivos(List<PeriodoLetivoEntity> periodosLetivos) {
		this.periodosLetivos = periodosLetivos;
	}
	
	
	
}
