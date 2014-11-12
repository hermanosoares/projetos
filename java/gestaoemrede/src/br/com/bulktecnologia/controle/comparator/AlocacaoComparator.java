package br.com.bulktecnologia.controle.comparator;

import java.util.Comparator;

import br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO;
/**
 * 
 * Workaround que realiza a ordenação da lista com base em criterios especificos e peculiares da alocação de alunos.
 * 
 * @author hsoares
 *
 */
public final class AlocacaoComparator implements Comparator<ResultadoAlocacaoDTO> {

	public int compare(ResultadoAlocacaoDTO aloc1,ResultadoAlocacaoDTO aloc2) {
		
		if ( Boolean.FALSE.equals(aloc1.getFlagItemDiarioAberto()) ){
			return aloc1.getIdAlocacaoItem().compareTo(aloc2.getIdAlocacaoItem());
		}
		
		return 0;
	}
	
	

}
