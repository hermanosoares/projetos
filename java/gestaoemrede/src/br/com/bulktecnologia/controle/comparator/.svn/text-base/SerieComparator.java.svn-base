package br.com.bulktecnologia.controle.comparator;

import java.util.Comparator;

import br.com.bulktecnologia.modelo.entidade.SerieEntity;
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
public class SerieComparator implements Comparator<SerieEntity> {

	public int compare(SerieEntity s1, SerieEntity s2) {
		return s1.getTipoSerie().compareTo(s2.getTipoSerie());
	}

}
