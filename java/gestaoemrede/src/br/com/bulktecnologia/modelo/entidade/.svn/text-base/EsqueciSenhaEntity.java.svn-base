package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="esquecisenha")
public class EsqueciSenhaEntity implements Serializable{
	
	@Id
	@GeneratedValue(generator="esquecisenha_gen")
	@SequenceGenerator(name="esquecisenha_gen",sequenceName="esquecisenha_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private UsuarioEntity usuario;
	
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date data;


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


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}
	

}
