package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.comuns.util.JSFUtil;
import br.com.bulktecnologia.modelo.dao.AlunoDAO;
import br.com.bulktecnologia.modelo.dao.MatriculaDAO;
import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO;
import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.enums.ModoVisualizacaoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoSelecaoMatricula;
@Name("MatriculaList")
@Scope(ScopeType.CONVERSATION)
public class MatriculaList extends GenericList implements Serializable {

	@In
	private MatriculaDAO MatriculaDAO;

	
	//resultados fabricados apartir dos filtros
	private List<ResultadoPesquisaAlunoDTO> alunosMatriculados;
	private List<ResultadoPesquisaAlunoDTO> alunosSemMatricula;
	private List<ResultadoPesquisaAlunoDTO> alunoSelecionadoByPesquisa;
	private List<Long> anosDiponiveisMatricula;
	
	//filtros selecionados by usuarios
	private TipoMatricula tipoMatriculaSelecionada;
	private TipoSelecaoMatricula tipoSelecaoMatricula;
	private ModoVisualizacaoMatricula modoVisualizacaoMatriculaSelecionado;
	
	public Boolean getTodosFiltrosPreenchidos(){
		if ( ModoVisualizacaoMatricula.Matriculados.equals(modoVisualizacaoMatriculaSelecionado) ){
			return tipoMatriculaSelecionada!=null && tipoSelecaoMatricula!=null?Boolean.TRUE:Boolean.FALSE;
		}
		else{
			if ( ModoVisualizacaoMatricula.Não_Matriculados.equals(modoVisualizacaoMatriculaSelecionado) ){
				return Boolean.TRUE;
			}
			else{
				return Boolean.FALSE;
			}
			
		}
		
	}
	
	
	public Boolean getModoMatriculadoSelecionado(){
		return ModoVisualizacaoMatricula.Matriculados.equals(this.modoVisualizacaoMatriculaSelecionado);
	}
	
	
	public Boolean getModoNaoMatriculadoSelecionado(){
		return ModoVisualizacaoMatricula.Não_Matriculados.equals(this.modoVisualizacaoMatriculaSelecionado);
	}

	
	public TipoMatricula getTipoMatriculaSelecionada() {
		return tipoMatriculaSelecionada;
	}

	public void setTipoMatriculaSelecionada(TipoMatricula tipoMatriculaSelecionada) {
		this.tipoMatriculaSelecionada = tipoMatriculaSelecionada;
	}

	public TipoSelecaoMatricula getTipoSelecaoMatricula() {
		return tipoSelecaoMatricula;
	}

	public void setTipoSelecaoMatricula(TipoSelecaoMatricula tipoSelecaoMatricula) {
		this.tipoSelecaoMatricula = tipoSelecaoMatricula;
	}
	
	public ModoVisualizacaoMatricula getModoVisualizacaoMatriculaSelecionado() {
		return modoVisualizacaoMatriculaSelecionado;
	}

	public void setModoVisualizacaoMatriculaSelecionado(
			ModoVisualizacaoMatricula modoVisualizacaoMatriculaSelecionado) {
		this.modoVisualizacaoMatriculaSelecionado = modoVisualizacaoMatriculaSelecionado;
	}


	public List<ResultadoPesquisaAlunoDTO> getAlunosMatriculados() {
		if ( this.alunosMatriculados==null){
			if (this.tipoSelecaoMatricula!=null){
				this.alunosMatriculados = this.MatriculaDAO.recuperaAlunosMatriculados(this.cookie.getIdInstituicao(),this.cookie.getConfigEspecificaInstituicao().getAno(),this.tipoMatriculaSelecionada, this.tipoSelecaoMatricula);	
			}
		}
		
		return this.alunosMatriculados;
	}
	
	public void cleanupDataModel(){
		this.alunosMatriculados = null;
		this.alunosSemMatricula = null;
	}

	public void cleanupControlesSelecao(){
		this.tipoMatriculaSelecionada = null;
		this.tipoSelecaoMatricula = null;
	}
	
	@Observer("MatriculaList.cleanupCompleto")
	public void cleanupCompleto(){
		this.cleanupDataModel();
		this.cleanupControlesSelecao();
	}
	
	
	public List<ResultadoPesquisaAlunoDTO> getAlunosSemMatricula() {
		if (this.alunosSemMatricula==null){
			this.alunosSemMatricula =  this.MatriculaDAO.recuperaAlunosSemMatricula(this.cookie.getIdInstituicao());
		}
		return alunosSemMatricula;
	}
	
	
	public List<ResultadoPesquisaAlunoDTO> getDataModel() {
		
		if (this.alunoSelecionadoByPesquisa!=null){
			return alunoSelecionadoByPesquisa;
		}
		else{
			if ( getModoMatriculadoSelecionado() ){
				return getAlunosMatriculados();
			}
			else{
				if (getModoNaoMatriculadoSelecionado()){
					return getAlunosSemMatricula();
				}
				else{
					return null;
				}
			}
		}
	}
	
	
	public void select(Boolean status){
		
		for (ResultadoPesquisaAlunoDTO dto: this.getDataModel()){
			dto.setSelecionado(status);
		}
		
	}
	
	public List<ResultadoPesquisaAlunoDTO> getDataModelSelecionados(){
		
		Long idAluno = cookie.getRegistroSelecionado(AlunoEntity.class,true);
		
		if (idAluno!=null){
			this.alunoSelecionadoByPesquisa = new ArrayList<ResultadoPesquisaAlunoDTO>();
			AlunoDAO dao =  (AlunoDAO) Component.getInstance("AlunoDAO");
			ResultadoPesquisaAlunoDTO dto  = dao.recuperaDtoAlunoByIdAluno(idAluno);
			dto.setSelecionado(true);
			this.alunoSelecionadoByPesquisa.add(dto);			
		}
		
		return (List) JSFUtil.obtemEntidadesSelecionadas(this.getDataModel());
	}


	public List<Long> getAnosDiponiveisMatricula() {
		if (this.anosDiponiveisMatricula==null || this.anosDiponiveisMatricula.size()==0){
			this.anosDiponiveisMatricula = this.MatriculaDAO.recuperaAnosComMatriculasDisponiveis(this.cookie.getIdInstituicao());
		}		
		return anosDiponiveisMatricula;
	}

	public Boolean getMatriculaRegularSelecionado(){
		return TipoMatricula.Regular.compareTo(this.tipoMatriculaSelecionada)==0;
	}
	

	
}
