package br.com.bulktecnologia.controle.service;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.contexts.Contexts;

import br.com.bulktecnologia.comuns.converter.FacesMessageSessionScoped;
import br.com.bulktecnologia.modelo.dao.AlunoDAO;
import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaDTO;
import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.entidade.ContatoEntity;
import br.com.bulktecnologia.modelo.entidade.EnderecoEntity;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;

@Name("AlunoService")
@Scope(ScopeType.CONVERSATION)
public class AlunoService extends GenericService implements Serializable{
	
	@In
	private AlunoDAO AlunoDAO;

	@In(required=false)
	@Out(required=false)
	private PessoaEntity pessoaEntity;

	@In(required=false)
	@Out(required=false)
	private AlunoEntity alunoEntity;

	@In(required=false)
	@Out(required=false)
	private EnderecoEntity enderecoEntity;
	
	@In(required=false)
	@Out(required=false)
	private ContatoEntity contatoEntity;
	
	private Boolean nenhumaDasPessoasEncontradas = Boolean.FALSE;

	private Boolean atualizando = Boolean.FALSE;

	private Boolean enderecoViaCEP = Boolean.TRUE;
	private String cepResidencial;
	
	@In
	private FacesMessageSessionScoped FacesMessageSessionScoped;
	
	
	private static final String MESMA_PAGINA = "MesmaPagina";
	private static final String PROXIMO = "proximo";
	
	public String edita(ResultadoPesquisaDTO dto){
		this.alunoEntity = this.AlunoDAO.recuperaAlunoPorIdPessoa(dto.getIdPessoa());
		this.pessoaEntity = this.alunoEntity.getPessoa();
		this.enderecoEntity = this.alunoEntity.getPessoa().getEndereco();
		this.contatoEntity = this.alunoEntity.getPessoa().getContato();
		this.atualizando = Boolean.TRUE;
		
		return "editaAluno";
	}
	
	public String removeRelacaoAluno(ResultadoPesquisaDTO dto){
		
		return null;
	}
	
	public String insereRelacaoAluno(ResultadoPesquisaDTO dto){
		return null;
	}
	
	
	/**
	 * Desativa a recuperacao via CEP.
	 */
	public void desativaUsoCEP(){
		this.enderecoViaCEP = Boolean.FALSE;
	}
	
	/**
	 * Desativa a recuperacao via CEP.
	 */
	public void ativaUsoCEP(){
		this.enderecoViaCEP = Boolean.TRUE;
	}
	
	
	
	
	public void insereAlunoNaInstituicao(){
/*		PessoaEntity p = (PessoaEntity)AlunoDAO.find(PessoaEntity.class,idPessoa);
		if (p!=null){

			if ( AlunoDAO.AlunoAssociadoNaInstituicao(idPessoa, cookie.getIdInstituicao()) ){
				facesMessages.add(p.getNome()+", atualmente ja encontra-se cadastrado em sua instituicao de ensino!. ");
				return;
			}

			AlunoEntity a = AlunoDAO.recuperaAlunoPorIdPessoa(idPessoa);
			
			if (a==null){
				a = new AlunoEntity();
				a.setPessoa(p);
				AlunoDAO.persist(a);
			}
			
			AlunoDAO.insereAlunoNaInstituicao(a,p, cookie.getIdInstituicao(), cookie.getLogin());
			facesMessages.add("Aluno: "+p.getNome()+", Incluido com sucesso em sua instituicao de ensino! ");
		}
		else{
			facesMessages.add("Pessoa nao existe no Gestao em Rede. ");
		}
*/		
		Contexts.removeFromAllContexts("pessoasEncontradas");
		pessoaEntity = null;
		throw new UnsupportedOperationException("ainda nao implementando!!!");
	}

	

	
	
