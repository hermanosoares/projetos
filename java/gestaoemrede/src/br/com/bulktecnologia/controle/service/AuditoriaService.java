package br.com.bulktecnologia.controle.service;

import java.sql.Timestamp;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.UsuarioDAO;
import br.com.bulktecnologia.modelo.entidade.AuditoriaAcessoEntity;

@Name("AuditoriaService")
@Scope(ScopeType.CONVERSATION)
public class AuditoriaService extends GenericService{

	@In
	private UsuarioDAO UsuarioDAO;
	
	/**
	 * gera a auditoria de acesso e retorna o ID deste registro da auditoria criado.
	 * 
	 * @param login
	 * @param host           
	 * @param ip
	 * @param httpHeader
	 * 
	 * @return IdAuditoriaAcesso
	 */
	public Long criaAuditoriaAcesso(String login,String host,String ip,String httpHeader){
		
		AuditoriaAcessoEntity auditoria = new AuditoriaAcessoEntity();
		auditoria.setLogin(login);
		auditoria.setDthoralogin(new Timestamp(System.currentTimeMillis()));
		auditoria.setHost(host);
		auditoria.setIp(ip);
		auditoria.setRequestheader(httpHeader);
		
		UsuarioDAO.persist(auditoria);
		return auditoria.getId();
	}
	
	
	
	
	
	
}
