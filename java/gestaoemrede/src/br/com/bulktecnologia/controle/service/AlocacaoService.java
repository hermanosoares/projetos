package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.testng.log4testng.Logger;

import br.com.bulktecnologia.comuns.converter.FacesMessageSessionScoped;
import br.com.bulktecnologia.comuns.util.TurmaHelper;
import br.com.bulktecnologia.controle.exception.BusinessException;
import br.com.bulktecnologia.controle.list.AlocacaoList;
import br.com.bulktecnologia.modelo.dao.AlocacaoDAO;
import br.com.bulktecnologia.modelo.dao.RemanejamentoDAO;
import br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO;
import br.com.bulktecnologia.modelo.entidade.AlocacaoEntity;
import br.com.bulktecnologia.modelo.entidade.AlocacaoItemEntity;
import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.MatriculaDisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.MatriculaEntity;
import br.com.bulktecnologia.modelo.entidade.RemanejamentoEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.entidade.TipoEncerramentoMatriculaEntity;
import br.com.bulktecnologia.modelo.entidade.TurmaEntity;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;
import br.com.bulktecnologia.modelo.enums.TipoEnsino;
import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoSerie;
@Name("AlocacaoService")
@Scope(ScopeType.CONVERSATION)
public class AlocacaoService extends GenericService implements Serializable {

	
	@In
	@Out(required=false)
	private AlocacaoList AlocacaoList;

	@In
	private FacesMessageSessionScoped FacesMessageSessionScoped;
	
	@In
	private AlocacaoDAO AlocacaoDAO;

	
	
	
	
	private Logger log = Logger.getLogger(this.getClass());

	private Boolean operacaoRemanejamento = Boolean.FALSE;
	
	private TurnoEntity turnoDestinoRemanejamento;
	
	private TurmaEntity turmaDestinoRemanejamento;
	
	private List<TurmaEntity> turmasDisponiveisParaRemanejamento;
	
	private EnsinoEntity ensinoDestinoRemanejamento;
	
	private SerieEntity serieDestinoRemanejamento;
	
	
	public void validaSelecaoTurnoRemanejamento(){
		TipoEnsino te = this.AlocacaoList.getEnsinoSelecionado().getTipoEnsino();
		TipoSerie ts = this.AlocacaoList.getSerieSelecionado().getTipoSerie();
		
		Query q1 = this.AlocacaoDAO.getEm().createQuery("select e from EnsinoEntity e where e.turno.id = :idTurno and e.tipoEnsino = :tipoEnsino");
		q1.setParameter("idTurno", this.turnoDestinoRemanejamento.getId());
		q1.setParameter("tipoEnsino", te);
		try {
			this.ensinoDestinoRemanejamento = (EnsinoEntity) q1.getSingleResult();
		} catch (NoResultException e) {
			facesMessages.add("não há na configuração Tipo de Ensino "+this.AlocacaoList.getEnsinoSelecionado().getNome()+" no turno "+this.turnoDestinoRemanejamento.getNome());
			return;
		}
		
		Query q2 = this.AlocacaoDAO.getEm().createQuery("select s from SerieEntity s where s.ensino.id = :idEnsino and s.tipoSerie = :tipoSerie");
		q2.setParameter("idEnsino", this.ensinoDestinoRemanejamento.getId());
		q2.setParameter("tipoSerie", ts);
		try {
			this.serieDestinoRemanejamento = (SerieEntity) q2.getSingleResult();
		} catch (NoResultException e) {
			facesMessages.add("não há na configuração Série "+this.AlocacaoList.getSerieSelecionado().getNome()+" para o ensino "+this.ensinoDestinoRemanejamento.getNome()+" no turno "+this.turnoDestinoRemanejamento.getNome());
			return;
		}
		
		carregaTurmasRemanejamento();
	}
	
	
	
	
	public void swapRemanejamento(){
		this.operacaoRemanejamento = !(this.operacaoRemanejamento);
	}
	
	
	
