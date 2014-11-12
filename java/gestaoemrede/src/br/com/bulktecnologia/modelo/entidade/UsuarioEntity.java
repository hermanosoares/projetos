package br.com.bulktecnologia.modelo.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

@Entity
@Table(name="usuario")
@NamedQueries(value={  
		
  	     //@NamedQuery(name="UsuarioEntity.recuperaPapeisUsuarioNaInstituicao",query="select ui.papel from UsuarioInstituicaoEntity ui WHERE ui.instituicao.id = :idInstituicao AND ui.usuario.id = :idUsuario"),
		   //@NamedQuery(name="UsuarioEntity.removeUsuarioLogado",query="update UsuarioEntity u set u.logado = false, u.dtHoraLogin = null where u.id = :idUsuario"),
		   @NamedQuery(name="UsuarioEntity.countEsqueciSenha",query="select count(es.id) from EsqueciSenhaEntity es WHERE  es.usuario.login = :login and es.data = CURRENT_DATE"),
		   @NamedQuery(name="UsuarioEntity.recuperaUsuarioByEmail",query="select u from UsuarioEntity u where u.email = :email"),
		   //@NamedQuery(name="UsuarioEntity.recuperaUsuarioByCPF",query="select u from UsuarioEntity u where u.pessoa.cpf = :cpf"),
		   @NamedQuery(name="UsuarioEntity.verificaDisponibilidadeLogin",query="select count(u.id) from UsuarioEntity u where u.login = :login"),
		   @NamedQuery(name="UsuarioEntity.alterasenha",query="update UsuarioEntity u set u.senha = :novaSenha where u.id = :idUsuario"),
		   @NamedQuery(name="UsuarioEntity.recuperaUsuarioByLogin",query="select u from UsuarioEntity u where LOWER(u.login) = LOWER(:login)"),
		   @NamedQuery(name="UsuarioEntity.DeslogarUsuariosNoStartupAppServer",query="update UsuarioEntity u set u.logado = false where u.logado = true"),
		   //@NamedQuery(name="UsuarioEntity.recuperaUsuarioByIDPessoa",query="select u from UsuarioEntity u where u.pessoa.id = :idPessoa"),
		  @NamedQuery(name="UsuarioEntity.atualizaStatusUsuarioLogado",query="update UsuarioEntity u set u.logado = :statusLogado where u.id = :idUsuario")		   																						  	
		})
@Name("usuarioEntity")		
public class UsuarioEntity extends BaseEntity{
	
	
	@Id
	@SequenceGenerator(name="gen_usuario",sequenceName="usuario_seq")
	@GeneratedValue(generator="gen_usuario")
	private Long id;
	
	@NotNull(message="global obrigatório")
	@Column(nullable=false)
	private Boolean global = Boolean.FALSE;
	
	@Length(min=6,message="Login mínimo 6 caracteres.")
	@NotNull(message="login obrigatório")
	@Column(nullable=false,unique=true)
	private String login;

	@NotNull(message="nome obrigatório")
	@Length(min=6,message="nome inválido minimo 6 caracteres.")
	@Column(nullable=false)
	private String nome;
	
	@Email(message="email invalido")
	@NotNull(message="email obrigatorio")
	@Column(nullable=false)
	private String email;
	
	@NotNull(message="senha obrigatória")
	@Length(min=6,message="tamanho mínimo da senha de 6 caracteres.")
	@Column(nullable=false)
	private String senha;

	@Column(nullable=false)
	private Boolean logado = Boolean.FALSE;
	
	@OneToMany(mappedBy="usuario",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id",nullable=false)
	private List<EsqueciSenhaEntity> esquecisenha;

	@OneToMany(mappedBy="usuario",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id",nullable=false)
	private List<UsuarioInstituicaoEntity> usuarioinstituicao;

	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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


	public Boolean getLogado() {
		return logado;
	}



	public void setLogado(Boolean logado) {
		this.logado = logado;
	}



	public List<EsqueciSenhaEntity> getEsquecisenha() {
		return esquecisenha;
	}



	public void setEsquecisenha(List<EsqueciSenhaEntity> esquecisenha) {
		this.esquecisenha = esquecisenha;
	}



	public List<UsuarioInstituicaoEntity> getUsuarioinstituicao() {
		return usuarioinstituicao;
	}



	public void setUsuarioinstituicao(
			List<UsuarioInstituicaoEntity> usuarioinstituicao) {
		this.usuarioinstituicao = usuarioinstituicao;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	

	public Boolean getCamposObrigatoriosPreenchidos(){
		return !StringUtils.isBlank(nome) && !StringUtils.isBlank(senha) && !StringUtils.isBlank(this.email);
	}



	public Boolean getGlobal() {
		return global;
	}



	public void setGlobal(Boolean global) {
		this.global = global;
	}

}
