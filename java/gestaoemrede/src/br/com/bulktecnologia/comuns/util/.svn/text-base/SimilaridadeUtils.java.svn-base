package br.com.bulktecnologia.comuns.util;

public final class SimilaridadeUtils {

	
	
	
	
	public static Boolean similar(String target,String source){
		if (source!=null && target!=null){
			
			source  = AcentuacaoUtil.removeAcentos(source).trim();
			target = AcentuacaoUtil.removeAcentos(target).trim();
			
			
			if ( target.equalsIgnoreCase(source) || 
				 target.startsWith(source) || 
				 target.endsWith(source) || 
				 target.contains(source) ){
				return Boolean.TRUE;
			}
			
			String[] tokens = source.split(" ");
			for (String t: tokens){
				if (target.contains(t)){
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
	
	
	
	
	
	
	
	
}
