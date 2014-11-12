package br.com.bulktecnologia.controle.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.comuns.util.DataUtil;
import br.com.bulktecnologia.controle.jsf.EasyPaginacao;
import br.com.bulktecnologia.modelo.entidade.ContatoEntity;
import br.com.bulktecnologia.modelo.entidade.CookieSessaoUsuario;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;
import br.com.bulktecnologia.modelo.entidade.TipoOrdenacao;
import br.com.bulktecnologia.modelo.enums.ModoVisualizacaoMatricula;
import br.com.bulktecnologia.modelo.enums.Perfil;
import br.com.bulktecnologia.modelo.enums.Sexo;
import br.com.bulktecnologia.modelo.enums.Skins;
import br.com.bulktecnologia.modelo.enums.TipoAdesao;
import br.com.bulktecnologia.modelo.enums.TipoAtividadeDisciplina;
import br.com.bulktecnologia.modelo.enums.TipoAvaliacao;
import br.com.bulktecnologia.modelo.enums.TipoComputaCargaHoraria;
import br.com.bulktecnologia.modelo.enums.TipoConceito;
import br.com.bulktecnologia.modelo.enums.TipoCondicaoFinalUltAno;
import br.com.bulktecnologia.modelo.enums.TipoContrato;
import br.com.bulktecnologia.modelo.enums.TipoCor;
import br.com.bulktecnologia.modelo.enums.TipoDisciplina;
import br.com.bulktecnologia.modelo.enums.TipoEnsino;
import br.com.bulktecnologia.modelo.enums.TipoEstadoCivil;
import br.com.bulktecnologia.modelo.enums.TipoEtapaTemporal;
import br.com.bulktecnologia.modelo.enums.TipoFormacaoTurma;
import br.com.bulktecnologia.modelo.enums.TipoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoModalidadeContratacao;
import br.com.bulktecnologia.modelo.enums.TipoNacionalidade;
import br.com.bulktecnologia.modelo.enums.TipoPertence;
import br.com.bulktecnologia.modelo.enums.TipoRecuperacao;
import br.com.bulktecnologia.modelo.enums.TipoRegimeContratacao;
import br.com.bulktecnologia.modelo.enums.TipoRelatorio;
import br.com.bulktecnologia.modelo.enums.TipoReligiao;
import br.com.bulktecnologia.modelo.enums.TipoSelecaoMatricula;
import br.com.bulktecnologia.modelo.enums.TipoSerie;
import br.com.bulktecnologia.modelo.enums.TipoTurno;
import br.com.bulktecnologia.modelo.enums.TipoUnidade;

@Name("Factories")
public class Factories {


	
	@In
	private CookieSessaoUsuario cookie;
	
	
	@Factory("FabricaSexo")
	public Sexo[] fabricaSexo(){
		return Sexo.values();
	}
	

	@Factory(value="easy1",scope=ScopeType.CONVERSATION)
	public EasyPaginacao FabricaEasy1(){
		return new EasyPaginacao();
	}

	@Factory(value="easy2",scope=ScopeType.CONVERSATION)
	public EasyPaginacao FabricaEasy2(){
		return new EasyPaginacao();
	}
	
	@Factory("contatoEntityInstituicao")
	public ContatoEntity fabricacontatoDaInstituicao(){
		return new ContatoEntity();
	}

	@Factory("ModosVisualizacaoMatricula")
	public ModoVisualizacaoMatricula[] FabricaTipoVisualizacaoMatricula(){
		return ModoVisualizacaoMatricula.values();
	}
	
	
	@Factory("TiposDeEspacoFisico")
	public List<String> fabricaEspacosFisicos(){
		
		List funcoesSala = new ArrayList<String>();
		funcoesSala.add("Sala de Aula");
		funcoesSala.add("Biblioteca");
		funcoesSala.add("Cantina");
		funcoesSala.add("Diretoria");
		funcoesSala.add("Secretaria");
		funcoesSala.add("Patio");
		funcoesSala.add("Outros");
		
		return funcoesSala;
	}
	

	
	@Factory("nomeInstituicaoLogada")
	public String nomeInstituicaoLogada(){
		return StringUtils.capitaliseAllWords(cookie.getNomeInstituicao().toLowerCase());
	}
	
	@Factory("GrauParentesco")
	public List<String> fabricaGrauParentes(){
		List<String> parentesco = new ArrayList<String>();
		parentesco.add("Tio/Tia");
		parentesco.add("Padrinho/Madrinha");
		parentesco.add("Padrasto/Madrasta");
		parentesco.add("Avô/Avó");
		parentesco.add("Irmão/Irmã");
		parentesco.add("Amigo/Amiga");
		parentesco.add("Orgão/Conselho Tutelar");
		return parentesco;
	}
	

	@Factory("TipoTurmaDefault")
	public TipoFormacaoTurma[] fabricaTipoTurma(){
		return TipoFormacaoTurma.values();
	}
	
	
	
