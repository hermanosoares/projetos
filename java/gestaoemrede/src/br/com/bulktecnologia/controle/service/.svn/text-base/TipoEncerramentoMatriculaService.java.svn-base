package br.com.bulktecnologia.controle.service;

import java.util.Collection;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import br.com.bulktecnologia.controle.list.TipoEncerramentoMatriculaList;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.TipoEncerramentoMatriculaEntity;

@Name("TipoEncerramentoMatriculaService")
public class TipoEncerramentoMatriculaService extends BaseCrudService{

	@In(required=false)
	@Out(required=false)
	private TipoEncerramentoMatriculaEntity tipoEncerramentoMatriculaEntity;
	
	@In(required=false)
	private TipoEncerramentoMatriculaList TipoEncerramentoMatriculaList;
	
	public TipoEncerramentoMatriculaService(){
		super(false);
	}

	@Override
	protected boolean antesGravar(Object o) {
		
		Boolean descricaoJaExistente = Boolean.TRUE;
		
		try {
			Query q = this.BaseDAO.getEm().createNamedQuery("TipoEncerramentoMatriculaEntity.validaNomeJaExistente");
			q.setParameter("idinstituicao", cookie.getIdInstituicao());
			q.setParameter("nome","%"+tipoEncerramentoMatriculaEntity.getNome().trim()+"%");
			q.getSingleResult();
		} catch (NoResultException e) {
			descricaoJaExistente = Boolean.FALSE;
		}
		
		if (descricaoJaExistente){
			facesMessages.add("nome já existente ou nome reservado,tente outro nome de encerramento de matricula");
			return false;
		}
		else{
			TipoEncerramentoMatriculaEntity tipo = (TipoEncerramentoMatriculaEntity)o;
			InstituicaoEntity instituicao = super.recuperaInstituicao();
			tipo.setInstituicao(instituicao);
			tipo.setPermiteExclusao(Boolean.TRUE);
			
			return true;
		}
		
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
		this.tipoEncerramentoMatriculaEntity = (TipoEncerramentoMatriculaEntity)o;
		return PAGINA_ADD_EDIT;
	}

	@Override
	public String insere() {
		this.tipoEncerramentoMatriculaEntity = null;
		return PAGINA_ADD_EDIT;
	}


	
}
