package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.EtapaEntity;
import br.com.bulktecnologia.modelo.entidade.EtapaRecuperacaoEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
@Name("EtapaRecuperacaoService")
@Scope(ScopeType.CONVERSATION)
public class EtapaRecuperacaoService extends BaseCrudService implements Serializable {
	
	@In(required=false)
	@Out(required=false)
	private SerieEntity serieEntity;

	
	
	private EtapaEntity etapaSelecionada;
	
	
	public EtapaRecuperacaoService(){
		super(false);
	}
	
	
	public String edita(Object o){
		this.serieEntity = (SerieEntity)o;
		return PAGINA_ADD_EDIT;
	}

	
	
	public String removeEtapaDaRecuperacao(EtapaRecuperacaoEntity recuperacao,EtapaEntity etapa){
		recuperacao.getEtapas().remove(etapa);
		etapa.getEtapasRecuperacao().remove(recuperacao);
		
		return PAGINA_ADD_EDIT;
	}
	
	public String removeEtapaRecuperacao(EtapaRecuperacaoEntity recuperacao){
		
		this.serieEntity.getEtapasRecuperacao().remove(recuperacao);
		
		return PAGINA_ADD_EDIT; 
	}
	
	
	public String commit() {
		super.flush();
		facesMessages.add("Etapas de Recuperação salvas com sucesso!");
		return PAGINA_LIST;
	}
	
	
	
	
	
	public String addEtapaRecuperacao(){
		
		EtapaRecuperacaoEntity recuperacao = new EtapaRecuperacaoEntity();
		recuperacao.setSerie(this.serieEntity);
		
		this.serieEntity.getEtapasRecuperacao().add(recuperacao);
		
		return PAGINA_ADD_EDIT;
	}

	
	
	
	
	
	
	
	
	
	public String addEtapaSelecionadaNaRecuperacao(EtapaRecuperacaoEntity recuperacao){
		
		if (this.etapaSelecionada!=null){
			
			if (recuperacao.getEtapas()==null){
				recuperacao.setEtapas(new ArrayList<EtapaEntity>());
			}
			
			this.etapaSelecionada.getEtapasRecuperacao().add(recuperacao);
			recuperacao.getEtapas().add(this.etapaSelecionada);
		
		}
		else{
			facesMessages.add("selecione uma etapa para adicionar na recuperação.");
		}
		
		return PAGINA_ADD_EDIT;
	}


	
	
	public EtapaEntity getEtapaSelecionada() {
		return etapaSelecionada;
	}

	
	
	

	public void setEtapaSelecionada(EtapaEntity etapaSelecionada) {
		this.etapaSelecionada = etapaSelecionada;
	}

	
	
	@Override
	protected boolean antesGravar(Object o) {
		return true;
	}

	
	
	@Override
	protected boolean antesRemover(Object o) {
		return true;
	}

	
	
	/**
	 * delete many-to-many
	 * informa as etapas que elas não possuem mais uma etapa de recuperação.
	 * 
	 */
	@Override
	protected boolean antesRemoverDetalhe(Collection<Object> collectionMestre, Object detalhe) {
		
		EtapaRecuperacaoEntity recuperacao = (EtapaRecuperacaoEntity)detalhe;
		
		if ( recuperacao.getEtapas()!=null ){

			
			for (EtapaEntity e: recuperacao.getEtapas()){
				e.getEtapasRecuperacao().remove(recuperacao);
			}
			recuperacao.getEtapas().clear();
		}
		return true;	
	}
	
	
	

	
	@Override
	public String insere() {
		return null;
	}

	
}
