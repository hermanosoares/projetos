package br.com.bulktecnologia.controle.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.seam.Component;

import br.com.bulktecnologia.comuns.util.DataUtil;
import br.com.bulktecnologia.controle.list.GenericList;
import br.com.bulktecnologia.modelo.annotation.CampoObrigatorio;
import br.com.bulktecnologia.modelo.dao.AlunoDAO;
import br.com.bulktecnologia.modelo.dao.BaseDAO;
import br.com.bulktecnologia.modelo.dao.FuncionarioDAO;
import br.com.bulktecnologia.modelo.dao.PessoaDAO;
import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.entidade.FuncionarioEntity;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;
/**
 * Classe de servico base para derivação das demais SERVICE.
 * 
 * @author hsoares
 *
 */
public abstract class GenericService extends GenericList implements Serializable {
	
	protected final String PAGINA_ADD_EDIT = "add_edit";
	protected final String PAGINA_LIST = "list";
	
	
	
	/**
	 * obtem a sessão logada da sessão, e carrega o objeto instituicao
	 * a cada chamada neste.
	 * 
	 * @return InstituicaoEntity
	 */
	public InstituicaoEntity recuperaInstituicao(){
		BaseDAO BaseDAO = (BaseDAO) Component.getInstance("BaseDAO");
		return (InstituicaoEntity)BaseDAO.find(InstituicaoEntity.class, cookie.getIdInstituicao());
	}
	
	
	
	
	

	
	/**
	 * valida a Consistencia de operações de inserção/atualização de funcionário(docente).
	 * @param funcionario
	 * @return boolean - valido ou não valido.
	 */
	public boolean validaInsercaoAtualizacaoFuncionario(FuncionarioEntity funcionarioEntity){
		
		if (funcionarioEntity==null){
			throw new RuntimeException("impossivel validar insercao/atualizacao funcionario: OBJETO NULO");
		}
		
		FuncionarioDAO FuncionarioDAO = (FuncionarioDAO) Component.getInstance("FuncionarioDAO");
		
		Boolean consistenteCodigoFuncional = Boolean.TRUE;
		
		if ( !StringUtils.isBlank(funcionarioEntity.getCodigoFuncional()) ){
			FuncionarioEntity funcionarioJaCadastrado = FuncionarioDAO.recuperaFuncionaroByCodigoFuncional(funcionarioEntity.getCodigoFuncional());
			if (funcionarioJaCadastrado!=null){
				
				//operação de inserção
				if (funcionarioEntity.getId()==null){
					this.exibeMensagemInconsistenciaCodigoFuncional(funcionarioJaCadastrado);
					consistenteCodigoFuncional = false;
				}
				else{
					//operação de atualização
					if ( funcionarioJaCadastrado.getId().compareTo(funcionarioEntity.getId())==0){
						consistenteCodigoFuncional = true;
					}
					else{
						this.exibeMensagemInconsistenciaCodigoFuncional(funcionarioJaCadastrado);
						consistenteCodigoFuncional = false;
					}
				}
			}
			else{
				consistenteCodigoFuncional = true;
			}
		}
	
		return consistenteCodigoFuncional;
	}
	
	
	

	
	
	
	
	
	/**
	 * valida a Consistencia de operações de inserção/atualização de pessoa. 
	 * @param pessoaEntity
	 * @return boolean - valido ou não valido.
	 */
	public boolean validaInsercaoAtualizacaoPessoa(PessoaEntity pessoaEntity){
		if (pessoaEntity==null){
			throw new RuntimeException("impossivel validar insercao/atualizacao pessoa: OBJETO NULO");
		}
		
		Boolean consistenteCPF = Boolean.TRUE;			//não é obrigatorio  
		Boolean consistenteRG = Boolean.TRUE;          //não é obrigatorio  
		Boolean consistenteNome = Boolean.TRUE;	  
		
		PessoaDAO PessoaDAO = (PessoaDAO)Component.getInstance("PessoaDAO");
		
		if ( !StringUtils.isBlank(pessoaEntity.getCpf()) ){
			PessoaEntity pessoaJaCadastrada = PessoaDAO.recuperaPessoaByCPF(pessoaEntity.getCpf());
			
			if ( pessoaJaCadastrada!=null ){
				
				//operação de inserção
				if (pessoaEntity.getId()==null){
					this.exibeMensagemInconsistenciaCPF(pessoaJaCadastrada);
					consistenteCPF = false;
				}
				else{
					//operação de atualização
					if ( pessoaJaCadastrada.getId().compareTo(pessoaEntity.getId())==0){
						consistenteCPF = true;
					}
					else{
						this.exibeMensagemInconsistenciaCPF(pessoaJaCadastrada);
						consistenteCPF = false;
					}
				}


			}
			else{
				consistenteCPF = true;
			}
		}
	
		if ( !StringUtils.isBlank(pessoaEntity.getRgorgaoexpedido()) && !StringUtils.isBlank(pessoaEntity.getRg()) ){
			PessoaEntity pessoaJaCadastrada = PessoaDAO.recuperaPessoaByRG(pessoaEntity.getRg(),pessoaEntity.getRgorgaoexpedido());
			if ( pessoaJaCadastrada!=null ){
				//operação de inserção
				if (pessoaEntity.getId()==null){
					this.exibeMensasgemInconsistenciaRG(pessoaJaCadastrada);
					consistenteRG = false;
				}
				else{
					//operação de atualização
					if ( pessoaJaCadastrada.getId().compareTo(pessoaEntity.getId())==0){
						consistenteRG = true;
					}
					else{
						this.exibeMensasgemInconsistenciaRG(pessoaJaCadastrada);
						consistenteRG = false;
					}
				}
			}
			else{
				consistenteRG = true;
			}
		}

		PessoaEntity pessoaJaCadastrada = PessoaDAO.recuperaPessoaByCamposObrigatoriosCadastro( 	 pessoaEntity.getNomeSemAcento(),
																										 pessoaEntity.getSexo(), 
																										 pessoaEntity.getDtNascimento(), 
																										 pessoaEntity.getNomeMaeSemAcento(), 
																										 pessoaEntity.getDtNascimentoMae() );
			
			if ( pessoaJaCadastrada!=null ){
				//operação de inserção
				if (pessoaEntity.getId()==null){
					this.exibeMensagemInconsistenciaPessoaJaExistente(pessoaJaCadastrada);
					consistenteNome = false;
				}
				else{
					//operação de atualização
					if ( pessoaJaCadastrada.getId().compareTo(pessoaEntity.getId())==0){
						consistenteRG = true;
					}
					else{
						this.exibeMensagemInconsistenciaPessoaJaExistente(pessoaJaCadastrada);
						consistenteNome = false;
					}
				}
			}
			else{
				consistenteNome = true;
			}
			
		
			if ( !consistenteCPF || !consistenteRG || !consistenteNome ){
				return Boolean.FALSE;
			}
			else{
				return Boolean.TRUE;
			}
			 
	}
	
	

	
	
	
	
