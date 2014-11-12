package br.com.bulktecnologia.controle.service;

import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.comuns.util.DataUtil;
import br.com.bulktecnologia.modelo.dao.InstituicaoDAO;
import br.com.bulktecnologia.modelo.entidade.AdesaoEntity;
import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
import br.com.bulktecnologia.modelo.entidade.ContatoEntity;
import br.com.bulktecnologia.modelo.entidade.DisciplinaEntity;
import br.com.bulktecnologia.modelo.entidade.EnderecoEntity;
import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.entidade.EtapaEntity;
import br.com.bulktecnologia.modelo.entidade.EtapaRecuperacaoEntity;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.PeriodoLetivoEntity;
import br.com.bulktecnologia.modelo.entidade.SerieEntity;
import br.com.bulktecnologia.modelo.entidade.TipoEncerramentoMatriculaEntity;
import br.com.bulktecnologia.modelo.entidade.TipoFuncaoEntity;
import br.com.bulktecnologia.modelo.entidade.TurnoEntity;
import br.com.bulktecnologia.modelo.enums.TipoEncerramentoMatricula;

@Name("InstituicaoService")
@Scope(ScopeType.CONVERSATION)
public class InstituicaoService extends BaseCrudService{
	
	private Boolean novaAdesao = Boolean.TRUE;
	private AdesaoEntity adesaoSelecionada;
	private ConfiguracaoEntity configuracaoTemplateSelecionada;
	

	public InstituicaoService(){
		super(false);
	}
	
	
	@In(required=false)
	@Out(required=false)
	private EnderecoEntity enderecoEntity;
	
	@In(required=false)
	@Out(required=false)
	private InstituicaoEntity instituicaoEntity;

	@In(required=false)
	@Out(required=false)
	private ContatoEntity contatoEntityInstituicao;

	@In
	private InstituicaoDAO InstituicaoDAO;
	
	@In(required=false)
	@Out(required=false)
	private AdesaoEntity adesaoEntity;
	
	public void copiarEndereco(){
		this.adesaoEntity.setEstado(this.enderecoEntity.getEstado());
		this.adesaoEntity.setCidade(this.enderecoEntity.getCidade());
	}
	
	public String grava(){
		
		if ( validaInsercaoAtualizacaoInstituicao(this.instituicaoEntity) ){
			if ( !isManaged(this.instituicaoEntity) ){
				insereInstituicao();
				facesMessages.add("Instituição #{instituicaoEntity.nome} inserida com sucesso!");
			}
			else{
				this.InstituicaoDAO.merge(instituicaoEntity);
				this.InstituicaoDAO.merge(enderecoEntity);
				this.InstituicaoDAO.merge(contatoEntityInstituicao);
				if (adesaoEntity!=null){
					this.InstituicaoDAO.merge(adesaoEntity);
				}
				this.cookie.setNomeInstituicao(this.instituicaoEntity.getNome());
				facesMessages.add("Instituição #{instituicaoEntity.nome} atualizada com sucesso!");
			}
			
			InstituicaoDAO.flush();
			
			return "sucesso";
		}
		
		
		return null;
	}
	
	
	
	
	
