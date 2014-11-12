package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.enums.TipoCondicaoFinalUltAno;
import br.com.bulktecnologia.modelo.enums.TipoEncerramentoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
@Entity
@Table(name="matricula")
@Name("matriculaEntity")
@NamedQueries(value={
		
		
					@NamedQuery(name="MatriculaEntity.AtualizaAlocacaoDisciplinasDeMatriculaDependencia",query="update MatriculaDisciplinaEntity md set md.alocado = :alocado where md.id in (select md2.id from MatriculaDisciplinaEntity md2 where md2.matricula.id = :idMatricula) "),
		
					@NamedQuery(name="MatriculaEntity.AtualizaStatusAlocacaoEmDisciplinaDeMatriculaDepencia",query="update MatriculaDisciplinaEntity md set md.alocado = :alocado where md.id in (select md2.id from MatriculaDisciplinaEntity md2 where md2.matricula.id = :idMatricula and md2.disciplina.id = :idDisciplina) "),
		
					@NamedQuery(name="MatriculaEntity.recuperaDisciplinasMatriculaDependencia",
							query="select dep.disciplina from MatriculaEntity m join m.matriculaDependencia dep where m.id = :idMatricula"),

					@NamedQuery(name="MatriculaEntity.recuperaTiposMatriculasByInstituicaoESerie",
								query="select distinct(m.tipoMatricula) from MatriculaEntity m where m.instituicao.id = :idInstituicao and m.serie.id = :idSerie"),
		
					//@NamedQuery(name="MatriculaEntity.recuperaSeriesDeMatriculasByIdEnsino",query="select distinct(m.serie) from MatriculaEntity m where m.instituicao.id = :idInstituicao and m.ensino.id = :idEnsino order by m.serie.tipoSerie asc"),
					@NamedQuery(name="MatriculaEntity.recuperaSeriesDeMatriculasByIdEnsino",query="select s from SerieEntity s where s.ensino.id = :idEnsino and exists(select m.id from MatriculaEntity m where m.serie.id = s.id)"),
		
					//@NamedQuery(name="MatriculaEntity.recuperaEnsinosDeMatriculasByIdTurno",query="select distinct(m.ensino) from MatriculaEntity m where m.instituicao.id = :idInstituicao and m.turno.id = :idTurno order by m.ensino.tipoEnsino asc"),
					  @NamedQuery(name="MatriculaEntity.recuperaEnsinosDeMatriculasByIdTurno",query="select e from EnsinoEntity e where e.turno.id = :idTurno and exists(select m.id from MatriculaEntity m where m.ensino.id = e.id)"),	

					@NamedQuery(name="MatriculaEntity.recuperaTurnosDeMatriculasByAnoSelecionado",
							    query="select distinct(m.turno) from MatriculaEntity m where m.instituicao.id = :idInstituicao and m.anoAdm = :anoSelecionado"),

					@NamedQuery(name="MatriculaEntity.recuperaAnosDisponiveis",
									    query="select distinct(m.anoAdm) from MatriculaEntity m where m.instituicao.id = :idInstituicao and m.dtEncerramento is null and m.tipoencerramentoMatricula is null"),
							    
					@NamedQuery(name="MatriculaEntity.atualizaEncerramentoMatriculaByIdMatriculas",
									    query="update MatriculaEntity m set m.tipoencerramentoMatricula.id = :idTipoEncerramento, m.dtEncerramento = :dtEncerramento where m.id in (:idMatriculas)"),
							    
					@NamedQuery(name="MatriculaEntity.recuperaMatriculasByIdAluno",query="select new " +
													" br.com.bulktecnologia.modelo.dto.ResultadoMatriculaDTO( m.id, m.anoAdm, " +
													" m.dtMatricula, m.dtEncerramento," +
													" (select te.nome from TipoEncerramentoMatriculaEntity te where te.id = m.tipoencerramentoMatricula.id)," +
													" m.tipoMatricula, " +
													" m.ensino.id, m.ensino.nome,"+
													" m.serie.id, m.serie.nome,"+
													" m.turno.id, m.turno.nome,m.alocacaoIncompleta) "+
													" from MatriculaEntity m where m.aluno.id = :idAluno"),		    
							    
    				@NamedQuery( name="MatriculaEntity.validaInsercaoDeMatricula",
							     query="select m from MatriculaEntity m where " +
							     	   " m.aluno.id = :idAluno  and " +
							    	   " m.ensino.id = :idTipoEnsino and " +
							    	   " m.serie.id = :idTipoSerie and " +
							       	   " m.turno.id = :idTipoTurno and " +
							     	   " m.anoAdm = :anoAdm and "+
							    	   " m.dtEncerramento is null and " +
							    	   " m.tipoencerramentoMatricula is null "),
})
public class MatriculaEntity implements Serializable {
	
