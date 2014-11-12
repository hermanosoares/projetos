package br.com.bulktecnologia.controle.exception;


public class BusinessException extends Exception {

	public BusinessException(String exceptionCause, Throwable e) {
		super(exceptionCause, e);
	}

	public BusinessException(String exceptionCause) {
		super(exceptionCause);
	}
	
	public BusinessException() {
	}
	
}
