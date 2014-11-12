package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;

@Name("TipoEnsinoList")
@Scope(ScopeType.CONVERSATION)
public class TipoEnsinoList extends GenericList implements Serializable{
	
	
/*	@In
	private TipoEnsinoDAO TipoEnsinoDAO;
*/
	
	public List<EnsinoEntity> getEnsinos(TurnoEntity turnoSelecionado){
		return turnoSelecionado.getEnsinos();
		//return this.TipoEnsinoDAO.recuperaEnsinosByIdInstituicaoEIdTurno(cookie.getIdInstituicao(), turnoSelecionado.getId());	
	}
	
	
}
