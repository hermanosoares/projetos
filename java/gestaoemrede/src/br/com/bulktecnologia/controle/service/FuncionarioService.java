package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.util.ArrayList;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.modelo.dao.FuncionarioDAO;
import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaDTO;
import br.com.bulktecnologia.modelo.entidade.ContratoTrabalhoEntity;
import br.com.bulktecnologia.modelo.entidade.FuncionarioEntity;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;
import br.com.bulktecnologia.modelo.entidade.RelacaoPessoaEntity;
@Name("FuncionarioService")
@Scope(ScopeType.CONVERSATION)
public class FuncionarioService extends GenericService implements Serializable {

	@In
	private FuncionarioDAO FuncionarioDAO;
	
	@In
	private FacesMessages facesMessages;

	@In(required=false)
	@Out(required=false)
	private FuncionarioEntity funcionarioEntity;
	
	@In(required=false)
	@Out(required=false)
	private PessoaEntity pessoaEntity;
	
	@In(required=false)
	@Out(required=false)
	private ContratoTrabalhoEntity contratoTrabalhoEntity;
	
	
	/**
	 * Boolean que sinaliza direcao navegacional de 'proximo' ou 'anterior'.
	 * caso ocorra falha de validacao na etapa deverá receber FALSE.
	 * caso não ocorra falha de validacao na etapa deverá receber TRUE.
	 * indicando assim que poderá proseguir na navegação.
	 */
	private Boolean etapaValidada = Boolean.TRUE;
	
	
	private Boolean atualizando = Boolean.FALSE;
	
	public String edita(ResultadoPesquisaDTO dto){
		
		this.funcionarioEntity = (FuncionarioEntity) FuncionarioDAO.find(FuncionarioEntity.class, dto.getIdFuncionario());
		this.pessoaEntity = this.funcionarioEntity.getPessoa();
		this.contratoTrabalhoEntity = this.FuncionarioDAO.recuperaContratoTrabalhoByFuncionarioInstituicao(this.funcionarioEntity.getId(), cookie.getIdInstituicao());
		this.atualizando = Boolean.TRUE;
		return "editaFuncionario";
	}
	
	
	public String removeRelacaoFuncionario(ResultadoPesquisaDTO dto){
		return null;
	}
	
	public String insereRelacaoFuncionario(ResultadoPesquisaDTO dto){
		return null;
	}
	
	
	public String cancela(){
		return "cancela";
	}
	
	public String proximo(){
		return etapaValidada?"proximo":"MesmaPagina";	
	}
	
	
	public String anterior(){
		return etapaValidada?"anterior":"MesmaPagina";
	}

	
	public void validaCadastroEtapa1(){
		
		if ( validaInsercaoAtualizacaoFuncionario(this.funcionarioEntity) && 
			 validaInsercaoAtualizacaoPessoa(this.pessoaEntity) ){
			this.etapaValidada = true;
		}
		else{
			this.etapaValidada = false;
		}
		
	}

	public void validaCadastroEtapa2(){
		
	}

	
	public String grava(){
		if (this.etapaValidada){
			if ( atualizando ){
				FuncionarioDAO.merge(this.funcionarioEntity);
				FuncionarioDAO.merge(this.pessoaEntity);
				FuncionarioDAO.merge(this.contratoTrabalhoEntity);
				facesMessages.add("Funcionário: " + this.pessoaEntity.getNome() +"- atualizado com sucesso!");
			}
			else{
				
				this.funcionarioEntity.setPessoa(this.pessoaEntity);
				
				RelacaoPessoaEntity relacao = new RelacaoPessoaEntity();
				relacao.setPessoa(this.pessoaEntity);
				relacao.setInstituicao(recuperaInstituicao());
				relacao.setFuncionario(this.funcionarioEntity);
				//relacao.setDtEntrada(new Date());
				
				if ( this.funcionarioEntity.getContratosTrabalho()==null){
					this.funcionarioEntity.setContratosTrabalho(new ArrayList<ContratoTrabalhoEntity>());
				}
				
				this.contratoTrabalhoEntity.setFuncionario(this.funcionarioEntity);
				this.contratoTrabalhoEntity.setInstituicao(super.recuperaInstituicao());
				
				this.funcionarioEntity.getContratosTrabalho().add(this.contratoTrabalhoEntity);
				
				FuncionarioDAO.persist(this.funcionarioEntity);
				FuncionarioDAO.persist(this.contratoTrabalhoEntity);
				FuncionarioDAO.persist(relacao);
				facesMessages.add("Funcionário: " + this.pessoaEntity.getNome() +"- cadastrado com sucesso!");
			}
				
				
				FuncionarioDAO.flush();

				this.pessoaEntity = null;
				this.funcionarioEntity = null;
				this.contratoTrabalhoEntity = null;
				return "conclui";
		}
		
		return null;
	}
	
	
	
	public Boolean getAtualizando() {
		return atualizando;
	}

	
	public void setAtualizando(Boolean atualizando) {
		this.atualizando = atualizando;
	}


	
	
}
