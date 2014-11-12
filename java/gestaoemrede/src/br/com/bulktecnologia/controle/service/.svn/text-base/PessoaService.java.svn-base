package br.com.bulktecnologia.controle.service;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.controle.jsf.EasyPaginacao;
import br.com.bulktecnologia.modelo.dao.PessoaDAO;
import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaDTO;
import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.entidade.FuncionarioEntity;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;
import br.com.bulktecnologia.modelo.enums.ControlePaginacao;

@Name("PessoaService")
@Scope(ScopeType.CONVERSATION)
@AutoCreate
public class PessoaService extends GenericService{
	
	
	@In(required=false)
	private AlunoEntity alunoEntity;

	@In(required=false)
	private FuncionarioEntity funcionarioEntity;
	
	@In(required=false)
	private PessoaEntity pessoaEntity;

	
	@In
	private EasyPaginacao easyPaginacao;
	
	@In
	private PessoaDAO PessoaDAO;
	
	@In
	private FacesMessages facesMessages;
	
	private List<ResultadoPesquisaDTO> resultadoPesquisaPessoas;
	
	private Boolean pesquisarSomenteNaMinhaInstituicao = Boolean.TRUE;
	
	
	public String pesquisa(){
		
		resultadoPesquisaPessoas = PessoaDAO.pesquisaByDadosCadastrais( this.pessoaEntity,
															 			this.cookie.getIdInstituicao(),
															 			this.pesquisarSomenteNaMinhaInstituicao.booleanValue());
		
		if (resultadoPesquisaPessoas==null){
			facesMessages.add("preencha algum filtro de pesquisa.");
		}
		else{ 
			 if ( resultadoPesquisaPessoas.size()==0 ){
				 facesMessages.add("nenhum registro encontrado.");
			 }
		}
		
		return PAGINA_LIST;
	}
	
	
	
	public void pesquisaByAjax(){
		this.pesquisa();
	}
	
	
	
	public void pesquisaByControleNavegacao(String nomeControle){
		this.pesquisa();
		
		ControlePaginacao controle = ControlePaginacao.valueOf(nomeControle);
		
		if (ControlePaginacao.proximo.equals(controle)){
			easyPaginacao.proximoComboNavegacao();
		}
		else{
			if (ControlePaginacao.anterior.equals(controle)){
				easyPaginacao.anteriorComboNavegacao();	
			}
			else{
				if (ControlePaginacao.primeiro.equals(controle)){
					easyPaginacao.primeiroComboNavegacao();
				}
				else{
					if (ControlePaginacao.ultimo.equals(controle)){
						easyPaginacao.ultimoComboNavegacao();
					}
				}
			}
		}
	}
	
	
	
	


	public String remove(PessoaEntity p){
		PessoaDAO.remove(p);
		PessoaDAO.flush();
		resultadoPesquisaPessoas.remove(p);
		
		return PAGINA_LIST;
	}



	public List<ResultadoPesquisaDTO> getResultadoPesquisaPessoas() {
		return resultadoPesquisaPessoas;
	}



	public Boolean getPesquisarSomenteNaMinhaInstituicao() {
		return pesquisarSomenteNaMinhaInstituicao;
	}



	public void setPesquisarSomenteNaMinhaInstituicao(
			Boolean pesquisarSomenteNaMinhaInstituicao) {
		this.pesquisarSomenteNaMinhaInstituicao = pesquisarSomenteNaMinhaInstituicao;
	}



	
}