	private void trataNaoPermitidos(List<ResultadoAlocacaoDTO> selecionados,List<ResultadoAlocacaoDTO> naoPermitidos) throws BusinessException{
		if (selecionados!=null && naoPermitidos!=null && selecionados.size()>0 && naoPermitidos.size()>0){
			Iterator<ResultadoAlocacaoDTO> it = selecionados.iterator();
			while (it.hasNext()){
				ResultadoAlocacaoDTO item = it.next();
				for (ResultadoAlocacaoDTO np: naoPermitidos){
					if (item.getIdAluno().compareTo(np.getIdAluno())==0){
						facesMessages.add(np.getNome()+" não alocado. Existe alocação ativa "+np.getTipoFormacaoTurma().toString()+" no Ano,Turno,Ensino e Série selecionado.");
						throw new BusinessException();
					}
				}
			}
		}
	}
	
	
	
	public void alocar(){
		
		AlocacaoEntity alocacaoEntity = null;
		
		List<ResultadoAlocacaoDTO> selecionados = this.AlocacaoList.getAlunosNaoAlocadosSelecionados();
		
		List<Long> idsAlunosSelecionados = new ArrayList<Long>(selecionados.size());
		
		for (ResultadoAlocacaoDTO selecionado: selecionados){
			idsAlunosSelecionados.add(selecionado.getIdAluno());
		}
		TipoFormacaoTurma tipo1 = null;
		TipoFormacaoTurma tipo2 = null;
		
		if (TurmaHelper.isTurmaPorDisciplina(this.AlocacaoList.getTipoFormacaoTurmaSelecionado())){
			tipo1 = TipoFormacaoTurma.Por_Disciplina;
			tipo2 = TipoFormacaoTurma.Multi_Seriada_E_Por_Disciplina;
		}
		else{
			tipo1 = TipoFormacaoTurma.Regular;
			tipo2 = TipoFormacaoTurma.Multi_Seriada;
		}
		
		List<ResultadoAlocacaoDTO> naopermitidos =		this.AlocacaoDAO.recuperaStatusDeAlocacoesByIdsAlunos(this.AlocacaoList.getAnoSelecionado(),
																												  this.AlocacaoList.getEnsinoSelecionado().getId(),
																												  this.AlocacaoList.getSerieSelecionado().getId(),
																												  this.AlocacaoList.getTurnoSelecionado().getId(),
																												  idsAlunosSelecionados,
																												  tipo1,
																												  tipo2);
		
		try {
			this.trataNaoPermitidos(selecionados,naopermitidos);
		} catch (BusinessException e) {
			return;
		}
		
		if (!(selecionados.size()>0)){
			return;
		}
		
		AlocacaoEntity aloc =	this.AlocacaoDAO.recuperaAlocacaoExistente(this.AlocacaoList.getAnoSelecionado(),
												   this.AlocacaoList.getTipoMatriculaSelecionada(),
												   this.AlocacaoList.getTurnoSelecionado().getId(),
												   this.AlocacaoList.getEnsinoSelecionado().getId(),
												   this.AlocacaoList.getSerieSelecionado().getId());
	
		if (aloc!=null){
			alocacaoEntity = aloc;
		}
	
		if (alocacaoEntity==null){
			alocacaoEntity = new AlocacaoEntity();
		}
		
		
		if (selecionados.size()>0){
			
			List<AlocacaoItemEntity> items = new ArrayList<AlocacaoItemEntity>(selecionados.size());
			
			alocacaoEntity.setAnoAdm(this.AlocacaoList.getAnoSelecionado());
			alocacaoEntity.setDtAlocacao(new Date());
			alocacaoEntity.setEnsino(this.AlocacaoList.getEnsinoSelecionado());
			alocacaoEntity.setSerie(this.AlocacaoList.getSerieSelecionado());
			alocacaoEntity.setTipoMatricula(this.AlocacaoList.getTipoMatriculaSelecionada());
			alocacaoEntity.setTurno(this.AlocacaoList.getTurnoSelecionado());
			
			
			for (ResultadoAlocacaoDTO a: selecionados){
				AlunoEntity aluno = (AlunoEntity) this.AlocacaoDAO.find(AlunoEntity.class, a.getIdAluno());
				
				if (aluno!=null){
					AlocacaoItemEntity item = new AlocacaoItemEntity();
					
					item.setAlocacao(alocacaoEntity);
					item.setAluno(aluno);
					item.setTurma(this.AlocacaoList.getTurmaSelecionado());
					item.setDiarioAberto(this.AlocacaoList.getDiarioAberto());
					item.setDiarioAbertoMomentoAlocacao(this.AlocacaoList.getDiarioAberto());
					MatriculaEntity m = (MatriculaEntity) this.AlocacaoDAO.find(MatriculaEntity.class, a.getIdMatricula());
					item.setMatricula(m);
					
					if (this.AlocacaoList.getDisciplinaSelecionado()!=null){
						
						List<DisciplinaEntity> disciplinas = new ArrayList<DisciplinaEntity>(1);
						disciplinas.add(this.AlocacaoList.getDisciplinaSelecionado());
						
						item.setDisciplinas(disciplinas);
					}
					
					items.add(item);
				}
			
				//sinaliza na matricula que houve alocacao para esta matricula. 
				if (  TipoMatricula.Regular.equals(this.AlocacaoList.getTipoMatriculaSelecionada()) ){
					
					if ( TurmaHelper.isTurmaPorSerie(this.AlocacaoList.getTipoFormacaoTurmaSelecionado()) ){
						
						MatriculaEntity mat = (MatriculaEntity) this.AlocacaoDAO.find(MatriculaEntity.class, a.getIdMatricula());
						if (mat!=null){
							mat.setAlocacaoIncompleta(Boolean.FALSE);
						}
					}
					else{
						
						if ( TurmaHelper.isTurmaPorDisciplina(this.AlocacaoList.getTipoFormacaoTurmaSelecionado()) ){
							
							List<DisciplinaEntity>	dRestante = this.AlocacaoDAO.recuperaDisciplinasRestantesParaCompletarAMetaDaMatricula(
																							this.AlocacaoList.getAnoSelecionado(),
																							this.AlocacaoList.getTurnoSelecionado().getId(),
																							this.AlocacaoList.getEnsinoSelecionado().getId(),
																							this.AlocacaoList.getSerieSelecionado().getId(),
																							this.AlocacaoList.getTipoMatriculaSelecionada(),
																							a.getIdAluno());
							dRestante.remove(this.AlocacaoList.getDisciplinaSelecionado());
							
							if (dRestante.size()>0){
								
								MatriculaEntity mat = (MatriculaEntity) this.AlocacaoDAO.find(MatriculaEntity.class, a.getIdMatricula());
								if (mat!=null){
									mat.setAlocacaoIncompleta(Boolean.TRUE);
								}
								
							}
							else{
								
								MatriculaEntity mat = (MatriculaEntity) this.AlocacaoDAO.find(MatriculaEntity.class, a.getIdMatricula());
								if (mat!=null){
									mat.setAlocacaoIncompleta(Boolean.FALSE);
								}
								
							}
							
						}
						
					}
					
				}
				else{
					if (TurmaHelper.isTurmaPorDisciplina(this.AlocacaoList.getTipoFormacaoTurmaSelecionado())){
						
						Integer affectedRows =		this.AlocacaoDAO.atualizaAlocacaoDisciplinaDeMatriculaDependencia(a.getIdMatricula(),
								 this.AlocacaoList.getDisciplinaSelecionado().getId(),
								 Boolean.TRUE);
					}
					else{
						if (TurmaHelper.isTurmaPorSerie(this.AlocacaoList.getTipoFormacaoTurmaSelecionado())){
							
							Integer affectedRows =		this.AlocacaoDAO.atualizaAlocacaoDisciplinasDeMatriculaDependencia(a.getIdMatricula(),
									 					Boolean.TRUE);
							
						}
						else{
							throw new RuntimeException("erro: tipo formacao de turma nao previsto.");
						}
						
					}
					
					
				}
				
				
			}
			
			alocacaoEntity.setItems(items);
			
			this.AlocacaoDAO.persist(alocacaoEntity);


		}
	
	
		this.AlocacaoDAO.flush();
	}
	
	
	public void fecharDiario(){
		
		List<Long> idsAlocacaoItem = new ArrayList<Long>();
		
		for (ResultadoAlocacaoDTO aloc : this.AlocacaoList.getAlunosAlocados()){
			idsAlocacaoItem.add(aloc.getIdAlocacaoItem());
		}
		
		Integer affectedRows1 = this.AlocacaoDAO.atualizaStatusDiarioAberto(idsAlocacaoItem,Boolean.FALSE);

		this.AlocacaoDAO.flush();
		
	}
	

