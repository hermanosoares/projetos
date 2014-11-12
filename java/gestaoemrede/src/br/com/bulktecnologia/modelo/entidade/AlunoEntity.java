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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.jboss.seam.annotations.Name;
@Name("alunoEntity")
@Entity
@Table(name="aluno")
@Audited
@NamedQueries(value={
				@NamedQuery(name="AlunoEntity.validaCodigoEducaCenso",query="select a from AlunoEntity a where lower(a.codeducacenso) = lower(:idCodeducacenso)"),

				@NamedQuery(name="AlunoEntity.recuperaDTOAlunoByIdAluno",query="select new "+
								" br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO(" +
								" a.id, a.codeducacenso,a.pessoa.nome,a.pessoa.dtNascimento,a.pessoa.sexo,a.pessoa.nomeMae) " +
								" from AlunoEntity a " +
								" where a.id = :idAluno "),
					 
				@NamedQuery(name="AlunoEntity.recuperaAlunosMatriculadosDaInstituicaoFiltroTodas",query=" select distinct new " +
								 " br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO( m.aluno.id," +
								 " m.aluno.codeducacenso, m.aluno.pessoa.nome, m.aluno.pessoa.dtNascimento, " +
								 " m.aluno.pessoa.sexo, m.aluno.pessoa.nomeMae)  " +
								 " from MatriculaEntity m where m.instituicao.id = :idInstituicao and " +
								 " m.anoAdm = :anoAdm and m.tipoMatricula = :tipoMatricula  "+
								 " ORDER by m.aluno.pessoa.nome ASC "),

				@NamedQuery(name="AlunoEntity.recuperaAlunosMatriculadosDaInstituicaoFiltroEncerradas",query=" select distinct new " +
																 " br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO( m.aluno.id," +
																 " m.aluno.codeducacenso, m.aluno.pessoa.nome, m.aluno.pessoa.dtNascimento, " +
																 " m.aluno.pessoa.sexo, m.aluno.pessoa.nomeMae)  " +
																 " from MatriculaEntity m where m.instituicao.id = :idInstituicao and " +
																 " m.anoAdm = :anoAdm and m.tipoMatricula = :tipoMatricula  and " +
																 " m.tipoencerramentoMatricula is not null "+
																 " ORDER by m.aluno.pessoa.nome ASC "),		
													 
				@NamedQuery(name="AlunoEntity.recuperaAlunosMatriculadosDaInstituicaoFiltroAtivas",query=" select distinct new " +
																			 " br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO( m.aluno.id," +
																			 " m.aluno.codeducacenso, m.aluno.pessoa.nome, m.aluno.pessoa.dtNascimento, " +
																			 " m.aluno.pessoa.sexo, m.aluno.pessoa.nomeMae)  " +
																			 " from MatriculaEntity m where m.instituicao.id = :idInstituicao and " +
																			 " m.anoAdm = :anoAdm and m.tipoMatricula = :tipoMatricula  and " +
																			 " m.tipoencerramentoMatricula is null "+
																			 " ORDER by m.aluno.pessoa.nome ASC "),
					 
	})
public class AlunoEntity implements Serializable,EntidadeSelecionavel {
	
	@Id
	@GeneratedValue(generator="aluno_seq")
	@SequenceGenerator(name="aluno_seq",sequenceName="aluno_seq")
	private Long id;
	
	@Column(nullable=true)
	private String horarioreuniao;
	
	@Column(nullable=true)
	private String autorizadosbuscar;
	
	@Column(nullable=true,unique=true)
	private String codeducacenso;
	
	@Column(nullable=true)
	private String codpasta;
	
	@Transient
	private Boolean usatransppublico;

	@Column(nullable=true)
	private String resptransporte;

	@NotAudited
	@OneToMany(mappedBy="aluno",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private  List<MatriculaEntity> matriculas;
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private PessoaEntity pessoa;

	@NotAudited
	@OneToMany(mappedBy="aluno",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<RelacaoPessoaEntity> relacaoPessoaInstituicao;

/*	@NotAudited
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinTable
	private List<AlocacaoEntity> alocacoes;*/
	
	public PessoaEntity getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHorarioreuniao() {
		return horarioreuniao;
	}
	public void setHorarioreuniao(String horarioreuniao) {
		this.horarioreuniao = horarioreuniao;
	}
	public String getAutorizadosbuscar() {
		return autorizadosbuscar;
	}
	public void setAutorizadosbuscar(String autorizadosbuscar) {
		this.autorizadosbuscar = autorizadosbuscar;
	}


	public Boolean getUsatransppublico() {
		return usatransppublico;
	}
	public void setUsatransppublico(Boolean usatransppublico) {
		this.usatransppublico = usatransppublico;
	}
	public String getResptransporte() {
		return resptransporte;
	}
	public void setResptransporte(String resptransporte) {
		this.resptransporte = resptransporte;
	}
	public String getCodeducacenso() {
		return codeducacenso;
	}
	public void setCodeducacenso(String codeducacenso) {
		this.codeducacenso = codeducacenso;
	}
	public String getCodpasta() {
		return codpasta;
	}
	public void setCodpasta(String codpasta) {
		this.codpasta = codpasta;
	}
	
	
	//workaround usado para identificar selecoes em JSF.
	@Transient
	private Boolean selecionado = Boolean.FALSE;

	public Boolean getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}
	public List<MatriculaEntity> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(List<MatriculaEntity> matriculas) {
		this.matriculas = matriculas;
	}
	public List<RelacaoPessoaEntity> getRelacaoPessoaInstituicao() {
		return relacaoPessoaInstituicao;
	}
	public void setRelacaoPessoaInstituicao(
			List<RelacaoPessoaEntity> relacaoPessoaInstituicao) {
		this.relacaoPessoaInstituicao = relacaoPessoaInstituicao;
	}
	
/*	public List<AlocacaoEntity> getAlocacoes() {
		return alocacoes;
	}
	public void setAlocacoes(List<AlocacaoEntity> alocacoes) {
		this.alocacoes = alocacoes;
	}
*/


	
	
}
