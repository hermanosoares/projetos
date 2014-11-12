package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jboss.seam.annotations.Name;

@Entity
@Name("projetoEntity")
@Table(name="projeto")
public class ProjetoEntity implements Serializable{
		
	@Id
	@GeneratedValue(generator="projeto_gen")
	@SequenceGenerator(name="projeto_gen",sequenceName="projeto_seq")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="instituicao_id",nullable=false)
	private InstituicaoEntity instituicao;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=true)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date dtInicio;

	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date dtTermino;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtTermino() {
		return dtTermino;
	}

	public void setDtTermino(Date dtTermino) {
		this.dtTermino = dtTermino;
	}
	
	
}
