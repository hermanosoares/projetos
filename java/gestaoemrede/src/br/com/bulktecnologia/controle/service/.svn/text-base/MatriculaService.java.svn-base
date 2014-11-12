package br.com.bulktecnologia.controle.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.navigation.Pages;

import br.com.bulktecnologia.comuns.converter.FacesMessageSessionScoped;
import br.com.bulktecnologia.controle.exception.MatriculaException;
import br.com.bulktecnologia.controle.jsf.ExceptionHelper;
import br.com.bulktecnologia.modelo.dao.MatriculaDAO;
import br.com.bulktecnologia.modelo.dto.ResultadoMatriculaDTO;
import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.MatriculaDisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.MatriculaEntity;
import br.com.bulktecnologia.modelo.enums.TipoEnsino;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoSerie;
/**
 * Classe de Negócio responsavel pelo UC de Matricula.
 * 
 * @author hsoares
 *
 */
@Name("MatriculaService")
@Scope(ScopeType.CONVERSATION)
public class MatriculaService extends BaseCrudService{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	public MatriculaService(){
		super(false);
	}
	
	@In
	private MatriculaDAO MatriculaDAO;
	
	
	@In(required=false)
	@Out(required=false)
	private MatriculaEntity matriculaEntity;

	private List<MatriculaEntity> matriculasAtiva;
	private String fromViewId;

	private List<DisciplinaEntity> disciplinasDependentes;
	private DisciplinaEntity disciplinaDependente;
	
	private List<ResultadoMatriculaDTO> matriculasAlunoSelecionado;
	
	@In
	private FacesMessageSessionScoped FacesMessageSessionScoped;
	
	private ResultadoMatriculaDTO matriculaSelecionada;
	private ResultadoPesquisaAlunoDTO alunoSelecionado;
	
	public String encerraMatricula(){

			if ( this.matriculaEntity.getDtEncerramento()==null ){
				 this.matriculaEntity.setDtEncerramento( new Date() );
			}
			List<Long> idMatricula = new ArrayList<Long>();
			idMatricula.add(this.matriculaSelecionada.getIdMatricula());
			
			this.MatriculaDAO.atualizaEncerramentoMatricula(this.matriculaEntity.getDtEncerramento(),
															this.matriculaEntity.getTipoencerramentoMatricula().getId(),
															idMatricula);
			
			
			MatriculaDAO.flush();
			
			facesMessages.add(" Matrícula encerrada com sucesso !");
		 	
		 	this.matriculasAlunoSelecionado = this.MatriculaDAO.recuperaMatriculaByIdAluno(this.alunoSelecionado.getIdAluno());
		 	
		 	return PAGINA_LIST;
	}
	
	
	
	public String selecionaEncerramentoMatricula(ResultadoMatriculaDTO matriculaDto){
		this.matriculaSelecionada = matriculaDto;
		
		return "encerrar";
	}
	
	
	public String ativarMatricula(ResultadoMatriculaDTO matriculaDto){
	
		MatriculaEntity matriculaAtiva = this.MatriculaDAO.validaCadastroMatricula( this.alunoSelecionado.getIdAluno(),
																				    matriculaDto.getIdEnsino(), matriculaDto.getIdSerie(),
																				    matriculaDto.getIdTurno(), matriculaDto.getAnoAdm() ); 
		if (matriculaAtiva==null){
			
			List<Long> idMatricula = new ArrayList<Long>();
			idMatricula.add(matriculaDto.getIdMatricula());
			
			this.MatriculaDAO.atualizaEncerramentoMatricula(null,null,idMatricula);
			this.matriculasAlunoSelecionado = this.MatriculaDAO.recuperaMatriculaByIdAluno(this.alunoSelecionado.getIdAluno());
		}
		else{
			facesMessages.add("Impossível realizar matrícula, pois já existe uma matrícula regular ativa para o turno/ensino/serie selecionado");
		}
		
		return "refresh";
	}
	
