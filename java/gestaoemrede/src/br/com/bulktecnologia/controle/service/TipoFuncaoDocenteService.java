package br.com.bulktecnologia.controle.service;

import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.TipoFuncaoEntity;

@Name("TipoFuncaoDocenteService")
@Scope(ScopeType.CONVERSATION)
public class TipoFuncaoDocenteService extends BaseCrudService {
	
	@In(required=false)
	@Out(required=false)
	private TipoFuncaoEntity tipoFuncaoEntity;

	
	public TipoFuncaoDocenteService() {
		super(false);
	}

	
	@Override
	protected boolean antesGravar(Object o) {
		this.tipoFuncaoEntity.setInstituicao(recuperaInstituicao());
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
	public String edita(Object o) {
		this.tipoFuncaoEntity = (TipoFuncaoEntity) o;
		return PAGINA_ADD_EDIT;
	}

	
	@Override
	public String insere() {
		this.tipoFuncaoEntity = null;
		return PAGINA_ADD_EDIT;
	}

	
	
}
