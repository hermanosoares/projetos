package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.event.JPAValidateListener;
import org.jboss.seam.annotations.Name;

@Name("espacoFisicoEntity")
@Entity
@Table(name="espacofisico")
@NamedQueries(value={@NamedQuery(name="EspacoFisicoEntity.recuperaEspacosFisico",query="select ef from EspacoFisicoEntity ef where ef.instituicao.id = :idInstituicao"),
					 @NamedQuery(name="EspacoFisicoEntity.validaEspacoFisicoDuplicado", query="select ef from EspacoFisicoEntity ef where ef.identificadorEspaco = :identificadorEspaco and ef.tipoEspaco = :tipoEspaco and ef.instituicao.id = :idInstituicao") 
					})
@EntityListeners( JPAValidateListener.class )
public class EspacoFisicoEntity implements Serializable {

	
	@Id
	@GeneratedValue(generator="sala_gen")
	@SequenceGenerator(name="sala_gen",sequenceName="sala_seq")
	private Long id;
	
	@Column(nullable=false)
	private String identificadorEspaco;
	
	@Column
	private String descricao;
	
	@Column
	private String tipoEspaco;
	
	@Column(nullable=false)
	private Integer area;
	
	@Column
	private String localizacao;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="instituicao_id",nullable=false)
	private InstituicaoEntity instituicao;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificadorEspaco() {
		return identificadorEspaco;
	}

	public void setIdentificadorEspaco(String identificadorEspaco) {
		this.identificadorEspaco = identificadorEspaco;
	}

	public String getTipoEspaco() {
		return tipoEspaco;
	}

	public void setTipoEspaco(String tipoEspaco) {
		this.tipoEspaco = tipoEspaco;
	}
}
