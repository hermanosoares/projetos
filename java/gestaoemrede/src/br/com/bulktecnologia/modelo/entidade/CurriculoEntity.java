package br.com.bulktecnologia.modelo.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name="curriculo")
@Name("curriculoEntity")
public class CurriculoEntity implements Serializable{

	@Id
	@GeneratedValue(generator="curriculo_gen")
	@SequenceGenerator(name="curriculo_gen",sequenceName="curriculo_seq")
	private Long id;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private PessoaEntity pessoa;
	
	@ManyToMany
	@JoinColumn(nullable=true)
	private List<DisciplinaEntity> disciplinasHabilitadas;
	
	
}
