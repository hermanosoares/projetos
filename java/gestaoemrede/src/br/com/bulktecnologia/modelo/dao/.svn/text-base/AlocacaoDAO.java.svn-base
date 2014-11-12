package br.com.bulktecnologia.modelo.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.testng.log4testng.Logger;

import br.com.bulktecnologia.comuns.util.TurmaHelper;
import br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO;
import br.com.bulktecnologia.modelo.entidade.AlocacaoEntity;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.TipoEncerramentoMatriculaEntity;
import br.com.bulktecnologia.modelo.entidade.TurmaEntity;
import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoSerie;

@Name("AlocacaoDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class AlocacaoDAO extends BaseDAO{

	private Logger log = Logger.getLogger(this.getClass());
	
	
	public TipoEncerramentoMatriculaEntity recuperaTipoEncerramentoMatriculaByRemanejamento(Long idInstituicao){
		try {
			Query q = entityManager.createNamedQuery("AlocacaoEntity.recuperaTipoEncerramentoMatriculaByRemanejamento");
			q.setParameter("idInstituicao", idInstituicao);
			TipoEncerramentoMatriculaEntity te = (TipoEncerramentoMatriculaEntity) q.getSingleResult();
			return te;
		} catch (Exception e) {
			log.fatal("### Base de Dados Inconsistente, Tipo de Encerramento de Matricula (Propria do Sistema 'Remanejamento') não foi encontrada na base de dados! ####");
		}
		return null;
	}

	
	public Integer atualizaAlocacaoItemEmLancamentos(Long idAlocacaoItemOld,Long idAlocacaoItemNew){
		Query q =  this.entityManager.createNamedQuery("Lancamento.atualizaAlocacaoItemEmLancamentos");
		q.setParameter("idAlocacaoItemNew", idAlocacaoItemNew);
		q.setParameter("idAlocacaoItemOld", idAlocacaoItemOld);
		return q.executeUpdate();
	}

	public Integer atualizaAlocacaoItemEmLancamentosRecuperacao(Long idAlocacaoItemOld,Long idAlocacaoItemNew){
		Query q =  this.entityManager.createNamedQuery("LancamentoRecEntity.atualizaAlocacaoItemEmLancamentosRecuperacao");
		q.setParameter("idAlocacaoItemNew", idAlocacaoItemNew);
		q.setParameter("idAlocacaoItemOld", idAlocacaoItemOld);
		return q.executeUpdate();
	}

	
	public List<TurmaEntity> recuperaTurmasParaRemanejamentoPorDisciplina(Long idDisciplina ,Long idTurno){
		Query q = entityManager.createNamedQuery("TurmaEntity.recuperaTurmasRemanejamentoPorDisciplina");
		q.setParameter("idDisciplina", idDisciplina);
		q.setParameter("idTurno",idTurno);
		return q.getResultList();
	}

	
	
	
	public List<TurmaEntity> recuperaTurmasParaRemanejamentoPorSerie(Long idSerie ,Long idTurno){
		Query q = entityManager.createNamedQuery("TurmaEntity.recuperaTurmasRemanejamentoPorSerie");
		q.setParameter("idTurno",idTurno);
		q.setParameter("idSerie", idSerie);
		
		return q.getResultList();
	}
	
	
	
	
	public Integer atualizaTurnosDeMatriculas(List<Long> idsMatriculas,Long idTurno){
		Query q = entityManager.createNamedQuery("AlocacaoEntity.atualizaTurnoDaMatriculaQuandoRemanejado");
		q.setParameter("idTurno", idTurno);
		q.setParameter("idsMatriculas", idsMatriculas);
		return q.executeUpdate();
	}
	
	

	/**
	 * Marca registros como remanejado.
	 * 
	 * @param idsAlocacaoItem
	 * @param entityManager
	 * @return
	 */
	public Integer atualizaAlocacaoItemParaRemanejado(List<Long> idsAlocacaoItem){
		Query q = entityManager.createNamedQuery("AlocacaoEntity.atualizaAlocacaoParaRemanejadaByIdsAlocacaoItem");
		q.setParameter("idsAlocacaoItem", idsAlocacaoItem);
		return q.executeUpdate();
	}
	
	
	public Integer atualizaAlocacaoDisciplinaDeMatriculaDependencia(Long idMatricula, Long idDisciplina, Boolean alocado){
		Query q =  entityManager.createNamedQuery("MatriculaEntity.AtualizaStatusAlocacaoEmDisciplinaDeMatriculaDepencia");
		q.setParameter("idMatricula", idMatricula);
		q.setParameter("idDisciplina", idDisciplina);
		q.setParameter("alocado", alocado);
		
		
		return q.executeUpdate();
	}
	
	public Integer atualizaAlocacaoDisciplinasDeMatriculaDependencia(Long idMatricula, Boolean alocado){
		Query q =  entityManager.createNamedQuery("MatriculaEntity.AtualizaAlocacaoDisciplinasDeMatriculaDependencia");
		q.setParameter("idMatricula", idMatricula);
		q.setParameter("alocado", alocado);
		
		
		return q.executeUpdate();
	}
	
	
	
	
	/**
	 * usado para validar alocacao, impedindo que haja alocacao regular quando já houver uma alocação por disciplina.
	 * e vice-versa.
	 * 
	 * @param anoAdm
	 * @param idEnsino
	 * @param idSerie
	 * @param idTurno
	 * @param idsAlunos
	 * @param entityManager
	 * @return List<ResultadoAlocacaoDTO>
	 */
	public List<ResultadoAlocacaoDTO> recuperaStatusDeAlocacoesByIdsAlunos(Long anoAdm, Long idEnsino, Long idSerie, Long idTurno, List<Long> idsAlunos,TipoFormacaoTurma tipo1, TipoFormacaoTurma tipo2){
		Query q = entityManager.createNamedQuery("AlocacaoEntity.recuperaStatusAlocacaoByIdsAlunos");
		q.setParameter("anoAdm",anoAdm);
		q.setParameter("idEnsino", idEnsino);
		q.setParameter("idSerie", idSerie);
		q.setParameter("idTurno", idTurno);
		q.setParameter("idsAlunos",idsAlunos);
		q.setParameter("tipo1", tipo1);
		q.setParameter("tipo2", tipo2);
		
		return q.getResultList();
	}
	
	
	
	
	/**
	 * 
	 * RECUPERA ALUNOS NÃO ALOCADOS
	 * 
	 * @param idInstituicao - obrigatorio
	 * @param ano - obrigatorio
	 * @param idTurno - obrigatorio
	 * @param idEnsino - obrigatorio
	 * @param idSerie - obrigatorio
	 * @param tipoMatricula - obrigatorio
	 * @param idDisciplinaDependencia - OPCIONAL
	 * @return List<ResultadoPesquisaAlunoDTO>
	 */
	public List<ResultadoAlocacaoDTO> recuperaAlunosNaoAlocados(Long idInstituicao, Long ano, Long idTurno, Long idEnsino, Long idSerie, TipoMatricula tipoMatricula, Long idDisciplinaDependencia,Long idTurma,TipoFormacaoTurma tipoFormacaoTurma){

			HashMap<String,Object> parametros = new HashMap<String, Object>();
			parametros.put("anoAdm",ano);
			parametros.put("idTurno",idTurno);
			parametros.put("idEnsino",idEnsino);
			parametros.put("idSerie",idSerie);
			parametros.put("tipoMatricula",tipoMatricula);
			parametros.put("idInstituicao",idInstituicao);
			parametros.put("idTurmaSelecionada", idTurma);
			parametros.put("idDisciplinaDependencia", idDisciplinaDependencia);

			
			StringBuilder campos = new StringBuilder();
			campos.append(" select distinct new ").
				   append(" br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO").
				   append("(m.id, m.aluno.id, m.aluno.pessoa.nome, m.aluno.pessoa.dtNascimento, m.aluno.pessoa.nomeMae) ");
			 
			
			StringBuilder hql = new StringBuilder();
			hql.append(" from MatriculaEntity m WHERE ").
				append(" m.instituicao.id = :idInstituicao and ").
				append(" m.anoAdm = :anoAdm and ").
				append(" m.turno.id = :idTurno and ").
				append(" m.ensino.id = :idEnsino and ").
				append(" m.serie.id = :idSerie and ").
				append(" m.tipoMatricula = :tipoMatricula and ").
				append(" m.tipoencerramentoMatricula is null and ").
				append(" m.dtEncerramento is null and ").
				append(" (m.alocacaoIncompleta is null or ").
				append("  m.alocacaoIncompleta = true) ");
			
			if ( TipoMatricula.Regular.equals(tipoMatricula) ){
				
				if ( TurmaHelper.isTurmaPorSerie(tipoFormacaoTurma) ){
					hql.append( "and not exists( ");
					hql.append( "select i from AlocacaoEntity aloc join aloc.items i where" );
					hql.append( " aloc.ensino.id = :idEnsino and ");
					hql.append( " aloc.serie.id = :idSerie and " );
					hql.append( " aloc.turno.id = :idTurno and " );
					hql.append( " aloc.tipoMatricula = :tipoMatricula and " );
					hql.append( " aloc.anoAdm = :anoAdm and ");
					hql.append("  i.matricula.id = m.id and ");
					hql.append("  i.aluno.id = m.aluno.id) ");
					
				}
				else{
					if ( TurmaHelper.isTurmaPorDisciplina(tipoFormacaoTurma) ){
						hql.append( "and not exists( ");
						hql.append( "select d.id from AlocacaoEntity aloc join aloc.items i join i.disciplinas d where" );
						hql.append( " aloc.ensino.id = :idEnsino and ");
						hql.append( " aloc.serie.id = :idSerie and " );
						hql.append( " aloc.turno.id = :idTurno and " );
						hql.append( " aloc.tipoMatricula = :tipoMatricula and " );
						hql.append( " aloc.anoAdm = :anoAdm  and ");
						hql.append( " d.id = :idDisciplinaDependencia and ");
						hql.append("  i.matricula.id = m.id and");
						hql.append("  i.aluno.id = m.aluno.id)");
					}
					else{
						throw new RuntimeException("erro: #01# tipo formacao de tuma não previsto");
					}
				}
				
			}
			else{
				if ( TipoMatricula.Dependencia.equals(tipoMatricula) ){
					
					
					if ( TurmaHelper.isTurmaPorSerie(tipoFormacaoTurma) ){
						hql.append("and exists(select d from TurmaEntity t");
						hql.append(" join t.series s ");
						hql.append("join s.disciplinas d ");
						hql.append("where t.id = :idTurmaSelecionada and ");
						hql.append("s.id = :idSerie and ");
						hql.append("d.id in ");
						hql.append("(select md.disciplina.id from MatriculaEntity m2 join m2.matriculaDependencia md where m2.id = m.id and md.alocado = false) )");
					}
					else{
						if ( TurmaHelper.isTurmaPorDisciplina(tipoFormacaoTurma) ){
							hql.append("and exists(select dep from MatriculaEntity m2 join m2.matriculaDependencia dep ");
							hql.append("where m2.id = m.id and dep.disciplina.id = :idDisciplinaDependencia and dep.alocado = false)");
						}
						else{
							throw new RuntimeException("erro: #02# tipo formacao de tuma não previsto");
						}
					}
					
				}
				else{
					throw new RuntimeException("erro: #03# tipo de matricula não previsto");
				}
				
			}
			
			String fullHQL = campos.toString() + hql.toString() + " ORDER by m.aluno.pessoa.nome ASC ";
			

			Query q = this.entityManager.createQuery(fullHQL);
			
			configuraParametrosQuery(q, parametros);
			return q.getResultList();
	}
	
	
	
	
	
	
	public AlocacaoEntity recuperaAlocacaoExistente(Long anoAdm, TipoMatricula tipoMatricula, Long idTurno, Long idEnsino, Long idSerie){
		Query q = entityManager.createNamedQuery("AlocacaoEntity.recuperaAlocacaoJaExistente");
		q.setParameter("idEnsino", idEnsino);
		q.setParameter("idSerie", idSerie);
		q.setParameter("idTurno", idTurno);
		q.setParameter("tipoMatricula", tipoMatricula);
		q.setParameter("anoAdm", anoAdm);
		
		try {
			return (AlocacaoEntity) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Boolean recuperaStatusDoDiarioDeUmaAlocacaoRegular(Long anoAdm, TipoMatricula tipoMatricula, Long idTurno, Long idEnsino, Long idSerie,Long idTurma){
		
		Query q = entityManager.createNamedQuery("AlocacaoEntity.recuperaStatusDoDiarioDeUmaAlocacaoRegular");
		q.setParameter("idEnsino", idEnsino);
		q.setParameter("idSerie", idSerie);
		q.setParameter("idTurno", idTurno);
		q.setParameter("tipoMatricula", tipoMatricula);
		q.setParameter("anoAdm", anoAdm);
		q.setParameter("idTurma", idTurma);
		
		try {
			List<Boolean>  result =  q.getResultList();
			
			if (result.size()>0){
				return result.get(0);
			}
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	public Boolean recuperaStatusDoDiarioDeUmaAlocacaoDisciplina(Long anoAdm, TipoMatricula tipoMatricula, Long idTurno, Long idEnsino, Long idSerie,Long idTurma,Long idDisciplina){
		
		Query q = entityManager.createNamedQuery("AlocacaoEntity.recuperaStatusDoDiarioDeUmaAlocacaoPorDisciplina");
		q.setParameter("idEnsino", idEnsino);
		q.setParameter("idSerie", idSerie);
		q.setParameter("idTurno", idTurno);
		q.setParameter("tipoMatricula", tipoMatricula);
		q.setParameter("anoAdm", anoAdm);
		q.setParameter("idTurma", idTurma);
		q.setParameter("idDisciplina", idDisciplina);
		
		
		try {
			List<Boolean> result = q.getResultList();
			
			if ( result.size() > 0 ){
				return result.get(0);
			}
			
		} catch (NoResultException e) {
			return null;
		}
		
		return null;
	}
	
	
	
	
	
	public List<ResultadoAlocacaoDTO> recuperaAlunosAlocados(Long ano, Long idTurno, Long idEnsino, Long idSerie, TipoMatricula tipoMatricula, Long idDisciplinaDependencia, Long idTurma,String orderBy){
		
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		parametros.put("anoAdm",ano);
		parametros.put("idTurno",idTurno);
		parametros.put("idEnsino",idEnsino);
		parametros.put("idSerie",idSerie);
		parametros.put("tipoMatricula",tipoMatricula);
		parametros.put("turmaId",idTurma);
		parametros.put("orderByParam", orderBy);
		
		String hqlQuery = null;
		
		if (idDisciplinaDependencia==null){
			hqlQuery =   " select distinct new " +
  					     " br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO(alocItem.aluno.id," +
						 " alocItem.aluno.pessoa.nome, alocItem.aluno.pessoa.sexo, alocItem.aluno.pessoa.dtNascimento," +
						 " alocItem.aluno.pessoa.nomeMae, aloc.id, alocItem.diarioAberto, alocItem.id, alocItem.diarioAbertoMomentoAlocacao,alocItem.matricula.id,(select tem.nome from TipoEncerramentoMatriculaEntity tem where tem.id = alocItem.matricula.tipoencerramentoMatricula.id),alocItem.remanejamentoDestino.id) " +
						 " from AlocacaoEntity aloc join aloc.items alocItem WHERE " +
						 " aloc.anoAdm = :anoAdm and " +
						 " aloc.turno.id = :idTurno and " +
						 " aloc.ensino.id = :idEnsino and " +
						 " aloc.serie.id = :idSerie and " +
						 " aloc.tipoMatricula = :tipoMatricula and " +
						 " alocItem.turma.id = :turmaId ";
		}
		else{
			parametros.put("idDisciplina",idDisciplinaDependencia);
			
			hqlQuery =   " select distinct new " +
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
						 " disc.id = :idDisciplina ";
		}
		
		hqlQuery += orderBy;
		
	 	Query q = entityManager.createQuery(hqlQuery);
	 	
	 	configuraParametrosQuery(q, parametros);
	 	
		return q.getResultList();
	}
	
	
	

	public Integer atualizaFlagDiarioAbertoMomentoAlocacao(List<Long> idsAlocacaoItem, Boolean status){
		Query q = entityManager.createNamedQuery("AlocacaoEntity.atualizaFlagDiarioAbertoMomentoAlocacao");
		q.setParameter("idsAlocacaoItem", idsAlocacaoItem);
		q.setParameter("status", status);
		return q.executeUpdate();
	}

	
	public Integer atualizaStatusDiarioAberto(List<Long> idsAlocacaoItem, Boolean status){
		Query q = entityManager.createNamedQuery("AlocacaoEntity.atualizaStatusDiarioAberto");
		q.setParameter("idsAlocacaoItem",idsAlocacaoItem );
		q.setParameter("status", status);
		return q.executeUpdate();
	}
	
	public Integer atualizaAlocacaoItemsParaDiarioFechado(List<Long> idsAlocacaoItems){
		Query q = entityManager.createNamedQuery("AlocacaoEntity.atualizaAlocacaoItemsParaDiarioFechado");
		q.setParameter("idsItems", idsAlocacaoItems);
		return q.executeUpdate();
	}
	
	
	

	
	
	
	public List<DisciplinaEntity> recuperaDisciplinasRestantesParaCompletarAMetaDaMatricula(Long anoAdm, Long idTurno, Long idEnsino, Long idSerie, TipoMatricula tipoMatricula, Long idAluno){
		
		Query q = entityManager.createNamedQuery("AlocacaoEntity.validaSeCompletouAMetaDisciplinas");
		
		q.setParameter("idEnsino", idEnsino);
		q.setParameter("idSerie", idSerie);
		q.setParameter("idTurno", idTurno);
		q.setParameter("tipoMatricula", tipoMatricula);
		q.setParameter("anoAdm", anoAdm);
		q.setParameter("idAluno", idAluno);
		
		return q.getResultList();
	}
	
	
	
	
	
	
	public Boolean recuperaStatusDoDiario(Long anoAdm, TipoMatricula tipoMatricula, Long idTurno, Long idEnsino, Long idSerie,Long idTurma,DisciplinaEntity disciplina){
		if (disciplina==null){
			return this.recuperaStatusDoDiarioDeUmaAlocacaoRegular(anoAdm, tipoMatricula, idTurno, idEnsino, idSerie, idTurma);
		}
		else{
			return this.recuperaStatusDoDiarioDeUmaAlocacaoDisciplina(anoAdm, tipoMatricula, idTurno, idEnsino, idSerie, idTurma, disciplina.getId());
		}
	}
	
	
	
	
	
	
}
