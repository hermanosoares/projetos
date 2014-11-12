package br.com.bulktecnologia.comuns.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.navigation.Pages;

import br.com.bulktecnologia.modelo.entidade.EntidadeSelecionavel;

@Name("JSFUtil")
@Scope(ScopeType.STATELESS)
public final class JSFUtil {

	/**
	 * 
	 * provoca um refresh, na arvore de objetos do JSF.
	 * consequentemente um refresh na pagina.
	 * 
	 */
	public void refreshPagina(){
		  FacesContext facesContext = FacesContext.getCurrentInstance();
	      String viewId = Pages.getViewId(facesContext);
	      UIViewRoot viewRoot = facesContext.getApplication().getViewHandler().createView(facesContext, viewId);
	      facesContext.setViewRoot(viewRoot);
	}


	/**
	 * Obtem as entidades selecionadas na UI.
	 * 
	 * @author hsoares
	 * @param entidadesSelecionaveis
	 * @return List<FilledEntity>
	 */
	public static List<? extends EntidadeSelecionavel> obtemEntidadesSelecionadas(List<? extends EntidadeSelecionavel> entidadesSelecionaveis){
		ArrayList<EntidadeSelecionavel> resultado = new ArrayList<EntidadeSelecionavel>();
		
		if (entidadesSelecionaveis!=null){
			for (EntidadeSelecionavel f: entidadesSelecionaveis){
				if (f.getSelecionado()){
					resultado.add(f);
				}
			}
		}
		return resultado;
	}



	/**
	 * devolve o endereco IP do solicitante.
	 * @return String
	 */
	public static String getRequestedIP(){
		return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();		
	}
	
}
