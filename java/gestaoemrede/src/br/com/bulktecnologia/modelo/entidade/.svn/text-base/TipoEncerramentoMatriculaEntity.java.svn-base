package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;
@Entity
@Name("tipoEncerramentoMatriculaEntity")
@Table(name="TipoEncerramentoMatricula")
@NamedQueries(value={
		
				@NamedQuery(name="TipoEncerramentoMatriculaEntity.recuperaTipoEncerramentoMatriculaPorInstituicao",query="select te from TipoEncerramentoMatriculaEntity te where te.instituicao.id = :idinstituicao and te.permiteSelecao = true"),
				
				@NamedQuery(name="TipoEncerramentoMatriculaEntity.validaNomeJaExistente",query="select te from TipoEncerramentoMatriculaEntity te where te.instituicao.id = :idinstituicao and lower(te.nome) like lower(:nome) ")
	}
 	)
public class TipoEncerramentoMatriculaEntity implements Serializable {
	
	@Id
	@GeneratedValue(generator="tipoEncerramentoMatricula_gen")
	@SequenceGenerator(name="tipoEncerramentoMatricula_gen",sequenceName="tipoEncerramentoMatricula_seq")
	private Long id;
	
	@Column(nullable=false)
	private String nome;

	@Column(nullable=true)
	private String descricao;

	@ManyToOne
	@JoinColumn(nullable=false)
	private InstituicaoEntity instituicao;
	
	@Column(nullable=false)
	private Boolean permiteExclusao = Boolean.FALSE;
	
	@Column(nullable=false)
	private Boolean permiteSelecao = Boolean.TRUE;
	
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

	public Boolean getPermiteExclusao() {
		return permiteExclusao;
	}

	public void setPermiteExclusao(Boolean permiteExclusao) {
		this.permiteExclusao = permiteExclusao;
	}

	public Boolean getPermiteSelecao() {
		return permiteSelecao;
	}

	public void setPermiteSelecao(Boolean permiteSelecao) {
		this.permiteSelecao = permiteSelecao;
	}

}
