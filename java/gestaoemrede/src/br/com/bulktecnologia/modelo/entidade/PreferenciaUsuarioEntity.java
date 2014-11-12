package br.com.bulktecnologia.modelo.entidade;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

@Entity
@Table(name="preferenciausuario")
@Name("preferenciaUsuarioEntity")
@NamedQueries(value={
			@NamedQuery(name="PreferenciaUsuarioEntity.recuperaPreferencia",query="select p from PreferenciaUsuarioEntity p where p.usuario.id = :idUsuario and p.chave = :chave")
})
public class PreferenciaUsuarioEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(generator="preferenciausuario_gen")
	@SequenceGenerator(name="preferenciausuario_gen",sequenceName="preferenciausuario_seq")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private UsuarioEntity usuario;
	
	@NotNull(message="nome da preferencia")
	@Column(nullable=false)
	private String chave;
	
	@NotNull(message="valor da preferencia")
	private String valor;

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

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
}