	public String detalhes(ResultadoPesquisaAlunoDTO aluno){
		this.alunoSelecionado = aluno;
		this.matriculasAlunoSelecionado = this.MatriculaDAO.recuperaMatriculaByIdAluno(aluno.getIdAluno());
		
		for (ResultadoMatriculaDTO dto: this.matriculasAlunoSelecionado){
			if ( TipoMatricula.Dependencia.compareTo(dto.getTipoMatricula())==0 ){
				List<MatriculaDisciplinaEntity> mds = this.MatriculaDAO.recuperaMatriculaDisciplinasByIdMatricula( dto.getIdMatricula() );
				dto.setMatriculaDependencia(mds);
			}
		}
		
		return "detalhes";
	}
	
	
	
	
	/**
	 * Método de negócio que realiza inclusão de uma matricula.
	 * 
	 * @author hsoares
	 */
	public String insereMatricula(List<ResultadoPesquisaAlunoDTO> alunosSemMatricula){

		if ( validaMatricula(this.matriculaEntity) ){
			
			try {
				Integer affectedRows = MatriculaDAO.insereMatricula(alunosSemMatricula,
																	cookie.getIdInstituicao(),
																	cookie.getConfigEspecificaInstituicao().getAno(),
																	this.disciplinasDependentes,
																	matriculaEntity.getDtEncerramento(),
																	new Date(), 
																	cookie.getLogin(),
																	matriculaEntity.getNovatoEstudos(),
																	matriculaEntity.getTipoCondicaoFinalUltAno(),
																	matriculaEntity.getTipoMatricula(),
																	matriculaEntity.getEnsino(),
																	matriculaEntity.getSerie(),
																	matriculaEntity.getTurno());
				
				//flush manual para evitar do ajax, realizar um update em selecoes no combo.
				MatriculaDAO.flush();

				if (affectedRows>1){
					FacesMessageSessionScoped.add(affectedRows+" Matrículas realizadas com sucesso! ");
				}
				else{
					FacesMessageSessionScoped.add(" Matrícula de "+alunosSemMatricula.get(0).getNome()+" realizada com sucesso! ");
				}
				
				
			} catch (MatriculaException e) {
				this.matriculasAtiva = e.getMatriculasAtivas();
				facesMessages.add(e.getMessage());
				return PAGINA_ADD_EDIT;
			} 

		}
		else{
			return PAGINA_ADD_EDIT;
		}
		
		this.matriculaEntity = null;
		return PAGINA_LIST;
	}


	
	
	
	private Boolean validaMatricula(MatriculaEntity m){
		
		Boolean valido = Boolean.TRUE;

		if (m.getTipoMatricula()==null){
			facesMessages.add("Campo Obrigatório: Tipo Matricula");
			valido = Boolean.FALSE;
		}

		if (m.getTurno()==null){
			facesMessages.add("Campo Obrigatório: Turno");
			valido = Boolean.FALSE;
		}
		
		if (m.getEnsino()==null){
			facesMessages.add("Campo Obrigatório: Ensino");
			valido = Boolean.FALSE;
			
		}
		
		if (m.getSerie()==null){
			facesMessages.add("Campo Obrigatório: Série");
			valido = Boolean.FALSE;
		}
		
		if ( TipoMatricula.Dependencia.equals(m.getTipoMatricula()) && (this.disciplinasDependentes==null || this.disciplinasDependentes.size()==0) ){
			facesMessages.add("Matricula de Dependência precisa ter no mínimo uma disciplina");
			valido = Boolean.FALSE;
		}
		
		if ( m.getNovatoEstudos()!=null && !m.getNovatoEstudos() && m.getTipoCondicaoFinalUltAno()==null ){
			facesMessages.add("Campo Obrigatório: Condição Final do Ultimo Ano");
			valido = Boolean.FALSE;
		}
		
		return valido;
	}
	
	
	
	
	public String atualizarMatricula(MatriculaEntity matricula){
			
				if ( validaMatricula(matricula) ){
					
					MatriculaEntity matriculaJaExistente =	MatriculaDAO.validaCadastroMatricula(matricula.getAluno().getId(),
							matricula.getEnsino().getId(),
							matricula.getSerie().getId(), 
							matricula.getTurno().getId(), 
							matricula.getAnoAdm());

					if ( matriculaJaExistente==null || matricula.getId().compareTo(matriculaJaExistente.getId())==0){
						FacesMessageSessionScoped.add("Matricula de #{matriculaEntity.aluno.pessoa.nome} atualizada com sucesso!");
						super.grava(matricula);
						return PAGINA_LIST;
					}
					else{
						List<MatriculaEntity> matriculaJaAtiva = new ArrayList<MatriculaEntity>();
						matriculaJaAtiva.add(matriculaJaExistente);

						this.matriculasAtiva = matriculaJaAtiva;
						facesMessages.add("Aluno com Matrícula já ativa para o mesmo Ano, Tipo Ensino, Série e Turno");
						return PAGINA_ADD_EDIT;
					}
					
				}
				
				return PAGINA_ADD_EDIT;
				
		}
	
		
			
		
	

	
	
	
	



	@Override
	protected boolean antesGravar(Object o) {
		return true;
	}



	@Override
	protected boolean antesRemover(Object o) {
		return true;
	}



	@Override
	protected boolean antesRemoverDetalhe(Collection<Object> collectionMestre,Object detalhe) {
		return true;
	}



