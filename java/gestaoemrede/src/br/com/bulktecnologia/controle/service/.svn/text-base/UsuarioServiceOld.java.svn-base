package br.com.bulktecnologia.controle.service;
/*package br.com.bulktecnologia.controle.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaDTO;
import br.com.bulktecnologia.modelo.entidade.ContatoEntity;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;
import br.com.bulktecnologia.modelo.entidade.RelacaoPessoaEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioInstituicaoEntity;
import br.com.bulktecnologia.modelo.enums.PapelUsuario;
import br.com.bulktecnologia.modelo.enums.PapelUsuarioTecnico;

@Name("UsuarioService")
@Scope(ScopeType.CONVERSATION)
public class UsuarioService  extends GenericService{

	@In
	private UsuarioDAO UsuarioDAO;
	
	@In(required=false)
	@Out(required=false)
	private UsuarioEntity usuarioEntity;
	
	@In(required=false)
	@Out(required=false)
	private PessoaEntity pessoaEntity;

	@In(required=false)
	@Out(required=false)
	private ContatoEntity contatoEntity;

	@In(required=false)
	@Out(required=false)
	private UsuarioInstituicaoEntity usuarioInstituicaoEntity;
	
	private Boolean disponibilidadeLogin = Boolean.FALSE;
	
	private String msgDisponibilidadeLogin;
	
	private Boolean atualizando = Boolean.FALSE;
	
	private Object papelSelecionado;
	
	
	public Boolean getPapelSelecionadoNaoTecnico(){
		return papelSelecionado instanceof PapelUsuario;
	}
	
	
	
	public String cancela(){
		this.usuarioEntity = null;
		this.pessoaEntity = null;
		this.contatoEntity = null;
		this.disponibilidadeLogin = Boolean.FALSE;
		this.usuarioInstituicaoEntity = null;		
		
		if (atualizando){
			return "cancelaAtualizacao";
		}
		
		return "cancelaCriacao";
	}

	
	
	
	public String edita(ResultadoPesquisaDTO dto){
		this.usuarioEntity = this.UsuarioDAO.recuperaUsuarioByIDPessoa(dto.getIdPessoa());
		this.pessoaEntity = (PessoaEntity) this.UsuarioDAO.find(PessoaEntity.class, dto.getIdPessoa());
		this.contatoEntity = this.pessoaEntity.getContato();
		if (this.usuarioEntity==null){
			this.atualizando = Boolean.FALSE;
		}
		else{
			this.atualizando = Boolean.TRUE;	
		}
		
		//this.disponibilidadeLogin = Boolean.TRUE;
		
		return "editaUsuario";
	}
	
	
	
	public void alterarLogin(){
		this.disponibilidadeLogin = Boolean.FALSE;
		this.msgDisponibilidadeLogin="";
	}

	
	
	public String removerUsuarioInstituicao(UsuarioInstituicaoEntity ui){
		this.usuarioEntity.getUsuarioinstituicao().remove(ui);
		this.UsuarioDAO.remove(ui);
		return null;
	}
	
	public String addInstituicao(){
		if ( this.usuarioEntity.getUsuarioinstituicao()==null){
			 this.usuarioEntity.setUsuarioinstituicao(new ArrayList<UsuarioInstituicaoEntity>());
		}
		
		if ( !existeInstituicaoSelecionada(usuarioInstituicaoEntity.getInstituicao())){
			
			usuarioInstituicaoEntity.setDtIngresso(new Date());
			usuarioInstituicaoEntity.setUsuario(this.usuarioEntity);
			this.usuarioEntity.getUsuarioinstituicao().add(usuarioInstituicaoEntity);
			
			usuarioInstituicaoEntity = new UsuarioInstituicaoEntity();
		}
		else{
			facesMessages.add("Instituição #{UsuarioService.instituicaoSelecionada.nome} já foi adicionada.");
		}
		
		return null;
	}
	
	
	
	*//**
	 * metodo de validacao para evitar inconsistencia adicionar duas vezes a mesma instituicao.
	 * @param instituicaoSelecionada
	 * @return Boolean.
	 *//*
	private Boolean existeInstituicaoSelecionada(InstituicaoEntity instituicaoSelecionada){
		
		List<UsuarioInstituicaoEntity> uis = this.usuarioEntity.getUsuarioinstituicao(); 
		if ( uis != null ){
			for(UsuarioInstituicaoEntity u: uis){
				if ( u.getInstituicao().getNome().equals(instituicaoSelecionada.getNome()) ){
					return Boolean.TRUE;
				}
			}
		}
		
		
		return Boolean.FALSE;
	}
	public Boolean verificaDisponibilidadeLogin(){
		
		Boolean valido = Boolean.TRUE;
		
		if ( usuarioEntity.getLogin()!=null ){
			 if ( usuarioEntity.getLogin().length() < ApplicationConstants.Parametros.MIN_LENGTH_LOGIN ){
				 facesMessages.add("Tamanho Mínimo do Login são "+ApplicationConstants.Parametros.MIN_LENGTH_LOGIN+" caracteres.");
				 valido = false;
			 }
		}

		for (String reservado : ApplicationConstants.Parametros.LOGINS_RESERVADOS){
			if ( usuarioEntity.getLogin().toLowerCase().startsWith(reservado) ){
				this.disponibilidadeLogin = false;
				facesMessages.add("nenhum login pode ser criado iniciando com: 'adm', 'app' ou 'root'");
				valido = false;
			}
		}
		
		UsuarioEntity u = this.UsuarioDAO.recuperaUsuarioByLogin(usuarioEntity.getLogin());
		if (u==null){
			this.disponibilidadeLogin = Boolean.TRUE;
			this.msgDisponibilidadeLogin = "OK, "+usuarioEntity.getLogin()+" login disponível.";
		}
		else{
			this.disponibilidadeLogin = Boolean.FALSE;
			this.msgDisponibilidadeLogin = "Login: "+usuarioEntity.getLogin()+" já existente, tente outro.";
			valido = false;
		}
		
		return valido;
	}

	
	public String gravar(){
		
		if (!atualizando){
			
			if ( validaCriacaoUsuario(usuarioEntity) && validaInsercaoAtualizacaoPessoa(this.pessoaEntity)  ){
				
				//se usuário administrador não deve ter ligacao com instituicao uma-a-uma.
				if ( usuarioEntity.getPapelTecnico()!=null ){ 
					usuarioEntity.setUsuarioinstituicao(null);
				}
				
				if ( usuarioEntity.getPapelTecnico()==null && 
						 (this.usuarioEntity.getUsuarioinstituicao()==null || this.usuarioEntity.getUsuarioinstituicao().size()==0) ){
						facesMessages.add("Usuário #{usuarioEntity.login} precisa ter acesso no mínimo 1 Instituição de Ensino. ");
						return null;
					}
					
					
					this.pessoaEntity.setContato(this.contatoEntity);
					
					this.UsuarioDAO.persist(this.pessoaEntity);
					
					this.usuarioEntity.setPessoa(this.pessoaEntity);
					
					this.UsuarioDAO.persist(this.usuarioEntity);

					//se usuario possui relacao via usuarioinstituicao, é um usuário não tecnico. (professor,aluno,diretor,secretaria,etc)
					if (this.usuarioEntity.getUsuarioinstituicao()!=null){
						
						//insere tambem na relacaopessoa, (tabela) usada para otimizar pesquisas.
						for (UsuarioInstituicaoEntity ui: this.usuarioEntity.getUsuarioinstituicao()){
							RelacaoPessoaEntity rp = new RelacaoPessoaEntity();
							rp.setPessoa(this.pessoaEntity);
							rp.setInstituicao(ui.getInstituicao());
							this.UsuarioDAO.persist(rp);
						}
						
					}
					else{
						//aqui é quando o usuário é técnico (administrador de dados, suporte,call_center,etc).
						
						
						
					}

					this.UsuarioDAO.flush();
					
					facesMessages.add("usuário: "+usuarioEntity.getLogin() +"- cadastrado com sucesso!");
					
					this.usuarioEntity = null;
					this.pessoaEntity = null;
					
					return "CriadoComSucesso";
				
			}			
		}
		else{
				this.UsuarioDAO.merge(this.usuarioEntity);
				this.UsuarioDAO.merge(this.pessoaEntity);
				this.UsuarioDAO.merge(this.contatoEntity);
				this.UsuarioDAO.flush();
				
				facesMessages.add("usuário: "+usuarioEntity.getLogin() +"- atualizado com sucesso!");
				return "AtualizadoComSucesso";
		}


		return null;
	}

	
	
	
	
	private Boolean validaCriacaoUsuario(UsuarioEntity usuarioEntity){
		
		Boolean valido = Boolean.TRUE;
		
			if ( validaConstraintsAnotadasNasEntidades( new Object[]{ this.usuarioEntity,this.pessoaEntity,this.contatoEntity} ) &&
					verificaDisponibilidadeLogin() ){
				
				if ( contatoEntity.getTelfixo1()==null ){
					 valido = false;
					 facesMessages.add("Campo Obrigatório: telefone");
				}
				
				
				if ( usuarioEntity.getSenha()!=null ){
					if ( usuarioEntity.getSenhaAtual().length() < ApplicationConstants.Parametros.MIN_LENGTH_SENHA ){
						 valido = false;
						 facesMessages.add("Tamanho Mínimo da Senha são "+ApplicationConstants.Parametros.MIN_LENGTH_SENHA+" caracteres.");
					}

					if ( !usuarioEntity.getSenhaAtual().equals(usuarioEntity.getConfirmacaoNovaSenha()) ){
						 valido = false;
						 facesMessages.add("Senha e Confirmação de Senha não conferem, verifique digitação. ");
					}
					
					if (!valido){
						this.usuarioEntity.setSenhaAtual("");
						this.usuarioEntity.setConfirmacaoNovaSenha("");
					}
					
				}
				
				
			}
			else{
				valido = Boolean.FALSE;
			}
			
		return valido;
	}
	
	
	
	
	
		
	public Boolean getDisponibilidadeLogin() {
		return disponibilidadeLogin;
	}


	public void setDisponibilidadeLogin(Boolean disponibilidadeLogin) {
		this.disponibilidadeLogin = disponibilidadeLogin;
	}


	public String getMsgDisponibilidadeLogin() {
		return msgDisponibilidadeLogin;
	}


	public void setMsgDisponibilidadeLogin(String msgDisponibilidadeLogin) {
		this.msgDisponibilidadeLogin = msgDisponibilidadeLogin;
	}
	
	public String proximo(){
			return "proximo";	
	}
	
	public String anterior(){
		return "anterior";
	}
	
	public Boolean getAtualizando() {
		return atualizando;
	}

	public String proximo1(){
		if (this.disponibilidadeLogin){
			if (getPapelSelecionadoNaoTecnico()){
				return "cadastroUsuarioInstituicao";
			}
			else{
				this.usuarioEntity.setPapelTecnico((PapelUsuarioTecnico)this.papelSelecionado);
				return "proximo";
			}
		}
		else{
			facesMessages.add("Verifique a disponibilidade do Login, antes de prosseguir.");
			return null;
		}
	}


	public String anterior2(){
		if (getPapelSelecionadoNaoTecnico()){
			return "cadastroUsuarioInstituicao";
		}
		else{
			return "anterior";
		}
	}

	public Object getPapelSelecionado() {
		return papelSelecionado;
	}




	public void setPapelSelecionado(Object papelSelecionado) {
		this.papelSelecionado = papelSelecionado;
	}
	
}*/