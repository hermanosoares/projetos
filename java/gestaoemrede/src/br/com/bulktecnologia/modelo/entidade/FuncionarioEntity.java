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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;
@Entity
@Table(name="funcionario")
@Name("funcionarioEntity")
@NamedQueries(value={
					@NamedQuery(name="FuncionarioEntity.recuperaFuncionarioByIdPessoa",query="select f from FuncionarioEntity f where f.pessoa.id = :idPessoa "),
					@NamedQuery(name="FuncionarioEntity.recuperaFuncionarioByCodigoFuncional",query="select f from FuncionarioEntity f where f.codigoFuncional = :codigoFuncional ")
					})
public class FuncionarioEntity implements Serializable {

	@Id
	@SequenceGenerator(name="funcionario_gen",sequenceName="funcionario_seq")
	@GeneratedValue(generator="funcionario_gen")
	private Long id;

	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="pessoa_id",nullable=false)
	private PessoaEntity pessoa;
	
	@Column(unique=true,nullable=true)
	private String codigoFuncional;

	@OneToMany(mappedBy="funcionario",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<RelacaoPessoaEntity> relacaoPessoaInstituicao;

	@OneToMany(mappedBy="funcionario",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<ContratoTrabalhoEntity> contratosTrabalho;
	

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

	public String getCodigoFuncional() {
		return codigoFuncional;
	}

	public void setCodigoFuncional(String codigoFuncional) {
		this.codigoFuncional = codigoFuncional;
	}

	public List<RelacaoPessoaEntity> getRelacaoPessoaInstituicao() {
		return relacaoPessoaInstituicao;
	}

	public void setRelacaoPessoaInstituicao(
			List<RelacaoPessoaEntity> relacaoPessoaInstituicao) {
		this.relacaoPessoaInstituicao = relacaoPessoaInstituicao;
	}

	public List<ContratoTrabalhoEntity> getContratosTrabalho() {
		return contratosTrabalho;
	}

	public void setContratosTrabalho(List<ContratoTrabalhoEntity> contratosTrabalho) {
		this.contratosTrabalho = contratosTrabalho;
	}




}
