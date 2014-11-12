package br.com.bulktecnologia.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.controle.exception.MatriculaException;
import br.com.bulktecnologia.modelo.dto.ResultadoMatriculaDTO;
import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO;
import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.MatriculaDisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.MatriculaEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.entidade.TipoEncerramentoMatriculaEntity;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;
import br.com.bulktecnologia.modelo.enums.TipoCondicaoFinalUltAno;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoSelecaoMatricula;

@Name("MatriculaDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class MatriculaDAO extends BaseDAO{

	
	
	public List<MatriculaDisciplinaEntity> recuperaMatriculaDisciplinasByIdMatricula(Long idMatricula){
		Query q = this.entityManager.createNamedQuery("MatriculaDisciplinaEntity.recuperaMatriculaDisciplinaByIdMatricula");
		q.setParameter("idMatricula", idMatricula);
		return q.getResultList();
	}
	
	
	
	public Integer atualizaEncerramentoMatricula(Date dtEncerramento,Long idTipoEncerramento,List<Long> idMatriculas){
		Query q =  this.getEm().createNamedQuery("MatriculaEntity.atualizaEncerramentoMatriculaByIdMatriculas");
		q.setParameter("idTipoEncerramento", idTipoEncerramento);
		q.setParameter("dtEncerramento", dtEncerramento);
		q.setParameter("idMatriculas", idMatriculas);
		
		return q.executeUpdate();
	}
	
	
	
	public List<TurnoEntity> recuperaTurnosDeMatriculasByAno(Long idInstituicao, Long ano){
		Query q = this.getEm().createNamedQuery("MatriculaEntity.recuperaTurnosDeMatriculasByAnoSelecionado");
		q.setParameter("idInstituicao", idInstituicao);
		q.setParameter("anoSelecionado", ano);
		return q.getResultList();
	}
	

	public List<EnsinoEntity> recuperaEnsinosDeMatriculasByTurno(Long idTurno){
		Query q = this.getEm().createNamedQuery("MatriculaEntity.recuperaEnsinosDeMatriculasByIdTurno");
		q.setParameter("idTurno", idTurno);
		return q.getResultList();
	}

	
	public List<SerieEntity> recuperaSeriesDeMatriculasByEnsino(Long idEnsino){
		Query q = this.getEm().createNamedQuery("MatriculaEntity.recuperaSeriesDeMatriculasByIdEnsino");
		q.setParameter("idEnsino", idEnsino);
		return q.getResultList();
	}

	
	public List<TipoMatricula> recuperaTiposMatriculasBySerie(Long idInstituicao,Long idSerie){
		Query q = this.getEm().createNamedQuery("MatriculaEntity.recuperaTiposMatriculasByInstituicaoESerie");
		q.setParameter("idInstituicao", idInstituicao);
		q.setParameter("idSerie",idSerie);
		return q.getResultList();
	}
	
	
	public List<ResultadoMatriculaDTO> recuperaMatriculaByIdAluno(Long idAluno){
		Query q =  this.getEm().createNamedQuery("MatriculaEntity.recuperaMatriculasByIdAluno");
		q.setParameter("idAluno", idAluno);
		return q.getResultList();
	}
	
	
	
	public List<Long> recuperaAnosComMatriculasDisponiveis(Long idInstituicao){
		Query q = this.getEm().createNamedQuery("MatriculaEntity.recuperaAnosDisponiveis");
		q.setParameter("idInstituicao", idInstituicao);
		return q.getResultList();
	}
	

	
	
	public List<ResultadoPesquisaAlunoDTO> recuperaAlunosSemMatricula(Long idInstituicao){
		Query q =  this.getEm().createNamedQuery("RelacaoPessoaEntity.recuperaAlunosSemMatricula");
		q.setParameter("idInstituicao", idInstituicao);
		return q.getResultList();
	}


	
	public List<ResultadoPesquisaAlunoDTO> recuperaAlunosMatriculados(Long idInstituicao, Long anoAdm, TipoMatricula tipoMatricula, TipoSelecaoMatricula tipoSelecao){
		
		String namedQuery = null;
		
		if ( TipoSelecaoMatricula.Todas.equals(tipoSelecao) ){
			namedQuery = "AlunoEntity.recuperaAlunosMatriculadosDaInstituicaoFiltroTodas";
		}
		else{
			if ( TipoSelecaoMatricula.Ativa.equals(tipoSelecao) ){
				namedQuery = "AlunoEntity.recuperaAlunosMatriculadosDaInstituicaoFiltroAtivas";
				
			 }
			 else{
				 if ( TipoSelecaoMatricula.Encerrada.equals(tipoSelecao) ){
					 namedQuery = "AlunoEntity.recuperaAlunosMatriculadosDaInstituicaoFiltroEncerradas";	 	 
				 }
			 }
		}
		
		Query q = this.getEm().createNamedQuery(namedQuery);
		q.setParameter("anoAdm",anoAdm);
		q.setParameter("tipoMatricula", tipoMatricula);
		q.setParameter("idInstituicao",idInstituicao);
		
		return q.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Metodo que pesquisa se ja existe uma matricula ativa para o aluno no mesmo, tipo de ensino, serie, e turno.
	 * Caso exista ja uma matricula ativa ele devolve o objeto matricula, senão devolve null.
	 * 
	 * @author hsoares
	 * @param idAluno
	 * @param idTipoEnsino
	 * @param idTipoSerie
	 * @param idTipoTurno
	 * @return MatriculaEntity
	 */
	public MatriculaEntity validaCadastroMatricula(Long idAluno,Long idTipoEnsino,Long idTipoSerie,Long idTipoTurno, Long anoAdm){
		
		Query q = this.getEm().createNamedQuery("MatriculaEntity.validaInsercaoDeMatricula");
		q.setParameter("idAluno", idAluno);
		q.setParameter("idTipoEnsino", idTipoEnsino);
		q.setParameter("idTipoSerie", idTipoSerie);
		q.setParameter("idTipoTurno", idTipoTurno);
		q.setParameter("anoAdm", anoAdm);
		try {
			return (MatriculaEntity) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	
	/**
	 * insere Matricula do Aluno
	 * 
	 * @param alunosNaoMatriculados
	 * @param idInstituicao
	 * @param anoAdm
	 * @param dependencias
	 * @param dtEncerramento
	 * @param dtMatricula
	 * @param loginAud
	 * @param novatoEstudos
	 * @param tipoCondicaoFinalUltAno
	 * @param tipoMatricula
	 * @param tipoEnsino
	 * @param tipoSerie
	 * @param tipoTurno
	 * @throws MatriculaException 
	 */
	public Integer insereMatricula(List<ResultadoPesquisaAlunoDTO> alunosNaoMatriculados, Long idInstituicao,
								 Long anoAdm, List<DisciplinaEntity> disciplinasDependente,Date dtEncerramento,
								 Date dtMatricula, String loginAud, Boolean novatoEstudos, 
								 TipoCondicaoFinalUltAno tipoCondicaoFinalUltAno, TipoMatricula tipoMatricula,
								 EnsinoEntity ensino, SerieEntity serie, TurnoEntity turno) throws MatriculaException{
		
		InstituicaoEntity ie = this.getEm().find(InstituicaoEntity.class, idInstituicao);
		
		Integer affectedRows = 0;
		
		List<MatriculaEntity> matriculasAtivas = new ArrayList<MatriculaEntity>();
		
		for (ResultadoPesquisaAlunoDTO a : alunosNaoMatriculados){
			
			if (a.getSelecionado()){

				MatriculaEntity matriculaAtiva = validaCadastroMatricula( a.getIdAluno(),
																		  ensino.getId(),
																		  serie.getId(),
																		  turno.getId(),
																		  anoAdm );
				
				if (matriculaAtiva ==null ){
						AlunoEntity aluno = this.getEm().find(AlunoEntity.class, a.getIdAluno());
						MatriculaEntity m = new MatriculaEntity();
						m.setAluno(aluno);
						m.setInstituicao(ie);
						m.setAnoAdm(anoAdm);
						m.setDtMatricula(dtMatricula);
						
						if (disciplinasDependente!=null){
							List<MatriculaDisciplinaEntity> mds = new ArrayList<MatriculaDisciplinaEntity>(disciplinasDependente.size());
							
							for (DisciplinaEntity d: disciplinasDependente){
								MatriculaDisciplinaEntity md = new MatriculaDisciplinaEntity(d,m);
								mds.add(md);
							}
							m.setMatriculaDependencia(mds);
						}
						
						m.setNovatoEstudos(novatoEstudos);
						m.setTipoCondicaoFinalUltAno(tipoCondicaoFinalUltAno);
						m.setTipoMatricula(tipoMatricula);
						m.setEnsino(ensino);
						m.setSerie(serie);
						m.setTurno(turno);
						persist(m);
						affectedRows++;
				}
				else{
					matriculasAtivas.add(matriculaAtiva);
				}
			}
		}
		 
		if (matriculasAtivas.size()>0){
			throw new MatriculaException(" Aluno com Matricula já ativa para o mesmo Ano, Tipo Ensino, Série e Turno",matriculasAtivas);
		}
		
		return affectedRows;
	}
	
	
	
	
	
	/**
	 * Obtem os tipos de encerramento de matricula da instituicao de ensino.
	 * 
	 * @param idInstituicao
	 * @return List<TipoEncerramentoMatriculaEntity>
	 */
	public List<TipoEncerramentoMatriculaEntity> recuperaTiposEncerramentoMatricula(Long idInstituicao){
		Query q = getEm().createNamedQuery("TipoEncerramentoMatriculaEntity.recuperaTipoEncerramentoMatriculaPorInstituicao");
		q.setParameter("idinstituicao",idInstituicao);
		return q.getResultList();
	}
	
	
	
	
	
	
	public List<DisciplinaEntity> recuperaDisciplinasByMatriculaDependencia(Long idMatriculaDependencia){
		Query q = this.getEm().createNamedQuery("MatriculaEntity.recuperaDisciplinasMatriculaDependencia");
		q.setParameter("idMatricula", idMatriculaDependencia);
		return q.getResultList();
	}
	
	
	

	
}
