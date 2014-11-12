package br.com.bulktecnologia.controle.service;

import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.ProjetoEntity;

@Name("ProjetoService")
@Scope(ScopeType.CONVERSATION)
public class ProjetoService extends BaseCrudService{
	
	public ProjetoService(){
		super(false);
	}
	
	
	@In(required=false)
	@Out(required=false)
	private ProjetoEntity projetoEntity;
	
	@Override
	protected boolean antesGravar(Object o) {
		ProjetoEntity p = (ProjetoEntity)o;
		p.setInstituicao(recuperaInstituicao());
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
		this.projetoEntity = (ProjetoEntity)o;
		return PAGINA_ADD_EDIT;
	}

	@Override
	public String insere() {
		this.projetoEntity = null;
		return PAGINA_ADD_EDIT;
	}

	
	
}
