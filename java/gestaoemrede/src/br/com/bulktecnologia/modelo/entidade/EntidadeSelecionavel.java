package br.com.bulktecnologia.modelo.entidade;
/**
 * Interface que sinaliza que a entidade foi selecionada na interface (ui).
 * comumente selecoes via checkbox ou option. 
 * 
 * 
 * <br>
 * <b> usado para saber se a entidade foi selecionada ou não. 
 * 
 * @author hsoares
 *
 */
public interface EntidadeSelecionavel {
	
	public Boolean getSelecionado();
	public void setSelecionado(Boolean selecionado);
}
