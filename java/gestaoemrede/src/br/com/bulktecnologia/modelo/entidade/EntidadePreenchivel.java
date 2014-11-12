package br.com.bulktecnologia.modelo.entidade;
/**
 * Interface que sinaliza que a entidade pode possuir alguma propriedade 
 * preenchida.
 * 
 * <br>
 * <b> usado para saber se houve preenchimento em alguma entidade onde nenhum campo é obrigatorio.
 * @author hsoares
 *
 */
public interface EntidadePreenchivel {

	public Boolean isPreenchido();
}
