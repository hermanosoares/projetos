package br.com.bulktecnologia.comuns.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.bulktecnologia.modelo.entidade.ContatoEntity;

public class EntityHelper {
	
	private static Logger log = Logger.getLogger(EntityHelper.class);
	
	
	
	
	/**
	 * Metodo auxiliar que recebe uma entidade como parametro 
	 * verifica nela existe alguma propriedade preenchida.
	 * caso exista ele retornar TRUE, se todas propriedades estiverem nulas ou 
	 * vazias retorna FALSE.
	 * 
	 * 
	 * @author hsoares
	 * @param o - Entitidade
	 * @param ignorePropertiesList - lista com o nome das propriedades da entidade que deverão ser desprezadas, pois possuem valores pre-inicializados.
	 * @return Boolean
	 */
	public static final Boolean entityHasAnyPropertyFilled(Object o,List<String> ignorePropertiesList){
		if (o==null){
			return Boolean.FALSE;
		}
		
		Class c = o.getClass();
		for (Field f : c.getDeclaredFields()){
			
			if (ignorePropertiesList!=null &&  ignorePropertiesList.contains(f.getName())){
				continue;
			}
			
			if (PropertyUtils.isReadable(o, f.getName())){
				try {
					Object value = PropertyUtils.getProperty(o, f.getName());
					if (value!=null ){
						if (value instanceof String){
							String s = (String) value;
							if (!s.trim().equals("")){
								return Boolean.TRUE;
							}
						}
						else{
							return Boolean.TRUE;
						}
						
					}
					
				} catch (IllegalAccessException e) {
					log.fatal(e);
				} catch (InvocationTargetException e) {
					log.fatal(e);
				} catch (NoSuchMethodException e) {
					log.fatal(e);
				}
			}
		}
		
		return Boolean.FALSE;
	}
	
	
	
	
	
	public static void main(String[] args) {

	}
}
