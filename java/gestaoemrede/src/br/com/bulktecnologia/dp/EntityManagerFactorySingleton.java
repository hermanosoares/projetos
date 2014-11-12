package br.com.bulktecnologia.dp;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	private static EntityManagerFactorySingleton instance;
	
	private EntityManagerFactorySingleton(){
	}
	
	public static EntityManagerFactorySingleton getInstance(){
		if ( instance == null ){
			 instance = new  EntityManagerFactorySingleton();
		}
		return instance;
	}
	
	
	private Map<String, EntityManagerFactory>  emfPUs = new HashMap<String, EntityManagerFactory>();

	
	public EntityManagerFactory getEMFactory(String pUName){
		EntityManagerFactory emfPU = emfPUs.get(pUName);
		if (emfPU == null){
		emfPU = Persistence.createEntityManagerFactory(pUName);
		emfPUs.put(pUName, emfPU);
		}
		return emfPU;
	}
	
	public EntityManagerFactory getDefaultEntityManagerFactory(){
		return this.getEMFactory("jiniPU");
	}
	
}
