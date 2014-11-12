package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO;
@Entity
@Name("lancamento")
@NamedQueries(value={
		@NamedQuery(name="Lancamento.recuperaLancamentos",query="select lanc from Lancamento lanc where lanc.etapa.id = :idEtapa and lanc.disciplina.id = :idDisciplina and lanc.alocacaoItem.id in (:idsAlocacaoItem) "),
		@NamedQuery(name="Lancamento.atualizaAlocacaoItemEmLancamentos",query="update Lancamento l set l.alocacaoItem.id = :idAlocacaoItemNew where l.alocacaoItem.id = :idAlocacaoItemOld")
})
public class Lancamento extends BaseEntity implements Serializable {
	
	
	@Id
	@GeneratedValue(generator="lancamento_gen")
	@SequenceGenerator(name="lancamento_gen",sequenceName="lancamento_seq")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn(nullable=false)
	private AlocacaoItemEntity alocacaoItem;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn(nullable=false)
	private EtapaEntity etapa;

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn(nullable=false)
	private DisciplinaEntity disciplina;

	@Column
	@Min(value=0,message="nota mínima 0")
	@Max(value=100,message="nota máxima 100")
	private Double nota;

	@Column
	@Min(value=0,message="mínima de 0 falta.")
	@Max(value=264,message="máximo de 264 faltas anuais.")
	private Long falta;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn
	private Conceito conceito;

	@Transient
	private ResultadoAlocacaoDTO alocacaoDto;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlocacaoItemEntity getAlocacaoItem() {
		return alocacaoItem;
	}

	public void setAlocacaoItem(AlocacaoItemEntity alocacaoItem) {
		this.alocacaoItem = alocacaoItem;
	}

	public EtapaEntity getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaEntity etapa) {
		this.etapa = etapa;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Long getFalta() {
		return falta;
	}

	public void setFalta(Long falta) {
		this.falta = falta;
	}

	public DisciplinaEntity getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(DisciplinaEntity disciplina) {
		this.disciplina = disciplina;
	}

	public ResultadoAlocacaoDTO getAlocacaoDto() {
		return alocacaoDto;
	}

	public void setAlocacaoDto(ResultadoAlocacaoDTO alocacaoDto) {
		this.alocacaoDto = alocacaoDto;
	}

	public Conceito getConceito() {
		return conceito;
	}

	public void setConceito(Conceito conceito) {
		this.conceito = conceito;
	}



}