	public void abrirDiario(){

		List<Long> idsAlocacaoItem = new ArrayList<Long>();
		
		for (ResultadoAlocacaoDTO aloc : this.AlocacaoList.getAlunosAlocados()){
			idsAlocacaoItem.add(aloc.getIdAlocacaoItem());
		}
		
		Integer affectedRows1 = this.AlocacaoDAO.atualizaStatusDiarioAberto(idsAlocacaoItem,Boolean.TRUE);
		
		Integer affectedRows2 = this.AlocacaoDAO.atualizaFlagDiarioAbertoMomentoAlocacao(idsAlocacaoItem,Boolean.TRUE);
		
		this.AlocacaoDAO.flush();
	}
	
	
	
	
	private void remaneja(ResultadoAlocacaoDTO item){

		MatriculaEntity matriculaClone = null;
		
		MatriculaEntity matricula = null;
		
		// ENCERRA MATRICULA E CRIA UMA NOVA MATRICULA 
		try {
				matricula =  (MatriculaEntity)this.AlocacaoDAO.find(MatriculaEntity.class, item.getIdMatricula());
				matriculaClone = (MatriculaEntity) BeanUtils.cloneBean(matricula);
				
				matriculaClone.setTurno(this.turnoDestinoRemanejamento);
				matriculaClone.setEnsino(this.ensinoDestinoRemanejamento);
				matriculaClone.setSerie(this.serieDestinoRemanejamento);
				
				encerramentoMatriculaPorRemanejamento(matricula);
				
				matriculaClone.setId(null);
				
				preencheMatriculaDependenciaClonada(matriculaClone);
				
				
				AlocacaoDAO.merge(matricula);
				AlocacaoDAO.persist(matriculaClone);
				
		} catch (Exception e) {
				log.fatal(e);
		}

		
		AlocacaoEntity alocPai = this.AlocacaoDAO.recuperaAlocacaoExistente( this.AlocacaoList.getAnoSelecionado(),
																		     this.AlocacaoList.getTipoMatriculaSelecionada(),
																		     this.turnoDestinoRemanejamento.getId(),
																		     this.ensinoDestinoRemanejamento.getId(), 
																		     this.serieDestinoRemanejamento.getId());
		//se não existe uma alocacao já existente então cria.
		if ( alocPai==null ){
			 alocPai = new AlocacaoEntity();
			 alocPai.setAnoAdm(this.AlocacaoList.getAnoSelecionado());
			 alocPai.setDtAlocacao(new Date());
			 alocPai.setEnsino(this.ensinoDestinoRemanejamento);
			 alocPai.setSerie( this.serieDestinoRemanejamento);
			 alocPai.setTipoMatricula(this.AlocacaoList.getTipoMatriculaSelecionada());
			 alocPai.setTurno(this.turnoDestinoRemanejamento);
			 AlocacaoDAO.persist(alocPai);
			 AlocacaoDAO.flush();
		}
		
		
		//cria a alocacaoItem.
		try {
			
			AlocacaoItemEntity alocItem = (AlocacaoItemEntity)AlocacaoDAO.find(AlocacaoItemEntity.class, item.getIdAlocacaoItem());
			AlocacaoDAO.refresh(alocItem);
			
			Boolean diarioAberto = this.AlocacaoDAO.recuperaStatusDoDiario(this.AlocacaoList.getAnoSelecionado(),
													this.AlocacaoList.getTipoMatriculaSelecionada(),
													this.turnoDestinoRemanejamento.getId(),
													this.ensinoDestinoRemanejamento.getId(),
													this.serieDestinoRemanejamento.getId(), 
													this.turmaDestinoRemanejamento.getId(), 
													this.AlocacaoList.getDisciplinaSelecionado());
			
			
			if (diarioAberto==null){
				diarioAberto = Boolean.TRUE;
			}
												  
			AlocacaoItemEntity alocItemClone = (AlocacaoItemEntity) BeanUtils.cloneBean(alocItem);
			
			
			alocItemClone.setId(null);
			alocItemClone.setTurma(this.turmaDestinoRemanejamento);
			alocItemClone.setDiarioAberto(diarioAberto);
			alocItemClone.setDiarioAbertoMomentoAlocacao(diarioAberto);
			alocItemClone.setAlocacao(alocPai);
			alocItemClone.setMatricula(matriculaClone);

			
			trataDisciplinasDaAlocacaoItem(alocItemClone);
			
			AlocacaoDAO.merge(alocItem);
			AlocacaoDAO.persist(alocItemClone);
			
			
			AlocacaoDAO.flush();
			
			Integer lancamentosAtualizados = this.AlocacaoDAO.atualizaAlocacaoItemEmLancamentos(alocItem.getId(), alocItemClone.getId());
			Integer lancamentosAtualizadosRecuperacao = this.AlocacaoDAO.atualizaAlocacaoItemEmLancamentosRecuperacao(alocItem.getId(), alocItemClone.getId());
			
			
			RemanejamentoEntity remanejamento = new RemanejamentoEntity();
			remanejamento.setMatriculaOrigem(matricula);
			remanejamento.setMatriculaDestino(matriculaClone);
			remanejamento.setAlocacaoItemOrigem(alocItem);
			remanejamento.setAlocacaoItemDestino(alocItemClone);
			
			AlocacaoDAO.persist(remanejamento);
			
			alocItem.setRemanejamentoDestino(remanejamento);
			AlocacaoDAO.merge(alocItem);
			
		} catch (Exception e) {
			log.fatal(e);
		}
		
		
	}
	