	@Factory("SiglasEstadosBrasileiros")
	public List<String> FabricaSiglasEstadosBrasileiros(){
		
		List<String> estados = new ArrayList<String>();
		
		estados.add("MG");
		estados.add("SP");
		estados.add("RJ");
		estados.add("SC");
		estados.add("MA");
		estados.add("GO");
		estados.add("BA");
		estados.add("MT");
		
		return estados;
	}
	
	
	
	
	/**
	 * fabrica ano Administrativo para o Ano Atual e ano seguinte.
	 * @return List<Long>
	 */
	@Factory("AnoConfiguracaoAtiva")
	public Long fabricaAnosAdministrativos(){
		return this.cookie.getConfigEspecificaInstituicao().getAno();
	}
	

	
	@Factory("CondicoesFinaisUltAno")
	public TipoCondicaoFinalUltAno[] FabricaCondicoesFinalUltAno(){
		return TipoCondicaoFinalUltAno.values();
	}
	
	
	@Factory("TiposMatriculas")
	public TipoMatricula[] FabricaTiposMatriculas(){
		return TipoMatricula.values();
	}
	
	
	@Factory("TiposSelecaoMatricula")
	public TipoSelecaoMatricula[] FabricaTipoSelecaoMatricula(){
		return TipoSelecaoMatricula.values();
	}
	
	@Factory("TiposUsuarios")
	public List<String> fabricaTiposUsuarios(){
		List<String> usuarios = new ArrayList<String>();
		usuarios.add("Professor(a)");
		usuarios.add("Diretor(a)");
		usuarios.add("Secretario(a)");
		usuarios.add("Aluno(a)");
		usuarios.add("Equipe Gestão em Rede");
	
		return usuarios;
	}

	@Factory("TiposdeZonas")
	public List<String> fabricaZonas(){
		List<String> zonas = new ArrayList<String>();
		zonas.add("Urbana");
		zonas.add("Rural");
		return zonas;
	}

	@Factory("TurnosDefault")
	public List<TipoTurno> fabricaTurnos(){
		List<TipoTurno> t = new ArrayList<TipoTurno>();
		t.add(TipoTurno.Manha);
		t.add(TipoTurno.Tarde);
		t.add(TipoTurno.Noite);
		t.add(TipoTurno.Integral);
		return t;
	}
	
	@Factory("TiposEnsinoDefault")
	public List<TipoEnsino> fabricaTiposEnsino(){
		
		List<TipoEnsino> te = new ArrayList<TipoEnsino>();
		te.add(TipoEnsino.Fundamental);
		te.add(TipoEnsino.Medio);
		te.add(TipoEnsino.Eja);
		
		return te;
	}
	
	
	@Factory("TiposSeriesDefault")
	public TipoSerie[] fabricaTipoSeries(){
		return TipoSerie.values();
	}
	
	
	
	
	@Factory("PossuiMultiplaInstituicao")
	public Boolean possuiMultiplaInstituicao(){
		return cookie.getPossuiMultiplaInstituicao();
	}
	

	@Factory("TipoBaseDaDisciplina")
	public List<String> FabricaTipoBaseDisciplina(){
		List<String> tipo = new ArrayList<String>();
		tipo.add("Base Nacional Comum");
		tipo.add("Base Diversificada");
		return tipo;
	}
	
	

	
	@Factory("DisciplinasDefault")
	public TipoDisciplina[] FabricaDisciplinas(){
		return TipoDisciplina.values(); 
	}
	
	
	@Factory("AtividadeDisciplinaDefault")
	public TipoAtividadeDisciplina[] FabricaTipoAtividadeDisciplina(){
		return TipoAtividadeDisciplina.values();
	}
	
	@Factory("PertenceDefault")
	public TipoPertence[] FabricaTipoAtividadeDisciplinaa(){
		return TipoPertence.values();
	}
	
	@Factory("ComputaCargaHorariaDefault")
	public TipoComputaCargaHoraria[] FabricaTipoComputaCargaHoraria(){
		return TipoComputaCargaHoraria.values();
	}
	
	@Factory("FotoCapturada")
	public byte[] fabricaFotoCapturada(){
		PessoaEntity p = (PessoaEntity) Component.getInstance("pessoaEntity");
		return p.getFoto();
	}
	
	@Factory("TipoAvaliacaoDefault")
	public TipoAvaliacao[] FabricaTipoAvaliacaoDefault(){
		return TipoAvaliacao.values();
	}

	@Factory("TipoNacionalidades")
	public TipoNacionalidade[] fabricaPaisesNacionalidades(){
		return TipoNacionalidade.values();
	}
	
	@Factory("TipoEstadoCivil")
	public TipoEstadoCivil[] fabricaEstadoCivil(){
		return TipoEstadoCivil.values();
	}
	
	@Factory("TipoCores")
	public TipoCor[] fabricaCores(){
		return TipoCor.values();
	}
	
	
	@Factory("TipoReligiao")
	public TipoReligiao[] fabricaReligiao(){
		return TipoReligiao.values();
	}

	
	@Factory("TiposRegimeContratacao")
	public TipoRegimeContratacao[] fabricaRegimeContratacao(){
		return TipoRegimeContratacao.values();
	}
	
	
	@Factory("TiposContrato")
	public TipoContrato[] fabricaTipoContrato(){
		return TipoContrato.values();
	}
	
	
	@Factory("TiposModalidadeContratacao")
	public TipoModalidadeContratacao[] TipoModalidadeContratacao(){
		return TipoModalidadeContratacao.values();
	}
	

