package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.bulktecnologia.modelo.enums.TipoLogout;
@Entity
@Table(name="auditoriaacesso")
@NamedQueries(value={
		@NamedQuery(name="AuditoriaAcessoEntity.atualizaDtHoraLogout",query="update AuditoriaAcessoEntity set dthoralogout = :dthoralogout, tipologout = :tipoLogout where id = :idAuditoriaAcesso"),
		@NamedQuery(name="AuditoriaAcessoEntity.atualizaDesligamentoServidor",query="update AuditoriaAcessoEntity set tipoLogout = 'SERVIDOR_DESLIGOU' where tipoLogout is null")
		})
public class AuditoriaAcessoEntity implements Serializable {
	
	@Id
	@GeneratedValue(generator="auditoriaacesso_seq")
	@SequenceGenerator(name="auditoriaacesso_seq",sequenceName="auditoriaacesso_seq")
	private Long id;
	
	@Column(nullable=true)
	private String ip;
	
	@Column(nullable=true)
	private String host;

	@Column(nullable=true)
	private String requestheader;
	
	@Column(nullable=false)
	private String login;

	@Column(nullable=false)
	private Timestamp dthoralogin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true)
	private Date dthoralogout;

	@Enumerated(EnumType.STRING)
	@Column
	private TipoLogout tipoLogout;

	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getRequestheader() {
		return requestheader;
	}


	public void setRequestheader(String requestheader) {
		this.requestheader = requestheader;
	}

	public Timestamp getDthoralogin() {
		return dthoralogin;
	}


	public void setDthoralogin(Timestamp dthoralogin) {
		this.dthoralogin = dthoralogin;
	}

	public Date getDthoralogout() {
		return dthoralogout;
	}

	public void setDthoralogout(Date dthoralogout) {
		this.dthoralogout = dthoralogout;
	}

	public TipoLogout getTipoLogout() {
		return tipoLogout;
	}

	public void setTipoLogout(TipoLogout tipoLogout) {
		this.tipoLogout = tipoLogout;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


}