	public boolean validaInsercaoAtualizacaoAluno(AlunoEntity alunoEntity){

		if (alunoEntity==null){
			throw new RuntimeException("impossivel validar insercao/atualizacao aluno: OBJETO NULO");
		}

		AlunoDAO AlunoDAO = (AlunoDAO)Component.getInstance("AlunoDAO");
		
		Boolean consistenteCodigoEducaCenso = Boolean.TRUE;
		
		if ( !StringUtils.isBlank(alunoEntity.getCodeducacenso()) ){

			AlunoEntity alunoJaExistente = AlunoDAO.verificaCodigoEducaCenso(alunoEntity.getCodeducacenso().trim());
			
			if (alunoJaExistente!=null){

				if ( alunoEntity.getId()==null ){
					this.exibeMensagemInconsistenciaAluno(alunoJaExistente);
					consistenteCodigoEducaCenso = false;
				}
				else{
					if (alunoJaExistente.getId().compareTo(alunoEntity.getId())==0){
						consistenteCodigoEducaCenso = true;
					}
					else{
						this.exibeMensagemInconsistenciaAluno(alunoJaExistente);						
						consistenteCodigoEducaCenso = false;
					}
				}
				
			}
			else{
				consistenteCodigoEducaCenso = true;
			}
		}
		
		return consistenteCodigoEducaCenso;
	}
	
	
	
	
	
	
	private void exibeMensagemImpossivelContinuar(){
		facesMessages.add("impossível continuar. Localize a pessoa ela já está na rede.");		
	}
	
