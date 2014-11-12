package br.com.bulktecnologia.controle.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;

import br.com.bulktecnologia.comuns.util.JSFUtil;
import br.com.bulktecnologia.comuns.util.TurmaHelper;
import br.com.bulktecnologia.controle.comparator.AlocacaoComparator;
import br.com.bulktecnologia.modelo.dao.AlocacaoDAO;
import br.com.bulktecnologia.modelo.dao.MatriculaDAO;
import br.com.bulktecnologia.modelo.dao.TurmaDAO;
import br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.entidade.TipoOrdenacao;
import br.com.bulktecnologia.modelo.entidade.TurmaEntity;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;
import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
@Name("AlocacaoList")
@Scope(ScopeType.CONVERSATION)
public class AlocacaoList extends GenericList implements Serializable {

	@In
	private TurmaDAO TurmaDAO;
	
	@In
	private MatriculaDAO MatriculaDAO;
	
	@In
	private AlocacaoDAO AlocacaoDAO;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//Filtros
	private List<ResultadoAlocacaoDTO> alunosNaoAlocados;
	
	private List<ResultadoAlocacaoDTO> alunosAlocados;
	
	private List<TipoFormacaoTurma> tiposFormacaoTurma;
	private List<TurmaEntity> turmasByFiltroTipoTurma;
	private List<Long> anosDiponiveisMatricula;
	private List<TurnoEntity> turnosDeMatriculasByAnoSelecionado;
	private List<EnsinoEntity> ensinosDeMatriculasByTurnoSelecionado;
	private List<SerieEntity> seriesDeMatriculasByEnsinoSelecionado;
	private List<TipoMatricula> tiposMatriculas;


	//Filtros Selecionados
	private TurnoEntity turnoSelecionado;
	private EnsinoEntity ensinoSelecionado;
	private SerieEntity serieSelecionado;
	private Long anoSelecionado;	
	private TipoFormacaoTurma tipoFormacaoTurmaSelecionado;
	private TurmaEntity turmaSelecionado;
	private DisciplinaEntity disciplinaSelecionado;
	private TipoMatricula tipoMatriculaSelecionada;


	public List<TipoFormacaoTurma> getTiposFormacaoTurma() {
		return tiposFormacaoTurma;
	}

	public List<Long> getAnosDiponiveisMatricula() {
		if (this.anosDiponiveisMatricula==null){
			this.anosDiponiveisMatricula = this.MatriculaDAO.recuperaAnosComMatriculasDisponiveis(this.cookie.getIdInstituicao());
		}
		return anosDiponiveisMatricula;
	}	
	
	public TipoFormacaoTurma getTipoFormacaoTurmaSelecionado() {
		return tipoFormacaoTurmaSelecionado;
	}


	public void setTipoFormacaoTurmaSelecionado(
			TipoFormacaoTurma tipoFormacaoTurmaSelecionado) {
		this.tipoFormacaoTurmaSelecionado = tipoFormacaoTurmaSelecionado;
	}

	public void carregaDadosFiltro2Alocacao(){
		this.turnosDeMatriculasByAnoSelecionado = this.MatriculaDAO.recuperaTurnosDeMatriculasByAno(this.cookie.getIdInstituicao(),this.getAnoSelecionado());
		limpaSelecaoAposTurno();
/*		this.turnoSelecionado = null;
		this.carregaGridAlunosNaoAlocados();
*/	}

	public void carregaDadosFiltro3Alocacao(){
		this.ensinosDeMatriculasByTurnoSelecionado = this.MatriculaDAO.recuperaEnsinosDeMatriculasByTurno(this.turnoSelecionado.getId());
		limpaSelecaoAposTurno();
/*		this.ensinoSelecionado = null;
		this.carregaGridAlunosNaoAlocados();
*/	}

	public void carregaDadosFiltro4Alocacao(){
		this.seriesDeMatriculasByEnsinoSelecionado = this.MatriculaDAO.recuperaSeriesDeMatriculasByEnsino(this.ensinoSelecionado.getId());
		this.limpaSelecaoAposEnsino();
/*		this.serieSelecionado = null;
		this.carregaGridAlunosNaoAlocados();
*/	}

	public void carregaDadosFiltro5Alocacao(){
		this.tiposMatriculas = this.MatriculaDAO.recuperaTiposMatriculasBySerie(this.cookie.getIdInstituicao(),this.serieSelecionado.getId());
		this.limpaSelecaoAposSerie();
/*		this.tipoMatriculaSelecionada = null;
		this.carregaGridAlunosNaoAlocados();
*/	}

