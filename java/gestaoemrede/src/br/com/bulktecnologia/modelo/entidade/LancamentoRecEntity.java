package br.com.bulktecnologia.modelo.entidade;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.dto.ResultadoAlocacaoDTO;

@Entity
@Name("lancamentoRecEntity")
@Table(name="LancamentoRec")
@NamedQueries(value={
		@NamedQuery(name="LancamentoRecEntity.recuperaLancamentosRec",query="select lanc from LancamentoRecEntity lanc where lanc.etapaRecuperacao.id = :idEtapaRec and lanc.disciplina.id = :idDisciplina and lanc.alocacaoItem.id in (:idsAlocacaoItem) "),
		@NamedQuery(name="LancamentoRecEntity.atualizaAlocacaoItemEmLancamentosRecuperacao",query="update LancamentoRecEntity l set l.alocacaoItem.id = :idAlocacaoItemNew where l.alocacaoItem.id = :idAlocacaoItemOld")
})
public class LancamentoRecEntity extends BaseEntity {

	
	@Id
	@GeneratedValue(generator="lancamentorec_gen")
	@SequenceGenerator(name="lancamentorec_gen",sequenceName="lancamentorec_seq")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn(nullable=false)
	private AlocacaoItemEntity alocacaoItem;

	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn
	private EtapaRecuperacaoEntity etapaRecuperacao;
	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn(nullable=false)
	private DisciplinaEntity disciplina;

	@Column
	@Min(value=0,message="nota de recuperação mínima 0")
	@Max(value=100,message="nota de recuperação máxima 100")
	private Double notaRecuperacao;

	@Column
	@Min(value=0,message="mínima de 0 falta de recuperação.")
	@Max(value=264,message="máximo de 264 faltas anuais de recuperação.")
	private Long faltaRecuperacao;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH})
	@JoinColumn
	private Conceito conceitoRecuperacao;
	
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

	public EtapaRecuperacaoEntity getEtapaRecuperacao() {
		return etapaRecuperacao;
	}

	public void setEtapaRecuperacao(EtapaRecuperacaoEntity etapaRecuperacao) {
		this.etapaRecuperacao = etapaRecuperacao;
	}

	public DisciplinaEntity getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(DisciplinaEntity disciplina) {
		this.disciplina = disciplina;
	}

	public Double getNotaRecuperacao() {
		return notaRecuperacao;
	}

	public void setNotaRecuperacao(Double notaRecuperacao) {
		this.notaRecuperacao = notaRecuperacao;
	}

	public Long getFaltaRecuperacao() {
		return faltaRecuperacao;
	}

	public void setFaltaRecuperacao(Long faltaRecuperacao) {
		this.faltaRecuperacao = faltaRecuperacao;
	}

	public Conceito getConceitoRecuperacao() {
		return conceitoRecuperacao;
	}

	public void setConceitoRecuperacao(Conceito conceitoRecuperacao) {
		this.conceitoRecuperacao = conceitoRecuperacao;
	}

	public ResultadoAlocacaoDTO getAlocacaoDto() {
		return alocacaoDto;
	}

	public void setAlocacaoDto(ResultadoAlocacaoDTO alocacaoDto) {
		this.alocacaoDto = alocacaoDto;
	}

	
}
