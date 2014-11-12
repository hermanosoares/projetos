package br.com.bulktecnologia.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.comuns.util.DataUtil;
import br.com.bulktecnologia.modelo.enums.Sexo;

/**
 * Objeto de Transferencia de Resultado da Pesquisa.
 * 
 * @author hsoares
 *
 */
public final class ResultadoPesquisaDTO implements Serializable{

	
	private Long idPessoa;
	private Long idAluno;
	private Long idFuncionario;
	private String nome;
	private Sexo sexo;
	private Date dtNascimento;
	private String nomePai;
	private String nomeMae;
	private String cpf;
	private String rg;
	private String rgOrgaoExpedidor;
	private String naturalidade;
	private byte[] foto;
	
	
	
	
	/**
	 * Construtor usado pelo JPA, para definir resultado do JP-QL.
	 * 
	 * @param idPessoa
	 * @param idAluno
	 * @param idFuncionario
	 * @param nome
	 * @param sexo
	 * @param dtNascimento
	 * @param nomePai
	 * @param nomeMae
	 * @param cpf
	 * @param rg
	 * @param rgOrgaoExpedidor
	 * @param naturalidade
	 * @param foto
	 */
	public ResultadoPesquisaDTO(Long idPessoa,Long idAluno, Long idFuncionario, String nome,Sexo sexo,
								Date dtNascimento,String nomePai, String nomeMae, String cpf, String rg,
								String rgOrgaoExpedidor, String naturalidade,byte[] foto) {
		
		this.idPessoa = idPessoa;
		this.idAluno = idAluno;
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.sexo = sexo;
		this.dtNascimento = dtNascimento;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.cpf = cpf;
		this.rg = rg;
		this.rgOrgaoExpedidor = rgOrgaoExpedidor;
		this.naturalidade = naturalidade;
		this.foto = foto;
	}
	
	
	
	
	
	public Long getIdPessoa() {
		return idPessoa;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public String getNomePai() {
		return nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getRgOrgaoExpedidor() {
		return rgOrgaoExpedidor;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public byte[] getFoto() {
		return foto;
	}
	
	
	public Boolean getMaiorIdade() {
		return DataUtil.calculaIdade(this.dtNascimento) >  ApplicationConstants.Parametros.IDADE_MAIORIDADE;
	}

	public String getFileNameImage() {
		return "_img"+this.idPessoa;
	}

	
}
