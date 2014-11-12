package br.com.bulktecnologia.controle.service;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.comuns.converter.FacesMessageSessionScoped;
import br.com.bulktecnologia.controle.exception.EmailDuplicadoException;
import br.com.bulktecnologia.controle.list.UsuarioList;
import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.PerfilEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioInstituicaoEntity;
import br.com.bulktecnologia.modelo.enums.Perfil;

@Name("usuarioService")
@Scope(ScopeType.CONVERSATION)
public class UsuarioService implements Serializable {

	@In
	private FacesMessages facesMessages;

	@In
	private UsuarioDAO UsuarioDAO;

	@In(required=false)
	@Out(required=false)
	private UsuarioEntity usuarioEntity;
	
	private Boolean travarLogin = Boolean.FALSE;
	
	private Boolean permiteRedefinirSenha = Boolean.FALSE;
	
	private String status;
	
	private Perfil perfil;
	
	@In(required=false)
	@Out(required=false)
	private UsuarioInstituicaoEntity usuarioInstituicaoEntity;
	
	@In
	private EntityManager entityManager;
	
	@In
	private FacesMessageSessionScoped FacesMessageSessionScoped;
	
	
	public void redefinirSenha(){
		this.permiteRedefinirSenha=!permiteRedefinirSenha;
	}
	
	
	public String desativaRelacaoUsuarioInstituicao(UsuarioInstituicaoEntity ui){
		ui.setAtivo(Boolean.FALSE);
		entityManager.merge(ui);
		facesMessages.add("Login: "+ui.getUsuario().getLogin()+" desativado na "+ui.getInstituicao().getNome());
		entityManager.flush();
		return "MesmaPagina";
	}
	
	
	public String ativaRelacaoUsuarioInstituicao(UsuarioInstituicaoEntity ui){
		ui.setAtivo(Boolean.TRUE);
		entityManager.merge(ui);
		facesMessages.add("Login: "+ui.getUsuario().getLogin()+" ativado na "+ui.getInstituicao().getNome());
		entityManager.flush();
		return "MesmaPagina";
	}
	
	
	public String edita(UsuarioInstituicaoEntity ui){
		this.usuarioEntity = ui.getUsuario();
		this.usuarioInstituicaoEntity = ui;
		return "add_edit";
	}
	
	
	public String novoUsuario(){
		this.usuarioInstituicaoEntity = new UsuarioInstituicaoEntity();
		return "add_edit";
	}
	
	
	
	public String novoUsuarioAdministrativo(){
		this.usuarioEntity = new UsuarioEntity();
		this.usuarioEntity.setGlobal(Boolean.TRUE);
		return "add_edit";
	}
	
	

	
	public void destravarLogin(){
		this.travarLogin = Boolean.FALSE;
		Boolean globalAux = this.usuarioEntity.getGlobal();
		this.usuarioEntity = new UsuarioEntity();
		this.usuarioEntity.setGlobal(globalAux);
		this.status = null;
	}
	
	public void addPerfil(){
		if (this.perfil==null){
			facesMessages.add("Selecione um perfil para adicionar!");
			return;
		}
		
		for (PerfilEntity pe: this.usuarioInstituicaoEntity.getPerfis()){
			if (pe.getPerfil().equals(this.perfil)){
				facesMessages.add("Perfil já adicionado!");
				return;
			}
		}
		
		PerfilEntity p = new PerfilEntity();
		p.setPerfil(this.perfil);
		
		this.usuarioInstituicaoEntity.getPerfis().add(p);
		this.perfil =null;
	}
	