	public void carregaDadosFiltro6Alocacao(){
		this.tiposFormacaoTurma = TurmaDAO.recuperaTiposFormacaoTurmaDaConfiguracaoAtiva(this.cookie.getConfigEspecificaInstituicao().getId());
		this.limpaSelecaoAposTipoMatricula();
/*		this.tipoFormacaoTurmaSelecionado = null;
		this.carregaGridAlunosNaoAlocados();
*/	}
	
	public void carregaDadosFiltro7Alocacao(){
		this.turmasByFiltroTipoTurma = this.TurmaDAO.recuperaTurmasDaConfiguracaoAtivaByTipoFormacaoTurma(this.cookie.getConfigEspecificaInstituicao().getId(), this.tipoFormacaoTurmaSelecionado,this.serieSelecionado.getId(),this.turnoSelecionado.getId());
		this.limpaSelecaoAposTipoFormacaoTurma();
/*		this.turmaSelecionado = null;
		this.carregaGridAlunosNaoAlocados();
*/	}
	

	public void limpaTodosFiltros(){
		this.turnosDeMatriculasByAnoSelecionado = null;
		this.turnoSelecionado = null;
		limpaSelecaoAposTurno();
	}
	
	private void limpaSelecaoAposTurno(){
		this.ensinoSelecionado = null;
		this.serieSelecionado = null;
		this.tipoMatriculaSelecionada = null;
		this.tipoFormacaoTurmaSelecionado = null;
		this.turmaSelecionado = null;
		this.disciplinaSelecionado = null;
	}
	

	private void limpaSelecaoAposEnsino(){
		this.serieSelecionado = null;
		this.tipoMatriculaSelecionada = null;
		this.tipoFormacaoTurmaSelecionado = null;
		this.turmaSelecionado = null;
		this.disciplinaSelecionado = null;
	}

	private void limpaSelecaoAposSerie(){
		this.tipoMatriculaSelecionada = null;
		this.tipoFormacaoTurmaSelecionado = null;
		this.turmaSelecionado = null;
		this.disciplinaSelecionado = null;
	}

	private void limpaSelecaoAposTipoMatricula(){
		this.tipoFormacaoTurmaSelecionado = null;
		this.turmaSelecionado = null;
		this.disciplinaSelecionado = null;
	}

	private void limpaSelecaoAposTipoFormacaoTurma(){
		this.turmaSelecionado = null;
		this.disciplinaSelecionado = null;
	}

	public void limpaSelecaoAposTurma(){
		this.disciplinaSelecionado = null;
	}

	
	public List<TurmaEntity> getTurmasByFiltroTipoTurma() {
		return this.turmasByFiltroTipoTurma;
	}


	public TurmaEntity getTurmaSelecionado() {
		return turmaSelecionado;
	}



	public void setTurmaSelecionado(TurmaEntity turmaSelecionado) {
		this.turmaSelecionado = turmaSelecionado;
	}
	

