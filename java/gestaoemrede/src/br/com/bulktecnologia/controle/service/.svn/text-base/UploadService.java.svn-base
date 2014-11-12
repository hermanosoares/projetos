package br.com.bulktecnologia.controle.service;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

@Name("UploadService")
public class UploadService  implements Serializable{
	
	@In(required=false,scope=ScopeType.CONVERSATION)
	@Out(required=false,scope=ScopeType.CONVERSATION)
	private List<File> logoMarcas;

	@Begin(join=true)
	public void upload(UploadEvent event){
		
		if ( logoMarcas == null ){
  			 logoMarcas = new ArrayList<File>();
		}
		
		if ( logoMarcas.size() < 3 && validaTamanho(event.getUploadItem()) ){
			 logoMarcas.add(event.getUploadItem().getFile());
		}
		
	}
	
	

	private boolean validaTamanho(UploadItem item){

		if (item.getFile().length() > (1024*300)){
			return false;
		}
		return true;
	}


	public void cleanSnapShotbyAjax(){

	}
	
}
