package br.com.bulktecnologia.modelo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.bulktecnologia.modelo.entidade.MatriculaDisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.TipoEncerramentoMatriculaEntity;
import br.com.bulktecnologia.modelo.enums.TipoEncerramentoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;

public final class ResultadoMatriculaDTO implements Serializable {
	
	private Long idMatricula;
	private Long anoAdm;
	private Date dtMatricula;
	
	private Date dtEncerramentoMatricula;
	private String motivoEncerramentoMatricula;
	
	private TipoMatricula tipoMatricula;
	
	private String ensino;
	private Long idEnsino;
	
	private String serie;
	private Long idSerie;
	
	private String turno;
	private Long idTurno;
	
	private List<MatriculaDisciplinaEntity> matriculaDependencia;
	private Boolean alocacaoIncompleta;
	
	
	public ResultadoMatriculaDTO( Long idMatricula,Long anoAdm, Date dtMatricula, Date dtEncerramentoMatricula,
								  String motivoEncerramentoMatricula, TipoMatricula tipoMatricula,Long idEnsino,String ensino,
								  Long idSerie, String serie,Long idTurno, String turno, Boolean alocacaoIncompleta){
		
		this.idMatricula = idMatricula;
		this.anoAdm = anoAdm;
		this.dtMatricula = dtMatricula;
		this.dtEncerramentoMatricula = dtEncerramentoMatricula;
		this.motivoEncerramentoMatricula = motivoEncerramentoMatricula;
		this.tipoMatricula = tipoMatricula;
		this.idEnsino = idEnsino;
		this.idSerie = idSerie;
		this.idTurno = idTurno;
		this.ensino = ensino;
		this.serie = serie;
		this.turno = turno;
		this.alocacaoIncompleta = alocacaoIncompleta;
	}
	
	

	
	
	

	public Long getIdMatricula() {
		return idMatricula;
	}


	public Long getAnoAdm() {
		return anoAdm;
	}


	public Date getDtMatricula() {
		return dtMatricula;
	}


	public Date getDtEncerramentoMatricula() {
		return dtEncerramentoMatricula;
	}


	public String getMotivoEncerramentoMatricula() {
		return motivoEncerramentoMatricula;
	}


	public TipoMatricula getTipoMatricula() {
		return tipoMatricula;
	}


	public String getEnsino() {
		return ensino;
	}


	public String getSerie() {
		return serie;
	}


	public String getTurno() {
		return turno;
	}





	public String getStatus() {
		if (this.dtEncerramentoMatricula==null){
			return "Ativo";
		}
		else{
			return	"Encerrado";
		}
	}





	public Long getIdEnsino() {
		return idEnsino;
	}





	public Long getIdSerie() {
		return idSerie;
	}





	public Long getIdTurno() {
		return idTurno;
	}




	public Boolean getPermiteAtivarMatricula(){
		
		if ( motivoEncerramentoMatricula !=null ){
			//se matricula foi encerrada por motivo de remanejamento não é permitido re-ativar.
			if (TipoEncerramentoMatricula.Remanejamento.toString().equalsIgnoreCase(motivoEncerramentoMatricula)){
				return Boolean.FALSE;
			}
			else{
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}







	public List<MatriculaDisciplinaEntity> getMatriculaDependencia() {
		return matriculaDependencia;
	}







	public void setMatriculaDependencia(
			List<MatriculaDisciplinaEntity> matriculaDependencia) {
		this.matriculaDependencia = matriculaDependencia;
	}







	public Boolean getAlocacaoIncompleta() {
		return alocacaoIncompleta;
	}

	public String getStatusAlocacaoRegular(){
		if(this.dtEncerramentoMatricula!=null){
			return "sim / inativo.";
		}
		
		if (this.alocacaoIncompleta==null){
			return "Não";
		}
		else{
			if (this.alocacaoIncompleta){
				return "Parcialmente";
			}
			else{
				return "Sim";
			}
		}
		
	}
	
	
}