	private void encerramentoMatriculaPorRemanejamento(MatriculaEntity m){
		if (m!=null){

			TipoEncerramentoMatriculaEntity te = AlocacaoDAO.recuperaTipoEncerramentoMatriculaByRemanejamento(cookie.getIdInstituicao());
			m.setDtEncerramento(new Date());
			m.setTipoencerramentoMatricula(te);

		}
	}
	
	private void trataDisciplinasDaAlocacaoItem(AlocacaoItemEntity item){
		if (item!=null){
			if (item.getDisciplinas()!=null){
				if (item.getDisciplinas().size()<1){
					item.setDisciplinas(null);
				}
				else{
					List<DisciplinaEntity> listaDisciplinas = new ArrayList<DisciplinaEntity>();
					for (DisciplinaEntity d: item.getDisciplinas()){
						listaDisciplinas.add(d);
					}
					item.setDisciplinas(listaDisciplinas);
				}
			}
		}
	}
	
	
	
	private void preencheMatriculaDependenciaClonada(MatriculaEntity m){
		if (m!=null){
			if (m.getMatriculaDependencia()!=null){
				if (m.getMatriculaDependencia().size()<1){
					m.setMatriculaDependencia(null);
				}
				else{
					List<MatriculaDisciplinaEntity> listaMd2 = new ArrayList<MatriculaDisciplinaEntity>();
					
					for (MatriculaDisciplinaEntity md: m.getMatriculaDependencia()){

						MatriculaDisciplinaEntity md2 = new MatriculaDisciplinaEntity();
						md2.setAlocado(md.getAlocado());
						md2.setDisciplina(md.getDisciplina());
						md2.setMatricula(m);
						listaMd2.add(md2);
						
					}
					m.setMatriculaDependencia(listaMd2);
				}
			}
		}
	}

	
	public String remanejar(){

		List<ResultadoAlocacaoDTO> aRemanejar = this.AlocacaoList.getAlunosAlocadosSelecionados();
		
		for (ResultadoAlocacaoDTO aloc: aRemanejar){
			remaneja(aloc);
		}
		
		AlocacaoDAO.flush();
		
		FacesMessageSessionScoped.add(aRemanejar.size()+" remanejamento realizado com sucesso! ");
		
		return "sucesso";
		
	}
	
	
	
	
	public void trataDesfazerRemanejamento(List<Long> idsMatriculas){
		
		if (idsMatriculas!=null && idsMatriculas.size()>0){

			RemanejamentoDAO dao = (RemanejamentoDAO) Component.getInstance("RemanejamentoDAO");
			
			List<RemanejamentoEntity> remanejamentos = dao.recuperaRemanejamentosByIdMatricula(idsMatriculas);
			
			if ( remanejamentos.size() > 0 ){
				
				List<Long> idsAlocacaoItemOrigem = new ArrayList<Long>();
				List<Long> idsRemanejamentos = new ArrayList<Long>();
				List<Long> idsAlocacaoItemDestino = new ArrayList<Long>();
				List<Long> idsMatriculaDestino = new ArrayList<Long>();
				List<Long> idsMatriculaOrigem  = new ArrayList<Long>();
				
				for (RemanejamentoEntity r: remanejamentos){
					idsAlocacaoItemOrigem.add(r.getAlocacaoItemOrigem().getId());
					idsRemanejamentos.add(r.getId());
					idsAlocacaoItemDestino.add(r.getAlocacaoItemDestino().getId());
					idsMatriculaDestino.add(r.getMatriculaDestino().getId());
					idsMatriculaOrigem.add(r.getMatriculaOrigem().getId());
					
					Integer affected5 = this.AlocacaoDAO.atualizaAlocacaoItemEmLancamentos(r.getAlocacaoItemDestino().getId(), r.getAlocacaoItemOrigem().getId());
					Integer affected6 = this.AlocacaoDAO.atualizaAlocacaoItemEmLancamentosRecuperacao(r.getAlocacaoItemDestino().getId(), r.getAlocacaoItemOrigem().getId());
					
				}
				
				Integer affected1 = dao.atualizaAlocacaoItemParaNaoRemanejada(idsAlocacaoItemOrigem);
				Integer affected2 = dao.removeRemanejamentos(idsRemanejamentos);
				Integer affected3 = dao.removeAlocacaItemDestino(idsAlocacaoItemDestino);
				Integer affected4 = dao.removeMatriculasRemanejadasEAtualizaMatriculasOrigemParaNaoEncerrada(idsMatriculaDestino,idsMatriculaOrigem);
				
				this.AlocacaoList.limpaTodosFiltros();
			}
		}

	}
	
	
	
