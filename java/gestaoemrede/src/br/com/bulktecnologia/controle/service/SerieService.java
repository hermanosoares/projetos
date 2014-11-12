package br.com.bulktecnologia.controle.service;

import java.util.ArrayList;
import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.enums.TipoSerie;

@Name("SerieService")
@Scope(ScopeType.CONVERSATION)
public class SerieService extends BaseCrudService{
	
	@In(required=false)
	@Out(required=false)
	private EnsinoEntity ensinoEntity;
	
	private TipoSerie tipoSerieSelecionada;
	
	@Override
	public String cancela() {
		if (cookie.getConfigEspecificaInstituicao()==null){
			return "intituicoesensino";
		}
		else{
			return "cancela";
		}
	}
	
	public String addSerie(){
		
		if (tipoSerieSelecionada==null){
			facesMessages.add("selecione uma série!");
			return PAGINA_ADD_EDIT;
		}
		
		if (ensinoEntity.getSeries()==null){
			ensinoEntity.setSeries(new ArrayList<SerieEntity>());
		}
		
		for (SerieEntity s: this.ensinoEntity.getSeries()){
			if ( tipoSerieSelecionada.equals(s.getTipoSerie()) ){
				facesMessages.add("Série "+tipoSerieSelecionada.name()+" já existente!");
				return PAGINA_ADD_EDIT;
			}
		}
		
		
		SerieEntity s = new SerieEntity(tipoSerieSelecionada);
		s.setEnsino(ensinoEntity);
		s.setNome(tipoSerieSelecionada.getLabel());
		
		
		this.ensinoEntity.getSeries().add(s);
		
		return PAGINA_ADD_EDIT;
	}

	
	public SerieService(){
		super(false);
	}
	
	
	@Override
	protected boolean antesGravar(Object o) {
		return true;
	}

	
	@Override
	protected boolean antesRemover(Object o) {
		return true;
	}

	@Override
	protected boolean antesRemoverDetalhe(Collection<Object> collectionMestre,Object detalhe) {
		return true;
	}

	@Override
	public String edita(Object o) {
		this.ensinoEntity = (EnsinoEntity)o;
		return PAGINA_ADD_EDIT;
	}

	
	
	@Override
	public String insere() {
		return PAGINA_ADD_EDIT;
	}


	public TipoSerie getTipoSerieSelecionada() {
		return tipoSerieSelecionada;
	}


	public void setTipoSerieSelecionada(TipoSerie tipoSerieSelecionada) {
		this.tipoSerieSelecionada = tipoSerieSelecionada;
	}


	
}
