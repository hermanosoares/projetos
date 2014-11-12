package br.com.bulktecnologia.controle.comparator;

import java.util.Comparator;

import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
/**
 * Comparator usado para realizar Ordenacao na Collection
 * Collections.sort(colecao,this);
 * 
 * para ordernar os registros da collection na ordem declarada 
 * nos tipos das respectivas entidades;
 * 
 * @author hsoares
 *
 */
public class DisciplinaComparator implements Comparator<DisciplinaEntity> {

	public int compare(DisciplinaEntity d1, DisciplinaEntity d2) {
		return d1.getTipoDisciplina().compareTo(d2.getTipoDisciplina());
	}
	
	


}
