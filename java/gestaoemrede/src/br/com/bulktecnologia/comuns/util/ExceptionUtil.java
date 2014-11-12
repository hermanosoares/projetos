package br.com.bulktecnologia.comuns.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

	
	
	public static String toString(Throwable t){
		if (t!=null){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			return sw.getBuffer().toString();
		}
		return null;
	}
	
	
	
}