	@Factory("SkinsTela")
	public Skins[] fabricaSkins(){
			return Skins.values();
	}

	
	
	

/*
	@Factory("FabricaPapeisBaseadoNasCredenciais")
	public  List<String> fabricaPapeisUsuario(){
		
		List<String> roles = new ArrayList<String>();
		
		if ( Identity.instance().hasRole(PapelUsuarioTecnico.App.name()) ){
			roles.add(PapelUsuarioTecnico.Administrador.name());
		}
		else{
			if ( Identity.instance().hasRole(PapelUsuarioTecnico.Administrador.name()) ){
				
				roles.add( PapelUsuarioTecnico.Suporte.name() );
				
				for (PapelUsuario p: PapelUsuario.values()){
					roles.add( p.name() );
				}
				
			}
			else{
				
				if ( Identity.instance().hasRole(PapelUsuarioTecnico.Suporte.name()) ){
					
					for (PapelUsuario p: PapelUsuario.values()){
						roles.add( p.name() );
					}
					
				}
				
				else{
					if ( Identity.instance().hasRole(PapelUsuario.Diretor.name()) ){
						roles.add(PapelUsuario.Secretário.name());
						roles.add(PapelUsuario.Professor.name());
						roles.add(PapelUsuario.Aux_Educação.name());
						roles.add(PapelUsuario.Aluno.name());
						
					}
					else{
						if ( Identity.instance().hasRole(PapelUsuario.Secretário.name()) ){
							roles.add(PapelUsuario.Professor.name());
							roles.add(PapelUsuario.Aluno.name());
							roles.add(PapelUsuario.Aux_Educação.name());
						}
					}
				}
			}
		}

		
		return roles;
	}*/
	
	

	
	@Factory("TiposAdesao")
	public TipoAdesao[] fabricaAdesao(){
		return TipoAdesao.values();
	}
	
	
	@Factory("TiposUnidade")
	public TipoUnidade[] fabricaUnidades(){
		return TipoUnidade.values();
	}
	
	

	@Factory("TiposEtapasTemporal")
	public TipoEtapaTemporal[] FabricaTiposEtapaTemporal(){
		return TipoEtapaTemporal.values();
	}

	@Factory("TiposRecuperacao")
	public TipoRecuperacao[] fabricaTipoRecuperacao(){
		return TipoRecuperacao.values();
	}

	@Factory("TiposOrdenacao")
	public TipoOrdenacao[] fabricaTipoOrdenacao(){
		return TipoOrdenacao.values();
	}

	@Factory("Perfis")
	public Perfil[] fabricaPerfis(){
		return Perfil.values();
	}
	
	@Factory("usuarioAdministrativo")
	public Boolean fabricaUsuarioGlobal(){
		return cookie.getGlobal();
	}
	
	@Factory("idConfiguracaoGlobalSelecionada")
	public Long fabricaIdConfiguracaoGlobalSelecionada(){
		return cookie.getIdConfigGlobalSelecionada();
	}
	
	@Factory("nomeConfiguracaoGlobalSelecionada")
	public String fabricaNomeConfiguracaoGlobalSelecionada(){
		
		if (cookie.getNomeConfigGlobalSelecionada()!=null){
			return cookie.getNomeConfigGlobalSelecionada().toUpperCase();
		}
		return null;
	}
	
	
	@Factory("TipoDaConfiguracao")
	public String FabricaOperandoModoConfiguracaoGlobal(){
		 if ( cookie.getConfigEspecificaInstituicao()==null ){
			 return "Configuração GLOBAL";
		 }
		 else{
			 return "Configuração ESPECÍFICA";
		 }
	}
	
	@Factory("idInstituicaoLogada")
	public Long FabricaIdInstituicaoLogada(){
		return cookie.getIdInstituicao();
	}
	
	@Factory("existeConfiguracaoEspecificaSelecionada")
	public Boolean FabricaExisteConfiguracaoEspecificaSelecionada(){
		 return cookie.getConfigEspecificaInstituicao()!=null;
	}
	
	@Factory("TiposDeRelatorio")
	public TipoRelatorio[] FabricaTiposRelatorio(){
		return TipoRelatorio.values();
	}
	
	@Factory("operandoAnoBasePassadoOuFuturo")
	public Boolean FabricaAnoBaseSelecionadoFuturo(){
		return  this.cookie.getConfigEspecificaInstituicao().getAno() > DataUtil.getAnoAtual() ||
				this.cookie.getConfigEspecificaInstituicao().getAno() < DataUtil.getAnoAtual() ;
	}
	
	
	@Factory("TiposConceito")
	public TipoConceito[] FabricaConceitos(){
		return TipoConceito.values();
	}
}





