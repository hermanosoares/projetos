package br.com.bulktecnologia.comuns.util;

import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;
/**
 * Classe com regra auxiliar usado por logicas de alocacao, matricula, e outros.. 
 * 
 * @author hsoares
 *
 */
public class TurmaHelper {

	
	
	
	public static Boolean isTurmaPorDisciplina(TipoFormacaoTurma tipoFormacaoTurma){
		if ( TipoFormacaoTurma.Por_Disciplina.equals(tipoFormacaoTurma) ||
			 TipoFormacaoTurma.Multi_Seriada_E_Por_Disciplina.equals(tipoFormacaoTurma) ){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	
	
	
	public static Boolean isTurmaPorSerie(TipoFormacaoTurma tipoFormacaoTurma){
		if ( TipoFormacaoTurma.Multi_Seriada.equals(tipoFormacaoTurma) ||
			 TipoFormacaoTurma.Regular.equals(tipoFormacaoTurma) ){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	
	

}