	public Boolean getRenderizaComboDisciplina(){
		if (this.turmaSelecionado!=null ){
			if (  TipoFormacaoTurma.Multi_Seriada_E_Por_Disciplina.equals(turmaSelecionado.getTipoTurma()) ||
				   TipoFormacaoTurma.Por_Disciplina.equals(turmaSelecionado.getTipoTurma())  ){
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}
	

	public Boolean getRenderizaDatatables(){
		
		if (turmaSelecionado!=null ){
			
			if (!getRenderizaComboDisciplina()){
				return Boolean.TRUE;	
			}
			else{
				if (this.disciplinaSelecionado!=null){
					return Boolean.TRUE;
				}
			}
			
		}
		
		return Boolean.FALSE;
		
	}

	public DisciplinaEntity getDisciplinaSelecionado() {
		return disciplinaSelecionado;
	}


	
	public void setDisciplinaSelecionado(DisciplinaEntity disciplinaSelecionado) {
		this.disciplinaSelecionado = disciplinaSelecionado;
	}

	public Long getAnoSelecionado() {
		return this.cookie.getConfigEspecificaInstituicao().getAno();
		//return anoSelecionado;
	}

	public void setAnoSelecionado(Long anoSelecionado) {
		this.anoSelecionado = anoSelecionado;
	}

	public void carregaGridAlunosAlocados(){
		
		if ( getPermiteCarregarGrids() ){
			
			StringBuffer orderBy = new StringBuffer();
			
			TipoOrdenacao tipoOrdenacaoTurma =  getTurmaSelecionado().getTipoOrdenacao();

			Boolean diarioAberto = null;
			if ( TurmaHelper.isTurmaPorSerie(tipoFormacaoTurmaSelecionado) ){
				diarioAberto =  this.AlocacaoDAO.recuperaStatusDoDiarioDeUmaAlocacaoRegular(getAnoSelecionado(), 
																						    getTipoMatriculaSelecionada(),
																						    getTurnoSelecionado().getId(),
																						    getEnsinoSelecionado().getId(),
																						    getSerieSelecionado().getId(),
																						    getTurmaSelecionado().getId());
			}
			else{
				if ( TurmaHelper.isTurmaPorDisciplina(tipoFormacaoTurmaSelecionado) ){
					
					if ( getDisciplinaSelecionado()!=null){
						
						diarioAberto =  this.AlocacaoDAO.recuperaStatusDoDiarioDeUmaAlocacaoDisciplina(getAnoSelecionado(), 
																									    getTipoMatriculaSelecionada(),
																									    getTurnoSelecionado().getId(),
																									    getEnsinoSelecionado().getId(),
																									    getSerieSelecionado().getId(),
																									    getTurmaSelecionado().getId(),
																									    getDisciplinaSelecionado().getId());
					}
					
				}
				else{
					throw new RuntimeException("tipo formacao de turma não previsto");
				}
				
			}
			
			
				if (TipoOrdenacao.Alfabetico.equals(tipoOrdenacaoTurma)){
					orderBy.append(" order by  alocItem.aluno.pessoa.nome ");
				}
				else{
					if (TipoOrdenacao.Masc_Primeiro.equals(tipoOrdenacaoTurma) ){
						orderBy.append(" order by  alocItem.aluno.pessoa.sexo desc, alocItem.aluno.pessoa.nome asc");
					}
					else{
						if (TipoOrdenacao.Fem_Primeiro.equals(tipoOrdenacaoTurma) ){
							orderBy.append(" order by  alocItem.aluno.pessoa.sexo asc, alocItem.aluno.pessoa.nome asc ");
						}
						else{
							if (TipoOrdenacao.Sequencial.equals(tipoOrdenacaoTurma) ){
								orderBy.append(" order by  alocItem.id ");
							}
							else{
								log.fatal(" #1# AlocacaoList.carregaGridAlunosAlocados() : tipo de ordenação não previsto.!");
							}
						}
					}
				}
			
				
			if ( Boolean.FALSE.equals(diarioAberto) ){
				orderBy.insert(9, " alocItem.diarioAbertoMomentoAlocacao desc, ");
			}
			
			this.alunosAlocados = this.AlocacaoDAO.recuperaAlunosAlocados(		 getAnoSelecionado(),
																				 getTurnoSelecionado().getId(),
																				 getEnsinoSelecionado().getId(),
																				 getSerieSelecionado().getId(),
																				 getTipoMatriculaSelecionada(),
																				 getDisciplinaSelecionado()!=null?getDisciplinaSelecionado().getId():null,
																				 getTurmaSelecionado().getId(),
																				 orderBy.toString());
			if (Boolean.FALSE.equals(diarioAberto)){
				Collections.sort(this.alunosAlocados,new AlocacaoComparator());	
			}
			
		}
		else{
			this.alunosAlocados = null;
		}
		
	}
	
	
	
	public void carregaGrids(){
		this.carregaGridAlunosNaoAlocados();
		this.carregaGridAlunosAlocados();
	}
	
	public void carregaGridAlunosNaoAlocados(){
		
		if ( getPermiteCarregarGrids() ){
			
				this.alunosNaoAlocados = this.AlocacaoDAO.recuperaAlunosNaoAlocados( cookie.getIdInstituicao(),
																					 getAnoSelecionado(),
																					 getTurnoSelecionado().getId(),
																					 getEnsinoSelecionado().getId(),
																					 getSerieSelecionado().getId(),
																					 getTipoMatriculaSelecionada(),
																					 getDisciplinaSelecionado()!=null?getDisciplinaSelecionado().getId():null,
																					 getTurmaSelecionado().getId(),
																					 getTipoFormacaoTurmaSelecionado());
				
			
		}
		else{
			this.alunosNaoAlocados = null;
		}
	}
	
	
	
	
	
	
	/**
	 * 
	 * Lógica de Seleção de combos aninhados.
	 * 
	 * @return
	 */
	public Boolean getPermiteCarregarGrids(){
		
		if ( getAnoSelecionado()!=null && getTurnoSelecionado() !=null && 
			 getEnsinoSelecionado()!=null &&	getSerieSelecionado()!=null &&
			 getTipoMatriculaSelecionada()!=null && getTipoFormacaoTurmaSelecionado()!=null	&&
			 getTurmaSelecionado()!=null ){
			
			if ( TipoMatricula.Regular.equals(tipoMatriculaSelecionada) ){
				if ( TurmaHelper.isTurmaPorDisciplina(this.tipoFormacaoTurmaSelecionado) && this.disciplinaSelecionado==null){
					return Boolean.FALSE;
				}
			}

			
			return Boolean.TRUE;
		}

		
		return Boolean.FALSE;
	}
	

	
	
	public TurnoEntity getTurnoSelecionado() {
		return turnoSelecionado;
	}

	public void setTurnoSelecionado(TurnoEntity turnoSelecionado) {
		this.turnoSelecionado = turnoSelecionado;
	}

	public EnsinoEntity getEnsinoSelecionado() {
		return ensinoSelecionado;
	}

	public void setEnsinoSelecionado(EnsinoEntity ensinoSelecionado) {
		this.ensinoSelecionado = ensinoSelecionado;
	}

	public SerieEntity getSerieSelecionado() {
		return serieSelecionado;
	}

	public void setSerieSelecionado(SerieEntity serieSelecionado) {
		this.serieSelecionado = serieSelecionado;
	}

	public TipoMatricula getTipoMatriculaSelecionada() {
		return tipoMatriculaSelecionada;
	}

	public void setTipoMatriculaSelecionada(TipoMatricula tipoMatriculaSelecionada) {
		this.tipoMatriculaSelecionada = tipoMatriculaSelecionada;
	}

	public List<ResultadoAlocacaoDTO> getAlunosNaoAlocados() {
		return alunosNaoAlocados;
	}

	public List<TurnoEntity> getTurnosDeMatriculasByAnoSelecionado() {
		if (turnosDeMatriculasByAnoSelecionado==null){
			this.carregaDadosFiltro2Alocacao();
		}
		return turnosDeMatriculasByAnoSelecionado;
	}

	public List<EnsinoEntity> getEnsinosDeMatriculasByTurnoSelecionado() {
		return ensinosDeMatriculasByTurnoSelecionado;
	}

	public List<SerieEntity> getSeriesDeMatriculasByEnsinoSelecionado() {
		return seriesDeMatriculasByEnsinoSelecionado;
	}

	public List<TipoMatricula> getTiposMatriculas() {
		return tiposMatriculas;
	}

	public Boolean getExisteAlunosNaoAlocadosSelecionados(){
		 return JSFUtil.obtemEntidadesSelecionadas(this.getAlunosNaoAlocados()).size() > 0;
	}

	public Boolean getExisteAlunosAlocadosSelecionados(){
		 return JSFUtil.obtemEntidadesSelecionadas(this.getAlunosAlocados()).size() > 0;
	}

	public List<ResultadoAlocacaoDTO> getAlunosAlocadosSelecionados(){
		 return (List<ResultadoAlocacaoDTO>) JSFUtil.obtemEntidadesSelecionadas(this.getAlunosAlocados());
	}
	
	public List<ResultadoAlocacaoDTO> getAlunosNaoAlocadosSelecionados(){
		 return (List<ResultadoAlocacaoDTO>) JSFUtil.obtemEntidadesSelecionadas(this.getAlunosNaoAlocados());
	}

	public List<ResultadoAlocacaoDTO> getAlunosAlocados() {
		return alunosAlocados;
	}

	public Boolean getRenderizaDiario(){
		return this.alunosAlocados!=null && this.alunosAlocados.size()>0;
	}
	
	
	public Boolean getDiarioAberto(){
		
		if ( this.alunosAlocados.size() > 0 ){
			return this.alunosAlocados.get(0).getDiarioAbertoAlocacao();
		}
		
		return Boolean.TRUE;
	}
	
	
	
	public String getStatusDiario(){
		if (getDiarioAberto()){
			return " Matrículas iniciais ";
		}
		else{
			return " Matrículas suplementares ";
		}
	}

	
	
	public List<TurnoEntity> getTurnosDisponiveisParaRemanejamento(){
		if ( permiteTrocaDeTurnoEmRemanejamento() ){
			TurnoList turnoList = (TurnoList)Component.getInstance("TurnoList");
			return turnoList.getTurnos();
		}
		else{
			List<TurnoEntity> turnos = new ArrayList<TurnoEntity>();
			turnos.add(this.turnoSelecionado);
			return turnos;
		}
	}
	
	
	
	/**
	 * Caso seja um remanejamento por disciplina não é permitido trocar de turno.
	 * caseo se faça necessário trocar de turno, deverá ser encerrado a matricula.
	 * 
	 * @return
	 */
	private Boolean permiteTrocaDeTurnoEmRemanejamento(){
		return this.getDisciplinaSelecionado()==null;
	}

	
}
