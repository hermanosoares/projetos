package br.com.bulktecnologia.comuns.util;

public class AcentuacaoUtil {

	
	
	    public static String removeAcentos(String s) {
	        if ( s == null ) return s;
	        StringBuffer chars = new StringBuffer();

	        // Loop over the characters, replace those that need to be.
	        for (int i = 0; i < s.length(); i++) {
	            switch (s.charAt(i)) {
	               	case '\u00C0':
	                case '\u00C1':
	                case '\u00C2':
	                case '\u00C3':
	                case '\u00C4':
	                case '\u00C5':
	                    chars.append("A");
	                    break;
	                case '\u00C6':
	                    chars.append("AE");
	                    break;
	                case '\u00C7':
	                    chars.append("C");
	                    break;
	                case '\u00C8':
	                case '\u00C9':
	                case '\u00CA':
	                case '\u00CB':
	                    chars.append("E");
	                    break;
	                case '\u00CC':
	                case '\u00CD':
	                case '\u00CE':
	                case '\u00CF':
	                    chars.append("I");
	                    break;
	                case '\u00D0':
	                    chars.append("D");
	                    break;
	                case '\u00D1':
	                    chars.append("N");
	                    break;
	                case '\u00D2':
	                case '\u00D3':
	                case '\u00D4':
	                case '\u00D5':
	                case '\u00D6':
	                case '\u00D8':
	                    chars.append("O");
	                    break;
	                case '\u0152':
	                    chars.append("OE");
	                    break;
	                case '\u00DE':
	                    chars.append("TH");
	                    break;
	                case '\u00D9':
	                case '\u00DA':
	                case '\u00DB':
	                case '\u00DC':
	                    chars.append("U");
	                    break;
	                case '\u00DD':
	                case '\u0178':
	                    chars.append("Y");
	                    break;
	                case '\u00E0':
	                case '\u00E1':
	                case '\u00E2':
	                case '\u00E3':
	                case '\u00E4':
	                case '\u00E5':
	                    chars.append("a");
	                    break;
	                case '\u00E6':
	                    chars.append("ae");
	                    break;
	                case '\u00E7':
	                    chars.append("c");
	                    break;
	                case '\u00E8':
	                case '\u00E9':
	                case '\u00EA':
	                case '\u00EB':
	                    chars.append("e");
	                    break;
	                case '\u00EC':
	                case '\u00ED':
	                case '\u00EE':
	                case '\u00EF':
	                    chars.append("i");
	                    break;
	                case '\u00F0':
	                    chars.append("d");
	                    break;
	                case '\u00F1':
	                    chars.append("n");
	                    break;
	                case '\u00F2':
	                case '\u00F3':
	                case '\u00F4':
	                case '\u00F5':
	                case '\u00F6':
	                case '\u00F8':
	                    chars.append("o");
	                    break;
	                case '\u0153':
	                    chars.append("oe");
	                    break;
	                case '\u00DF':
	                    chars.append("ss");
	                    break;
	                case '\u00FE':
	                    chars.append("th");
	                    break;
	                case '\u00F9':
		            case '\u00FA':
		            case '\u00FB':
		            case '\u00FC':
	                    chars.append("u");
	                    break;
	                case '\u00FD':
	                case '\u00FF':
	                    chars.append("y");
	                    break;
	                default:
	                    chars.append(s.charAt(i));
	                    break;
	            }
	        }
	        return chars.toString();		
	}
	    
	    
}
