package br.com.bulktecnologia.controle.listener;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.Transactional;

import br.com.bulktecnologia.controle.service.VersaoService;

@Name("AppServerStartupListener")
@Scope(ScopeType.APPLICATION)
@Startup
public class AppServerStartupListener {

	@In(required=false)
	private EntityManager entityManager;
	
	@In
	private VersaoService versaoService;
	
	private String versaoSoftware;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Create
	@Transactional
	public void aoInicializar(){
		
		if (this.entityManager!=null){
			Query q = this.entityManager.createNamedQuery("UsuarioEntity.DeslogarUsuariosNoStartupAppServer");
			int affectedRows = q.executeUpdate();
			log.info("** Deslogado no banco de dados  "+affectedRows + " usuários.");
			
			Query q2 = this.entityManager.createNamedQuery("AuditoriaAcessoEntity.atualizaDesligamentoServidor");
			int affectedRows2  =q2.executeUpdate();
			
			if ( affectedRows2 > 0 ){
				log.info("Atualizou desligamento de servidor ");	
			}
			
		}
		
		if (versaoSoftware==null){
			versaoSoftware = this.versaoService.getApplicationVersion();
		}
		
	}
	
	
	
	
	@Observer("AppServerStartupListener.DesbloquearTodosAcessos")
	public void DesbloquearAcessos(){
		this.aoInicializar();
	}




	public String getVersaoSoftware() {
		return versaoSoftware;
	}
	
	
	
}