package br.com.bulktecnologia.modelo.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
/**
 * DTO
 * 
 * @author hsoares
 *
 */
public final class RegistroSelecionado implements Serializable {
	
	private Long id;
	private String url;
	private Class type;
	
	public RegistroSelecionado(final Long idRegistro,final String url,final Class type){
		this.id = idRegistro;
		this.url = url;
		this.type = type;
		
		if (idRegistro==null || StringUtils.isBlank(url) || type == null){
			throw new RuntimeException("todos os campos para uma seleção são necessário, verifique..");
		}
	}
	
	
	
	

	public Long getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public Class getType() {
		return type;
	}
	
	
}
