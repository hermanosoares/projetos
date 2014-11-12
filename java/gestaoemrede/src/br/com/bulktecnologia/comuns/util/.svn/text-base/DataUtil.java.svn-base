package br.com.bulktecnologia.comuns.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DataUtil {
	
	
	public static Boolean isDataEstaCompreendida(Date data,Date inicio,Date fim){
		if (data.compareTo(inicio)==0){
			return Boolean.TRUE;
		}
		if (data.compareTo(fim)==0){
			return Boolean.TRUE;
		}
		
		return data.after(inicio) && data.before(fim);
	}



    public static int calculaIdade(Date dataNasc){
    	
    	if (dataNasc==null){
    		throw new RuntimeException("datanascimento nula, parametro invalido!");
    	}
    	
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNasc);
        

        Calendar today = Calendar.getInstance();
        

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        
        dateOfBirth.add(Calendar.YEAR, age);
        

        if (today.before(dateOfBirth)) {
            age--;
        }
        return age;
        
    }
 
    
    
    /**
     * devolve o ano atual usando 4 digitos para o ano
     * @return
     */
    public static Long getAnoAtual(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return new Long( sdf.format(new Date()) );
    }
    
    public static String formataDataDDMMYYYY(Date date){
    	
		if (date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(date);
		}	
    	
		return null;
    }
}
