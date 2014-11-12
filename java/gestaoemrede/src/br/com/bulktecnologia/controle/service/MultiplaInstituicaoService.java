package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.security.Identity;

import br.com.bulktecnologia.comuns.util.DataUtil;
import br.com.bulktecnologia.controle.listener.UsuarioOnlineListener;
import br.com.bulktecnologia.modelo.dao.ConfiguracaoDAO;
import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.AdesaoEntity;
import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
import br.com.bulktecnologia.modelo.entidade.ContatoEntity;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.EnderecoEntity;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.UsuarioInstituicaoEntity;
import br.com.bulktecnologia.modelo.enums.Perfil;

@Name("MultiplaInstituicaoService")
@Scope(ScopeType.CONVERSATION)
public class MultiplaInstituicaoService implements Serializable{
	
	
	
	@In
	@Out
	private CookieSessaoUsuario cookie;
	
	@Out(required=false)
	private InstituicaoEntity instituicaoEntity;
	
	@Out(required=false)
	private EnderecoEntity enderecoEntity;
	
	@Out(required=false)
	private AdesaoEntity adesaoEntity;
	
	@Out(required=false)
	private ContatoEntity contatoEntityInstituicao;
	
	private InstituicaoEntity instituicaoSelecionada;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@In
	private UsuarioDAO UsuarioDAO;
	
	@In
	private FacesMessages facesMessages;
	
	private ConfiguracaoEntity configuracaoEspecificaSelecionada;
	
	@In
	private ConfiguracaoDAO ConfiguracaoDAO;
	
	private List<ConfiguracaoEntity> configuracoes;
	
	public void carregaConfiguracoes(){
		this.configuracoes = this.ConfiguracaoDAO.recuperaConfiguracoesByInstituicao(this.instituicaoSelecionada.getId());
	}
	
	public String edita(){
		this.instituicaoEntity = this.instituicaoSelecionada;
		this.enderecoEntity = this.instituicaoEntity.getEndereco();
		this.adesaoEntity = this.instituicaoEntity.getAdesao();
		this.contatoEntityInstituicao = this.instituicaoEntity.getContato();
		 
		return "editaInstituicaoEnsino";
	}
	
	
	
	
	
	
	public String acessar(){

		List<InstituicaoEntity> instituicoesDoUsuario = (List<InstituicaoEntity>) Component.getInstance("InstituicaoList");
		Boolean possuiMultiplasInstituicoes = instituicoesDoUsuario.size() > 1;
		
		cookie.setIdInstituicao(this.instituicaoSelecionada.getId());
		cookie.setNomeInstituicao(this.instituicaoSelecionada.getNome());
		cookie.setPossuiMultiplaInstituicao(possuiMultiplasInstituicoes);
		
		//cleanup caso tenha alguma configuracao global sendo manipulada. 
		cookie.setIdConfigGlobalSelecionada(null);
		cookie.setNomeConfigGlobalSelecionada(null);
		
		if ( this.configuracaoEspecificaSelecionada.getAno() > DataUtil.getAnoAtual() ){
			this.facesMessages.add("ATENÇÃO: você selecionou o ano de "+this.configuracaoEspecificaSelecionada.getAno()+ ", você operando o Ano Base NO FUTURO!");
		}
		else{
			if ( this.configuracaoEspecificaSelecionada.getAno() < DataUtil.getAnoAtual() ){
				this.facesMessages.add("ATENÇÃO: você selecionou o ano de "+this.configuracaoEspecificaSelecionada.getAno()+ ",você operando o Ano Base NO PASSADO!");
			}
		}
		
		cookie.setConfigEspecificaInstituicao(this.configuracaoEspecificaSelecionada);
		
		if ( cookie.getGlobal() ) {
			return "main";
		}
		else{
			
			UsuarioInstituicaoEntity ui = this.UsuarioDAO.recuperaUsuarioInstituicaoByUsuarioEInstituicao(this.instituicaoSelecionada.getId(), cookie.getIdUsuario());
			if ( !StringUtils.isBlank(ui.getBloqueio()) ){
				facesMessages.add(ui.getBloqueio());
				return "MesmaPagina";
			}
			
			if (ui.getDtLimiteAcesso()!=null && new Date().after(ui.getDtLimiteAcesso())){
				facesMessages.add("O seu Login expirou "+DataUtil.formataDataDDMMYYYY(ui.getDtLimiteAcesso())+". Contate nossa central de suporte. ");
				return "MesmaPagina";
			}
			
			List<Perfil> perfis  = this.UsuarioDAO.recuperaPerfis(this.instituicaoSelecionada.getId(), cookie.getIdUsuario());
			
			if (perfis.size()>0){
				for (Perfil p :perfis){
					Identity.instance().addRole(p.toString());
				}
				return "main";
			}
			else{
				this.facesMessages.add("Não é possivel acessar seu login está sem perfis de acesso para acessar a Instituição: "+ this.instituicaoSelecionada.getNome() );
				return "MesmaPagina";
			}
			
		}
		
		
	}
	

	public InstituicaoEntity getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}
	

	public void setInstituicaoSelecionada(InstituicaoEntity instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}


	public void irTelaMultiplasInstituicoes(){
		Conversation.instance().killAllOthers();
		Conversation.instance().begin(true,false);
		Redirect.instance().setViewId("/login/instituicoesensino.xhtml");
		Redirect.instance().execute();
	}
	
	
	
	
	/**
	 * recupera instituicoes do usuario de acordo com os papeis atribuidos ao mesmo.
	 * 
	 * @return List<InstituicaoEntity>
	 */
	@Observer(value={"org.jboss.seam.security.loginSuccessful","MultiplaInstituicaoService.evento.publicaInstituicoesDoUsuarioNoContexto"})
	public void publicaInstituicoesDoUsuarioNoContexto(){
		
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(Boolean.FALSE);
		UsuarioOnlineListener.contabilizaAcesso(sessao);
		
	}






	public ConfiguracaoEntity getConfiguracaoEspecificaSelecionada() {
		return configuracaoEspecificaSelecionada;
	}






	public void setConfiguracaoEspecificaSelecionada(
			ConfiguracaoEntity configuracaoEspecificaSelecionada) {
		this.configuracaoEspecificaSelecionada = configuracaoEspecificaSelecionada;
	}

	public List<ConfiguracaoEntity> getConfiguracoes() {
		return configuracoes;
	}


	



}
