package br.com.bulktecnologia.modelo.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Id;

import org.jboss.seam.annotations.Name;
import org.xseam.model.BaseEntity;

import br.com.bulktecnologia.modelo.enums.Perfil;
@Entity
@Table(name="perfil")
@Name("perfilEntity")
public class PerfilEntity extends BaseEntity {

	@Id
	@GeneratedValue(generator="perfil_gen")
	@SequenceGenerator(name="perfil_gen",sequenceName="perfil_seq")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private UsuarioInstituicaoEntity usuarioInstituicao;
	
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	
	public PerfilEntity(UsuarioInstituicaoEntity ui, Perfil perfil){
		this.setUsuarioInstituicao(ui);
		this.setPerfil(perfil);
	}
	
	public PerfilEntity(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioInstituicaoEntity getUsuarioInstituicao() {
		return usuarioInstituicao;
	}

	public void setUsuarioInstituicao(UsuarioInstituicaoEntity usuarioInstituicao) {
		this.usuarioInstituicao = usuarioInstituicao;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}
