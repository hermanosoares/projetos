package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.modelo.dao.ConceitoDAO;
import br.com.bulktecnologia.modelo.entidade.Conceito;
import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.enums.TipoConceito;
@Name("ConceitoService")
@Scope(ScopeType.CONVERSATION)
public class ConceitoService implements Serializable {
	
	
	private List<Conceito> conceitos = new ArrayList<Conceito>();
	
	private TipoConceito tipoConceito;
	
	@In
	private CookieSessaoUsuario cookie;
	
	@In
	private ConceitoDAO ConceitoDAO;
	
	@In
	private FacesMessages facesMessages;
	
	
	private ConfiguracaoEntity configuracao;
	
	@Create
	public void aoInicializar(){
		this.conceitos  = this.ConceitoDAO.recuperaConceitosDaConfiguracao(this.cookie.getConfigEspecificaInstituicao().getId());
		configuracao =  ConceitoDAO.getEm().find(ConfiguracaoEntity.class,this.cookie.getConfigEspecificaInstituicao().getId());	
	}
	
	
	
	public String addConceito(){
		
		if (this.tipoConceito==null){
			facesMessages.add("Selecione o Conceito!");
			return "MesmaPagina";
		}
		for (Conceito c: this.conceitos){
			if ( c.getTipoConceito().equals(this.tipoConceito) ){
				facesMessages.add("Conceito "+c.getTipoConceito()+" já adicionado! ");
				return "MesmaPagina";
			}
		}
		
		Conceito c = new Conceito();
		c.setLabel(this.tipoConceito.toString());
		c.setTipoConceito(this.tipoConceito);
		c.setConfiguracao(this.configuracao);
		
		this.conceitos.add(c);
		this.ConceitoDAO.persist(c);
		return "MesmaPagina";
	}
	

	
	public String grava(){
		facesMessages.add("Conceitos Gravados com sucesso!");
		this.ConceitoDAO.flush();
		return "Sucesso";
	}
	
	
	public String remove(Conceito c){
		this.conceitos.remove(c);
		this.ConceitoDAO.remove(c);
		return "MesmaPagina";
	}
	
	
	
	public List<Conceito> getConceitos() {
		return conceitos;
	}


	public TipoConceito getTipoConceito() {
		return tipoConceito;
	}

	public void setTipoConceito(TipoConceito tipoConceito) {
		this.tipoConceito = tipoConceito;
	}
	
	
	
}
