package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name="relacaopessoa")
@Name("relacaoPessoaEntity")
@NamedQueries(
		value={
				
	@NamedQuery(name="RelacaoPessoaEntity.recuperaAlunosSemMatricula",query="select new "+
																			" br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO(" +
																			"a.id, a.codeducacenso,a.pessoa.nome,a.pessoa.dtNascimento,a.pessoa.sexo,a.pessoa.nomeMae) " +
																			" from RelacaoPessoaEntity rp, AlunoEntity a" +
																			" where rp.instituicao.id = :idInstituicao and " +
																			" rp.aluno.id = a.id and not exists " +
																			" ( select m2.aluno from MatriculaEntity m2 " +
																			" where m2.instituicao.id = :idInstituicao and " +
																			" m2.aluno.id = a.id and " +
																			" m2.dtEncerramento is null " +
																		//	"and  year(m2.dtMatricula) = year(CURRENT_DATE) " +
																			") order by a.pessoa.nome ASC"),
																			


																													 
					}
)
public class RelacaoPessoaEntity implements Serializable{
	
	@Id
	@GeneratedValue(generator="relacaopessoa_gen")
	@SequenceGenerator(name="relacaopessoa_gen",sequenceName="relacaopessoa_seq")
	private Long id;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="pessoa_id",nullable=false)
	private PessoaEntity pessoa;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="aluno_id")
	private AlunoEntity aluno;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="funcionario_id")
	private FuncionarioEntity funcionario;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="instituicao_id",nullable=false)
	private InstituicaoEntity instituicao;
	
	
	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public AlunoEntity getAluno() {
		return aluno;
	}

	public void setAluno(AlunoEntity aluno) {
		this.aluno = aluno;
	}

	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	
}
