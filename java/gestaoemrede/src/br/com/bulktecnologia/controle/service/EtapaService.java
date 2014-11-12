package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.BaseDAO;
import br.com.bulktecnologia.modelo.entidade.EtapaEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.enums.TipoEtapaTemporal;

@Name("EtapaService")
@Scope(ScopeType.CONVERSATION)
public class EtapaService extends GenericService implements Serializable {

	@In(required=false)
	@Out(required=false)
	private SerieEntity serieEntity;
	
	private TipoEtapaTemporal tipoEtapaTemporalSelecionada;
	
	@In
	private BaseDAO BaseDAO;
	
	

	public String cancela() {
		if (cookie.getConfigEspecificaInstituicao()==null){
			return "intituicoesensino";
		}
		else{
			return "cancela";
		}
	}
	
	public String edita(Object o){
		this.serieEntity = (SerieEntity)o;
		return PAGINA_ADD_EDIT;
	}

	public TipoEtapaTemporal getTipoEtapaTemporalSelecionada() {
		return tipoEtapaTemporalSelecionada;
	}

	public void setTipoEtapaTemporalSelecionada(
			TipoEtapaTemporal tipoEtapaTemporalSelecionada) {
		this.tipoEtapaTemporalSelecionada = tipoEtapaTemporalSelecionada;
	}


	
	public String removeEtapas(){
		this.serieEntity.getEtapas().clear();
		return null;
	}
	
	
	
	
	public String grava(){
		if ( validaEtapas() ){
			this.BaseDAO.flush();
			facesMessages.add("etapas salvas com sucesso!");
			return PAGINA_LIST;
		}
		else{
			return "MesmaPagina";
		}
	}
	
	
	private Boolean validaEtapas(){
		
		if (this.serieEntity.getEtapas()!=null){
			
			Double totalNotas = 0D;
			
			for (EtapaEntity e: this.serieEntity.getEtapas()){
				totalNotas += e.getNotaMaximaEtapa();
			}
			
			if (totalNotas > this.serieEntity.getNotaMaxima()){
				facesMessages.add("As Etapas não podem ultrapassar a nota máxima desta série de "+this.serieEntity.getNotaMaxima()+" pontos.");
				return Boolean.FALSE;
			}

			if (totalNotas < this.serieEntity.getNotaMaxima()){
				facesMessages.add("A somatória das etapas abaixo precisam distribuir "+this.serieEntity.getNotaMaxima()+" pontos para esta série.");
				return Boolean.FALSE;
			}

			if ( totalNotas.compareTo(this.serieEntity.getNotaMaxima())==0 ){
				return Boolean.TRUE;	
			}
			
		}

		return Boolean.FALSE; 
	}
	
	
	
	
	
	public void fabricaEtapas(){
		
	 	List<EtapaEntity> etapasExistentes =  this.serieEntity.getEtapas();
	 	
	 	for (EtapaEntity e: etapasExistentes){
	 		this.BaseDAO.remove(e);
	 	}
	 	
	 	this.serieEntity.getEtapas().clear();
	 	
		if ( tipoEtapaTemporalSelecionada.equals(TipoEtapaTemporal.Anual) ){
			this.geraEtapas(1, TipoEtapaTemporal.Anual.name());
		}
		else{
			if ( tipoEtapaTemporalSelecionada.equals(TipoEtapaTemporal.Semestre) ){
				this.geraEtapas(2, TipoEtapaTemporal.Semestre.name());
			}
			else{
				if ( tipoEtapaTemporalSelecionada.equals(TipoEtapaTemporal.Trimestre) ){
					this.geraEtapas(3, TipoEtapaTemporal.Trimestre.name());		
				}
				else{
					if ( tipoEtapaTemporalSelecionada.equals(TipoEtapaTemporal.Bimestre) ){
						this.geraEtapas(4, TipoEtapaTemporal.Bimestre.name());
					}
				}
			}
		}
		
	}
	
	
	
	
	
	private void geraEtapas(int qtdeEtapas,String descEtapa){
		for (int i=0;i<qtdeEtapas;i++){
			EtapaEntity etapa = new EtapaEntity();
			etapa.setNome( (i+1) + descEtapa);
			etapa.setNomeReduzido((i+1) + descEtapa.substring(0,1) );
			etapa.setSerie(this.serieEntity);
			this.serieEntity.getEtapas().add(etapa);
		}
	}
	

	
	
	
	
	
}