	private void exibeMensagemInconsistenciaCodigoFuncional(FuncionarioEntity funcionarioJaCadastrado){
		this.exibeMensagemImpossivelContinuar();
		facesMessages.add("Código funcional: #{funcionarioEntity.codigoFuncional} já cadastrado");
		facesMessages.add("para: "+funcionarioJaCadastrado.getPessoa().getNome() + " ");
	}
	
	
	
	private void exibeMensasgemInconsistenciaRG(PessoaEntity pessoaJaCadastrada){
		this.exibeMensagemImpossivelContinuar();
		facesMessages.add("RG: #{pessoaEntity.rgorgaoexpedido}-#{pessoaEntity.rg} já cadastrado");
		facesMessages.add("para: "+pessoaJaCadastrada.getNome() + " ");
	}
	
	private void exibeMensagemInconsistenciaCPF(PessoaEntity pessoaJaCadastrada){
		this.exibeMensagemImpossivelContinuar();
		facesMessages.add("CPF: #{pessoaEntity.cpf} já cadastrado");
		facesMessages.add("para: "+pessoaJaCadastrada.getNome() + " ");
	}
	
	
	private void exibeMensagemInconsistenciaPessoaJaExistente(PessoaEntity pessoaEntity){
		this.exibeMensagemImpossivelContinuar();
		facesMessages.add("Nome: "+pessoaEntity.getNome()+"- Dt.Nasc: "+ DataUtil.formataDataDDMMYYYY(pessoaEntity.getDtNascimento()) );
		facesMessages.add("Mãe: " +pessoaEntity.getNomeMae()+" - Dt.Nasc: "+ DataUtil.formataDataDDMMYYYY(pessoaEntity.getDtNascimentoMae()) );
		facesMessages.add(" Já cadastrado! ");
	}
	
	private void exibeMensagemInconsistenciaAluno(AlunoEntity alunoJaExistente){
		this.exibeMensagemImpossivelContinuar();
		facesMessages.add("Código EducaCenso: "+alunoJaExistente.getCodeducacenso());
		facesMessages.add("Pertence a "+ alunoJaExistente.getPessoa().getNome());
		Date dtnascimento = alunoJaExistente.getPessoa().getDtNascimento();
		facesMessages.add("Nascido em :"+DataUtil.formataDataDDMMYYYY(dtnascimento));
	}
	
	
	
	/**
	 * Valida vários campos de uma só vez, usando o mesmo conceito do Hibernate Validator, exceto que ele não permite disparar um flush
	 * ao entitymanager, que é o comportamento default do hibernate validator, devido a essa possibilidade quando a validacao dele falha 
	 * o validator torna as entidades detached, e a transação irrecuperável.
	 * <br/>
	 * <br/>
	 * <b>
	 * Esta Workaround, evita todo esse problema, usando validação proprio inspirada no conceito do hibernate validator, porem ele não 
	 * envia nada(FLUSH) ao entitymanager, que esteja inconsistente, tornando a transação recuperável.
	 * </b>
	 * <br/>
	 *  
	 * @author hsoares 
	 * @param campos
	 * @return Boolean
	 */
	protected Boolean validaConstraintsAnotadasNasEntidades(Object[] entidades){
		
		Boolean valido = Boolean.TRUE;
		
		for (Object entidade: entidades){
			
			if (entidade!=null){
				for (Field field : entidade.getClass().getDeclaredFields()){
					
					if ( !validaConstraint(field,entidade) ){
						 	valido = false;
				    }
				}
			}
				
		}
		
		return valido;
	}
	
	
	
	
	/**
	 * TRUE indica que a validacao de constraint está ok, não fere nenhuma constraint.
	 * FALSE indica que a validacao de constraint  não está ok, fere constraint(s).
	 * @param o
	 * @return Boolean
	 */
	private Boolean validaConstraint(Field o, Object entidade){
		
		CampoObrigatorio required = o.getAnnotation(CampoObrigatorio.class);
		if ( required != null ){
			
			Object value = null;
			
			try {
				value = PropertyUtils.getProperty(entidade, o.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if ( !(o.getType().isAssignableFrom(String.class)) && value==null ){
				facesMessages.add("Campo Obrigatório: "+required.nomeCampo());
				return Boolean.FALSE;
			}
			else{
				if ( value==null || StringUtils.isBlank(value.toString()) ){
					facesMessages.add("Campo Obrigatório: "+required.nomeCampo());
					return Boolean.FALSE;
				}
			}
		}
		
		return Boolean.TRUE;
	}
	
	
	
	
	
	
}
