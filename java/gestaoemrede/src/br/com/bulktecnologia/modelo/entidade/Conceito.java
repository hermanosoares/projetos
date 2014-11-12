package br.com.bulktecnologia.modelo.entidade;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.NotNull;

import br.com.bulktecnologia.modelo.enums.TipoConceito;

@Entity
@NamedQueries(value={
			@NamedQuery(name="Conceito.recuperaConceitoDaConfiguracao",query="select c from Conceito c where c.configuracao.id = :idConfiguracaoEspecifica")
})
public class Conceito extends BaseEntity {

	@Id
	@GeneratedValue(generator="conceito_gen")
	@SequenceGenerator(name="conceito_gen",sequenceName="conceito_seq")
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	@NotNull(message="Conceito Obrigatório")
	private TipoConceito tipoConceito;

	@Column(nullable=false)
	@NotNull(message="Rótulo do Conceito Obrigatório")
	private String label;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private ConfiguracaoEntity configuracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoConceito getTipoConceito() {
		return tipoConceito;
	}

	public void setTipoConceito(TipoConceito tipoConceito) {
		this.tipoConceito = tipoConceito;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ConfiguracaoEntity getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(ConfiguracaoEntity configuracao) {
		this.configuracao = configuracao;
	}
	
	
}
