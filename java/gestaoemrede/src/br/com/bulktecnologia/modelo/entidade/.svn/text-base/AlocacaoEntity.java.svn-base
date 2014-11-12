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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.enums.TipoMatricula;

@Entity
@Name("alocacaoEntity")
@Table(name="alocacao")
@NamedQueries(
		value={            

				@NamedQuery(name="AlocacaoEntity.validaSeCompletouAMetaDisciplinas",query="select d from DisciplinaEntity d where d.serie.id = :idSerie " +
																						" and d not in ( " +
																						" select disc from AlocacaoEntity aloc " +
																						" join aloc.items subitem" +
																						" join subitem.disciplinas disc " +
																						" where  " +
																						" aloc.ensino.id = :idEnsino and " +
																						" aloc.serie.id = :idSerie and " +
																						" aloc.turno.id = :idTurno and " +
																						" aloc.tipoMatricula = :tipoMatricula and " +
																						" aloc.anoAdm = :anoAdm and " +
																						" subitem.aluno.id = :idAluno ) " 
																						),
				   
				
				@NamedQuery(name="AlocacaoEntity.recuperaAlocacaoJaExistente",query="select aloc from AlocacaoEntity aloc where " +
																					" aloc.ensino.id = :idEnsino and " +
																					" aloc.serie.id = :idSerie and " +
																					" aloc.turno.id = :idTurno and " +
																					" aloc.tipoMatricula = :tipoMatricula and " +
																					" aloc.anoAdm = :anoAdm "),
																					
				@NamedQuery(name="AlocacaoEntity.recuperaStatusDoDiarioDeUmaAlocacaoRegular",query="select distinct(alocItem.diarioAberto) from AlocacaoEntity aloc join aloc.items alocItem where " +
																							" aloc.ensino.id = :idEnsino and " +
																							" aloc.serie.id = :idSerie and " +
																							" aloc.turno.id = :idTurno and " +
																							" aloc.tipoMatricula = :tipoMatricula and " +
																							" aloc.anoAdm = :anoAdm and " +
																							" alocItem.turma.id = :idTurma"),

				@NamedQuery(name="AlocacaoEntity.recuperaStatusDoDiarioDeUmaAlocacaoPorDisciplina",query="select distinct(alocItem.diarioAberto) from AlocacaoEntity aloc join aloc.items alocItem join alocItem.disciplinas d where " +
																									" aloc.ensino.id = :idEnsino and " +
																									" aloc.serie.id = :idSerie and " +
																									" aloc.turno.id = :idTurno and " +
																									" aloc.tipoMatricula = :tipoMatricula and " +
																									" aloc.anoAdm = :anoAdm and " +
																									" alocItem.turma.id = :idTurma and " +
																									" d.id = :idDisciplina"),
																							
																							
/*				@NamedQuery(name="AlocacaoEntity.recuperaAlunosAlocadosRegular",query=	 " select distinct new " +
								 " br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO(alocItem.aluno.id," +
								 " alocItem.aluno.pessoa.nome, alocItem.aluno.pessoa.sexo, alocItem.aluno.pessoa.dtNascimento," +
								 " alocItem.aluno.pessoa.nomeMae, aloc.id, alocItem.diarioAberto, alocItem.id, alocItem.diarioAbertoMomentoAlocacao,alocItem.matricula.id,(select tem.nome from TipoEncerramentoMatriculaEntity tem where tem.id = alocItem.matricula.tipoencerramentoMatricula.id),alocItem.remanejamentoDestino.id) " +
								 " from AlocacaoEntity aloc join aloc.items alocItem WHERE " +
								 " aloc.anoAdm = :anoAdm and " +
								 " aloc.turno.id = :idTurno and " +
								 " aloc.ensino.id = :idEnsino and " +
								 " aloc.serie.id = :idSerie and " +
								 " aloc.tipoMatricula = :tipoMatricula and " +
								 " alocItem.turma.id = :turmaId order by :orderByParam"),*/

								 
/*				@NamedQuery(name="AlocacaoEntity.recuperaAlunosAlocadosDisciplina",query=	 " select distinct new " +
											 " br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO(alocItem.aluno.id," +
											 " alocItem.aluno.pessoa.nome, alocItem.aluno.pessoa.sexo, alocItem.aluno.pessoa.dtNascimento," +
											 " alocItem.aluno.pessoa.nomeMae, aloc.id, alocItem.diarioAberto, alocItem.id, alocItem.diarioAbertoMomentoAlocacao, alocItem.matricula.id, (select tem.nome from TipoEncerramentoMatriculaEntity tem where tem.id = alocItem.matricula.tipoencerramentoMatricula.id),alocItem.remanejamentoDestino.id) " +
											 " from AlocacaoEntity aloc join aloc.items alocItem join alocItem.disciplinas disc WHERE " +
											 " aloc.anoAdm = :anoAdm and " +
											 " aloc.turno.id = :idTurno and " +
											 " aloc.ensino.id = :idEnsino and " +
											 " aloc.serie.id = :idSerie and " +
											 " aloc.tipoMatricula = :tipoMatricula and " +
											 " alocItem.turma.id = :turmaId and " +
											 " disc.id = :idDisciplina "),*/
											 
				@NamedQuery(name="AlocacaoEntity.atualizaFlagDiarioAbertoMomentoAlocacao",query="update AlocacaoItemEntity item set item.diarioAbertoMomentoAlocacao = :status where item.id in (:idsAlocacaoItem) "),
				@NamedQuery(name="AlocacaoEntity.atualizaStatusDiarioAberto",query="update AlocacaoItemEntity set diarioAberto = :status where id in ( :idsAlocacaoItem )"),
				
				@NamedQuery(name="AlocacaoEntity.removeAlocacaoItemsByIds",query="delete from AlocacaoItemEntity where id in ( :idsItems )"),
				@NamedQuery(name="AlocacaoEntity.atualizaStatusDeAlocadoEmMatricula",query="update MatriculaEntity set alocacaoIncompleta = :statusAloc where id = :idMatricula"),
				@NamedQuery(name="AlocacaoEntity.atualizaParaAlocadoFalseEmMatriculaDisciplinaByIdMatriculaByIdDisciplina",query="update MatriculaDisciplinaEntity md set md.alocado = false where md.matricula.id in ( :idsMatriculas) and md.disciplina.id = :idDisciplina"),
				@NamedQuery(name="AlocacaoEntity.atualizaParaAlocadoFalseEmMatriculaDisciplinaByIdMatricula",query="update MatriculaDisciplinaEntity md set md.alocado = false where md.matricula.id in ( :idsMatriculas)"),
				
				@NamedQuery(name="AlocacaoEntity.recuperaStatusAlocacaoByIdsAlunos",query="select new br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO" +
																						  "(a.id,p.nome,i.turma.tipoTurma) " +
																						  " from AlocacaoEntity aloc " +
																						  " join aloc.items i " +
																						  " join i.aluno a " +
																						  " join a.pessoa p " +
																						  " where aloc.anoAdm = :anoAdm and " +
																						  " aloc.ensino.id = :idEnsino and " +
																						  " aloc.serie.id = :idSerie and " +
																						  " aloc.turno.id = :idTurno and " +
																						  " not (i.turma.tipoTurma = :tipo1 or i.turma.tipoTurma = :tipo2) and " +
																						  " i.aluno.id in (:idsAlunos) "),
				@NamedQuery(name="AlocacaoEntity.atualizaAlocacaoParaRemanejadaByIdsAlocacaoItem",query="update AlocacaoItemEntity set remanejado = true where id in (:idsAlocacaoItem)"),
				
				@NamedQuery(name="AlocacaoEntity.atualizaTurnoDaMatriculaQuandoRemanejado",query="update MatriculaEntity m set m.turno.id = :idTurno where id in (:idsMatriculas)"),
				@NamedQuery(name="AlocacaoEntity.recuperaTipoEncerramentoMatriculaByRemanejamento",query="select te from TipoEncerramentoMatriculaEntity te where te.instituicao.id = :idInstituicao and te.nome = 'Remanejamento'")																				
		}
		)
															 
public class AlocacaoEntity implements Serializable{
	
	@Id
	@SequenceGenerator(name="alocacaogen",sequenceName="alocacao_seq")
	@GeneratedValue(generator="alocacaogen")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private EnsinoEntity ensino;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private SerieEntity serie;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private TurnoEntity turno;
	
	@Column(nullable=false)
	private Long anoAdm;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dtAlocacao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoMatricula tipoMatricula;
	
	@OneToMany(mappedBy="alocacao",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	private List<AlocacaoItemEntity> items;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getDtAlocacao() {
		return dtAlocacao;
	}

	public void setDtAlocacao(Date dtAlocacao) {
		this.dtAlocacao = dtAlocacao;
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

	public Long getAnoAdm() {
		return anoAdm;
	}

	public void setAnoAdm(Long anoAdm) {
		this.anoAdm = anoAdm;
	}

	public TipoMatricula getTipoMatricula() {
		return tipoMatricula;
	}

	public void setTipoMatricula(TipoMatricula tipoMatricula) {
		this.tipoMatricula = tipoMatricula;
	}

	public List<AlocacaoItemEntity> getItems() {
		return items;
	}

	public void setItems(List<AlocacaoItemEntity> items) {
		this.items = items;
	}
	
	
	
}