package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.comuns.util.TurmaHelper;
import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;
@Entity
@Table(name="turma")
@Name("turmaEntity")
@NamedQueries(value={	//@NamedQuery(name="TurmaEntity.recuperaTurmasByTipoFormacaoTurmaPorDisciplinaEMultiSeriadaPorDisciplina",query="select distinct t from TurmaEntity t join t.disciplinas disc, ConfiguracaoEntity c  where t.configuracao.id = c.id and c.ativo = true and c.instituicao.id = :idInstituicao and t.tipoTurma = :tipoFormacaoTurma and disc.serie.tipoSerie = :tipoSerie  and t.turno.id = :idTurno"),
						  @NamedQuery(name="TurmaEntity.recuperaTurmasByTipoFormacaoTurmaPorDisciplinaEMultiSeriadaPorDisciplina",query="select distinct t from TurmaEntity t join t.disciplinas disc where t.configuracao.id = :idConfiguracaoEspecifica and t.tipoTurma = :tipoFormacaoTurma and disc.serie.id = :idSerie  and t.turno.id = :idTurno"),
						//@NamedQuery(name="TurmaEntity.recuperaTurmasByTipoFormacaoTurmaRegularEMultiSeriada",query="select distinct t from TurmaEntity t join t.series s, ConfiguracaoEntity c  where t.configuracao.id = c.id and c.ativo = true and c.instituicao.id = :idInstituicao and t.tipoTurma = :tipoFormacaoTurma and s.tipoSerie = :tipoSerie and t.turno.id = :idTurno"),
						  @NamedQuery(name="TurmaEntity.recuperaTurmasByTipoFormacaoTurmaRegularEMultiSeriada",query="select distinct t from TurmaEntity t join t.series s  where t.configuracao.id = :idConfiguracaoEspecifica and t.tipoTurma = :tipoFormacaoTurma and s.id = :idSerie and t.turno.id = :idTurno"),
						@NamedQuery(name="TurmaEntity.recuperaTiposFormacaoTurmaDaConfiguracaoAtiva",query="select distinct(t.tipoTurma) from TurmaEntity t where t.configuracao.id = :idConfiguracaoEspecifica"),
						@NamedQuery(name="TurmaEntity.recuperaTodasTurmasDaConfiguracaoAtiva",query="select t from TurmaEntity t where t.configuracao.id = :idConfiguracaoEspecifica"),
						@NamedQuery(name="TurmaEntity.recuperaTurmasDasSeries",query="select distinct s.turmas from SerieEntity s where s.id in (:idsSeries)"),
						@NamedQuery(name="TurmaEntity.recuperaTurmasRemanejamentoPorDisciplina",query="select distinct turma from TurmaEntity turma join turma.disciplinas disciplina where turma.turno.id = :idTurno and disciplina.id = :idDisciplina"),
						     @NamedQuery(name="TurmaEntity.recuperaTurmasRemanejamentoPorSerie",query="select distinct turma from TurmaEntity turma join turma.series serie where turma.turno.id = :idTurno and serie.id = :idSerie "),
					})
public class TurmaEntity implements Serializable {

	@Id
	@GeneratedValue(generator="turma_gen")
	@SequenceGenerator(name="turma_gen",sequenceName="turma_seq")
	private Long id;

	
	@Column(nullable=false)
	private String nome;

	
	@Column(nullable=false)
	private Long maxAlunos;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoFormacaoTurma tipoTurma;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoOrdenacao tipoOrdenacao;
	
	@Column(nullable=true)
	private String tooltip;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable
	private List<SerieEntity> series = new ArrayList<SerieEntity>();
	
	
	@OrderBy("tipoDisciplina asc")
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable
	private List<DisciplinaEntity> disciplinas;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private ConfiguracaoEntity configuracao;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private TurnoEntity turno;

	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
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

	public Long getMaxAlunos() {
		return maxAlunos;
	}

	public void setMaxAlunos(Long maxAlunos) {
		this.maxAlunos = maxAlunos;
	}

	public List<SerieEntity> getSeries() {
		return series;
	}

	public void setSeries(List<SerieEntity> series) {
		this.series = series;
	}

	public List<DisciplinaEntity> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaEntity> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public TipoFormacaoTurma getTipoTurma() {
		return tipoTurma;
	}

	public void setTipoTurma(TipoFormacaoTurma tipoTurma) {
		this.tipoTurma = tipoTurma;
	}

	public ConfiguracaoEntity getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(ConfiguracaoEntity configuracao) {
		this.configuracao = configuracao;
	}

	public TipoOrdenacao getTipoOrdenacao() {
		return tipoOrdenacao;
	}

	public void setTipoOrdenacao(TipoOrdenacao tipoOrdenacao) {
		this.tipoOrdenacao = tipoOrdenacao;
	}


	public TurnoEntity getTurno() {
		return turno;
	}

	public void setTurno(TurnoEntity turno) {
		this.turno = turno;
	}

	public EnsinoEntity getEnsino() {
		return ensino;
	}

	public void setEnsino(EnsinoEntity ensino) {
		this.ensino = ensino;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
	
}