	public void removerAlocacao(){
		
		List<Long> idsAlocacaoItem = new ArrayList<Long>();
		List<Long> idsMatricula = new ArrayList<Long>();
		
		for (ResultadoAlocacaoDTO aloc: this.AlocacaoList.getAlunosAlocadosSelecionados()){
			idsAlocacaoItem.add(aloc.getIdAlocacaoItem());
			idsMatricula.add(aloc.getIdMatricula());
		}
		TipoFormacaoTurma tipoformacaoTurma = this.AlocacaoList.getTipoFormacaoTurmaSelecionado();
		DisciplinaEntity disciplina = this.AlocacaoList.getDisciplinaSelecionado();
		TipoMatricula tipoMatricula = this.AlocacaoList.getTipoMatriculaSelecionada();
		
		trataDesfazerRemanejamento(idsMatricula);

		
		if (  TurmaHelper.isTurmaPorSerie(tipoformacaoTurma) ){

			Query q =  AlocacaoDAO.getEm().createNamedQuery("AlocacaoEntity.removeAlocacaoItemsByIds");
			q.setParameter("idsItems", idsAlocacaoItem);

			Integer affectedRows = q.executeUpdate();

			atualizaStatusDeMatriculaAlocada(idsMatricula);
			
		}
		else{
			if ( TurmaHelper.isTurmaPorDisciplina(tipoformacaoTurma) ){

				//remove AlocacaoItem_Disciplina (many_to_many)
				for (Long idAlocacaoItem: idsAlocacaoItem){
					AlocacaoItemEntity  item = (AlocacaoItemEntity)this.AlocacaoDAO.find(AlocacaoItemEntity.class, idAlocacaoItem);
					List<DisciplinaEntity> disciplinas = item.getDisciplinas();

					Iterator<DisciplinaEntity> it = disciplinas.iterator();
					while (it.hasNext()){

						DisciplinaEntity d = it.next();

						if (d.getId().compareTo(disciplina.getId())==0){
							d.getAlocacaoItems().remove(item);
							it.remove();
						}
					}
				}
				
				this.AlocacaoDAO.flush();
				
				Query q =  AlocacaoDAO.getEm().createNamedQuery("AlocacaoEntity.removeAlocacaoItemsByIds");
				q.setParameter("idsItems", idsAlocacaoItem);
				
				try {
					Integer affectedRows = q.executeUpdate();
				} catch (javax.persistence.EntityExistsException e) {
					this.FacesMessageSessionScoped.add(" impossível remover esta alocação, existem dados lançados de nota,falta ou recuperação.");
					this.FacesMessageSessionScoped.add(" (apague os lançamentos de nota/falta, para conseguir desalocar) ");
					Conversation.instance().leave();
					this.AlocacaoList = null;
					return;
				}
				

				atualizaStatusDeMatriculaAlocada(idsMatricula);
			}	
			else{
				throw new RuntimeException(" Tipo de formacao de turma nao previsto ");
			}
		}
				
			if (  TipoMatricula.Dependencia.equals(tipoMatricula) ){
				
				if ( TurmaHelper.isTurmaPorDisciplina(tipoformacaoTurma) ){
					Query q = AlocacaoDAO.getEm().createNamedQuery("AlocacaoEntity.atualizaParaAlocadoFalseEmMatriculaDisciplinaByIdMatriculaByIdDisciplina");
					q.setParameter("idsMatriculas", idsMatricula);
					q.setParameter("idDisciplina", disciplina.getId());
					
					Integer affectedRows3 = q.executeUpdate();
				}
				else{
					Query q = AlocacaoDAO.getEm().createNamedQuery("AlocacaoEntity.atualizaParaAlocadoFalseEmMatriculaDisciplinaByIdMatricula");
					q.setParameter("idsMatriculas", idsMatricula);
					
					Integer affectedRows3 = q.executeUpdate();
				}
			}
		
		
		
		this.AlocacaoDAO.flush();
		
		if (idsAlocacaoItem.size()==1){
			facesMessages.add("removido "+idsAlocacaoItem.size()+" alocação com sucesso");	
		}
		else{
			if (idsAlocacaoItem.size()>1){
				facesMessages.add("removidas "+idsAlocacaoItem.size()+" alocações com sucesso");
			}
			else{
				facesMessages.add("nenhum alocação removida");
			}
		}
		
	}

	
	private void atualizaStatusDeMatriculaAlocada(List<Long> idsMatriculas){
		
		for (Long idMatricula : idsMatriculas){
			Query q1 = this.AlocacaoDAO.getEm().createQuery("select alocItem.id from AlocacaoItemEntity alocItem where alocItem.matricula.id = :idMatricula");
			q1.setParameter("idMatricula", idMatricula);
			List result = q1.getResultList();
			
			Boolean alocIncompleta = null;
			
			if (result.size()>0){
				alocIncompleta = Boolean.TRUE;
			}
			
			Query q2 =  AlocacaoDAO.getEm().createNamedQuery("AlocacaoEntity.atualizaStatusDeAlocadoEmMatricula");
			q2.setParameter("idMatricula", idMatricula);
			q2.setParameter("statusAloc", alocIncompleta);
			q2.executeUpdate();			
		}

	}
	
	
	
	
	
