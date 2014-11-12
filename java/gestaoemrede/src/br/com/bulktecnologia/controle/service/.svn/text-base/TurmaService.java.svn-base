package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.comuns.util.TurmaToolTipUtil;
import br.com.bulktecnologia.controle.comparator.DisciplinaComparator;
import br.com.bulktecnologia.controle.comparator.SerieComparator;
import br.com.bulktecnologia.modelo.dao.TurmaDAO;
import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.entidade.TurmaEntity;
import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;

@Name("TurmaService")
@Scope(ScopeType.CONVERSATION)
public class TurmaService extends BaseCrudService implements Serializable{
	
	@In
	private TurmaDAO TurmaDAO;
	
	@In(required=false)
	@Out(required=false)
	private TurmaEntity turmaEntity;
	
	private SerieEntity serieSelecionada;
	private DisciplinaEntity disciplinaSelecionado;
	
	public TurmaService(){
		super(false);
	}
	
	
	public String validaSelecaoSerie(){
		
		if ( TipoFormacaoTurma.Regular.equals(this.turmaEntity.getTipoTurma()) || 
			 TipoFormacaoTurma.Multi_Seriada.equals(this.turmaEntity.getTipoTurma()) ){
			if (serieSelecionada.getDisciplinas()==null || serieSelecionada.getDisciplinas().size()==0){
				this.serieSelecionada = null;
				facesMessages.add("a série selecionada não possui disciplinas configuradas a mesma.");
			}
		}
		
		return PAGINA_ADD_EDIT;
	}
	
	public String addSerieNaTurma(){
		if ( this.turmaEntity.getSeries()==null ){
			 this.turmaEntity.setSeries(new ArrayList<SerieEntity>());
		}
			if ( !this.turmaEntity.getSeries().contains(this.serieSelecionada) ){
				this.turmaEntity.getSeries().add(this.serieSelecionada);
			}
			else{
				facesMessages.add("a série selecionada já existe na turma.");
			}
		
		return PAGINA_ADD_EDIT;
	}


	public String addDisciplinaNaTurma(){
		
		if ( this.turmaEntity.getDisciplinas()==null ){
			 this.turmaEntity.setDisciplinas(new ArrayList<DisciplinaEntity>());
		}

		if ( !this.turmaEntity.getDisciplinas().contains(this.disciplinaSelecionado) ){
			this.turmaEntity.getDisciplinas().add(this.disciplinaSelecionado);
		}
		else{
			facesMessages.add("a disciplina selecionada já existe na turma.");
		}
		
		return PAGINA_ADD_EDIT;
	}

	
	