	@Override
	public String edita(Object o) {
		this.captureViewId();
		MatriculaEntity detachedObject = (MatriculaEntity)o;
		this.matriculaEntity = (MatriculaEntity) this.MatriculaDAO.find(MatriculaEntity.class, detachedObject.getId());
		this.disciplinasDependentes = this.MatriculaDAO.recuperaDisciplinasByMatriculaDependencia(this.matriculaEntity.getId());
		return PAGINA_ADD_EDIT;
	}


	
	/**
	 * Define a url de onde está vindo a requisicao.
	 * para se disparado o evento de 'cancelar'
	 * ele conseguir voltar para pagina onde estava anteriormente.
	 * 
	 * esta workaround foi criada, pois 'alunocommatriculalist.xhtml' e 'alunonaomatriculadolist.xhtml' 
	 * usam a mesma 'matricula.xhtml', e quando esta dispara cancelar ora irá para alunocommatriculalist.xhtml 
	 * e ora para alunonaomatriculadolist.xhtml.
	 * 
	 * @author hsoares
	 */
	public void captureViewId(){
		this.fromViewId = Pages.getCurrentViewId();
	}

	@Override
	public String insere() {
		return null;
	}




	public List<MatriculaEntity> getMatriculasAtiva() {
		return matriculasAtiva;
	}


	private Boolean validoAddDependencia(){
		
		Boolean valido = Boolean.TRUE;
		
		if ( this.disciplinaDependente==null ){
			 facesMessages.add("Campo Obrigatório: Dependência.");
			 valido = Boolean.FALSE;
		}

		return valido;
	}
	
	
	public String AddDependencia(){
	
		if ( validoAddDependencia() ){
			if (this.disciplinasDependentes==null){
				this.disciplinasDependentes = new ArrayList<DisciplinaEntity>();
			}
			
			Long qtdeDisciplinasAceitavel = this.matriculaEntity.getSerie().getNumDepenciaAceitavel();
			
			if (this.disciplinasDependentes.size() < qtdeDisciplinasAceitavel){
				
				if ( !this.disciplinasDependentes.contains(this.disciplinaDependente) ){
					this.disciplinasDependentes.add(this.disciplinaDependente);
					this.disciplinaDependente = null;
				}
				else{
					facesMessages.add("A Disciplina já selecionada!");
				}
				
			}
			else{
				facesMessages.add("Não é possível adicionar a disciplina, pois a : "+matriculaEntity.getSerie().getNome()+" Série Permite até "+qtdeDisciplinasAceitavel +" Dependência(s).");
			}
			
		}
		
		return PAGINA_ADD_EDIT;
	}
	
	

	public void removeDependencia(DisciplinaEntity disciplina){
		if ( this.disciplinasDependentes!=null){
			this.disciplinasDependentes.remove(disciplina);
		}	
	}
	
	
	public String getFromViewId() {
		if ( "/uc/matricula/alunonaomatriculadolist.xhtml".equals(fromViewId) ){
			return "cancelaFromAlunoNaoMatriculado";
		}
		else{
			if ("/uc/matricula/alunocommatriculalist.xhtml".equals(fromViewId) ){
				return "cancelaFromAlunoMatriculado";
			}
			else{
				 return null;
			}
		}
	}




	public DisciplinaEntity getDisciplinaDependente() {
		return disciplinaDependente;
	}




	public void setDisciplinaDependente(DisciplinaEntity disciplinaDependente) {
		this.disciplinaDependente = disciplinaDependente;
	}




	public List<DisciplinaEntity> getDisciplinasDependentes() {
		return disciplinasDependentes;
	}	

	private void limparDisciplinasDependentes(){
		if (this.disciplinasDependentes!=null){
			this.disciplinasDependentes.clear();
		}
	}
	
	public void handlerSerie(){
		if (this.matriculaEntity.getSerie()!=null){
			
			if ( TipoEnsino.Fundamental.equals(matriculaEntity.getEnsino().getTipoEnsino()) &&
				 TipoSerie.Primeiro.equals(this.matriculaEntity.getSerie().getTipoSerie()) ){
				this.matriculaEntity.setNovatoEstudos(true);
			}
			else{
				this.matriculaEntity.setNovatoEstudos(false);
			}
		}
		
		this.matriculaEntity.setTipoCondicaoFinalUltAno(null);		
		this.limparDisciplinasDependentes();
	}



	public List<ResultadoMatriculaDTO> getMatriculasAlunoSelecionado() {
		return matriculasAlunoSelecionado;
	}



	public ResultadoMatriculaDTO getMatriculaSelecionada() {
		return matriculaSelecionada;
	}



	public ResultadoPesquisaAlunoDTO getAlunoSelecionado() {
		return alunoSelecionado;
	}

}