	public void carregaTurmasRemanejamento(){
		
		if (TurmaHelper.isTurmaPorDisciplina(this.AlocacaoList.getTipoFormacaoTurmaSelecionado())){
			this.turmasDisponiveisParaRemanejamento =	this.AlocacaoDAO.
																recuperaTurmasParaRemanejamentoPorDisciplina(  
																this.AlocacaoList.getDisciplinaSelecionado().getId(),
																this.turnoDestinoRemanejamento.getId());
		}
		else{
			this.turmasDisponiveisParaRemanejamento =   this.AlocacaoDAO.
																 recuperaTurmasParaRemanejamentoPorSerie(
																 this.AlocacaoList.getSerieSelecionado().getId(),
																 this.turnoDestinoRemanejamento.getId());
		}
		
		this.turmasDisponiveisParaRemanejamento.remove(this.AlocacaoList.getTurmaSelecionado());
	}

	
	
	public TurnoEntity getTurnoDestinoRemanejamento() {
		return turnoDestinoRemanejamento;
	}


	public void setTurnoDestinoRemanejamento(TurnoEntity turnoDestinoRemanejamento) {
		this.turnoDestinoRemanejamento = turnoDestinoRemanejamento;
	}


	public TurmaEntity getTurmaDestinoRemanejamento() {
		return turmaDestinoRemanejamento;
	}


	public void setTurmaDestinoRemanejamento(TurmaEntity turmaDestinoRemanejamento) {
		this.turmaDestinoRemanejamento = turmaDestinoRemanejamento;
	}


	public Boolean getOperacaoRemanejamento() {
		return operacaoRemanejamento;
	}


	public void setOperacaoRemanejamento(Boolean operacaoRemanejamento) {
		this.operacaoRemanejamento = operacaoRemanejamento;
	}


	public List<TurmaEntity> getTurmasDisponiveisParaRemanejamento() {
		return turmasDisponiveisParaRemanejamento;
	}
	
	
	
}
