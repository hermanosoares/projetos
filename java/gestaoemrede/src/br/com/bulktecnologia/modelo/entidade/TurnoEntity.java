package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.enums.TipoTurno;
@Entity
@Table(name="turno")
//@NamedQueries(value={@NamedQuery(name="TurnoEntity.recuperaTurnosByConfiguracao",query="select t from TurnoEntity t where t.configuracao.id = :idConfiguracao")})
@Name("turnoEntity")
public class TurnoEntity implements Serializable {

	public TurnoEntity(){
	}
	
	public TurnoEntity(TipoTurno tipoturno){
		this.setTipoturno(tipoturno);
		this.setNome(tipoturno.name());
	}
	
	@Id
	@SequenceGenerator(name="turno_gen",sequenceName="turno_seq")
	@GeneratedValue(generator="turno_gen")
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	private TipoTurno tipoturno;
	
	@Column(nullable=false)
	private String nome;

	@OrderBy("tipoEnsino asc")
	@OneToMany(mappedBy="turno",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<EnsinoEntity> ensinos;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="configuracao_id",nullable=false)
	private ConfiguracaoEntity configuracao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTurno getTipoturno() {
		return tipoturno;
	}

	public void setTipoturno(TipoTurno tipoturno) {
		this.tipoturno = tipoturno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public ConfiguracaoEntity getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(ConfiguracaoEntity configuracao) {
		this.configuracao = configuracao;
	}

	public List<EnsinoEntity> getEnsinos() {
		return ensinos;
	}

	public void setEnsinos(List<EnsinoEntity> ensinos) {
		this.ensinos = ensinos;
	}


	
	
	
}
