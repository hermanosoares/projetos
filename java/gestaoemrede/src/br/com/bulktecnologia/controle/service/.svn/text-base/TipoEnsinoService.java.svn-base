package br.com.bulktecnologia.controle.service;

import java.util.ArrayList;
import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;
import br.com.bulktecnologia.modelo.enums.TipoEnsino;

@Name("TipoEnsinoService")
@Scope(ScopeType.CONVERSATION)
public class TipoEnsinoService extends BaseCrudService{
	
	public TipoEnsinoService() {
		super(false);
	}

	@In(required=false)
	@Out(required=false)
	private TurnoEntity turnoEntity;
	
	private TipoEnsino tipoEnsinoSelecionado;
	
	@Override
	public String cancela() {
		if (cookie.getConfigEspecificaInstituicao()==null){
			return "intituicoesensino";
		}
		else{
			return "cancela";
		}
	}
	
	public String addTipoEnsino(){
		
		if (tipoEnsinoSelecionado==null){
			facesMessages.add("selecione um tipo de ensino!");
			return PAGINA_ADD_EDIT;
		}
		
		if (this.turnoEntity.getEnsinos()==null){
			this.turnoEntity.setEnsinos(new ArrayList<EnsinoEntity>());
		}
		
		for (EnsinoEntity e: this.turnoEntity.getEnsinos()){
			if ( tipoEnsinoSelecionado.equals(e.getTipoEnsino()) ){
				facesMessages.add("Tipo de Ensino "+tipoEnsinoSelecionado.name()+" já existente!");
				return PAGINA_ADD_EDIT;
			}
		}
		
		EnsinoEntity e = new EnsinoEntity(tipoEnsinoSelecionado);
		e.setTurno(turnoEntity);
		e.setNome(tipoEnsinoSelecionado.name());
		
		this.turnoEntity.getEnsinos().add(e);
		
		return PAGINA_ADD_EDIT;
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
		this.turnoEntity = (TurnoEntity) o;
		return PAGINA_ADD_EDIT;
	}

	@Override
	public String insere() {
		return PAGINA_ADD_EDIT;
	}

	public TipoEnsino getTipoEnsinoSelecionado() {
		return tipoEnsinoSelecionado;
	}

	public void setTipoEnsinoSelecionado(TipoEnsino tipoEnsinoSelecionado) {
		this.tipoEnsinoSelecionado = tipoEnsinoSelecionado;
	}

	
	
}
