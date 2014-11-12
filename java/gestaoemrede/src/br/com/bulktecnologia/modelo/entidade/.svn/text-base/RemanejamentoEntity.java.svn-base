package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="remanejamento")
@NamedQueries(value={
		@NamedQuery(name="RemanejamentoEntity.recuperaRemanejamentoByIdMatriculas",query="select r from RemanejamentoEntity r where r.matriculaDestino.id in (:idMatriculas)"),
		@NamedQuery(name="RemanejamentoEntity.removeHistoricoRemanejamentoByIdsRemanejamentos",query="delete from RemanejamentoEntity r where r.id in (:idsRemanejamentos)"),
		@NamedQuery(name="RemanejamentoEntity.atualizaAlocacaoItemOrigemParaNaoRemanejada",query="update AlocacaoItemEntity alocItem set alocItem.remanejamentoDestino = null where alocItem.id in (:idsAlocacaoItemOrigem) "),
		@NamedQuery(name="RemanejamentoEntity.removeAlocacaoItemDestino",query="delete from AlocacaoItemEntity alocItem where alocItem.id in (:idsAlocacaoItemDestino) "),
		@NamedQuery(name="RemanejamentoEntity.removeMatriculaDependenciaRemanejada",query="delete from MatriculaDisciplinaEntity md where md.matricula.id in (:idsMatriculaRemanejadas)"),
		@NamedQuery(name="RemanejamentoEntity.removeMatriculaRemanejada",query="delete from MatriculaEntity m where m.id in (:idsMatriculaRemanejadas)"),
		@NamedQuery(name="RemanejamentoEntity.atualizaMatriculaOrigemParaNaoRemanejada",query="update MatriculaEntity m set m.dtEncerramento = null, m.tipoencerramentoMatricula = null where m.id in (:idsMatriculaOrigem)"),
		})
public class RemanejamentoEntity implements Serializable {

	@Id
	@GeneratedValue(generator="remanejamento_gen")
	@SequenceGenerator(name="remanejamento_gen",sequenceName="remanejamento_seq")
	private Long id;

	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private MatriculaEntity matriculaOrigem;
	
	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private MatriculaEntity matriculaDestino;

	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private AlocacaoItemEntity alocacaoItemOrigem;

	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private AlocacaoItemEntity alocacaoItemDestino;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MatriculaEntity getMatriculaOrigem() {
		return matriculaOrigem;
	}

	public void setMatriculaOrigem(MatriculaEntity matriculaOrigem) {
		this.matriculaOrigem = matriculaOrigem;
	}

	public MatriculaEntity getMatriculaDestino() {
		return matriculaDestino;
	}

	public void setMatriculaDestino(MatriculaEntity matriculaDestino) {
		this.matriculaDestino = matriculaDestino;
	}

	public AlocacaoItemEntity getAlocacaoItemOrigem() {
		return alocacaoItemOrigem;
	}

	public void setAlocacaoItemOrigem(AlocacaoItemEntity alocacaoItemOrigem) {
		this.alocacaoItemOrigem = alocacaoItemOrigem;
	}

	public AlocacaoItemEntity getAlocacaoItemDestino() {
		return alocacaoItemDestino;
	}

	public void setAlocacaoItemDestino(AlocacaoItemEntity alocacaoItemDestino) {
		this.alocacaoItemDestino = alocacaoItemDestino;
	}
	
	
	
}
