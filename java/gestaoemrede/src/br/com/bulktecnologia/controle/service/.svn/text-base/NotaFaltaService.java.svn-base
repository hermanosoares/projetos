package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.remoting.WebRemote;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.comuns.util.TurmaHelper;
import br.com.bulktecnologia.controle.list.AlocacaoList;
import br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO;
import br.com.bulktecnologia.modelo.dto.ResultadoMatriculaDisciplinaDTO;
import br.com.bulktecnologia.modelo.entidade.AlocacaoItemEntity;
import br.com.bulktecnologia.modelo.entidade.AulaDadaEntity;
import br.com.bulktecnologia.modelo.entidade.Conceito;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.EtapaEntity;
import br.com.bulktecnologia.modelo.entidade.EtapaRecuperacaoEntity;
import br.com.bulktecnologia.modelo.entidade.Lancamento;
import br.com.bulktecnologia.modelo.entidade.LancamentoRecEntity;
import br.com.bulktecnologia.modelo.enums.TipoAvaliacao;
import br.com.bulktecnologia.modelo.enums.TipoComputaCargaHoraria;
import br.com.bulktecnologia.modelo.enums.TipoDisciplina;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
@Name("NotaFaltaService")
@Scope(ScopeType.CONVERSATION)
public class NotaFaltaService implements Serializable {

	
	private EtapaEntity etapaSelecionada;
	private DisciplinaEntity disciplinaSelecionada;
	private EtapaRecuperacaoEntity etapaRecuperacaoSelecionada;
	
	@In(required=false)
	private AlocacaoList AlocacaoList;
	
	@In(required=false)
	@Out(required=false)
	private AulaDadaEntity aulaDadaEntity;
	
	@In
	private EntityManager entityManager;
	
	private List<Lancamento> lancamentos;
	
	private List<LancamentoRecEntity> lancamentosRecuperacao;
	
	@In
	private FacesMessages facesMessages;
	
	private Boolean lancamentosModificados = Boolean.FALSE;
	
	private List<Conceito> conceitos;
	


	
	public List<DisciplinaEntity> getDisciplinas(){
		
	 if ( TurmaHelper.isTurmaPorDisciplina(this.AlocacaoList.getTipoFormacaoTurmaSelecionado()) ){
			 if ( this.AlocacaoList.getTurmaSelecionado()!=null ){
				 return this.AlocacaoList.getTurmaSelecionado().getDisciplinas();
			 }
			 else{
				 return null;
			 }
		 }
		 else{
			 return this.AlocacaoList.getSerieSelecionado().getDisciplinas();
		 }
	}


	public void cleanupCampo6e7e8(){
		AlocacaoList.carregaDadosFiltro7Alocacao();
		cleanupCampo7e8();
		cleanupData();
	}
	
	public void cleanupCampo7e8(){
		this.disciplinaSelecionada = null;
		this.etapaSelecionada = null;
		this.etapaRecuperacaoSelecionada = null;
		cleanupData();
	}
	
	private void cleanupData(){
		this.lancamentos = null;
		this.lancamentosRecuperacao = null;
	}
	
	public void cleanupCampo8(){
		this.disciplinaSelecionada = null;
		cleanupData();
	}

	public EtapaEntity getEtapaSelecionada() {
		return etapaSelecionada;
	}

	public void setEtapaSelecionada(EtapaEntity etapaSelecionada) {
		this.etapaSelecionada = etapaSelecionada;
	}