	public void removePerfil(PerfilEntity p){
		if (p.getId()!=null){
			this.entityManager.remove(p);
		}
		this.usuarioInstituicaoEntity.getPerfis().remove(p);
	}
	
	
	public void verificaDisponibilidadeLogin(){
		
		for (String reservado : ApplicationConstants.Parametros.LOGINS_RESERVADOS){
			if ( usuarioEntity.getLogin().toLowerCase().startsWith(reservado) ){
				facesMessages.add("nenhum login pode ser criado iniciando com: 'adm', 'app' ou 'root'");
				this.usuarioEntity = null;
				return;
			}
		}
		
		 UsuarioEntity u = this.UsuarioDAO.recuperaUsuarioByLogin(usuarioEntity.getLogin());
		 
		 if (u!=null && this.usuarioEntity.getGlobal()){
				facesMessages.add("login :"+this.usuarioEntity.getLogin()+" já existente!");
				this.usuarioEntity.setLogin(null);
				return;
		 }
		 
		 this.travarLogin = Boolean.TRUE;
		 
		 if (u==null){
			 this.status = " Criando Login ";
			 if (usuarioEntity.getGlobal()){
				 this.status = this.status.concat(" Administrativo ");
			 }
			 this.status += ": "+this.usuarioEntity.getLogin(); 
		 }
		 else{
			 UsuarioList list = (UsuarioList) Component.getInstance("usuarioList");
			 
			 UsuarioInstituicaoEntity ui = this.UsuarioDAO.recuperaUsuarioInstituicaoByUsuarioEInstituicao(list.getInstituicaoSelecionada().getId(),u.getId());
			 if (ui==null){
				 this.status = " Vinculando login: "+this.usuarioEntity.getLogin();
				 this.usuarioEntity = u;
			 }
			 else{
				 	this.travarLogin  = Boolean.FALSE;
					facesMessages.add("Login: "+this.usuarioEntity.getLogin()+" já vinculado a "+list.getInstituicaoSelecionada().getNome());
					this.usuarioEntity = null;
			 }
			 
		 }
		 
	}
	
	
	@Transactional
	public String grava(){

		try {
			UsuarioEntity u = this.UsuarioDAO.recuperaUsuarioByEmail( usuarioEntity.getEmail());
			
			if ( usuarioEntity.getId()==null){
				if (u!=null){
					facesMessages.add("Não Permitido! email: "+u.getEmail()+" já existente para o Login: "+u.getLogin());
					this.usuarioEntity.setEmail(null);
					return "MesmaPagina";
				}
			}
			else{
				if ( u!=null && u.getId().compareTo(usuarioEntity.getId())!=0 ){
					facesMessages.add("Não Permitido! email: "+u.getEmail()+" já existente para o Login: "+u.getLogin());
					this.usuarioEntity.setEmail(null);
					return "MesmaPagina";
				}
			}
			
			this.entityManager.persist(this.usuarioEntity);

			if ( !usuarioEntity.getGlobal()){
				UsuarioList list = (UsuarioList) Component.getInstance("usuarioList");
				InstituicaoEntity instituicao = list.getInstituicaoSelecionada();

				this.usuarioInstituicaoEntity.setInstituicao(instituicao);
				this.usuarioInstituicaoEntity.setUsuario(this.usuarioEntity);

				this.entityManager.persist(this.usuarioInstituicaoEntity);
				
				for (PerfilEntity p: this.usuarioInstituicaoEntity.getPerfis()){
					p.setUsuarioInstituicao(this.usuarioInstituicaoEntity);
					this.entityManager.persist(p);
				}
			}
			
			this.entityManager.flush();

			FacesMessageSessionScoped.add("login: "+this.usuarioEntity.getLogin()+" gravado com sucesso!");

			return "usuariolist";
		} catch (EmailDuplicadoException e) {
			facesMessages.add(e.getMessage());
			return "MesmaPagina";
		}
	}
	



	public Boolean getTravarLogin() {
		return travarLogin;
	}



	public String getStatus() {
		return status;
	}



	public Perfil getPerfil() {
		return perfil;
	}



	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}





	public Boolean getPermiteRedefinirSenha() {
		return permiteRedefinirSenha;
	}



	public void setPermiteRedefinirSenha(Boolean permiteRedefinirSenha) {
		this.permiteRedefinirSenha = permiteRedefinirSenha;
	}






	
}
