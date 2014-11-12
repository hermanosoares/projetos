package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Transactional;

import br.com.bulktecnologia.modelo.dao.BaseDAO;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;

/**
 * Classe base para operacoes genericas de Crud
 * @author hsoares
 *
 */
public abstract class BaseCrudService extends GenericService implements Serializable{
	
	private boolean usarFacesMessages;
	
	protected BaseDAO BaseDAO;
	private Logger log = Logger.getLogger(this.getClass());
	
	
	public BaseCrudService(boolean usarFacesMessages){
		this.BaseDAO = (BaseDAO) Component.getInstance("BaseDAO");
		this.usarFacesMessages = usarFacesMessages;
	}
	
	
	
	/**
	 * Método Genérico de Edição.
	 * @param o
	 * @return casoNavegacional
	 */
	public abstract String edita(Object o);
	
	
	
	
	/**
	 * Método Genérico de Inserção.
	 * @param o
	 * @return casoNavegacional
	 */
	public abstract String insere();
	
	/**
	 * DP: template method de pre-validacao de eventos.
	 * @param o
	 * @return
	 */
	protected abstract boolean antesRemover(Object o);
	
	/**
	 * DP: template method de pre-validacao de eventos.
	 * @param o
	 * @return
	 */
	protected abstract boolean antesRemoverDetalhe(Collection<Object> collectionMestre, Object detalhe);

	
	/**
	 * DP: template method de pre-validacao de eventos.
	 * @param o
	 * @return
	 */
	protected abstract boolean antesGravar(Object o);
	
	
	/**
	 * Método Genérico de remoção para entidade-mestre.
	 * @param o
	 * @return casoNavegacional
	 */
	@Transactional
	public final String remove(Object o){
		if (antesRemover(o)){

			if (isManaged(o) ){
				BaseDAO.remove(o);
				this.flush();
				if (usarFacesMessages){
					facesMessages.add("registro removido com sucesso!");	
				}
				return PAGINA_LIST;
			}
			else{
				log.fatal("remoção não realizada, objeto não está sendo gerenciado pelo entitymanager.");
			}

		}
		return null;
	}
	
	

	
	/**
	 * Informa se a entidade é gerenciada pelo entitymanager ou não.
	 * 
	 * @param o
	 * @return boolean
	 */
	@Transactional
	protected boolean isManaged(Object o){
		return BaseDAO.isManaged(o);		
	}
	
	
	
	/**
	 * Método Genérico de Inserção e Atualização.
	 * @param o
	 * @return Integer - registros afetados
	 */
	@Transactional
	public final String grava(Object o){
		
		if ( antesGravar(o) ){
			if ( isManaged(o) ){
				BaseDAO.merge(o);
				if (usarFacesMessages){
					facesMessages.add("registro atualizado com sucesso!");	
				}
			}
			else{
				BaseDAO.persist(o);
				
				if (usarFacesMessages){
					facesMessages.add("registro inserido com sucesso!");
				}
			}
			
			BaseDAO.flush();
			return PAGINA_LIST;
		}
		
		return PAGINA_ADD_EDIT;
	}


	protected void flush(){
		BaseDAO.flush();
	}



	protected CookieSessaoUsuario getCookie() {
		return cookie;
	}
	
	
	
	/**
	 * cancela uma operação de gravação, 
	 * direciona a pagina de listagem.
	 * @return String
	 */
	public String cancela(){
		return "cancela";
	}
	
	
	
	
	
	public String removeDetalhe(Collection<Object> colecaoDetalhes,Object detalhe){
		
		if ( antesRemoverDetalhe(colecaoDetalhes, detalhe) ){
			if (isManaged(detalhe) ){
				BaseDAO.remove(detalhe);
			}
			colecaoDetalhes.remove(detalhe);
			return PAGINA_ADD_EDIT;
		}
		
		return null;
		
	}
	
	

	
	

	
}
