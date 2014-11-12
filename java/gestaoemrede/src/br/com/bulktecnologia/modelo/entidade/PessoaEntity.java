package br.com.bulktecnologia.modelo.entidade;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.richfaces.event.UploadEvent;
import org.xseam.domain.State;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.comuns.converter.PortugueseLatin1TextConverter;
import br.com.bulktecnologia.comuns.util.DataUtil;
import br.com.bulktecnologia.comuns.util.EntityHelper;
import br.com.bulktecnologia.comuns.util.FileUtils;
import br.com.bulktecnologia.modelo.annotation.CampoObrigatorio;
import br.com.bulktecnologia.modelo.enums.Sexo;
import br.com.bulktecnologia.modelo.enums.TipoCor;
import br.com.bulktecnologia.modelo.enums.TipoEstadoCivil;
import br.com.bulktecnologia.modelo.enums.TipoNacionalidade;
import br.com.bulktecnologia.modelo.enums.TipoReligiao;


@Name("pessoaEntity")
@Entity
@Audited
@Table(name="pessoa")
@AutoCreate
@NamedQueries(value={
					 @NamedQuery(name="PessoaEntity.count",query="select count(p.id) from PessoaEntity p"),
					 @NamedQuery(name="PessoaEntity.recuperaPessoaByCPF",query="select p from PessoaEntity p where p.cpf = :cpf"),
					 @NamedQuery(name="PessoaEntity.recuperaPessoaByRG",query="select p from PessoaEntity p where p.rg = :rg and p.rgorgaoexpedido = :ssp"),
					 @NamedQuery(name="PessoaEntity.recuperaPessoaByCertidao",query="select p from PessoaEntity p where p.numcertidao = :numcertidao and p.dtcertidao = :dtcertidao"),
			 })
public class PessoaEntity implements Serializable,EntidadePaginavel,EntidadePreenchivel{


	public PessoaEntity(){
	}
	
	public PessoaEntity(Long id,String nome, Sexo sexo,Date dtNascimento, String nomeMae, String nomePai, String cpf,String rgOrgaoExpedido, String rg,String naturalidade, byte[] foto){
		this.setId(id);
		this.setNome(nome);
		this.setSexo(sexo);
		this.setDtNascimento(dtNascimento);
		this.setNomeMae(nomeMae);
		this.setNomePai(nomePai);
		this.setCpf(cpf);
		this.setRgorgaoexpedido(rgOrgaoExpedido);
		this.setRg(rg);
		this.setNaturalidade(naturalidade);
		this.setFoto(foto);
	}
	
	

	public PessoaEntity(String nome){
		setNome(nome);
	}
	
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(generator="pessoa_seq")
	@SequenceGenerator(name="pessoa_seq",sequenceName="pessoa_seq")
	private Long id;
	
	@JoinColumn
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	@Basic(fetch=FetchType.LAZY)
	private  ContatoEntity contato;
	
	@JoinColumn
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	@Basic(fetch=FetchType.LAZY)
	private EnderecoEntity endereco;
	
	@Enumerated(EnumType.STRING)
	@Column
	private TipoNacionalidade nacionalidade;

	@Enumerated(EnumType.STRING)
	@Column
	private TipoEstadoCivil estadocivil;

	@CampoObrigatorio(nomeCampo="Sexo")
	@Enumerated(EnumType.STRING)
	@Column
	private Sexo sexo;

	@Enumerated(EnumType.STRING)
	@Column
	private TipoCor cor;
	
	@Enumerated(EnumType.STRING)
	@Column
	private TipoReligiao religiao;

	@Enumerated(EnumType.STRING)
	@Column
	private State ufcarteiratrab;

	@CampoObrigatorio(nomeCampo="Nome")
	@NotNull
	@Column(nullable=false)
	private String nome;

	@NotNull
	@Column(nullable=false)
	private String nomeSemAcento;

	@CampoObrigatorio(nomeCampo="Nome da Mãe")
	@Column
	private String nomeMae;
	
	@Column(nullable=false)
	private String nomeMaeSemAcento;

	@CampoObrigatorio(nomeCampo="Dt. Nasc. da Mãe")
	@Column
	@Temporal(TemporalType.DATE)
	private Date dtNascimentoMae;
	
	@Column
	private String nomePai;
	
	@Column
	private String nomePaiSemAcento;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dtNascimentoPai;

	@Column
	private String nomeResponsavel;
	
	@Column
	private String nomeResponsavelSemAcento;

	@Column(unique=true)
	private String rg;
	
	@Column
	private String rgorgaoexpedido;
	
	@Column
	private Date rgdtemissao;
	
	
	@Column(unique=true)
	private String cpf;

	@Column
	private String naturalidade;
	
	@CampoObrigatorio(nomeCampo="Dt. Nascimento")
	@Temporal(TemporalType.DATE)
	@Column
	private Date dtNascimento;
	
	@Column
	private Boolean falecido = Boolean.FALSE;
	
