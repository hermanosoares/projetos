package br.com.bulktecnologia.controle.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
@Entity
@Table(name="revisao")
@RevisionEntity(MyRevisionListener.class)
public class MyRevisionEntity extends DefaultRevisionEntity {
	
	@Column(nullable=false)
	private String login;
	
	@Column(nullable=false)
	private Long auditoriaacessoid;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Long getAuditoriaacessoid() {
		return auditoriaacessoid;
	}
	public void setAuditoriaacessoid(Long auditoriaacessoid) {
		this.auditoriaacessoid = auditoriaacessoid;
	}
	

}
