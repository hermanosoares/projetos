package br.com.bulktecnologia.controle.service;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.comuns.util.ClassIntrospectorUtil;
@Name("versaoService")
@AutoCreate
public class VersaoService {
	    
	private String versao;
	private String release;
	
	
	public String getApplicationVersion(){
		
		try {
			Date d = getLastDateBuildVersion();
			SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
			return "V_"+versao+"."+release+"_"+sdf1.format(d)+"_"+sdf2.format(d);
		} catch (Exception e) {
			return "-";
		}
	}

	public Date getLastDateBuildVersion() throws Exception{
		
		URL directoryBase = this.getClass().getClassLoader().getResource("br");
		
		File f = new File(directoryBase.toURI());
		
		List<File> files = ClassIntrospectorUtil.findClasses2(f,"br");
		
		Date data = null;
		
		for (File f2: files){
			 Date dateClass = new Date( f2.lastModified() );
			
			 if ( data == null ){
				  data = dateClass; 
			 }
			 else{
				 if (dateClass.after(data)){
					 data = dateClass;
				 }
			 }
		}
		
		return data;
	}
	
	
	
	  
	

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}
	
	
}
