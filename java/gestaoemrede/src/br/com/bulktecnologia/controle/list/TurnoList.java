package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.TurnoDAO;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;

@Name("TurnoList")
@Scope(ScopeType.CONVERSATION)
public class TurnoList extends GenericList implements Serializable{

	public List<TurnoEntity> turnos;
	
	@In
	private TurnoDAO TurnoDAO;
	
	@Create
	public void aoInicializar(){
		recarregar();
	}

	@Observer("TurnoList.evento.recarregar")
	public void recarregar(){
		if ( cookie.getIdConfigGlobalSelecionada()!=null ){
			turnos = TurnoDAO.recuperaTurnosByIdConfiguracao( cookie.getIdConfigGlobalSelecionada() );	
		}
		else{
			turnos = TurnoDAO.recuperaTurnosByIdConfiguracao( cookie.getConfigEspecificaInstituicao().getId() );
		}
	}

	public List<TurnoEntity> getTurnos() {
		return turnos;
	}

}
