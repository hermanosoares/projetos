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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;
@Entity
@Table(name="tipofuncao")
@Name("tipoFuncaoEntity")
@NamedQueries( value = {@NamedQuery(name="TipoFuncaoEntity.recuperaFuncoesByInstituicao",query="select tf from TipoFuncaoEntity tf where tf.instituicao.id = :idInstituicao")} )
public class TipoFuncaoEntity implements Serializable {

	@Id
	@SequenceGenerator(name="tipofuncao_gen",sequenceName="tipofuncao_seq")
	@GeneratedValue(generator="tipofuncao_gen")
	private Long id;

	@OneToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(nullable=false)
	private InstituicaoEntity instituicao;

	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=true)
	private String descricao;
	
	@OneToMany(mappedBy="tipoFuncao",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<ContratoTrabalhoEntity> contratosTrabalho;
	
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
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

	public List<ContratoTrabalhoEntity> getContratosTrabalho() {
		return contratosTrabalho;
	}

	public void setContratosTrabalho(List<ContratoTrabalhoEntity> contratosTrabalho) {
		this.contratosTrabalho = contratosTrabalho;
	}


	
}