	public DisciplinaEntity getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}


	public void setDisciplinaSelecionada(DisciplinaEntity disciplinaSelecionada) {
		
		if ( TurmaHelper.isTurmaPorDisciplina(this.AlocacaoList.getTipoFormacaoTurmaSelecionado()) ){
			this.AlocacaoList.setDisciplinaSelecionado(disciplinaSelecionada);
		}
		else{
			this.AlocacaoList.setDisciplinaSelecionado(null);
		}
		
		this.disciplinaSelecionada = disciplinaSelecionada;
		
	}
	
	private void carregaConceitos(){
		if (  this.disciplinaSelecionada!=null && TipoAvaliacao.Conceito.compareTo(this.disciplinaSelecionada.getTipoAvaliacao())==0 ){
			  Query q = this.entityManager.createQuery("select c from Conceito c where c.configuracao.id = :idConfiguracao");
			  CookieSessaoUsuario cookie = (CookieSessaoUsuario) Component.getInstance("cookie");
			  q.setParameter("idConfiguracao", cookie.getConfigEspecificaInstituicao().getId());
			  this.conceitos = q.getResultList();
		}		
	}

	
	
	
	public void processaAlunosAlocadosPassiveisALancamento(){
		
		if (this.disciplinaSelecionada!=null){
			
			if (this.etapaRecuperacaoSelecionada==null){
				carregaAulasDadas();	
			}
			
			carregaConceitos();
			
			AlocacaoList.carregaGridAlunosAlocados();
			
			List<ResultadoAlocacaoDTO> alocados = AlocacaoList.getAlunosAlocados();
			
			if ( alocados != null && alocados.size() > 0 ){
				
				trataMatriculasDependenciaComAlocacaoRegular(alocados);
				
				if ( this.etapaRecuperacaoSelecionada != null ){
					this.lancamentosRecuperacao = trataLancamentosRecuperacao(alocados);	
				}
				else{
					this.lancamentos = trataLancamentos(alocados);
				}
				
			}
		}
		
	}
	
	
	private void carregaAulasDadas(){
	   Query q = this.entityManager.createQuery("select ad from AulaDadaEntity ad where ad.etapa.id = :idEtapa and ad.disciplina.id = :idDisciplina");
	   q.setParameter("idEtapa", this.etapaSelecionada.getId());
	   q.setParameter("idDisciplina", this.disciplinaSelecionada.getId());
	   
	   try {
		this.aulaDadaEntity = (AulaDadaEntity) q.getSingleResult();
		} catch (NoResultException e) {
			this.aulaDadaEntity = null;
		}
	}
	
	
	

	private void trataMatriculasDependenciaComAlocacaoRegular(List<ResultadoAlocacaoDTO> alocados) {
		
		if ( TipoMatricula.Dependencia.equals(this.AlocacaoList.getTipoMatriculaSelecionada()) ){
			
			List<Long> idsMatriculas = new ArrayList<Long>();
			
			for (ResultadoAlocacaoDTO dto : alocados){
				idsMatriculas.add(dto.getIdMatricula());
			}
			
			Query q = this.entityManager.createNamedQuery("MatriculaDisciplinaEntity.recuperaIdsDisciplinasDosIdsMatriculas");
			q.setParameter("idsMatriculas", idsMatriculas);
			List<ResultadoMatriculaDisciplinaDTO> matriculados = q.getResultList();

			Iterator<ResultadoAlocacaoDTO> it = alocados.iterator();
			while (it.hasNext()){
				ResultadoAlocacaoDTO dto = it.next();
				for (ResultadoMatriculaDisciplinaDTO m : matriculados){
					if ( m.getIdMatricula().compareTo(dto.getIdMatricula())==0 ){
						if ( m.getIdDisciplina().compareTo(this.disciplinaSelecionada.getId())!=0 ){
							it.remove();
						}
					}
				}
			}
		}

	}
	
	
	private List<Lancamento> trataLancamentos(List<ResultadoAlocacaoDTO> alocados){
		
		List<Long> idsAlocacaoItem = new ArrayList<Long>();
		for (ResultadoAlocacaoDTO dto : alocados){
			idsAlocacaoItem.add(dto.getIdAlocacaoItem());
		}
		
		Query q = this.entityManager.createNamedQuery("Lancamento.recuperaLancamentos");
		q.setParameter("idsAlocacaoItem", idsAlocacaoItem);
		q.setParameter("idDisciplina", this.disciplinaSelecionada.getId());
		q.setParameter("idEtapa", this.etapaSelecionada.getId());
		
		List<Lancamento> lancamentos = q.getResultList();
		
		List<Lancamento> resultado = new ArrayList<Lancamento>();
		
		for (ResultadoAlocacaoDTO aloc: alocados){
			
				Lancamento lanc = this.recuperaLancamento(aloc.getIdAlocacaoItem(), lancamentos);
				lanc.setAlocacaoDto(aloc);
				
				if (lanc.getId()==null){
					lanc.setEtapa(this.etapaSelecionada);
					lanc.setDisciplina(this.disciplinaSelecionada);
				}
				
				resultado.add(lanc);
		}
		
		
		return resultado;
	}
	
	
	private List<LancamentoRecEntity> trataLancamentosRecuperacao(List<ResultadoAlocacaoDTO> alocados){
		
		List<Long> idsAlocacaoItem = new ArrayList<Long>();
		for (ResultadoAlocacaoDTO dto : alocados){
			idsAlocacaoItem.add(dto.getIdAlocacaoItem());
		}
		
		Query q = this.entityManager.createNamedQuery("LancamentoRecEntity.recuperaLancamentosRec");
		q.setParameter("idsAlocacaoItem", idsAlocacaoItem);
		q.setParameter("idDisciplina", this.disciplinaSelecionada.getId());
		q.setParameter("idEtapaRec", this.etapaRecuperacaoSelecionada.getId());
		
		List<LancamentoRecEntity> lancamentos = q.getResultList();
		
		List<LancamentoRecEntity> resultado = new ArrayList<LancamentoRecEntity>();
		
		for (ResultadoAlocacaoDTO aloc: alocados){
			
				LancamentoRecEntity lanc = this.recuperaLancamentoRecuperacao(aloc.getIdAlocacaoItem(), lancamentos);
				lanc.setAlocacaoDto(aloc);
				
				if (lanc.getId()==null){
					lanc.setEtapaRecuperacao(this.etapaRecuperacaoSelecionada);
					lanc.setDisciplina(this.disciplinaSelecionada);
				}
				
				resultado.add(lanc);
		}
		
		
		return resultado;
	}
	
	

	private Lancamento recuperaLancamento(Long idAlocacaoItem, List<Lancamento> lancamentos){
		
		for (Lancamento l: lancamentos){
			
			if (l.getAlocacaoItem().getId().compareTo(idAlocacaoItem)==0){
				this.entityManager.refresh(l);
				return l;
			}
			
		}
		
		return new Lancamento();
	}

	
	private LancamentoRecEntity recuperaLancamentoRecuperacao(Long idAlocacaoItem, List<LancamentoRecEntity> lancamentos){
		
		for (LancamentoRecEntity l: lancamentos){
			
			if (l.getAlocacaoItem().getId().compareTo(idAlocacaoItem)==0){
				this.entityManager.refresh(l);
				return l;
			}
			
		}
		
		return new LancamentoRecEntity();
	}


	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	

	public String gravaRecuperacao(){
		
		Iterator<LancamentoRecEntity> it = this.lancamentosRecuperacao.iterator();
		
		while (it.hasNext()){
			
			LancamentoRecEntity lanc = it.next();
			
			if ( lanc.getId()==null ){
				if ( lanc.getNotaRecuperacao()!=null || lanc.getFaltaRecuperacao()!=null || lanc.getConceitoRecuperacao()!=null){
					
					if (lanc.getNotaRecuperacao()!=null){
						if ( !validoLancamentoNotaRecuperacao(lanc) ){
							facesMessages.add("Erro de Lançamento de Recuperação! ");
							for (EtapaEntity e: lanc.getEtapaRecuperacao().getEtapas()){
								facesMessages.add("Etapa "+e.getNome() + " Nota Máxima: "+e.getNotaMaximaEtapa());	
							}
							facesMessages.add("A Nota de "+lanc.getAlocacaoDto().getNome()+" é superior a somatória do máximo das etapas  que são de "+obtemNotaMaximaEtapaRecuperacao(lanc)+" pontos.");
						}
					}
					
					AlocacaoItemEntity alocItem = this.entityManager.find(AlocacaoItemEntity.class,lanc.getAlocacaoDto().getIdAlocacaoItem());
					lanc.setAlocacaoItem(alocItem);
					this.entityManager.persist(lanc);
				}
			}
			else{
				if ( lanc.getNotaRecuperacao()==null && 
					 lanc.getFaltaRecuperacao()==null && 
					 lanc.getConceitoRecuperacao()==null ){
					this.entityManager.remove(lanc);
					lanc.setId(null);
				}
				else{
					
					if ( validoLancamentoNotaRecuperacao(lanc) ){
						this.entityManager.merge(lanc);
					}
					else{
						facesMessages.add("Erro de Lançamento de Recuperação! ");
						for (EtapaEntity e: lanc.getEtapaRecuperacao().getEtapas()){
							facesMessages.add("Etapa "+e.getNome() + " Nota Máxima: "+e.getNotaMaximaEtapa());	
						}
						facesMessages.add("A Nota de "+lanc.getAlocacaoDto().getNome()+" é superior a somatória do máximo das etapas  que são de "+obtemNotaMaximaEtapaRecuperacao(lanc)+" pontos.");
						return "MesmaPagina";
					}
				}
			}
			
		}
		
		
		this.facesMessages.add("Lançamentos de Recuperação Salvos com sucesso! Diário "+this.disciplinaSelecionada.getNome()+" Etapa Recuperaçãoo: "+this.etapaRecuperacaoSelecionada.getNome());
		this.entityManager.flush();
		
		this.lancamentosModificados = Boolean.FALSE;
		
		return "MesmaPagina";
	}
	
	
	
	
	public String grava(){
		
		Iterator<Lancamento> it = lancamentos.iterator();
		
		while (it.hasNext()){
			
			Lancamento lanc = it.next();
			
			if ( lanc.getId()==null ){
				if ( lanc.getNota()!=null || lanc.getFalta()!=null || lanc.getConceito()!=null){
					
					if (lanc.getNota()!=null){
						if ( !validoLancamentoNota(lanc) ){
							facesMessages.add("Erro de Lançamento! ");
							facesMessages.add("A Nota de "+lanc.getAlocacaoDto().getNome()+" é superior a nota máxima do "+this.etapaSelecionada.getNome()+" que são de "+this.etapaSelecionada.getNotaMaximaEtapa()+" pontos.");
							return "MesmaPagina";
						}
					}
					
					AlocacaoItemEntity alocItem = this.entityManager.find(AlocacaoItemEntity.class,lanc.getAlocacaoDto().getIdAlocacaoItem());
					lanc.setAlocacaoItem(alocItem);
					this.entityManager.persist(lanc);
				}
			}
			else{
				if ( lanc.getNota()==null && 
					 lanc.getFalta()==null && 
					 lanc.getConceito()==null ){
					this.entityManager.remove(lanc);
					lanc.setId(null);
				}
				else{
					
					if ( validoLancamentoNota(lanc) ){
						this.entityManager.merge(lanc);
					}
					else{
						facesMessages.add("Erro de Lançamento! ");
						facesMessages.add("A Nota de "+lanc.getAlocacaoDto().getNome()+" é superior a nota máxima do "+this.etapaSelecionada.getNome()+" que são de "+this.etapaSelecionada.getNotaMaximaEtapa()+" pontos.");
						return "MesmaPagina";
					}
				}
			}
			
		}
		
		
		if (this.aulaDadaEntity!=null){
			
				if (this.aulaDadaEntity.getId()==null){
					this.aulaDadaEntity.setEtapa(this.etapaSelecionada);
					this.aulaDadaEntity.setDisciplina(this.disciplinaSelecionada);
					this.entityManager.persist(this.aulaDadaEntity);
				}
				else{
					this.entityManager.merge(this.aulaDadaEntity);
				}
				
		}
		
		this.facesMessages.add("Lançamentos Salvos com sucesso! Diário "+this.disciplinaSelecionada.getNome()+" Etapa "+this.etapaSelecionada.getNome());
		this.entityManager.flush();
		
		this.lancamentosModificados = Boolean.FALSE;
		
		return "MesmaPagina";
	}
	

	public Boolean getRenderizaColunaNota(){
		
		if ( TipoDisciplina.TODAS.compareTo(this.disciplinaSelecionada.getTipoDisciplina())==0 ){
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	
	public Boolean getRenderizaColunaFalta(){
		TipoComputaCargaHoraria tc = this.disciplinaSelecionada.getCompCargHoraria();
		
		//se não for a disciplina 'Todas'
		if ( TipoDisciplina.TODAS.compareTo(this.disciplinaSelecionada.getTipoDisciplina())!=0 ){
			
			//se disciplina não computa carga horaria ou computa por dia não renderiza.
			if (TipoComputaCargaHoraria.NÃO_COMPUTA.compareTo(tc)==0 || TipoComputaCargaHoraria.POR_DIA.compareTo(tc)==0 ){
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}
	
	
	
	public void validaNota(Lancamento lancamento){
		
		this.notificaModificacao();	
		
		if ( !validoLancamentoNota(lancamento) ){
			facesMessages.add("Erro de Lançamento! ");
			facesMessages.add("A Nota de "+lancamento.getAlocacaoDto().getNome()+" é superior a nota máxima do "+this.etapaSelecionada.getNome()+" que são de "+this.etapaSelecionada.getNotaMaximaEtapa()+" pontos.");
		}
	}

	public void validaNotaRecuperacao(LancamentoRecEntity lancamentoRec){
		
		this.notificaModificacao();	
		
		if ( !validoLancamentoNotaRecuperacao(lancamentoRec) ){
			facesMessages.add("Erro de Lançamento de Recuperação! ");
			for (EtapaEntity e: lancamentoRec.getEtapaRecuperacao().getEtapas()){
				facesMessages.add("Etapa "+e.getNome() + " Nota Máxima: "+e.getNotaMaximaEtapa());	
			}
			facesMessages.add("A Nota de "+lancamentoRec.getAlocacaoDto().getNome()+" é superior a somatória do máximo das etapas  que são de "+obtemNotaMaximaEtapaRecuperacao(lancamentoRec)+" pontos.");
			
		}
	}

	private Boolean validoLancamentoNota(Lancamento lancamento){
		return lancamento.getNota().compareTo(this.etapaSelecionada.getNotaMaximaEtapa())<=0;
	}
	

	private Boolean validoLancamentoNotaRecuperacao(LancamentoRecEntity lancamentoRec){
		Double maxTotal = obtemNotaMaximaEtapaRecuperacao(lancamentoRec);
		return lancamentoRec.getNotaRecuperacao().compareTo(maxTotal)<=0;
	}

	
	private Double obtemNotaMaximaEtapaRecuperacao(LancamentoRecEntity lancamentoRec){
		Double maxTotal = 0D;
		for (EtapaEntity e: lancamentoRec.getEtapaRecuperacao().getEtapas()){
			 maxTotal += e.getNotaMaximaEtapa();
		}
		return maxTotal;
	}
	
	
	

	public Boolean getDisciplinaUsaConceito(){
		return TipoAvaliacao.Conceito.compareTo(this.disciplinaSelecionada.getTipoAvaliacao())==0;
	}


	public Boolean getLancamentosModificados() {
		return lancamentosModificados;
	}

	
	public void notificaModificacao(){
		this.lancamentosModificados = Boolean.TRUE;
	}

	
	@WebRemote
	public String existeModificacao(){
		if (this.lancamentosModificados){
			return "sim";
		}
		else{
			return "nao";
		}
	}
	
	
	
	@WebRemote
	public void sairSemSalvar(String valor){
		if ("sim".equals(valor)){
			this.lancamentosModificados = Boolean.FALSE;
		}
	}


	public List<Conceito> getConceitos() {
		return conceitos;
	}

	public EtapaRecuperacaoEntity getEtapaRecuperacaoSelecionada() {
		return etapaRecuperacaoSelecionada;
	}

	public void setEtapaRecuperacaoSelecionada(
			EtapaRecuperacaoEntity etapaRecuperacaoSelecionada) {
		this.etapaRecuperacaoSelecionada = etapaRecuperacaoSelecionada;
	}

	public List<LancamentoRecEntity> getLancamentosRecuperacao() {
		return lancamentosRecuperacao;
	}
	
}
