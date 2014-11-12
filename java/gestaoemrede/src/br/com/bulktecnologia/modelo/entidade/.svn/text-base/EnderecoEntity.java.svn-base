package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.jboss.seam.annotations.Name;
import org.xseam.domain.City;
import org.xseam.domain.State;

import br.com.bulktecnologia.comuns.util.EntityHelper;
@Entity
@Table(name="endereco")
@Name("enderecoEntity")
@Audited
public class EnderecoEntity implements Serializable,EntidadePreenchivel {

	@Id
	@Column(nullable=false)
	@GeneratedValue(generator="endereco_seq")
	@SequenceGenerator(name="endereco_seq",sequenceName="endereco_seq")
	private Long id;
	
	@Column(nullable=false)
	private String rua;
	
	@Column(nullable=false)
	private String numero;
	
	@Column(nullable=true)
	private String complemento;
	
	@Column(nullable=false)
	private String bairro;
	
	@Column(nullable=false)
	private City cidade;
	
	@Column(nullable=true)
	private String distrito;
	
	@Column(nullable=false)
	private String zona;
	
	@Column(nullable=true)
	private String iptu;
	
	@Column(nullable=true)
	private Long cep;
	
	@Column(nullable=true)
	private String observacao;

	@Column(nullable=false)
	private State estado;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}



	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getIptu() {
		return iptu;
	}

	public void setIptu(String iptu) {
		this.iptu = iptu;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}



	public Boolean isPreenchido() {
		return EntityHelper.entityHasAnyPropertyFilled(this,null);
	}
	
	public void setCidade(City cidade) {
		this.cidade = cidade;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	public City getCidade() {
		return cidade;
	}

	public State getEstado() {
		return estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	

	
	

}

