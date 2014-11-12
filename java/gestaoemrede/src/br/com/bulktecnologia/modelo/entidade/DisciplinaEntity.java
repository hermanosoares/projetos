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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Min;
import org.hibernate.validator.Pattern;
import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.enums.TipoAtividadeDisciplina;
import br.com.bulktecnologia.modelo.enums.TipoAvaliacao;
import br.com.bulktecnologia.modelo.enums.TipoComputaCargaHoraria;
import br.com.bulktecnologia.modelo.enums.TipoDisciplina;
import br.com.bulktecnologia.modelo.enums.TipoPertence;
@Entity
@Table(name="disciplina")
@Name("disciplinaEntity")
public class DisciplinaEntity implements Serializable {

	@Id
	@GeneratedValue(generator="disciplina_gen")
	@SequenceGenerator(name="disciplina_gen",sequenceName="disciplina_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name="serie_id",nullable=false)
	private SerieEntity serie;
	
	@ManyToMany(mappedBy="disciplinas",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<TurmaEntity> turmas;
	
	@Column(nullable=false)
	private String nome;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	private TipoDisciplina tipoDisciplina;
	
	@Column(nullable=true)
	private String legenda;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoComputaCargaHoraria compCargHoraria;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	private TipoPertence pertence;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoAtividadeDisciplina atividade;
	
	@Column(nullable=false)
	private Long numAulaSemana;
	
	@Column(nullable=false)
	private Long duracaoModAula;
	
	@Column(nullable=false)
	private Boolean participaResultFinal = Boolean.TRUE;
	
	@ManyToMany(mappedBy="disciplinas")
	private List<AlocacaoItemEntity> alocacaoItems;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoAvaliacao tipoAvaliacao;

	@Pattern(regex="[0-9]{4}:[0-9]{2}",message="Carga horária inválida!. Use o formatação de exemplo: 0166:40 (166 horas e 40minutos)  ")
	@Column
	private String cargaHorariaDisciplina;
	
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

	public SerieEntity getSerie() {
		return serie;
	}

	public void setSerie(SerieEntity serie) {
		this.serie = serie;
	}

	public TipoDisciplina getTipoDisciplina() {
		return tipoDisciplina;
	}

	public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public TipoComputaCargaHoraria getCompCargHoraria() {
		return compCargHoraria;
	}

	public void setCompCargHoraria(TipoComputaCargaHoraria compCargHoraria) {
		this.compCargHoraria = compCargHoraria;
	}

	public TipoPertence getPertence() {
		return pertence;
	}

	public void setPertence(TipoPertence pertence) {
		this.pertence = pertence;
	}

	public TipoAtividadeDisciplina getAtividade() {
		return atividade;
	}

	public void setAtividade(TipoAtividadeDisciplina atividade) {
		this.atividade = atividade;
	}

	public Long getNumAulaSemana() {
		return numAulaSemana;
	}

	public void setNumAulaSemana(Long numAulaSemana) {
		this.numAulaSemana = numAulaSemana;
	}

	public Long getDuracaoModAula() {
		return duracaoModAula;
	}

	public void setDuracaoModAula(Long duracaoModAula) {
		this.duracaoModAula = duracaoModAula;
	}

	public Boolean getParticipaResultFinal() {
		return participaResultFinal;
	}

	public void setParticipaResultFinal(Boolean participaResultFinal) {
		this.participaResultFinal = participaResultFinal;
	}

	/**
	 * metodo auxiliar usado para exibiçao em datatables...
	 * @return
	 */
	public String getParticipaResultFinalAux(){
		return participaResultFinal?"sim":"não";
	}

	public List<TurmaEntity> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<TurmaEntity> turmas) {
		this.turmas = turmas;
	}

	public List<AlocacaoItemEntity> getAlocacaoItems() {
		return alocacaoItems;
	}

	public void setAlocacaoItems(List<AlocacaoItemEntity> alocacaoItems) {
		this.alocacaoItems = alocacaoItems;
	}


	public Boolean getDisciplinaIsoladaSelecionada(){
		return TipoDisciplina.DISCIPLINA_ISOLADA.equals(this.tipoDisciplina);
	}
	
	public void cleanupNomeDisciplina(){
		this.nome=null;
	}

	public TipoAvaliacao getTipoAvaliacao() {
		return tipoAvaliacao;
	}

	public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}

	public String getCargaHorariaDisciplina() {
		return cargaHorariaDisciplina;
	}

	public void setCargaHorariaDisciplina(String cargaHorariaDisciplina) {
		this.cargaHorariaDisciplina = cargaHorariaDisciplina;
	}


	
	
	
}
