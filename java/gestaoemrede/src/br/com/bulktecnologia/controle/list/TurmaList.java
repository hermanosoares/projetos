package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.dao.TurmaDAO;
import br.com.bulktecnologia.modelo.entidade.TurmaEntity;
@Name("TurmaList")
@Scope(ScopeType.CONVERSATION)
public class TurmaList extends GenericList implements Serializable {

	@In
	private TurmaDAO TurmaDAO;
	
	private List<TurmaEntity> turmasDaConfAtiva;



	@Create
	public void aoInicializar(){
		recarregar();
	}

	@Observer("TurmaList.evento.recarregar")
	public void recarregar(){
		turmasDaConfAtiva = TurmaDAO.recuperaTodasTurmasDaConfiguracaoAtiva(this.cookie.getConfigEspecificaInstituicao().getId());
	}

	public List<TurmaEntity> getTurmasDaConfAtiva() {
		return turmasDaConfAtiva;
	}



	
	
	
}