	/**
	 * Valida inserção e atualização de Instituição.
	 * 
	 * @param instituicao
	 * @return
	 */
	public Boolean validaInsercaoAtualizacaoInstituicao(InstituicaoEntity instituicao){

		InstituicaoEntity instituicaoJaCadastrada = this.InstituicaoDAO.recuperaInstituicaoByCodInstituicao(instituicao.getCodInstituicao());
		
		if ( isManaged(this.instituicaoEntity) ){
				if ( instituicaoJaCadastrada !=null && instituicao.getId().compareTo(instituicaoJaCadastrada.getId())!=0 ){
					facesMessages.add("O Cod. Instituição:"+instituicaoJaCadastrada.getCodInstituicao()+" já está cadastrado para ");
					facesMessages.add(instituicaoJaCadastrada.getNome());
					facesMessages.add("verifique dados digitados.");
					return Boolean.FALSE;
				}
				else{
					return Boolean.TRUE;
				}
		}
		else{
			if (instituicaoJaCadastrada!=null){
				facesMessages.add("O Cod. Instituição:"+instituicaoJaCadastrada.getCodInstituicao()+" já está cadastrado para ");
				facesMessages.add(instituicaoJaCadastrada.getNome());
				facesMessages.add("verifique dados digitados.");
				return Boolean.FALSE;
			}
			else{
				return Boolean.TRUE;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	private void insereInstituicao(){
		
		
		if ( !contatoEntityInstituicao.getEmail1().equals(contatoEntityInstituicao.getConfirmacaoEmail1()) ){
			facesMessages.add("email da instituicao não confere verifique!");
			return;
		}
		
		
		AdesaoEntity adesao = null;
		if (!novaAdesao){
			adesao = adesaoSelecionada;
		}
		else{
			adesao = this.adesaoEntity;
		}
			instituicaoEntity.setContato(contatoEntityInstituicao);
			instituicaoEntity.setEndereco(enderecoEntity);
			instituicaoEntity.setAdesao(adesao);
			

			this.criaConfiguracaoEspecificaBaseadaConfiguracaoGlobal();
			
			if (novaAdesao){
				InstituicaoDAO.persist(adesao);	
			}
			
			TipoFuncaoEntity tipoFuncao1 = new TipoFuncaoEntity();
			tipoFuncao1.setNome("Professor");
			tipoFuncao1.setInstituicao(instituicaoEntity);
			
			TipoFuncaoEntity tipoFuncao2 = new TipoFuncaoEntity();
			tipoFuncao2.setNome("Secretário");
			tipoFuncao2.setInstituicao(instituicaoEntity);
			
			TipoFuncaoEntity tipoFuncao3 = new TipoFuncaoEntity();
			tipoFuncao3.setNome("Auxíliar de Educação");
			tipoFuncao3.setInstituicao(instituicaoEntity);
			
			TipoFuncaoEntity tipoFuncao4 = new TipoFuncaoEntity();
			tipoFuncao4.setNome("Diretor");
			tipoFuncao4.setInstituicao(instituicaoEntity);

			TipoEncerramentoMatriculaEntity encerramento0 = new TipoEncerramentoMatriculaEntity();
			encerramento0.setNome(TipoEncerramentoMatricula.Remanejamento.toString());
			encerramento0.setInstituicao(instituicaoEntity);
			encerramento0.setPermiteSelecao(Boolean.FALSE);
			
			TipoEncerramentoMatriculaEntity encerramento1 = new TipoEncerramentoMatriculaEntity();
			encerramento1.setNome(TipoEncerramentoMatricula.Abandono.toString());
			encerramento1.setInstituicao(instituicaoEntity);

			TipoEncerramentoMatriculaEntity encerramento2 = new TipoEncerramentoMatriculaEntity();
			encerramento2.setNome(TipoEncerramentoMatricula.Evasão.toString());
			encerramento2.setInstituicao(instituicaoEntity);

			TipoEncerramentoMatriculaEntity encerramento3 = new TipoEncerramentoMatriculaEntity();
			encerramento3.setNome(TipoEncerramentoMatricula.Erro.toString());
			encerramento3.setInstituicao(instituicaoEntity);

			TipoEncerramentoMatriculaEntity encerramento4 = new TipoEncerramentoMatriculaEntity();
			encerramento4.setNome(TipoEncerramentoMatricula.Falecimento.toString());
			encerramento4.setInstituicao(instituicaoEntity);
			
			InstituicaoDAO.persist(instituicaoEntity);
			InstituicaoDAO.persist(tipoFuncao1);
			InstituicaoDAO.persist(tipoFuncao2);
			InstituicaoDAO.persist(tipoFuncao3);
			InstituicaoDAO.persist(tipoFuncao4);
			
			InstituicaoDAO.persist(encerramento0);
			InstituicaoDAO.persist(encerramento1);
			InstituicaoDAO.persist(encerramento2);
			InstituicaoDAO.persist(encerramento3);
			InstituicaoDAO.persist(encerramento4);
			//InstituicaoDAO.persist(configuracao);
			
	}
	
	/**
	 * troca status de novaAdesao.
	 */
	public void novaAdesaoOnOff(){ 
		this.novaAdesao = !novaAdesao;
		if (novaAdesao){
			this.adesaoSelecionada=null;
		}
	}
	
	
	/**
	 * Cria configuração específica baseada em uma configuração Global.
	 * @param configGlobal
	 * @return Configuração Específica Criada.
	 */
	private void criaConfiguracaoEspecificaBaseadaConfiguracaoGlobal(){
		
		ConfiguracaoEntity configEspecifica = new ConfiguracaoEntity();
		configEspecifica.setAno(DataUtil.getAnoAtual());
		configEspecifica.setInstituicao(instituicaoEntity);
		configEspecifica.setNome("Configuração-Setup-Especifica-"+instituicaoEntity.getNome());
		
		
		this.InstituicaoDAO.persist(configEspecifica);
		
		this.createTurnos(configEspecifica,this.configuracaoTemplateSelecionada);
		
	}

	private void createTurnos(ConfiguracaoEntity configEspecifica,ConfiguracaoEntity configGlobal){
		
		for (TurnoEntity item: configGlobal.getTurnos()){
			TurnoEntity t = new TurnoEntity();
			t.setTipoturno(item.getTipoturno());
			t.setNome(item.getNome());
			t.setConfiguracao(configEspecifica);
			this.InstituicaoDAO.persist(t);
			createEnsinos(t, item);
		}
	}
	
	
	
	private void createEnsinos(TurnoEntity turnoEspecifico,TurnoEntity turnoGlobal){
		
		for (EnsinoEntity item: turnoGlobal.getEnsinos()){
			EnsinoEntity e = new EnsinoEntity();
			e.setNome(item.getNome());
			e.setTurno(turnoEspecifico);
			e.setTipoEnsino(item.getTipoEnsino());
			this.InstituicaoDAO.persist(e);
			createSeries(e, item);
		}
		
	}
	
	
	
	private void createSeries(EnsinoEntity ensinoEspecifico,EnsinoEntity ensinoGlobal){
		
		for (SerieEntity item: ensinoGlobal.getSeries()){
			SerieEntity s = new SerieEntity();
			s.setNome(item.getNome());
			s.setEnsino(ensinoEspecifico);
			s.setNotaMaxima(item.getNotaMaxima());
			s.setNumDepenciaAceitavel(item.getNumDepenciaAceitavel());
			s.setPercentFreqAprov(item.getPercentFreqAprov());
			s.setPercentNotaAprov(item.getPercentNotaAprov());
			s.setReprovaPorAvaliacao(item.getReprovaPorAvaliacao());
			s.setReprovaPorFrequencia(item.getReprovaPorFrequencia());
			s.setTipoSerie(item.getTipoSerie());
			this.InstituicaoDAO.persist(s);
			this.createDisciplinas(s, item);
			this.createEtapas(s, item);
		}
		
	}
	
	
	private void createEtapas(SerieEntity serieEspecifica,SerieEntity serieGlobal){
		
		for (EtapaEntity item: serieGlobal.getEtapas()){
			EtapaEntity e = new EtapaEntity();
			e.setNome(item.getNome());
			e.setNomeReduzido(item.getNomeReduzido());
			e.setNotaMaximaEtapa(item.getNotaMaximaEtapa());
			e.setSerie(serieEspecifica);
			this.InstituicaoDAO.persist(e);
		}
	}
	
	

/*	private void createPeriodosLetivos(EnsinoEntity ensinoEspecifico,EnsinoEntity ensinoGlobal){
		
		for (PeriodoLetivoEntity item: ensinoGlobal.getPeriodosLetivos()){
			PeriodoLetivoEntity p = new PeriodoLetivoEntity();
			p.setNome(item.getNome());
			p.setEnsino(ensinoEspecifico);
			p.setDtInicio(item.getDtInicio());
			p.setDtTermino(item.getDtTermino());
			p.setDtFechamentoDiario(item.getDtFechamentoDiario());
			this.InstituicaoDAO.persist(p);
		}
		
	}*/
	
	private void createDisciplinas(SerieEntity serieEspecifica, SerieEntity serieGlobal){
		
		for (DisciplinaEntity item : serieGlobal.getDisciplinas()){
			DisciplinaEntity d = new DisciplinaEntity();
			d.setNome(item.getNome());
			d.setAtividade(item.getAtividade());
			d.setCompCargHoraria(item.getCompCargHoraria());
			d.setDuracaoModAula(item.getDuracaoModAula());
			d.setLegenda(item.getLegenda());
			d.setTipoAvaliacao(item.getTipoAvaliacao());
			d.setNumAulaSemana(item.getNumAulaSemana());
			d.setParticipaResultFinal(item.getParticipaResultFinal());
			d.setPertence(item.getPertence());
			d.setSerie(serieEspecifica);
			d.setTipoDisciplina(item.getTipoDisciplina());
			this.InstituicaoDAO.persist(d);
		}
	}
	
	
	public void editaInstituicao(){
		this.instituicaoEntity  = (InstituicaoEntity)InstituicaoDAO.find(InstituicaoEntity.class,cookie.getIdInstituicao());
		this.contatoEntityInstituicao = this.instituicaoEntity.getContato();
		this.enderecoEntity = this.instituicaoEntity.getEndereco();
		this.adesaoEntity = this.instituicaoEntity.getAdesao();
		this.contatoEntityInstituicao.setConfirmacaoEmail1(this.contatoEntityInstituicao.getEmail1());
	}




/*	public Boolean getTrocarUsuarioMaster() {
		return trocarUsuarioMaster;
	}



	public void setTrocarUsuarioMaster(Boolean trocarUsuarioMaster) {
		this.trocarUsuarioMaster = trocarUsuarioMaster;
		if (trocarUsuarioMaster==Boolean.FALSE){
			this.novoUsuarioMaster = null;
		}
	}
*/


/*	public UsuarioEntity getNovoUsuarioMaster() {
		return novoUsuarioMaster;
	}



	public void setNovoUsuarioMaster(UsuarioEntity novoUsuarioMaster) {
		this.novoUsuarioMaster = novoUsuarioMaster;
	}
*/



	@Override
	protected boolean antesGravar(Object o) {
		return false;
	}




	@Override
	protected boolean antesRemover(Object o) {
		return false;
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




	public Boolean getNovaAdesao() {
		return novaAdesao;
	}




	public void setNovaAdesao(Boolean novaAdesao) {
		this.novaAdesao = novaAdesao;
	}

	public AdesaoEntity getAdesaoSelecionada() {
		return adesaoSelecionada;
	}

	public void setAdesaoSelecionada(AdesaoEntity adesaoSelecionada) {
		this.adesaoSelecionada = adesaoSelecionada;
	}

	public ConfiguracaoEntity getConfiguracaoTemplateSelecionada() {
		return configuracaoTemplateSelecionada;
	}

	public void setConfiguracaoTemplateSelecionada(
			ConfiguracaoEntity configuracaoTemplateSelecionada) {
		this.configuracaoTemplateSelecionada = configuracaoTemplateSelecionada;
	}





}
