package br.com.bulktecnologia.controle.jsf;

import org.jboss.seam.annotations.Name;

@Name("ExceptionHelper")
public class ExceptionHelper {
	
	private StringBuffer sb = new StringBuffer();
	
	private Class<? extends Exception> exceptionType;
	
	private ExceptionHelper(Class<? extends Exception> clazzType){
		this.exceptionType  = clazzType;
	}
	
	public static ExceptionHelper begin(Class<? extends Exception> ExceptionType){
		return new ExceptionHelper(ExceptionType);
	}
	
	public void add(String exception){
		this.sb.append(exception);
		this.sb.append("\n");
	}
	
	public void end()throws Exception{
		
		if ( sb.length() > 0 ){
			Exception e = this.exceptionType.getConstructor(String.class).newInstance(sb.toString());
			throw e;
		}
		
	}
	
	
}
