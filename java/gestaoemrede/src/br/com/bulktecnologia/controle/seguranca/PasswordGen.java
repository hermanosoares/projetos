package br.com.bulktecnologia.controle.seguranca;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

public class PasswordGen {
	
	
	private static Logger log = Logger.getLogger(PasswordGen.class);

	
	
	/**
	 * Criptografa password do usuario usando MD5.
	 * @param password - senha sem criptografia
	 * @return String - senha criptografada
	 */
	public static String encript(String password){
		
		if ( !StringUtils.isBlank(password) ){
	        try {     
	            MessageDigest digest = MessageDigest.getInstance("MD5");      
	            digest.update(password.getBytes());      
	            BASE64Encoder encoder = new BASE64Encoder ();      
	            return encoder.encode (digest.digest ());
	       } catch (NoSuchAlgorithmException e) {     
	            log.fatal(e);
	       }    		
		}
		
		
		return null;
	}
	

	/**
	 * Gera um password aleatorio com 4 letras minusculas e 4 numeros no final da String.
	 * 
	 * @return String com 8 caracteres.
	 */
	public static String generateRandomPassword(){
		return RandomStringUtils.randomAlphabetic(4).toLowerCase() + RandomStringUtils.randomNumeric(4);		
	}
	
}
