package br.com.bulktecnologia.teste;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jboss.seam.annotations.Name;
import org.xseam.domain.State;
import org.xseam.validation.CPF;

@Name("XSeamService")
public class XSeamService {

	private String name;

	private Integer age;

	@Temporal(TemporalType.DATE)
	private Date birth;
	
	private State estado;
	
	private State cidade;

	@CPF
	private String cpf;
	
	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	public State getCidade() {
		return cidade;
	}

	public void setCidade(State cidade) {
		this.cidade = cidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

}
