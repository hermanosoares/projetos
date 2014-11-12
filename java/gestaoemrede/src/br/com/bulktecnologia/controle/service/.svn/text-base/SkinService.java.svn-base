package br.com.bulktecnologia.controle.service;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.PreferenciaDAO;
import br.com.bulktecnologia.modelo.entidade.PreferenciaUsuarioEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioEntity;
import br.com.bulktecnologia.modelo.enums.Skins;

@Name("SkinService")
@Scope(ScopeType.CONVERSATION)
public class SkinService extends GenericService{
	
	private Skins skinSelecionado;
	
	@In
	private PreferenciaDAO preferenciaDAO;
	
	
	public String alteraSkin(){
		
			Long idUsuario = super.cookie.getIdUsuario();
			UsuarioEntity u = (UsuarioEntity)this.preferenciaDAO.find(UsuarioEntity.class, idUsuario);
			PreferenciaUsuarioEntity pu = preferenciaDAO.recuperaPreferencia(idUsuario, "skin");
			
			if ( pu == null ){
				
				pu = new PreferenciaUsuarioEntity();
				pu.setChave("skin");
				pu.setValor(this.skinSelecionado.toString());
				pu.setUsuario(u);
				
				preferenciaDAO.persist(pu);
			}
			else{
				pu.setValor(this.skinSelecionado.toString());
				preferenciaDAO.merge(pu);
			}
			
			preferenciaDAO.flush();

		
		
		
		facesMessages.add("Tema aplicada com sucesso!");
		cookie.setSkin(skinSelecionado.name());

		return "sucesso";
	}

	


	
	
	public Skins getSkinSelecionado() {
		return Skins.valueOf(Skins.class, cookie.getSkin());
	}

	
	public void setSkinSelecionado(Skins skinSelecionado) {
		this.skinSelecionado = skinSelecionado;
	}
	
	
	
}

