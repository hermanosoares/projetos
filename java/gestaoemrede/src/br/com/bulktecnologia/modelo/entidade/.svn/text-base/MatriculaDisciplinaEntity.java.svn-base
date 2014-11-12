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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name="matriculadisciplina")
@Name("matriculaDisciplinaEntity")
@NamedQueries(value={
		@NamedQuery(name="MatriculaDisciplinaEntity.recuperaIdsDisciplinasDosIdsMatriculas",query="select new br.com.bulktecnologia.modelo.dto.ResultadoMatriculaDisciplinaDTO(md.disciplina.id,md.matricula.id)  from MatriculaDisciplinaEntity md where md.matricula.id in (:idsMatriculas) "),
		@NamedQuery(name="MatriculaDisciplinaEntity.recuperaMatriculaDisciplinaByIdMatricula",query="select md from MatriculaDisciplinaEntity md where md.matricula.id = :idMatricula")
})
public class MatriculaDisciplinaEntity implements Serializable {

	public MatriculaDisciplinaEntity(){
	}
	
	public MatriculaDisciplinaEntity(DisciplinaEntity disciplina, MatriculaEntity matricula){
		this.setDisciplina(disciplina);
		this.setMatricula(matricula);
	}
	
	@Id
	@GeneratedValue(generator="matriculadisciplina_gen")
	@SequenceGenerator(name="matriculadisciplina_gen",sequenceName="matriculadisciplina_seq")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(nullable=false)
	private DisciplinaEntity disciplina;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(nullable=false)
	private MatriculaEntity matricula;
	
	@Column(nullable=false)
	private Boolean alocado = Boolean.FALSE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DisciplinaEntity getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(DisciplinaEntity disciplina) {
		this.disciplina = disciplina;
	}

	public MatriculaEntity getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculaEntity matricula) {
		this.matricula = matricula;
	}

	public Boolean getAlocado() {
		return alocado;
	}

	public void setAlocado(Boolean alocado) {
		this.alocado = alocado;
	}

}
