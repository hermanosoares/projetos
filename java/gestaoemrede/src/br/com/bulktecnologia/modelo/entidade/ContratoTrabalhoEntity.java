package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.modelo.enums.TipoContrato;
import br.com.bulktecnologia.modelo.enums.TipoModalidadeContratacao;
import br.com.bulktecnologia.modelo.enums.TipoRegimeContratacao;
@Entity
@Table(name="contratotrabalho")
@Name("contratoTrabalhoEntity")
@NamedQueries( value={
					   @NamedQuery(name="ContratoTrabalhoEntity.recuperaContratoTrabalhoByFuncionarioInstituicao",query="select c from ContratoTrabalhoEntity c where c.funcionario.id = :idFuncionario and c.instituicao.id = :idInstituicao") }
			 )
public class ContratoTrabalhoEntity implements Serializable {
	
	@Id
	@GeneratedValue(generator="contratotrabalho_gen")
	@SequenceGenerator(name="contratotrabalho_gen",sequenceName="contratotrabalho_seq")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="funcionario_id",nullable=false)
	private FuncionarioEntity funcionario;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="instituicao_id",nullable=false)
	private InstituicaoEntity instituicao;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="tipofuncao_id",nullable=false)
	private TipoFuncaoEntity tipoFuncao;

	@Enumerated(EnumType.STRING)
	@Column
	private TipoContrato tipoContrato;

	@Enumerated(EnumType.STRING)
	@Column
	private TipoRegimeContratacao tipoRegimeContratacao;

	@Enumerated(EnumType.STRING)
	@Column
	private TipoModalidadeContratacao tipoModalidadeContratacao;

	@Temporal(TemporalType.DATE)
	private Date dtAdmissao;
	
	@Temporal(TemporalType.DATE)
	private Date dtDemissao;
	
	@Temporal(TemporalType.DATE)
	private Date dtIniAtividade;
	
	@Temporal(TemporalType.DATE)
	private Date dtfimAtividade;



	public TipoFuncaoEntity getTipoFuncao() {
		return tipoFuncao;
	}

	public void setTipoFuncao(TipoFuncaoEntity tipoFuncao) {
		this.tipoFuncao = tipoFuncao;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public TipoRegimeContratacao getTipoRegimeContratacao() {
		return tipoRegimeContratacao;
	}

	public void setTipoRegimeContratacao(TipoRegimeContratacao tipoRegimeContratacao) {
		this.tipoRegimeContratacao = tipoRegimeContratacao;
	}

	public TipoModalidadeContratacao getTipoModalidadeContratacao() {
		return tipoModalidadeContratacao;
	}

	public void setTipoModalidadeContratacao(
			TipoModalidadeContratacao tipoModalidadeContratacao) {
		this.tipoModalidadeContratacao = tipoModalidadeContratacao;
	}

	public Date getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(Date dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public Date getDtDemissao() {
		return dtDemissao;
	}

	public void setDtDemissao(Date dtDemissao) {
		this.dtDemissao = dtDemissao;
	}

	public Date getDtIniAtividade() {
		return dtIniAtividade;
	}

	public void setDtIniAtividade(Date dtIniAtividade) {
		this.dtIniAtividade = dtIniAtividade;
	}

	public Date getDtfimAtividade() {
		return dtfimAtividade;
	}

	public void setDtfimAtividade(Date dtfimAtividade) {
		this.dtfimAtividade = dtfimAtividade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

}
