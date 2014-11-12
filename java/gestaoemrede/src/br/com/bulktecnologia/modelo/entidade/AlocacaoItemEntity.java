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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;
@Entity
@Name("alocacaoItemEntity")
@Table(name="alocacaoItem")
public class AlocacaoItemEntity extends StatusDiarioClasse implements Serializable {

	@Id
	@SequenceGenerator(name="alocacaoItem_gen",sequenceName="alocacaoitem_seq")
	@GeneratedValue(generator="alocacaoItem_gen")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private AlocacaoEntity alocacao;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private AlunoEntity aluno;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private TurmaEntity turma;

	//este flag indica o status do diario no momento desta alocacao.
	@Column(nullable=false,columnDefinition="boolean")
	private Boolean diarioAbertoMomentoAlocacao;

	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(nullable=true)
	private RemanejamentoEntity remanejamentoDestino;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private MatriculaEntity matricula;

	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable
	private List<DisciplinaEntity> disciplinas;

	public AlunoEntity getAluno() {
		return aluno;
	}

	public void setAluno(AlunoEntity aluno) {
		this.aluno = aluno;
	}

	public TurmaEntity getTurma() {
		return turma;
	}

	public void setTurma(TurmaEntity turma) {
		this.turma = turma;
	}

	public List<DisciplinaEntity> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaEntity> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public AlocacaoEntity getAlocacao() {
		return alocacao;
	}

	public void setAlocacao(AlocacaoEntity alocacao) {
		this.alocacao = alocacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public MatriculaEntity getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculaEntity matricula) {
		this.matricula = matricula;
	}

	public Boolean getDiarioAbertoMomentoAlocacao() {
		return diarioAbertoMomentoAlocacao;
	}

	public void setDiarioAbertoMomentoAlocacao(Boolean diarioAbertoMomentoAlocacao) {
		this.diarioAbertoMomentoAlocacao = diarioAbertoMomentoAlocacao;
	}

	public RemanejamentoEntity getRemanejamentoDestino() {
		return remanejamentoDestino;
	}

	public void setRemanejamentoDestino(RemanejamentoEntity remanejamentoDestino) {
		this.remanejamentoDestino = remanejamentoDestino;
	}

	
}
