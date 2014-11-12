package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jboss.seam.annotations.Name;
import org.xseam.domain.City;
import org.xseam.domain.State;

import br.com.bulktecnologia.modelo.enums.TipoAdesao;
import br.com.bulktecnologia.modelo.enums.TipoUnidade;
@Entity
@Table(name="adesao")
@Name("adesaoEntity")
@NamedQueries(value={
					@NamedQuery(name="AdesaoEntity.recuperaTodasAdesoes",query="select ad from AdesaoEntity ad")
					})
public class AdesaoEntity implements Serializable {
	
	@Id
	@GeneratedValue(generator="adesao_gen")
	@SequenceGenerator(name="adesao_gen",sequenceName="adesao_seq")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoAdesao tipoAdesao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoUnidade unidade;

	@Column(nullable=false)
	private Long tempo;
	
	@Column(nullable=false)
	private Double valor;

	@Column(nullable=false)
	private String cliente;

	@Column(nullable=false)
	private Long telCliente;

	@OneToMany(mappedBy="adesao",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<InstituicaoEntity> instituicoes;

	@Column
	private String responsavelContrato;
	
	@Column
	private String cargoResponsavelContrato;
	
	@Column
	private Long telResponsavelContrato;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dtIniAdesao;
	
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date dtTerminoAdesao;
	
	@Column
	private String nomeVendedor;

	@Column(nullable=false)
	private State estado;
	
	@Column(nullable=false)
	private City cidade;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAdesao getTipoAdesao() {
		return tipoAdesao;
	}

	public void setTipoAdesao(TipoAdesao tipoAdesao) {
		this.tipoAdesao = tipoAdesao;
	}

	public TipoUnidade getUnidade() {
		return unidade;
	}

	public void setUnidade(TipoUnidade unidade) {
		this.unidade = unidade;
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getResponsavelContrato() {
		return responsavelContrato;
	}

	public void setResponsavelContrato(String responsavelContrato) {
		this.responsavelContrato = responsavelContrato;
	}

	public String getCargoResponsavelContrato() {
		return cargoResponsavelContrato;
	}

	public void setCargoResponsavelContrato(String cargoResponsavelContrato) {
		this.cargoResponsavelContrato = cargoResponsavelContrato;
	}

	public Long getTelResponsavelContrato() {
		return telResponsavelContrato;
	}

	public void setTelResponsavelContrato(Long telResponsavelContrato) {
		this.telResponsavelContrato = telResponsavelContrato;
	}

	public Date getDtIniAdesao() {
		return dtIniAdesao;
	}

	public void setDtIniAdesao(Date dtIniAdesao) {
		this.dtIniAdesao = dtIniAdesao;
	}

	public Date getDtTerminoAdesao() {
		return dtTerminoAdesao;
	}

	public void setDtTerminoAdesao(Date dtTerminoAdesao) {
		this.dtTerminoAdesao = dtTerminoAdesao;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public Long getTelCliente() {
		return telCliente;
	}

	public void setTelCliente(Long telCliente) {
		this.telCliente = telCliente;
	}

	public List<InstituicaoEntity> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<InstituicaoEntity> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	public City getCidade() {
		return cidade;
	}

	public void setCidade(City cidade) {
		this.cidade = cidade;
	}


}
