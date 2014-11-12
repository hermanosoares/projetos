package br.com.bulktecnologia.comuns.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
/**
 * Classe auxiliar que realiza tratamento de acentuacao Latin1, usada no Brasil e Franca.
 * 
 * @author hsoares
 *
 */
public class PortugueseLatin1TextConverter {

    public final static String[] REPLACES = { "a","e", "i", "o", "u", "c", "A", "E", "I", "O", "U", "C","n","N","y","Y" };  
    
    public static Pattern[] PATTERNS = null;  
   
   public static void compilePatterns() {  
       PATTERNS = new Pattern[REPLACES.length];  
       PATTERNS[0]  = Pattern.compile("[âãáàä]");  
       PATTERNS[1]  = Pattern.compile("[éèêë]");  
       PATTERNS[2]  = Pattern.compile("[íìîï]");  
       PATTERNS[3]  = Pattern.compile("[óòôõö]");  
       PATTERNS[4]  = Pattern.compile("[úùûü]");  
       PATTERNS[5]  = Pattern.compile("[ç]");  
       PATTERNS[6]  = Pattern.compile("[ÂÃÁÀÄ]");  
       PATTERNS[7]  = Pattern.compile("[ÉÈÊË]");  
       PATTERNS[8]  = Pattern.compile("[ÍÌÎÏ]");  
       PATTERNS[9]  = Pattern.compile("[ÓÒÔÕÖ]");  
       PATTERNS[10] = Pattern.compile("[ÚÙÛÜ]");  
       PATTERNS[11] = Pattern.compile("[Ç]");  
       PATTERNS[12] = Pattern.compile("[ñ]");
       PATTERNS[13] = Pattern.compile("[Ñ]");
       PATTERNS[14] = Pattern.compile("[ýÿ]");
       PATTERNS[15] = Pattern.compile("[Ý]");
    }  
   

    /** 
.     * Substitui os caracteres acentuados por nao acentuados. 
     *  
     * @param text 
     * @return 
    */  
    private static String replaceSpecial(String text) {  
       if (PATTERNS == null) {  
          compilePatterns();  
       }  
   
       String result = text;  
       for (int i = 0; i < PATTERNS.length; i++) {  
          Matcher matcher = PATTERNS[i].matcher(result);  
          result = matcher.replaceAll(REPLACES[i]);  
       }  
       return result;  
    }  
    
    
	/**
	 * 	remove palavras que podem, comprometer a recuperação da pessoa atraves dos campos obrigatorios.
	 * 	pois em muitos casos estas palavras são ora preenchidas ora não, 
	 *	causando numa recuperação AND uma negacao por falta ou colocação da mesma.
	 *	por isso estas 'palavras complemento' são removidas APENAS em PESQUISA/RECUPERAÇÃO.
	 *		   
	 *
	 * @param nome
	 * @return nome
	 */
	private static String removeComplementoEmNome(String nome){
		StringBuffer sb = new StringBuffer();
		
		final String[] notWords = {"de","do","dos","du","da","das","di"};
		List<String> not = new ArrayList<String>(Arrays.asList(notWords));
		
		String[] nomeSplited = nome.trim().split(" ");
		
		for (String s: nomeSplited){
			if ( s.length() > 1 && !not.contains(s.toLowerCase()) ){
				sb.append(s).append(" ");
			}
		}
		
		return sb.toString().trim();
	}
	
	
	
	
	/**
	 *  workaround criada para tratar problemas com acentuacao e caracteres especiais em nomes para usar
	 *  em recuperações.
	 * @param nome
	 * @return String
	 */
    public static String trataNomeISOLatin1(String nome){
        if ( StringUtils.isBlank(nome) ){
    		return nome;
       }
    	String nomeTratado = removeAcentuacao(removeComplementoEmNome(nome));
    	return nomeTratado;
    }
    
    
    
    private static String removeAcentuacao(String s){
        if ( StringUtils.isBlank(s) ){
    		return s;
       }

    	return replaceSpecial(s).trim().toLowerCase();
    }
    
    
    
}
