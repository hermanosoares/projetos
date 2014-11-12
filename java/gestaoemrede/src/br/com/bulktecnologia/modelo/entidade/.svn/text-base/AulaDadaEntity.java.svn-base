package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.jboss.seam.annotations.Name;
@Name("aulaDadaEntity")
@Entity
@Table(name="auladada")
public class AulaDadaEntity extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator="auladada_gen")
	@SequenceGenerator(name="auladada_gen",sequenceName="auladada_seq")
	private Long id;
	

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn(nullable=false)
	private EtapaEntity etapa;

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn(nullable=false)
	private DisciplinaEntity disciplina;
	
	
	@Min(value=0,message="Mínimo de 0 aulas dadas")
	@Column
	private Long aulasdadas;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public EtapaEntity getEtapa() {
		return etapa;
	}


	public void setEtapa(EtapaEntity etapa) {
		this.etapa = etapa;
	}


	public DisciplinaEntity getDisciplina() {
		return disciplina;
	}


	public void setDisciplina(DisciplinaEntity disciplina) {
		this.disciplina = disciplina;
	}


	public Long getAulasdadas() {
		return aulasdadas;
	}


	public void setAulasdadas(Long aulasdadas) {
		this.aulasdadas = aulasdadas;
	}

	

}
