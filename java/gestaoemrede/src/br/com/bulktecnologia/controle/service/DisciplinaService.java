package br.com.bulktecnologia.controle.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.comuns.converter.FacesMessageSessionScoped;
import br.com.bulktecnologia.comuns.util.SimilaridadeUtils;
import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;

@Name("DisciplinaService")
@Scope(ScopeType.CONVERSATION)
public class DisciplinaService extends BaseCrudService{

	@In(required=false)
	@Out(required=false)
	private SerieEntity serieEntity;
	
	@In(required=false)
	@Out(required=false)
	private DisciplinaEntity disciplinaEntity;
	
	@In
	private FacesMessageSessionScoped FacesMessageSessionScoped;
	
	public DisciplinaService(){
		super(false);
	}
	
	public void sugereNomeDisciplina(){
		String label = this.disciplinaEntity.getTipoDisciplina().getLabel();
		this.disciplinaEntity.setNome(label);
		
		String[] conteudo = label.split(" ");
		
		if (conteudo!=null){
			if ( conteudo.length == 1 ){
				criaSugestaoDeLegenda(conteudo[0]);
			}
			else{
				if (conteudo.length > 1){
					criaSugestaoDeLegenda(conteudo[1]);				
				}
			}
		}
		
		
	}

	private void criaSugestaoDeLegenda(String valor){
		if (valor!=null){
			if ( valor.length()>3 ){
				this.disciplinaEntity.setLegenda(valor.substring(0,4));
			}
			else{
				if ( valor.length()>2 ){
					this.disciplinaEntity.setLegenda(valor.substring(0,3));	
				}
			}
		}
	}
	
	
	public String novo(){
		return PAGINA_ADD_EDIT;
	}
	
	@Override
	public String cancela() {
		if (cookie.getConfigEspecificaInstituicao()==null){
			return "intituicoesensino";
		}
		else{
			return "cancela";
		}
	}
	
	@Override
	protected boolean antesGravar(Object o) {
		boolean ok = true;
		
		if (this.disciplinaEntity.getTipoAvaliacao()==null){
			facesMessages.add("campo obrigatório: Tipo da Avaliação");
			ok = false;
		}

		
		if (this.disciplinaEntity.getTipoDisciplina()==null){
			facesMessages.add("campo obrigatório: Disciplina");
			ok = false;
		}

		if (StringUtils.isBlank(this.disciplinaEntity.getLegenda())){
			facesMessages.add("campo obrigatório: Legenda");
			ok = false;
		}
		if (this.disciplinaEntity.getNumAulaSemana()==null){
			facesMessages.add("campo obrigatório: Qtde. de Aulas Semanais");
			ok = false;
		}

		if (this.disciplinaEntity.getDuracaoModAula()==null){
			facesMessages.add("campo obrigatório: Duração Módulo Aula");
			ok = false;
		}

		if (this.disciplinaEntity.getAtividade()==null){
			facesMessages.add("campo obrigatório: Atividade");
			ok = false;
		}

		if (this.disciplinaEntity.getPertence()==null){
			facesMessages.add("campo obrigatório: Pertence");
			ok = false;
		}

		if (this.disciplinaEntity.getCompCargHoraria()==null){
			facesMessages.add("campo obrigatório: Computa Carga Horária");
			ok = false;
		}

		if (!ok){
			return Boolean.FALSE;	
		}

				
		//this.disciplinaEntity.setNome( this.disciplinaEntity.getTipoDisciplina().getLabel() );
		this.disciplinaEntity.setSerie( this.serieEntity );

		if ( this.serieEntity.getDisciplinas()==null ){
			 this.serieEntity.setDisciplinas(new ArrayList<DisciplinaEntity>());
		}
		else{
			if ( ! isManaged(this.disciplinaEntity) ){
				if ( existeDuplicidadeDisciplina(this.disciplinaEntity)){
					return Boolean.FALSE;	
				}
			}
		}

		this.serieEntity.getDisciplinas().add(this.disciplinaEntity);

		FacesMessageSessionScoped.add("Disciplina "+this.disciplinaEntity.getNome()+" salva com sucesso!");

		//limpa o objeto para poder adicionar uma nova disciplina.
		this.disciplinaEntity = new DisciplinaEntity();
		
		
		return Boolean.TRUE;
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
		this.serieEntity = (SerieEntity)o;
		return PAGINA_LIST;
	}

	public String editaDisciplina(DisciplinaEntity d){
		this.disciplinaEntity = d;
		return PAGINA_ADD_EDIT;
	}
	
	@Override
	public String insere() {
		return null;
	}

	

	
	public void validaNomeEmDuplicidadeDisciplinaByAjax(DisciplinaEntity d){
		if ( this.existeDuplicidadeDisciplina(d) ){
			d.setNome(null);
		}
	}
	
	
	
	public Boolean existeDuplicidadeDisciplina(DisciplinaEntity disc){

		for (int i=0;i<this.serieEntity.getDisciplinas().size();i++){
			DisciplinaEntity d = this.serieEntity.getDisciplinas().get(i);

				if (disc!=null){
					if ( SimilaridadeUtils.similar(disc.getNome(),d.getNome()) ){
						facesMessages.add("#{disciplinaEntity.nome} já cadastrado!, selecione outro.");
						return Boolean.TRUE;
					}
				}
		}
	
		return Boolean.FALSE;
	}
			

		
	
	
	
	
	
	
	
}