	public MatriculaEntity(){
		super();
	}
	@Id
	@GeneratedValue(generator="matricula_gen")
	@SequenceGenerator(name="matricula_gen",sequenceName="matricula_seq")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="aluno_id",nullable=false)
	private AlunoEntity aluno;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private InstituicaoEntity instituicao;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinColumn(nullable=true)
	private TipoEncerramentoMatriculaEntity tipoencerramentoMatricula;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true)
	private Date dtEncerramento;
	
	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private EnsinoEntity ensino;
	
	@OrderBy("tipoSerie")
	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private SerieEntity serie;
	
	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private TurnoEntity turno;
	
	@Column(nullable=false)
	private Long anoAdm;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtMatricula;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoMatricula tipoMatricula;
	
	@Column(nullable=false)
	private Boolean novatoEstudos;

	@OneToMany(mappedBy="matricula",cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	private List<MatriculaDisciplinaEntity> matriculaDependencia;
	
	@Enumerated(EnumType.STRING)
	@Column
	private TipoCondicaoFinalUltAno tipoCondicaoFinalUltAno;
	
	@Column
	private Boolean alocacaoIncompleta;
	
	@Transient
	private Boolean selecionado = Boolean.FALSE;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlunoEntity getAluno() {
		return aluno;
	}

	public void setAluno(AlunoEntity aluno) {
		this.aluno = aluno;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

	public TipoEncerramentoMatriculaEntity getTipoencerramentoMatricula() {
		return tipoencerramentoMatricula;
	}

	public void setTipoencerramentoMatricula(
			TipoEncerramentoMatriculaEntity tipoencerramentoMatricula) {
		this.tipoencerramentoMatricula = tipoencerramentoMatricula;
	}

	public Date getDtMatricula() {
		return dtMatricula;
	}

	public void setDtMatricula(Date dtMatricula) {
		this.dtMatricula = dtMatricula;
	}


	public Boolean getNovatoEstudos() {
		return novatoEstudos;
	}

	public void setNovatoEstudos(Boolean novatoEstudos) {
		this.novatoEstudos = novatoEstudos;
	}



	public Date getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(Date dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}

	public Long getAnoAdm() {
		return anoAdm;
	}

	public void setAnoAdm(Long anoAdm) {
		this.anoAdm = anoAdm;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}

	public EnsinoEntity getEnsino() {
		return ensino;
	}

	public void setEnsino(EnsinoEntity ensino) {
		this.ensino = ensino;
	}

	public SerieEntity getSerie() {
		return serie;
	}

	public void setSerie(SerieEntity serie) {
		this.serie = serie;
	}

	public TurnoEntity getTurno() {
		return turno;
	}

	public void setTurno(TurnoEntity turno) {
		this.turno = turno;
	}



	/**
	 * metodo auxiliar que indica se o usuario selecionou matricula dependente.
	 * @return
	 */
	public Boolean getMatriculaDependenciaSelecionada(){
		
		if ( tipoMatricula == null ){
			return Boolean.FALSE;
		}
		else{
			if (TipoMatricula.Dependencia.equals(this.tipoMatricula)){
				return Boolean.TRUE;	
			}
			else{
				return Boolean.FALSE;
			}
		}
		
	}

	
	public TipoMatricula getTipoMatricula() {
		return tipoMatricula;
	}

	public void setTipoMatricula(TipoMatricula tipoMatricula) {
		this.tipoMatricula = tipoMatricula;
	}



	public Boolean getAlocacaoIncompleta() {
		return alocacaoIncompleta;
	}

	public void setAlocacaoIncompleta(Boolean alocacaoIncompleta) {
		this.alocacaoIncompleta = alocacaoIncompleta;
	}

	public TipoCondicaoFinalUltAno getTipoCondicaoFinalUltAno() {
		return tipoCondicaoFinalUltAno;
	}

	public void setTipoCondicaoFinalUltAno(
			TipoCondicaoFinalUltAno tipoCondicaoFinalUltAno) {
		this.tipoCondicaoFinalUltAno = tipoCondicaoFinalUltAno;
	}

	public List<MatriculaDisciplinaEntity> getMatriculaDependencia() {
		return matriculaDependencia;
	}

	public void setMatriculaDependencia(
			List<MatriculaDisciplinaEntity> matriculaDependencia) {
		this.matriculaDependencia = matriculaDependencia;
	}



	
	

}

