package br.com.bulktecnologia.modelo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.comuns.util.SQLHelper;
import br.com.bulktecnologia.controle.jsf.EasyPaginacao;
import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaDTO;
import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.entidade.FuncionarioEntity;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;
import br.com.bulktecnologia.modelo.enums.Sexo;

@Name("PessoaDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class PessoaDAO extends BaseDAO{
	
	@In
	private EasyPaginacao easyPaginacao;

	
	/**
	 * Pesquisa Pessoas com o nome Informado.
	 * @param tipoPesquisa 
	 * 
	 * @param nome - Obrigatorio
	 * @param sexo - opcional
	 * @param dtnascimento - opcional
	 * @param cpf - opcional
	 * @param rg - opcional0,
	 * @return List<PessoaEntity>
	 */
	public List<ResultadoPesquisaDTO> pesquisaByDadosCadastrais(PessoaEntity p,Long idInstituicao, boolean pesquisarSomenteNaMinhaInstituicao){
		
		if ( StringUtils.isBlank(p.getNome()) &&
			 StringUtils.isBlank(p.getCpf()) &&
			 StringUtils.isBlank(p.getRg())  && 
			 StringUtils.isBlank(p.getRgorgaoexpedido()) &&
			 p.getSexo()==null &&
			 p.getDtNascimento()==null &&
			 StringUtils.isBlank(p.getNomeMae()) &&
			 StringUtils.isBlank(p.getNomePai()) ){
			
			return null;
		}
		
		Map<String,Object> parametros = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer();
		
		hql.append(" SELECT new br.com.bulktecnologia.modelo.dto.ResultadoPesquisaDTO(p.id, ");
		hql.append(" (select rp.aluno.id from RelacaoPessoaEntity rp where rp.instituicao.id = :idInstituicao   rp.pessoa.id = p.id), ");
		hql.append(" (select rp.funcionario.id from RelacaoPessoaEntity rp where rp.instituicao.id = :idInstituicao  rp.pessoa.id = p.id), ");
		hql.append(" p.nome, p.sexo, p.dtNascimento, p.nomePai, p.nomeMae, p.cpf, p.rg, p.rgorgaoexpedido , p.naturalidade, p.foto) ");
		hql.append(" FROM PessoaEntity p ");
		
		
		//pesquisa somente na instituicao do usuário
		if (pesquisarSomenteNaMinhaInstituicao){
			hql.append(", RelacaoPessoaEntity rp WHERE");
			hql.append("  rp.pessoa.id = p.id AND ");
			hql.append("  rp.instituicao.id = :idInstituicao");
		}
		else{
			hql.append(" WHERE ");
		}
		
		parametros.put("idInstituicao",idInstituicao);
		
		
		if (!StringUtils.isBlank(p.getNome())){
			String[] nomeSplit = SQLHelper.realizaLikeSplitEmNome(p.getNomeSemAcento());
			for (int i=0;i<nomeSplit.length;i++){
				String parameterName = "nome"+i;
				hql.append(" p.nomeSemAcento like :"+parameterName);
				parametros.put(parameterName,nomeSplit[i]);
			}
		}
		
		
		if (p.getSexo()!=null){
			hql.append(" p.sexo = :sexo ");
			parametros.put("sexo",p.getSexo());
		}
		
		if (p.getDtNascimento()!=null){
			hql.append(" p.dtNascimento = :dtnascimento ");
			parametros.put("dtnascimento",p.getDtNascimento());
		}
		
		if (!StringUtils.isBlank(p.getCpf())){
			hql.append(" p.cpf = :cpf ");
			parametros.put("cpf",p.getCpf());
		}

		if (!StringUtils.isBlank(p.getRg())){
			hql.append(" p.rg = :rg ");
			parametros.put("rg",p.getRg());
		}

		if (!StringUtils.isBlank(p.getNomeMaeSemAcento())){
			String[] nomeSplit = SQLHelper.realizaLikeSplitEmNome(p.getNomeMaeSemAcento());
			for (int i=0;i<nomeSplit.length;i++){
				String parameterName = "nomeMae"+i;
				hql.append(" p.nomeMaeSemAcento like :"+parameterName);
				parametros.put(parameterName,nomeSplit[i]);
			}			
		}

		
		if (!StringUtils.isBlank(p.getNomePaiSemAcento())){
			String[] nomeSplit = SQLHelper.realizaLikeSplitEmNome(p.getNomePaiSemAcento());
			for (int i=0;i<nomeSplit.length;i++){
				String parameterName = "nomePai"+i;
				hql.append(" p.nomePaiSemAcento like :"+parameterName);
				parametros.put(parameterName,nomeSplit[i]);
			}			
		}

		return easyPaginacao.executaHQLQueryPaginada( parametros, SQLHelper.workaroundAndClauseInQuery(hql.toString()) ," order by p.nome asc" );
	}
	

	
/*	public void (){
		if (aluno!=null && !StringUtils.isBlank(aluno.getCodeducacenso())){
			hqlAluno.append(" a.codeducacenso = :codeducacenso ");
			parametros.put("codeducacenso",aluno.getCodeducacenso());
		}
			
		
		if ( funcionario!=null ){
			if ( !StringUtils.isBlank(funcionario.getCodigoFuncional()) ){
				hqlFunc.append(" f.codigoFuncional = :codigoFuncional ");
				parametros.put("codigoFuncional",funcionario.getCodigoFuncional());
			}
		}
		
	}*/
	
	
	/**
	 * Pesquisa a pessoa atraves atraves de todos campos obrigatorios.
	 * 
	 * @param nome - Obrigatorio
	 * @param sexo - Obrigatorio
	 * @param dtnascimento - Obrigatorio
	 * @param nomeMae - Obrigatorio
	 * @param dtNascimentoMae - Obrigatorio
	 * 
	 * @return Object
	 */
	public PessoaEntity recuperaPessoaByCamposObrigatoriosCadastro(  String nome,
																	 Sexo sexo, 
																	 Date dtnascimento,
																	 String nomeMae,
																	 Date dtNascimentoMae){
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT distinct(p) FROM PessoaEntity p WHERE p.dtNascimento = :dtNascimento  p.dtNascimentoMae = :dtNascimentoMae p.sexo = :sexo ");
		
		String[] nomePesquisavelSplit = SQLHelper.realizaLikeSplitEmNome(nome);
		String[] nomeMaePesquisavelSplit = SQLHelper.realizaLikeSplitEmNome(nomeMae);
		
		for (int i=0;i<nomePesquisavelSplit.length;i++){
			hql.append(" p.nomeSemAcento like :nome"+i);
			parametros.put("nome"+i, nomePesquisavelSplit[i] );
		}

		for (int i=0;i<nomeMaePesquisavelSplit.length;i++){
			hql.append(" p.nomeMaeSemAcento like :nomeMae"+i);
			parametros.put("nomeMae"+i, nomeMaePesquisavelSplit[i] );
		}
		
		parametros.put("dtNascimento", dtnascimento);
		parametros.put("dtNascimentoMae", dtNascimentoMae);
		parametros.put("sexo", sexo);
		
		
		
		Query q =  this.getEm().createQuery(SQLHelper.workaroundAndClauseInQuery(hql.toString()) );
		configuraParametrosQuery(q,parametros);
		
		try {
			return (PessoaEntity) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (NonUniqueResultException nure) {
			this.easyPaginacao.log.fatal("# Problema de Duplicidade NonUniqueResultException. NOME:" + nome.toUpperCase());
			return null;
		}
		
		

	}
	
	
	
	
	
	
	
	public PessoaEntity recuperaPessoaByCPF(String cpf){
 		Query q = this.easyPaginacao.getEm().createNamedQuery("PessoaEntity.recuperaPessoaByCPF");
 		q.setParameter("cpf", cpf);
 		try {
			PessoaEntity p = (PessoaEntity) q.getSingleResult();
			return p;
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			this.easyPaginacao.log.fatal("Erro Fatal Encontrado Mais de um registro para cpf"+ cpf);
			return null;
		}
	}

	
	
	public PessoaEntity recuperaPessoaByRG(String rg, String ssp){
 		Query q = this.easyPaginacao.getEm().createNamedQuery("PessoaEntity.recuperaPessoaByRG");
 		q.setParameter("rg", rg);
 		q.setParameter("ssp", ssp);
 		try {
			PessoaEntity p = (PessoaEntity) q.getSingleResult();
			return p;
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			this.easyPaginacao.log.fatal("Erro Fatal Encontrado Mais de um registro para rg"+ rg + "ssp " +ssp);
			return null;
		}
	}

	
	
	
	public PessoaEntity recuperaPessoaByCertidaoNascimento(String numCertidao, Date dtcertidao){
 		Query q = this.easyPaginacao.getEm().createNamedQuery("PessoaEntity.recuperaPessoaByCertidao");
 		q.setParameter("numcertidao", numCertidao);
 		q.setParameter("dtcertidao", dtcertidao);
 		try {
			PessoaEntity p = (PessoaEntity) q.getSingleResult();
			return p;
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			this.easyPaginacao.log.fatal("Erro Fatal Encontrado Mais de um registro para NumCertidao"+ numCertidao + " e dtcertidao " +dtcertidao);
			return null;
		}
	}

	
	
	
	
}
 