package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jboss.seam.annotations.Name;

@Name("instituicaoEntity")
@Entity
@Table(name="instituicao")
@Cache(region="sampleCache3",usage=CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
			@NamedQuery(name="InstituicaoEntity.recuperaTodasInstituicoes",query="select i from InstituicaoEntity i order by i.codInstituicao",hints={ @QueryHint(name="org.hibernate.cacheable",value="true") , @QueryHint(name="org.hibernate.cacheRegion",value="sampleCache3") } ),
			@NamedQuery(name="InstituicaoEntity.recuperaProjetosByInstituicao",query="select i.projetos from InstituicaoEntity i where i.id = :idInstituicao"),
			@NamedQuery(name="InstituicaoEntity.recuperaProjetosAtivosByInstituicao",query="select p from InstituicaoEntity i, ProjetoEntity p where i.id = :idInstituicao and p.instituicao.id = i.id and p.dtTermino is null or p.dtTermino <= CURRENT_DATE"),
			@NamedQuery(name="InstituicaoEntity.recuperaInstituicaoByCodInstituicao",query="select i from InstituicaoEntity i where i.codInstituicao = :codInstituicao")
				})
public class InstituicaoEntity implements Serializable {
	public InstituicaoEntity(){
		
	}
	
	public InstituicaoEntity(Long id,String nomeInstituicao){
		this.setId(id);
		this.setNome(nomeInstituicao);
	}
	
	
	@Id
	@GeneratedValue(generator="instituicao_gen")
	@SequenceGenerator(name="instituicao_gen",sequenceName="instituicao_seq")
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false,unique=true)
	private String codInstituicao;
	
	@Column(nullable=false)
	private Boolean ativo = Boolean.TRUE;
	

	@OneToMany(mappedBy="instituicao",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<ConfiguracaoEntity> configuracao;
	
	@OneToMany(mappedBy="instituicao",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<EspacoFisicoEntity> espacosFisico;
	
	@OneToMany(mappedBy="instituicao",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<EventoEntity> eventos;
	
	@OneToMany(mappedBy="instituicao",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<RelacaoPessoaEntity> relacaoPessoaInstituicao;
	
	@Lob
	@Column(nullable=true)
	private byte[] logomarca1;
	
	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(nullable=true)
	private ContatoEntity contato;

	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(nullable=true)
	private EnderecoEntity endereco;
	
	@OneToMany(mappedBy="instituicao",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<ProjetoEntity> projetos;

	@OneToMany(mappedBy="instituicao",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<TipoEncerramentoMatriculaEntity> tiposEncerramento;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private AdesaoEntity adesao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = StringUtils.capitaliseAllWords(nome);
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	public String getCodInstituicao() {
		return codInstituicao;
	}

	public void setCodInstituicao(String codInstituicao) {
		this.codInstituicao = codInstituicao;
	}

	public byte[] getLogomarca1() {
		return logomarca1;
	}

	public void setLogomarca1(byte[] logomarca1) {
		this.logomarca1 = logomarca1;
	}

	public ContatoEntity getContato() {
		return contato;
	}

	public void setContato(ContatoEntity contato) {
		this.contato = contato;
	}

	public EnderecoEntity getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntity endereco) {
		this.endereco = endereco;
	}

	public List<ConfiguracaoEntity> getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(List<ConfiguracaoEntity> configuracao) {
		this.configuracao = configuracao;
	}

	public List<EspacoFisicoEntity> getEspacosFisico() {
		return espacosFisico;
	}

	public void setEspacosFisico(List<EspacoFisicoEntity> espacosFisico) {
		this.espacosFisico = espacosFisico;
	}

	public List<EventoEntity> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoEntity> eventos) {
		this.eventos = eventos;
	}

	public List<ProjetoEntity> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoEntity> projetos) {
		this.projetos = projetos;
	}


	public List<RelacaoPessoaEntity> getRelacaoPessoaInstituicao() {
		return relacaoPessoaInstituicao;
	}

	public void setRelacaoPessoaInstituicao(
			List<RelacaoPessoaEntity> relacaoPessoaInstituicao) {
		this.relacaoPessoaInstituicao = relacaoPessoaInstituicao;
	}

	public List<TipoEncerramentoMatriculaEntity> getTiposEncerramento() {
		return tiposEncerramento;
	}

	public void setTiposEncerramento(
			List<TipoEncerramentoMatriculaEntity> tiposEncerramento) {
		this.tiposEncerramento = tiposEncerramento;
	}

	public AdesaoEntity getAdesao() {
		return adesao;
	}

	public void setAdesao(AdesaoEntity adesao) {
		this.adesao = adesao;
	}

}
