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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

import br.com.bulktecnologia.comuns.util.EntityHelper;

@Name("ConfiguracaoEntity")
@Entity
@Table(name="configuracao")
@NamedQueries(value={
		@NamedQuery(name="ConfiguracaoEntity.recuperaConfiguracoesByInstituicao",query="select c from ConfiguracaoEntity c WHERE c.instituicao.id = :idInstituicao order by c.ano DESC"),
		//remover -- @NamedQuery(name="ConfiguracaoEntity.recuperaConfiguracaoAtivaByInstituicao",query="select c from ConfiguracaoEntity c WHERE c.instituicao.id = :idInstituicao and c.ativo = true"),
		//remover -- @NamedQuery(name="ConfiguracaoEntity.recuperaConfiguracaoAtivaDETACHEDByInstituicao",query="select new ConfiguracaoEntity(c.id, c.nome, c.ano) from ConfiguracaoEntity c WHERE c.instituicao.id = :idInstituicao and c.ativo = true"),
		//@NamedQuery(name="ConfiguracaoEntity.recuperaTurnosByInstituicaoAtiva",query="select turno from ConfiguracaoEntity c join c.turnos turno WHERE c.instituicao.id = :idInstituicao and c.ativo = true order by turno.tipoturno asc"),
		  @NamedQuery(name="ConfiguracaoEntity.recuperaTurnosByIdConfiguracao",query="select turno from ConfiguracaoEntity c join c.turnos turno WHERE c.id = :idConfiguracao order by turno.tipoturno asc"),
		@NamedQuery(name="ConfiguracaoEntity.recuperaConfiguracoesGlobais",query="select c from ConfiguracaoEntity c where c.instituicao is null and c.ano is null")
})
public class ConfiguracaoEntity implements Serializable,EntidadePreenchivel {

	@Id
	@GeneratedValue(generator="configuracao_gen")
	@SequenceGenerator(name="configuracao_gen",sequenceName="configuracao_seq")
	private Long id;
	
	//Campo Opcional devido os 2 Tipos de Configuração: Global e Específica.
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="instituicao_id")
	private InstituicaoEntity instituicao;

	//Campo Opcional devido os 2 Tipos de Configuração: Global e Específica.
	@Column
	private Long ano;

	@Column(nullable=false)
	private String nome;

	@OneToMany(mappedBy="configuracao",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<TurnoEntity> turnos;

	public ConfiguracaoEntity(){
	}
	
	public ConfiguracaoEntity(Long id, String nome, Long anoAdm){
		this.id = id;
		this.nome = nome;
		this.ano = anoAdm;
	}
	
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

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TurnoEntity> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<TurnoEntity> turnos) {
		this.turnos = turnos;
	}

	public Boolean isPreenchido() {
		return EntityHelper.entityHasAnyPropertyFilled(this,null);
	}

	
}
