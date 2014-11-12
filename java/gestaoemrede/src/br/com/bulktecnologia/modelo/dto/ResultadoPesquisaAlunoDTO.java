package br.com.bulktecnologia.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.bulktecnologia.modelo.entidade.EntidadeSelecionavel;
import br.com.bulktecnologia.modelo.enums.Sexo;

public final class ResultadoPesquisaAlunoDTO implements Serializable,EntidadeSelecionavel{
	
	private Long idAluno;
	private Long idMatricula;
	private Long idAlocacao;
	private  String codAluno;
	private String nome;
	private Date dtNascimento;
	private Sexo sexo;
	private String nomeMae;
	private Boolean selecionado = Boolean.FALSE;
	
	
	public ResultadoPesquisaAlunoDTO(Long idAluno, String codAluno, String nome, Date dtNascimento, Sexo sexo, String nomeMae){
		this.idAluno = idAluno;
		this.codAluno = codAluno;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.nomeMae = nomeMae;
	}

	//usado pelos alunos não alocados
	public ResultadoPesquisaAlunoDTO(Long idAluno,Long idMatricula, String codAluno, String nome, Date dtNascimento, Sexo sexo, String nomeMae){
		this.idAluno = idAluno;
		this.idMatricula = idMatricula;
		this.codAluno = codAluno;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.nomeMae = nomeMae;
	}
	
	
	public ResultadoPesquisaAlunoDTO(Long idAluno,String codAluno, String nome, Date dtNascimento, Sexo sexo, String nomeMae,Long idAlocacao){
		this.idAluno = idAluno;
		this.idAlocacao = idAlocacao;
		this.codAluno = codAluno;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.nomeMae = nomeMae;
	}

	
	public Long getIdAluno() {
		return idAluno;
	}

	public String getCodAluno() {
		return codAluno;
	}

	public String getNome() {
		return nome;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Long getIdMatricula() {
		return idMatricula;
	}

	public Long getIdAlocacao() {
		return idAlocacao;
	}
	
	
	
	
	
	
}
