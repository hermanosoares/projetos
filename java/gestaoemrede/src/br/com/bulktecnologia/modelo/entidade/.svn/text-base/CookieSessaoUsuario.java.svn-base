package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.navigation.Pages;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.modelo.dto.RegistroSelecionado;
import br.com.bulktecnologia.modelo.enums.TipoLogout;
/**
 * Classe que � colocada na Sess�o do Usu�rio.
 * Este Objeto representa tudo que estar� disponivel no Escopo Sess�o.
 *  
 * @author soares
 */
@Name("cookie")
@Scope(ScopeType.SESSION)
public class CookieSessaoUsuario implements Serializable {
	
	private Long idUsuario;
	private String login;
	private String senha;
	private Long idAuditoriaAcesso;
	private Long idInstituicao;
	private ConfiguracaoEntity configEspecificaInstituicao;
	private String nomeInstituicao;
	private Boolean global = Boolean.FALSE;	
	private Boolean possuiMultiplaInstituicao = Boolean.FALSE;
	private TipoLogout tipoLogout;
	private String skin = ApplicationConstants.Parametros.SKIN_PADRAO.name();
	
	//id da configuracao global selecionada
	private Long idConfigGlobalSelecionada;
	private String nomeConfigGlobalSelecionada;
	
	private List<RegistroSelecionado> registrosSelecionado = new ArrayList<RegistroSelecionado>();
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	

	public Long getIdAuditoriaAcesso() {
		return idAuditoriaAcesso;
	}

	public void setIdAuditoriaAcesso(Long idAuditoriaAcesso) {
		this.idAuditoriaAcesso = idAuditoriaAcesso;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}




	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Boolean getPossuiMultiplaInstituicao() {
		return possuiMultiplaInstituicao;
	}

	public void setPossuiMultiplaInstituicao(Boolean possuiMultiplaInstituicao) {
		this.possuiMultiplaInstituicao = possuiMultiplaInstituicao;
	}

	public TipoLogout getTipoLogout() {
		return tipoLogout;
	}

	public void setTipoLogout(TipoLogout tipoLogout) {
		this.tipoLogout = tipoLogout;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}


	public Long getRegistroSelecionado(Class type, Boolean cleanup) {
		
		String currentURL = Pages.getCurrentViewId();
		
		RegistroSelecionado registro = resolveClassTypeRegistroSelecionado(type);
		
		if (registro!=null){
			if ( currentURL.equals(registro.getUrl()) ){
				Long result = registro.getId();
				if (cleanup==null || cleanup){
					this.registrosSelecionado.remove(registro);
				}
				return result;
			}
			else{
				this.registrosSelecionado.remove(registro);
			}
		}

		return null;
	}


	public void setRegistroSelecionado(Long idRegistroSelecionado,String viewIdAlvo, Class tipo) {
		RegistroSelecionado r = resolveClassTypeRegistroSelecionado(tipo);
		
		if (r==null){
			r = new RegistroSelecionado(idRegistroSelecionado,viewIdAlvo,tipo);
			this.registrosSelecionado.add(r);
		}
		else{
			
			//thread-safe guarantee
			synchronized(r){
				r = new RegistroSelecionado(idRegistroSelecionado,viewIdAlvo,tipo);
			}
		}
		
	}
	

	
	private RegistroSelecionado resolveClassTypeRegistroSelecionado(Class type){
		
		for ( RegistroSelecionado r: this.registrosSelecionado ){
			if ( r.getType().equals(type) ){
				return r;
			}
		}
	
		return null;
	}


	


	public String getNomeInstituicao() {
		return nomeInstituicao;
	}


	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}


	public Boolean getGlobal() {
		return global;
	}


	public void setGlobal(Boolean global) {
		this.global = global;
	}





	public ConfiguracaoEntity getConfigEspecificaInstituicao() {
		return configEspecificaInstituicao;
	}


	public void setConfigEspecificaInstituicao(
			ConfiguracaoEntity configEspecificaInstituicao) {
		this.configEspecificaInstituicao = configEspecificaInstituicao;
	}


	public Long getIdConfigGlobalSelecionada() {
		return idConfigGlobalSelecionada;
	}


	public void setIdConfigGlobalSelecionada(Long idConfigGlobalSelecionada) {
		this.idConfigGlobalSelecionada = idConfigGlobalSelecionada;
	}


	public String getNomeConfigGlobalSelecionada() {
		return nomeConfigGlobalSelecionada;
	}


	public void setNomeConfigGlobalSelecionada(String nomeConfigGlobalSelecionada) {
		this.nomeConfigGlobalSelecionada = nomeConfigGlobalSelecionada;
	}

	
	
}
