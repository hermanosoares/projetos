package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

@Name("EventoEntity")
@Entity
@Table(name="eventos")
@NamedQueries(value={@NamedQuery(name="EventoEntity.recuperaEventos",query="select ev from EventoEntity ev"
						+ " where ev.instituicao.id = :idInstituicao order by ev.dataInicio"),
					 
						@NamedQuery(name="EventoEntity.validaEventoEntityDuplicado", query="select ev from EventoEntity ev "
						+ " where ev.instituicao.id = :idInstituicao and ev.descricao = :descricaoEvento")
					})
public class EventoEntity implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_evento")
	private Long idEvento;
	
	@Column(name="dt_inicio", nullable=false)
	private Date dataInicio;
	
	@Column(name="dt_termino", nullable=false)
	private Date dataTermino;
	
	@Column(name="descricao",nullable=false)
	private String descricao;
	
	@Column(name="legenda", nullable=false, length=5)
	private String legenda;
	
	@Column(name="dialetivo")
	private Boolean diaLetivo;
	
	@Column(name="diaescolar")
	private Boolean diaEscolar;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="instituicao_id", nullable=false)
	private InstituicaoEntity instituicao;
	
	public EventoEntity(){
		
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public Boolean getDiaLetivo() {
		return diaLetivo;
	}

	public void setDiaLetivo(Boolean diaLetivo) {
		this.diaLetivo = diaLetivo;
	}

	public Boolean getDiaEscolar() {
		return diaEscolar;
	}

	public void setDiaEscolar(Boolean diaEscolar) {
		this.diaEscolar = diaEscolar;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setInstituicaoEntity(InstituicaoEntity instituicaoEntity) {
		this.instituicao = instituicaoEntity;
	}

	public InstituicaoEntity getInstituicaoEntity() {
		return instituicao;
	}
}
