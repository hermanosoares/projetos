package br.com.bulktecnologia.comuns.util;

import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.entidade.TurmaEntity;

public class TurmaToolTipUtil {

	
	
	
	
	public static final void applyToolTip(TurmaEntity t){
		
		if (t!=null){
			StringBuffer sb = new StringBuffer();
			sb.append("( ");
				sb.append(t.getTurno().getTipoturno().getSigla());
				sb.append(" ");
				sb.append(t.getEnsino().getTipoEnsino().getSigla());
				sb.append(" ");
				sb.append( t.getTipoTurma().getSigla() );
				sb.append(" ");

				if (t.getSeries()!=null){
					for (SerieEntity s: t.getSeries()){
						sb.append(s.getTipoSerie().getSigla());
						sb.append(" ");		
					}
				}
								
				


				if (t.getDisciplinas()!=null){
					for (DisciplinaEntity d: t.getDisciplinas()){
						sb.append(d.getTipoDisciplina().getSigla());
						sb.append(" ");
					}
				}
				
			sb.append(" )");
			
			t.setTooltip(sb.toString());
		}
		
	}
	
	
	
	
	
	
	
}
