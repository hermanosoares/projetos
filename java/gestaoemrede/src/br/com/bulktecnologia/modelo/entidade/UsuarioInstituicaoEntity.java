package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Name;
@Entity
@Name("usuarioInstituicaoEntity")
@Table(name="usuarioInstituicao")
@NamedQueries(value={ 
		@NamedQuery(name="UsuarioInstituicao.recuperaInstituicoesDoUsuario",query="select ui.instituicao from UsuarioInstituicaoEntity ui where ui.usuario.id = :idusuario and ui.ativo = true and ui.instituicao.ativo = true order by ui.instituicao.codInstituicao"),
		@NamedQuery(name="UsuarioInstituicao.recuperaTodosUsuariosDaInstituicao",query="select ui from UsuarioInstituicaoEntity ui where ui.instituicao.id = :instituicao"),
		@NamedQuery(name="UsuarioInstituicao.recuperaUsuarioInstituicaoByUsuarioEInstituicao",query="select ui from UsuarioInstituicaoEntity ui where ui.instituicao.id = :idInstituicao and ui.usuario.id = :idUsuario"),
		@NamedQuery(name="UsuarioInstituicao.recuperaPerfisByUsuarioEInstituicao",query="select p.perfil from UsuarioInstituicaoEntity ui join ui.perfis p where ui.instituicao.id = :idInstituicao and ui.usuario.id = :idUsuario")
		})
public class UsuarioInstituicaoEntity implements Serializable {

	public UsuarioInstituicaoEntity(){
		
	}

	public UsuarioInstituicaoEntity(UsuarioEntity usuario,InstituicaoEntity instituicao){
		this.setUsuario(usuario);
		this.setInstituicao(instituicao);
	}
	
	

	@Id
	@GeneratedValue(generator="usuarioinstituicao_seq")
	@SequenceGenerator(name="usuarioinstituicao_seq",sequenceName="usuarioinstituicao_seq")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(nullable=false)
	private UsuarioEntity usuario;
	
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(nullable=false)
	private InstituicaoEntity instituicao;

	@OneToMany(mappedBy="usuarioInstituicao",fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	private List<PerfilEntity> perfis = new ArrayList<PerfilEntity>();
	
	@Column
	private String bloqueio;
	
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date dtLimiteAcesso;
	
	private Boolean ativo = Boolean.TRUE;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}
	
	public List<PerfilEntity> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilEntity> perfis) {
		this.perfis = perfis;
	}

	public String getBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(String bloqueio) {
		this.bloqueio = bloqueio;
	}

	public Date getDtLimiteAcesso() {
		return dtLimiteAcesso;
	}

	public void setDtLimiteAcesso(Date dtLimiteAcesso) {
		this.dtLimiteAcesso = dtLimiteAcesso;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	public String getExistemBloqueioAux(){
		
		StringBuffer sb = new StringBuffer();
		
		if ( this.dtLimiteAcesso!=null && new Date().after(this.dtLimiteAcesso)){
			sb.append("Data Limite,");
		}
		
		if (!StringUtils.isBlank(this.bloqueio)){
			sb.append("Motivo,");
		}
		
		if (this.perfis.size()==0){
			sb.append("Sem Perfis");
		}
		
		if (sb.length()==0){
			sb.append("não há.");
		}
	
		return sb.toString();
	}
	
	
}
