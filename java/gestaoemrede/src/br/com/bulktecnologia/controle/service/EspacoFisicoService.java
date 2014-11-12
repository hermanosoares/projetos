package br.com.bulktecnologia.controle.service;

import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.EspacoFisicoDAO;
import br.com.bulktecnologia.modelo.entidade.EspacoFisicoEntity;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;

@Name("EspacoFisicoService")
@Scope(ScopeType.CONVERSATION)
public class EspacoFisicoService extends BaseCrudService{


	public EspacoFisicoService(){
		super(false);
	}
	
	@In(required=false)
	@Out(required=false)
	private EspacoFisicoEntity espacoFisicoEntity;
	
	@In
	private EspacoFisicoDAO EspacoFisicoDAO;
	
	
	
	public boolean antesGravar(Object o){
			this.espacoFisicoEntity = (EspacoFisicoEntity) o;   
			EspacoFisicoEntity espaco = EspacoFisicoDAO.validaEspacoFisicoDuplicado(espacoFisicoEntity.getIdentificadorEspaco(),
																	  				espacoFisicoEntity.getTipoEspaco(),
																	  				getCookie().getIdInstituicao(),
																	  				this.espacoFisicoEntity.getId());
			
				if(espaco != null){
					facesMessages.add("Espaço Físico: #{espacoFisicoEntity.identificadorEspaco} , #{espacoFisicoEntity.tipoEspaco} já existe!");
					return false;
				} else {
					InstituicaoEntity instituicao = (InstituicaoEntity) EspacoFisicoDAO.find(InstituicaoEntity.class, getCookie().getIdInstituicao());
					espacoFisicoEntity.setInstituicao(instituicao);
					facesMessages.add("Espaço Físico: #{espacoFisicoEntity.identificadorEspaco} , #{espacoFisicoEntity.tipoEspaco} inserido com sucesso!");
					return true;
				}
	}
	
	



	
	@Override
	public String edita(Object o) {
		this.espacoFisicoEntity = (EspacoFisicoEntity)o;
		return PAGINA_ADD_EDIT;
	}
	

	
	@Override
	public String insere() {
		this.espacoFisicoEntity = null;
		return PAGINA_ADD_EDIT;
	}
	


	@Override
	protected boolean antesRemover(Object o) {
		return true;
	}






	@Override
	protected boolean antesRemoverDetalhe(Collection<Object> collectionMestre,Object detalhe) {
		return true;
	}
	
}	
