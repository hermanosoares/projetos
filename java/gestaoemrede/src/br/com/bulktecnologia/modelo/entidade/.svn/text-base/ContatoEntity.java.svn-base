package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.validator.Email;
import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.comuns.util.EntityHelper;
import br.com.bulktecnologia.modelo.annotation.CampoObrigatorio;
@Entity
@Table(name="contato")
@Name("contatoEntity")
@Audited
public class ContatoEntity implements Serializable,EntidadePreenchivel {
	
	@Id
	@GeneratedValue(generator="contato_seq")
	@SequenceGenerator(name="contato_seq",sequenceName="contato_seq")
	private Long id;
	
	@CampoObrigatorio(nomeCampo="Telefone")
	@Column
	private Long telfixo1;
	
	@Column
	private Long telfixo2;
	
	@Column
	private Long telfixo3;
	
	@Column
	private Long ramal;
	
	@Column
	private Long celular1;
	
	@Column
	private Long celular2;
	
	@Column
	private String recado;

	@CampoObrigatorio(nomeCampo="Email")
	@Email
	@Column
	private String email1;

	@CampoObrigatorio(nomeCampo="Confirmação Email")
	@Transient
	private String confirmacaoEmail1;
	
	@Email
	@Column
	private String email2;

	@Transient
	private String confirmacaoEmail2;
	
	@Email
	@Column
	private String email3;

	@Transient
	private String confirmacaoEmail3;

	
	@Column
	private String site;

	@Column
	private String msn;
	
	@Column
	private String googletalk;
	
	@Column
	private String skype;
	
	@Column
	private String orkut;
	
	@Column
	private String twitter;

	
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTelfixo1() {
		return telfixo1;
	}
	public void setTelfixo1(Long telfixo1) {
		this.telfixo1 = telfixo1;
	}
	public Long getTelfixo2() {
		return telfixo2;
	}
	public void setTelfixo2(Long telfixo2) {
		this.telfixo2 = telfixo2;
	}
	public Long getTelfixo3() {
		return telfixo3;
	}
	public void setTelfixo3(Long telfixo3) {
		this.telfixo3 = telfixo3;
	}
	public Long getRamal() {
		return ramal;
	}
	public void setRamal(Long ramal) {
		this.ramal = ramal;
	}
	public Long getCelular1() {
		return celular1;
	}
	public void setCelular1(Long celular1) {
		this.celular1 = celular1;
	}
	public Long getCelular2() {
		return celular2;
	}
	public void setCelular2(Long celular2) {
		this.celular2 = celular2;
	}
	public String getRecado() {
		return recado;
	}
	public void setRecado(String recado) {
		this.recado = recado;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail3() {
		return email3;
	}
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public String getGoogletalk() {
		return googletalk;
	}
	public void setGoogletalk(String googletalk) {
		this.googletalk = googletalk;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getOrkut() {
		return orkut;
	}
	public void setOrkut(String orkut) {
		this.orkut = orkut;
	}
	
	public Boolean isPreenchido() {
		return EntityHelper.entityHasAnyPropertyFilled(this,null);
	}
	public String getConfirmacaoEmail1() {
		return confirmacaoEmail1;
	}
	public void setConfirmacaoEmail1(String confirmacaoEmail1) {
		this.confirmacaoEmail1 = confirmacaoEmail1;
	}
	public String getConfirmacaoEmail2() {
		return confirmacaoEmail2;
	}
	public void setConfirmacaoEmail2(String confirmacaoEmail2) {
		this.confirmacaoEmail2 = confirmacaoEmail2;
	}
	public String getConfirmacaoEmail3() {
		return confirmacaoEmail3;
	}
	public void setConfirmacaoEmail3(String confirmacaoEmail3) {
		this.confirmacaoEmail3 = confirmacaoEmail3;
	}

}