	@Column
	private String numcarteiratrab;
	
	@Column
	private String seriecarteiratrab;
	
	@Column
	private String certificadomilitar;
	
	@Column
	private String passaporte;
	
	@Column
	private String nis;
	
	@Column
	private String pispasep;
	
	@Column
	private String previdenciasocial;
	
	@Column
	private Long numtituloeleitor;
	
	@Column
	private String zonatituloeleitor;
	
	@Column
	private String secaotituloeleitor;
	
	@Column
	private String numcertidao;
	
	@Column
	private Date dtcertidao;
	
	@Column
	private String livrocertidao;
	
	@Column
	private String folhacertidao;
	
	@Column
	private String cartoriocertidao;
	
	@Column
	private String cidadecertidao;
	
	@Column
	private String ufcertidao;
	
	@Column
	private String tipocertidao;
	
	@Column
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@NotAudited
	private byte[] foto;
	
	@Column(nullable=true)
	private String tipoResponsavel;
	
	@Transient
	private Boolean maeFalecida;
	
	@Transient
	private Boolean paiFalecido;


	@NotAudited
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<RelacaoPessoaEntity> relacaoPessoaInstituicoes;
	
	
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Boolean getFalecido() {
		return falecido;
	}


	public void setFalecido(Boolean falecido) {
		this.falecido = falecido;
	}



	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
		this.setNomeSemAcento( PortugueseLatin1TextConverter.trataNomeISOLatin1(nome) );
	}


	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRgorgaoexpedido() {
		return rgorgaoexpedido;
	}
	public void setRgorgaoexpedido(String rgorgaoexpedido) {
		this.rgorgaoexpedido = rgorgaoexpedido;
	}
	public Date getRgdtemissao() {
		return rgdtemissao;
	}
	public void setRgdtemissao(Date rgdtemissao) {
		this.rgdtemissao = rgdtemissao;
	}



	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}



	public String getNumcarteiratrab() {
		return numcarteiratrab;
	}
	public void setNumcarteiratrab(String numcarteiratrab) {
		this.numcarteiratrab = numcarteiratrab;
	}
	public String getSeriecarteiratrab() {
		return seriecarteiratrab;
	}
	public void setSeriecarteiratrab(String seriecarteiratrab) {
		this.seriecarteiratrab = seriecarteiratrab;
	}

	public String getCertificadomilitar() {
		return certificadomilitar;
	}
	public void setCertificadomilitar(String certificadomilitar) {
		this.certificadomilitar = certificadomilitar;
	}
	public String getPassaporte() {
		return passaporte;
	}
	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}
	public String getNis() {
		return nis;
	}
	public void setNis(String nis) {
		this.nis = nis;
	}
	public String getPispasep() {
		return pispasep;
	}
	public void setPispasep(String pispasep) {
		this.pispasep = pispasep;
	}
	public String getPrevidenciasocial() {
		return previdenciasocial;
	}
	public void setPrevidenciasocial(String previdenciasocial) {
		this.previdenciasocial = previdenciasocial;
	}
	public Long getNumtituloeleitor() {
		return numtituloeleitor;
	}
	public void setNumtituloeleitor(Long numtituloeleitor) {
		this.numtituloeleitor = numtituloeleitor;
	}
	public String getZonatituloeleitor() {
		return zonatituloeleitor;
	}
	public void setZonatituloeleitor(String zonatituloeleitor) {
		this.zonatituloeleitor = zonatituloeleitor;
	}
	public String getSecaotituloeleitor() {
		return secaotituloeleitor;
	}
	public void setSecaotituloeleitor(String secaotituloeleitor) {
		this.secaotituloeleitor = secaotituloeleitor;
	}
	public String getNumcertidao() {
		return numcertidao;
	}
	public void setNumcertidao(String numcertidao) {
		this.numcertidao = numcertidao;
	}
	public Date getDtcertidao() {
		return dtcertidao;
	}
	public void setDtcertidao(Date dtcertidao) {
		this.dtcertidao = dtcertidao;
	}
	public String getLivrocertidao() {
		return livrocertidao;
	}
	public void setLivrocertidao(String livrocertidao) {
		this.livrocertidao = livrocertidao;
	}
	public String getFolhacertidao() {
		return folhacertidao;
	}
	public void setFolhacertidao(String folhacertidao) {
		this.folhacertidao = folhacertidao;
	}
	public String getCartoriocertidao() {
		return cartoriocertidao;
	}
	public void setCartoriocertidao(String cartoriocertidao) {
		this.cartoriocertidao = cartoriocertidao;
	}
	public String getCidadecertidao() {
		return cidadecertidao;
	}
	public void setCidadecertidao(String cidadecertidao) {
		this.cidadecertidao = cidadecertidao;
	}
	public String getUfcertidao() {
		return ufcertidao;
	}
	public void setUfcertidao(String ufcertidao) {
		this.ufcertidao = ufcertidao;
	}
	public String getTipocertidao() {
		return tipocertidao;
	}
	public void setTipocertidao(String tipocertidao) {
		this.tipocertidao = tipocertidao;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getTipoResponsavel() {
		return tipoResponsavel;
	}

	public void setTipoResponsavel(String tipoResponsavel) {
		this.tipoResponsavel = tipoResponsavel;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNomeSemAcento() {
		return nomeSemAcento;
	}

	public void setNomeSemAcento(String nomeSemAcento) {
		this.nomeSemAcento = nomeSemAcento;
	}

	
	public Boolean isPreenchido() {
		List<String> ignoreList = Arrays.asList(new String[]{"sexo","falecido"});
		
		return EntityHelper.entityHasAnyPropertyFilled(this,ignoreList);
	}
	
	
	/**
	 * Metodo auxiliar usado pela EL para saber se 
	 * a pessoa é maior de idade ou não.
	 * 
	 * @return Boolean
	 */
	public Boolean getMaiorDeIdade(){
		if (this.dtNascimento==null){
			return Boolean.FALSE;
		}
		else{
			int idade = DataUtil.calculaIdade(this.dtNascimento);
			if ( idade >= ApplicationConstants.Parametros.IDADE_MAIORIDADE) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}

	public TipoNacionalidade getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(TipoNacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public TipoEstadoCivil getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(TipoEstadoCivil estadocivil) {
		this.estadocivil = estadocivil;
	}

	public TipoCor getCor() {
		return cor;
	}

	public void setCor(TipoCor cor) {
		this.cor = cor;
	}

	public TipoReligiao getReligiao() {
		return religiao;
	}

	public void setReligiao(TipoReligiao religiao) {
		this.religiao = religiao;
	}


	
	public void uploadFoto(UploadEvent event){
		File uploadFile = event.getUploadItem().getFile();
		if (uploadFile!=null){
			String s = uploadFile.getName();
			s.toString();
		}
		try {
			this.foto = FileUtils.getBytesFromFile(uploadFile);
		} catch (IOException e) {
			FacesMessages.instance().add("Problema no envio da imagem por Upload.");
		} 
	}
	
	public void limpaFoto(){
		this.foto = null;
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



	public String getNomeMae() {
		return nomeMae;
	}



	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
		this.setNomeMaeSemAcento(PortugueseLatin1TextConverter.trataNomeISOLatin1(nomeMae));
	}



	public Date getDtNascimentoMae() {
		return dtNascimentoMae;
	}



	public void setDtNascimentoMae(Date dtNascimentoMae) {
		this.dtNascimentoMae = dtNascimentoMae;
	}



	public String getNomePai() {
		return nomePai;
	}



	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
		this.setNomePaiSemAcento(PortugueseLatin1TextConverter.trataNomeISOLatin1(nomePai));
	}



	public Date getDtNascimentoPai() {
		return dtNascimentoPai;
	}



	public void setDtNascimentoPai(Date dtNascimentoPai) {
		this.dtNascimentoPai = dtNascimentoPai;
	}



	public String getNomeResponsavel() {
		return nomeResponsavel;
	}



	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
		this.setNomeResponsavelSemAcento(PortugueseLatin1TextConverter.trataNomeISOLatin1(nomeResponsavel));
	}



	public Boolean getMaeFalecida() {
		return maeFalecida;
	}



	public void setMaeFalecida(Boolean maeFalecida) {
		this.maeFalecida = maeFalecida;
	}



	public Boolean getPaiFalecido() {
		return paiFalecido;
	}



	public void setPaiFalecido(Boolean paiFalecido) {
		this.paiFalecido = paiFalecido;
	}



	public String getNomeMaeSemAcento() {
		return nomeMaeSemAcento;
	}



	public void setNomeMaeSemAcento(String nomeMaeSemAcento) {
		this.nomeMaeSemAcento = nomeMaeSemAcento;
	}



	public String getNomePaiSemAcento() {
		return nomePaiSemAcento;
	}



	public void setNomePaiSemAcento(String nomePaiSemAcento) {
		this.nomePaiSemAcento = nomePaiSemAcento;
	}



	public String getNomeResponsavelSemAcento() {
		return nomeResponsavelSemAcento;
	}



	public void setNomeResponsavelSemAcento(String nomeResponsavelSemAcento) {
		this.nomeResponsavelSemAcento = nomeResponsavelSemAcento;
	}



	public Date getDtNascimento() {
		return dtNascimento;
	}



	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public State getUfcarteiratrab() {
		return ufcarteiratrab;
	}

	public void setUfcarteiratrab(State ufcarteiratrab) {
		this.ufcarteiratrab = ufcarteiratrab;
	}

	public List<RelacaoPessoaEntity> getRelacaoPessoaInstituicoes() {
		return relacaoPessoaInstituicoes;
	}

	public void setRelacaoPessoaInstituicoes(
			List<RelacaoPessoaEntity> relacaoPessoaInstituicoes) {
		this.relacaoPessoaInstituicoes = relacaoPessoaInstituicoes;
	}



}
