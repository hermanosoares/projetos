package br.com.bulktecnologia.controle.exception;
/**
 * Exception disparada quando há alguma inconsistencia no cadastro
 * de pessoa no sistema.
 * independente se a pessoa é ALUNO, DOCENTE ou USUARIO.
 * 
 * @author hsoares
 *
 */
public class CadastroPessoaException extends BusinessException {

	public CadastroPessoaException(String exceptionCause){
		super(exceptionCause);
	}
	
	
}