	public String validaCadastro1(){
		
		if ( !validaInsercaoAtualizacaoAluno(this.alunoEntity) || !validaInsercaoAtualizacaoPessoa(this.pessoaEntity) ){
				return MESMA_PAGINA;	
		}
		
		return PROXIMO;
	}
	

	
	
	
	public String validaCadastro2(){

		
		if ( validaCamposObrigatoriosEndereco()){
			return PROXIMO;	
		}
		else{
			return MESMA_PAGINA;
		}
		
	}
	
	
	
	
	public String proximo(){
		return "proximo";
	}
	
	public String anterior(){
		return "anterior";
	}

	public String cancelar(){
		return "cancelar";
	}

	
	@Transactional
	public String concluirCadastro(){
		
		if ( PROXIMO.equals(validaCadastro1()) && 
			 PROXIMO.equals(validaCadastro2())	){

			
			if (contatoEntity!=null && contatoEntity.isPreenchido()){
				pessoaEntity.setContato(contatoEntity);
			}
			
			if (enderecoEntity!=null && enderecoEntity.isPreenchido()){
				this.pessoaEntity.setEndereco(enderecoEntity);
			}
			
			String nome = new String(pessoaEntity.getNome());
			
			if (!atualizando){
				
				AlunoDAO.insereAlunoNaInstituicao(alunoEntity, pessoaEntity, cookie.getIdInstituicao(), cookie.getLogin());
				AlunoDAO.flush();
				FacesMessageSessionScoped.add("aluno "+nome+" inserido com sucesso!");
			}
			else{
				
				this.alunoEntity.setPessoa(pessoaEntity);
				AlunoDAO.merge(alunoEntity);
				AlunoDAO.flush();
				FacesMessageSessionScoped.add("aluno "+nome+" atualizado com sucesso!");
			}
			
			this.pessoaEntity = null;
			this.alunoEntity = null;
			
			return "cadastroConcluido";
		}
		return MESMA_PAGINA;
	}
	
	
	
	
	
	private boolean validaCamposObrigatoriosEndereco(){
		boolean valido = true;
		
		if (enderecoEntity!=null && enderecoEntity.isPreenchido()){
			
			if (StringUtils.isBlank(enderecoEntity.getRua())){
				facesMessages.add("campo rua obrigatorio");
				valido = false;
			}

			if (enderecoEntity.getNumero()==null){
				facesMessages.add("campo numero da rua obrigatório");
				valido = false;
			}

			if (enderecoEntity.getCidade()!=null){
				facesMessages.add("campo cidade obrigatório");
				valido = false;
			}

			if (StringUtils.isBlank(enderecoEntity.getBairro())){
				facesMessages.add("campo bairro obrigatório");
				valido = false;
			}

			if (enderecoEntity.getEstado()!=null){
				facesMessages.add("campo estado obrigatório");
				valido = false;
			}
			if (StringUtils.isBlank(enderecoEntity.getZona())){
				facesMessages.add("campo Zona Urbana obrigatório");
				valido = false;
			}

		}
		
		
		return valido;
	}
		

		
	
	
	
	public Boolean getNenhumaDasPessoasEncontradas() {
		return nenhumaDasPessoasEncontradas;
	}

	public void setNenhumaDasPessoasEncontradas(Boolean nenhumaDasPessoasEncontradas) {
		this.nenhumaDasPessoasEncontradas = nenhumaDasPessoasEncontradas;
	}

	
	public Boolean getAtualizando() {
		return atualizando;
	}



	public void setAtualizando(Boolean atualizando) {
		this.atualizando = atualizando;
	}


	public Boolean getEnderecoViaCEP() {
		return enderecoViaCEP;
	}


	public void setEnderecoViaCEP(Boolean enderecoViaCEP) {
		this.enderecoViaCEP = enderecoViaCEP;
	}


	public String getCepResidencial() {
		return cepResidencial;
	}


	public void setCepResidencial(String cepResidencial) {
		this.cepResidencial = cepResidencial;
	}
	
}