	public Boolean getTipoRegularSelecionado(){
		
		if (    turmaEntity!=null &&
				turmaEntity.getTipoTurma()!=null &&
				TipoFormacaoTurma.Regular.equals(turmaEntity.getTipoTurma())){
			
			if (turmaEntity.getSeries()!=null && turmaEntity.getSeries().size()>=1){
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	public Boolean getTipoMultiSeriadaSelecionado(){
		
		if (    turmaEntity!=null &&
				turmaEntity.getTipoTurma()!=null &&
				TipoFormacaoTurma.Multi_Seriada.equals(turmaEntity.getTipoTurma()) ){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	public Boolean getTipoMultiSeriadaEPorDisciplinaSelecionado(){
		
		if (    turmaEntity!=null &&
				turmaEntity.getTipoTurma()!=null &&
				TipoFormacaoTurma.Multi_Seriada_E_Por_Disciplina.equals(turmaEntity.getTipoTurma()) ){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	public Boolean getTipoDisciplinaSelecionado(){
		
		if (    turmaEntity!=null &&
				turmaEntity.getTipoTurma()!=null &&
				TipoFormacaoTurma.Por_Disciplina.equals(turmaEntity.getTipoTurma()) ){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	public Boolean getDisabilitarComboTurno(){
		if ( turmaEntity!=null ){ 
			if ( TipoFormacaoTurma.Multi_Seriada.equals(this.turmaEntity.getTipoTurma()) && this.turmaEntity.getSeries().size()>0){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	public String edita(TurmaEntity turma){
		this.turmaEntity = turma;
		return PAGINA_ADD_EDIT;
	}
	
	
	
	
	
	
	public String novo(){
		this.turmaEntity = new TurmaEntity();
		return PAGINA_ADD_EDIT;
	}
	
	
	
	
	public String removeSerieDaTurma(SerieEntity serie, TurmaEntity turma){
		turma.getSeries().remove(serie);
		serie.getTurmas().remove(turma);
		
		return PAGINA_ADD_EDIT;
	}
	

	
	public String removeDisciplinaDaTurma(DisciplinaEntity disciplina, TurmaEntity turma){
		turma.getDisciplinas().remove(disciplina);
		disciplina.getTurmas().remove(turma);
		
		return PAGINA_ADD_EDIT;
	}

	


	public SerieEntity getSerieSelecionada() {
		return serieSelecionada;
	}

	public void setSerieSelecionada(SerieEntity serieSelecionada) {
		this.serieSelecionada = serieSelecionada;
	}
	
	@Override
	protected boolean antesGravar(Object o) {
		
		TurmaEntity turma = (TurmaEntity)o;
		
		if ( !isManaged(turma) ){
			ConfiguracaoEntity c = this.BaseDAO.getEm().find(ConfiguracaoEntity.class, this.cookie.getConfigEspecificaInstituicao().getId());
			turma.setConfiguracao(c);
		}
		
		FiltroSelecaoService selecao = (FiltroSelecaoService) Component.getInstance("FiltroSelecaoService");
		turma.setTurno(selecao.getTurnoSelecionado());
		turma.setEnsino(selecao.getEnsinoSelecionado());
		
		//garante a entrada ordenada.
		if (turma.getSeries()!=null){
			Collections.sort(turma.getSeries(), new SerieComparator());	
		}
		
		//garante a entrada ordenada.
		if (turma.getDisciplinas()!=null){
			Collections.sort(turma.getDisciplinas(), new DisciplinaComparator());
		}

		TurmaToolTipUtil.applyToolTip(turma);

		
		if (  (turma.getDisciplinas() ==null || turma.getDisciplinas().size() ==0) &&
			  (turma.getSeries()==null || turma.getSeries().size()==0) 	){
			 facesMessages.add("Turma incompleta, selecione o tipo de turma. ");
			 return false;
		}
		else{
			facesMessages.add("Turma adicionada com sucesso.");
			return true;
		}
		
	}

	@Override
	protected boolean antesRemover(Object o) {
		return true;
	}

	/**
	 * remove relacoes de turma com turmaserie e turmadisciplina.
	 * 
	 * caso existam registros associados, uma excessao será lancada
	 * e o seam tratara a mensagem em tela.
	 * 
	 * @param turma
	 * @return
	 */
	public String removeTurma(TurmaEntity turma){
		if ( turma.getSeries()!=null ){
			for (SerieEntity s: turma.getSeries()){
				s.getTurmas().remove(turma);
			}
			turma.getSeries().clear();
		}
		
		if (turma.getDisciplinas()!=null){
			for (DisciplinaEntity d: turma.getDisciplinas()){
				d.getTurmas().remove(turma);
			}
			turma.getDisciplinas().clear();
		}
		
		super.remove(turma);
		this.TurmaDAO.flush();
		
		return PAGINA_LIST;
	}
	
	
	
	
	@Override
	protected boolean antesRemoverDetalhe(Collection<Object> collectionMestre,Object detalhe) {
		return true;
	}



	@Override
	public String edita(Object o) {
		return null;
	}

	@Override
	public String insere() {
		return null;
	}



	public DisciplinaEntity getDisciplinaSelecionado() {
		return disciplinaSelecionado;
	}





	public void setDisciplinaSelecionado(DisciplinaEntity disciplinaSelecionado) {
		this.disciplinaSelecionado = disciplinaSelecionado;
	}






	
	
}
