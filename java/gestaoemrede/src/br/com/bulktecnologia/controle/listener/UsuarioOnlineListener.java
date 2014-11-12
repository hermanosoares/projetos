package br.com.bulktecnologia.controle.listener;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
@Name("usuarioOnlineListener")
public class UsuarioOnlineListener implements HttpSessionListener {

    private static final Logger log = Logger.getLogger(UsuarioOnlineListener.class);

    // Thread-safe read/write and non-blocking reads (snapshot reads)
    private static ConcurrentHashMap<String, HttpSession> usuariosOnlineAutenticados = new ConcurrentHashMap<String, HttpSession>();
    private static ConcurrentHashMap<String, HttpSession> usuariosOnlineNaoAutenticados = new ConcurrentHashMap<String, HttpSession>();

    public void sessionCreated(HttpSessionEvent event) {
    	usuariosOnlineNaoAutenticados.put(event.getSession().getId(), event.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        usuariosOnlineAutenticados.remove(event.getSession().getId());
        usuariosOnlineNaoAutenticados.remove(event.getSession().getId());
    }


    
    public static void contabilizaAcesso(HttpSession sessao){
    	if (sessao!=null){
    		if ( !usuariosOnlineAutenticados.containsKey(sessao.getId()) ){
    			usuariosOnlineAutenticados.put(sessao.getId(), sessao);	
    		}
    	}
    }
    
    @Factory("usuariosOnlineAutenticados")
    public Integer fabricaUsuariosOnlineAutenticados(){
    	return this.usuariosOnlineAutenticados.size();
    }
    
    
    @Factory("usuariosOnlineNaoAutenticados")
    public Integer fabricaUsuariosOnlineNaoAutenticados(){
    	return this.usuariosOnlineNaoAutenticados.size();
    }
    
    
}
