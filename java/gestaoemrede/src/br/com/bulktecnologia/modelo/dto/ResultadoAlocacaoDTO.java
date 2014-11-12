package br.com.bulktecnologia.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import br.com.bulktecnologia.modelo.entidade.AlocacaoItemEntity;
import br.com.bulktecnologia.modelo.entidade.EntidadeSelecionavel;
import br.com.bulktecnologia.modelo.enums.Sexo;
import br.com.bulktecnologia.modelo.enums.TipoEncerramentoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoTurno;

public final class ResultadoAlocacaoDTO implements Serializable,EntidadeSelecionavel{
	
	private Long idAluno;
	private String nome;
	private Date dtNascimento;
	private String nomeMae;
	private Long idAlocacao;
	private Boolean diarioAbertoAlocacao;
	private Long idMatricula;
	private Boolean selecionado = Boolean.FALSE;
	private Long idAlocacaoItem;
	private Sexo sexo;
	private Boolean flagItemDiarioAberto;
	private String motivoEncerramentoMatricula;
	private TipoFormacaoTurma tipoFormacaoTurma;
	private Long idRemanejamentoDestino;
	
	
	//CONTRUTOR USADO PARA RESULTADO DE ALUNOS ALOCADOS
	public ResultadoAlocacaoDTO(Long idAluno, String nome,Sexo sexo, Date dtNascimento, String nomeMae, Long idAlocacao,Boolean statusAbertoAlocacao, Long idAlocacaoItem, Boolean flagItemDiarioAberto, Long idMatricula, String motivoEncerramentoMatricula, Long idRemanejamentoDestino){
		this.idAluno = idAluno;
		this.nome = nome;
		this.sexo = sexo;
		this.dtNascimento = dtNascimento;
		this.nomeMae = nomeMae;
		this.idAlocacao = idAlocacao;
		this.diarioAbertoAlocacao = statusAbertoAlocacao;
		this.idAlocacaoItem = idAlocacaoItem;
		this.flagItemDiarioAberto = flagItemDiarioAberto;
		this.idMatricula = idMatricula;
		this.motivoEncerramentoMatricula = motivoEncerramentoMatricula;
		this.idRemanejamentoDestino = idRemanejamentoDestino;
	}
	
	
	
	
	
	//CONSTRUTOR USADO PARA VALIDAR ALOCACAO: SERIE-DISCIPLINA, IMPEDINDO AMBAS AS POSSIBILIDADES DE ALOCAÇÃO PARA MESMO TURNO,ENSINO,SERIE E TIPOMATRICULA.  
	public ResultadoAlocacaoDTO(Long idAluno, String nomeAluno, TipoFormacaoTurma tipoFormacaoTurma){
		this.idAluno = idAluno;
		this.nome = nomeAluno;
		this.tipoFormacaoTurma = tipoFormacaoTurma;
	}
	
	
	
	//CONTRUTOR USADO PARA RESULTADO DE ALUNOS NÃO ALOCADOS
	public ResultadoAlocacaoDTO(Long idMatricula,Long idAluno,String nome, Date dtNascimento, String nomeMae){
		this.idAluno = idAluno;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.nomeMae = nomeMae;
		this.idMatricula = idMatricula;
	}

	
	
	public Long getIdAluno() {
		return idAluno;
	}

	public String getNome() {
		return nome;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public Long getIdAlocacao() {
		return idAlocacao;
	}

	public Boolean getDiarioAbertoAlocacao() {
		return diarioAbertoAlocacao;
	}



	public Long getIdMatricula() {
		return idMatricula;
	}


	public Boolean getSelecionado() {
		return selecionado;
	}


	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}


	public Long getIdAlocacaoItem() {
		return idAlocacaoItem;
	}


	public Boolean getFlagItemDiarioAberto() {
		return flagItemDiarioAberto;
	}



	public Sexo getSexo() {
		return sexo;
	}


	

	public String getMotivoEncerramentoMatricula() {
		return TipoEncerramentoMatricula.Remanejamento.toString().equals(motivoEncerramentoMatricula)?null:motivoEncerramentoMatricula;
	}



	public TipoFormacaoTurma getTipoFormacaoTurma() {
		return tipoFormacaoTurma;
	}



	public Boolean getRemanejado() {
		return this.idRemanejamentoDestino!=null;
	}


	public String getCssStyleClass(){
		
		if (this.flagItemDiarioAberto){
			
			if ( getRemanejado() && StringUtils.isBlank(motivoEncerramentoMatricula)){
				return "Remanejado";
			}
			
			if ( !(getRemanejado()) &&  !StringUtils.isBlank(motivoEncerramentoMatricula) ){
				return "styleAlunoRemanejado";
			}

			if ( getRemanejado() &&  !StringUtils.isBlank(motivoEncerramentoMatricula) ){
				return "styleAlunoRemanejadoERemanejado";
			}
			
		}
		else{
			if ( getRemanejado() && StringUtils.isBlank(motivoEncerramentoMatricula)){
				return "RemanejadoNegrito";
			}
			
			if ( !(getRemanejado()) &&  !StringUtils.isBlank(motivoEncerramentoMatricula) ){
				return "styleAlunoRemanejadoNegrito";
			}

			if ( getRemanejado() &&  !StringUtils.isBlank(motivoEncerramentoMatricula) ){
				return "styleAlunoRemanejadoERemanejadoNegrito";
			}
			return "SomenteNegrito";
		}
		return null;
	}





	public Long getIdRemanejamentoDestino() {
		return idRemanejamentoDestino;
	}



	
	
}
