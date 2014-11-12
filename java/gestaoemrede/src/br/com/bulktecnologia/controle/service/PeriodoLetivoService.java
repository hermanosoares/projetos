package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.comuns.util.DataUtil;
import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.PeriodoLetivoEntity;
@Name("PeriodoLetivoService")
@Scope(ScopeType.CONVERSATION)
public class PeriodoLetivoService extends BaseCrudService implements	Serializable {

	public PeriodoLetivoService(){
		super(false);
	}
	
	
	@In(required=false)
	@Out(required=false)
	private PeriodoLetivoEntity periodoLetivoEntity;
	
	private EnsinoEntity ensinoSelecionado;
	
	public String edita(Object o){
		ensinoSelecionado = (EnsinoEntity) o;
		return PAGINA_ADD_EDIT;
	}
	
	public String addPeriodoLetivo(){
		
		Boolean valido = Boolean.TRUE;
		
		if ( validaConstraintsAnotadasNasEntidades(new Object[]{periodoLetivoEntity}) ){
			
			if (this.ensinoSelecionado.getPeriodosLetivos()==null){
				this.ensinoSelecionado.setPeriodosLetivos(new ArrayList<PeriodoLetivoEntity>());
			}
			
			if (periodoLetivoEntity.getDtInicio().after(periodoLetivoEntity.getDtTermino())){
				facesMessages.add("Data de Início está após o Término!.");
				valido = Boolean.FALSE;
			}

			if ( !DataUtil.isDataEstaCompreendida(periodoLetivoEntity.getDtFechamentoDiario(), periodoLetivoEntity.getDtInicio(), periodoLetivoEntity.getDtTermino()) ){
				facesMessages.add("Data Fechamento do Diário precisa estar compreendida entre Inicio e Término do Período Letivo.");
				valido = Boolean.FALSE;
			}

			for (PeriodoLetivoEntity pl:  this.ensinoSelecionado.getPeriodosLetivos()){
				if (pl.getNome().trim().equalsIgnoreCase(periodoLetivoEntity.getNome().trim())){
					facesMessages.add("Já existe uma descrição "+periodoLetivoEntity.getNome()+" cadastrada!.");
					valido = Boolean.FALSE;
				}
				if (pl.getDtInicio().compareTo(periodoLetivoEntity.getDtInicio())==0){
					facesMessages.add("Data de Início "+DataUtil.formataDataDDMMYYYY(pl.getDtInicio())+" já cadastrada!.");
					valido = Boolean.FALSE;
				}
				if (pl.getDtTermino().compareTo(periodoLetivoEntity.getDtTermino())==0){
					facesMessages.add("Data de Término "+DataUtil.formataDataDDMMYYYY(pl.getDtTermino())+" já cadastrada!.");
					valido = Boolean.FALSE;
				}
				if (pl.getDtFechamentoDiario().compareTo(periodoLetivoEntity.getDtFechamentoDiario())==0){
					facesMessages.add("Data de Fechamento de Diário "+DataUtil.formataDataDDMMYYYY(pl.getDtInicio())+" já cadastrada!.");
					valido = Boolean.FALSE;
				}

				if ( DataUtil.isDataEstaCompreendida(periodoLetivoEntity.getDtInicio(), pl.getDtInicio(), pl.getDtTermino())  ){
					facesMessages.add("A Data de Início já está compreendida na divisão: "+pl.getNome());
					valido = Boolean.FALSE;
				}
				
				if ( DataUtil.isDataEstaCompreendida(periodoLetivoEntity.getDtTermino(), pl.getDtInicio(), pl.getDtTermino())  ){
					facesMessages.add("A Data de Término já está compreendida na divisão: "+pl.getNome());
					valido = Boolean.FALSE;
				}
				
			}
			
			if (!valido){
				return PAGINA_ADD_EDIT;
			}
			
			this.periodoLetivoEntity.setEnsino(this.ensinoSelecionado);
			
			this.ensinoSelecionado.getPeriodosLetivos().add(this.periodoLetivoEntity);
			
			facesMessages.add("Divisão: " + this.ensinoSelecionado.getPeriodosLetivos().size()+" - " + this.periodoLetivoEntity.getNome() +" adicionado no ensino "+this.ensinoSelecionado.getNome() + " com sucesso.");
			
			this.periodoLetivoEntity = new PeriodoLetivoEntity();
		}
		
		return PAGINA_ADD_EDIT;
	}
	

	
	
	
	@Override
	protected boolean antesGravar(Object o) {
		facesMessages.add("período letivo gravado com sucesso.");
		return true;
	}

	@Override
	protected boolean antesRemover(Object o) {
		return true;
	}

	@Override
	protected boolean antesRemoverDetalhe(Collection<Object> collectionMestre, Object detalhe) {
		return true;
	}

	@Override
	public String insere() {
		return null;
	}

	public EnsinoEntity getEnsinoSelecionado() {
		return ensinoSelecionado;
	}

	
}
