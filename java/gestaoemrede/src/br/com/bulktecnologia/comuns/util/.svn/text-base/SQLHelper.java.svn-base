package br.com.bulktecnologia.comuns.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
/**
 * Classe utilitaria usada para apoiar na criacao/manipulacao/tratamento de queries dinamicas.
 * 
 * @author hsoares
 *
 */
public final class SQLHelper {

	
	/**
	 * usado para adicionar uma String em uma especificado posição do StringBuffer,
	 * caso esta String já não ocorra no StringBuffer.
	 * 
	 * @param sb
	 * @param stringToBeInserted
	 * @param insertPosition
	 */
	public static  void addStringPosition(StringBuffer sb,String stringToBeInserted,int insertPosition){
		if (sb!=null && sb.indexOf(stringToBeInserted) == -1 ){
			sb.insert(insertPosition, stringToBeInserted);
		}
	}

	
	
	
	/**
	 * Metodo auxiliar usado PessoaService, que utiliza uma funcao nativa no postgresql para realizar 
	 * pesquisas em strings mais elaboradas, tratando acentuacoes e sentividade no case da String.
	 *  
	 * @param loop
	 * @return String com parte da clausula SQL 
	 */
	public static String montaSQLClausePesquisa(int loop){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("( lower(to_ascii(convert_to(p.nome, 'latin1'), 'latin1')) like lower(to_ascii(convert_to( @nomeFull, 'latin1'), 'latin1')) )").
		   append(" or ");
		
		sb.append("( lower(to_ascii(convert_to(p.nome, 'latin1'), 'latin1')) like lower(to_ascii(convert_to( @firstName, 'latin1'), 'latin1')) ").
		   append(" and ").	
		   append("lower(to_ascii(convert_to(p.nome, 'latin1'), 'latin1')) like lower(to_ascii(convert_to( @lastName, 'latin1'), 'latin1')) ) ");
		
		if (loop==2){
			return sb.toString();
		}
		else{
			sb.append(" or ");
		}
		

		sb.append(" ( lower(to_ascii(convert_to(p.nome, 'latin1'), 'latin1')) like lower(to_ascii(convert_to( @firstName, 'latin1'), 'latin1')) ").
		   append(" and ").	
		   append("lower(to_ascii(convert_to(p.nome, 'latin1'), 'latin1')) like lower(to_ascii(convert_to( @middleName, 'latin1'), 'latin1')) ) ");
		
		   return sb.toString();
	}
	
	
	/**
 	 *
     * adiciona clausulas AND em query com namedParameter.
	 * 
     *
 	 *
     * @author hsoares
	 *
     * @param hql
	 *
     * @return hql
	 *
     */

    public static String workaroundAndClauseInQuery(String hql){

         
          StringBuffer sb = new StringBuffer();

         

          boolean achou = false;

          char[] content = hql.toCharArray();

          for (int i=0;i<content.length;i++){

                char c = content[i];

                if (achou){

                     if (c == ' '){

                           if ( i != (content.length-1) ){

                                 sb.append(" AND ");

                                 achou = false;

                           }

                     }

                     else{

                           sb.append(c);

                     }

                }

                else{

                 if ( c == '#' ){
                  	achou = true;
                 }
                 else{
                	 
                     sb.append(c);	
                     if (c == ':'){
                          achou = true;
                     }
                     
                 }
                	
                // : quando encontra um inicio de namedQuerie
                // # quando deve inserir uma clausula AND dinamica
                	
                
                
          }

     }

          return sb.toString();  

}



	
	
	/**
	 * adiciona clausulas AND em query com namedParameter.
	 * 
	 * @param EntityManger
	 * @param hql (sem clausulas and)
	 * @param valorParametros (mapa com nome do parametro seguido do valor, para binding)
	 * 
	 * @return javax.persistence.Query
	 */
	public static Query FabricarQueryPesquisa(EntityManager em,String hql, Map<String,Object> valorParametros, boolean nativeQuery){
		
		StringBuffer sb = new StringBuffer();
		StringBuffer parameters = new StringBuffer();
		
		boolean achou = false;
		
		List<String> items = new ArrayList<String>();
		
		char[] content = hql.toCharArray();
		for (int i=0;i<content.length;i++){
			char c = content[i];
			if (achou){
				if (c == ' '){
					if ( i != (content.length-1) ){
						sb.append(" AND ");
						achou = false;
						items.add(parameters.toString());
						parameters = new StringBuffer();
					}
				}
				else{
					sb.append(c);
					parameters.append(c);
				}
			}
			else{
			sb.append(c);
			if (c == ':'){
				achou = true;
			}
		}
	 }
		
	items.add(parameters.toString());
	String hqlFinal = sb.toString().replaceAll("@", ":");
	
	Query q = null;
	
	if (nativeQuery){
		q = em.createNativeQuery(hqlFinal);
	}
	else{
		q = em.createQuery(hqlFinal);
	}
	
		 
	for (String parametro : valorParametros.keySet()){
			q.setParameter(parametro,valorParametros.get(parametro));
	}
	
	return q;
  }


	
	public static String[] realizaLikeSplitEmNome(String nome){
		
		String[] split = nome.trim().split(" ");
		
		for (int i=0;i<split.length;i++){
			split[i] = "%" + split[i].toLowerCase() + "%";
		}
		
		return split;
	}
	
	
	public static String fabricaQueryCount(String sql){
		return fabricaQueryCountBasica(sql);
	}
	
	
	private static String fabricaQueryCountComSubQueries(String sql) {
		
		//sql = sql.toLowerCase();
		
		int inicioDistinct = StringUtils.indexOf(sql,"distinct");
		int fimDistinct = StringUtils.indexOf(sql, ")", inicioDistinct);
		
		String distinct = StringUtils.substring(sql, inicioDistinct,fimDistinct+1);
		distinct = distinct.replace("(", " ");
		distinct = distinct.replace(")", " ");
		//distinct =  StringUtils.removeStart(distinct,"select");

		String query = "SELECT count("+distinct+") ";
		
		int ultimoFrom = StringUtils.lastIndexOf(sql, "from");

		// remove ORDER BY
		int orderby =  StringUtils.indexOf(sql,"order by");
		if (orderby!=-1){
			query = query + StringUtils.substring(sql, ultimoFrom,orderby);
		}
		else{
			query = query + StringUtils.substring(sql, ultimoFrom);	
		}
		
		
		return query;
	}




	private static String fabricaQueryCountBasica(String sql){
		
		String queryPart = StringUtils.substringAfterLast(sql, "FROM");
		if (StringUtils.isBlank(queryPart)){
			queryPart = StringUtils.substringAfterLast(sql, "from");
			if (StringUtils.isBlank(queryPart)){
				throw new RuntimeException("query invalido, não foi possivel realizar select count(), verifique sua query! ");
			}
		}
		String saida = " select count(*) from "+queryPart; 
		return  saida;
	}
	
	
	
	public static void main(String[] args) {
		
		String testQueryComplexa = "select distinct(p.id), p.nome, " +
								  "	(select  a2.id from Aluno a2 where a2.pessoa_id = p.id) idAluno,"+
								  "	(select  f2.id from Funcionario f2 where f2.pessoa_id = p.id) idFunc"+  
								 "	from alunoinstituicao ai, aluno a, funcionarioinstituicao fi, funcionario f, pessoa p where "+
								 "	(ai.instituicao_id = 2 AND "+
									" ai.aluno_id = a.id AND "+
									" a.pessoa_id = p.id) OR "+
									" (fi.instituicao_id =2 AND "+
									" fi.funcionario_id = f.id AND "+
									" f.pessoa_id = p.id ) "+
									" order by p.id "+
									" limit 10 ";
		
		String test2 = "SELECT count( select new pessoaentity(distinct(p.id)) from alunoinstituicaoentity ai, alunoentity a, funcionarioinstituicaoentity fi, funcionarioentity f, pessoaentity p where (ai.instituicao.id = :idinstituicao and and  ai.aluno.id = a.id and  a.pessoa.id = p.id) or  (fi.instituicao.id = :idinstituicao and and  fi.funcionario.id = f.id and  f.pessoa.id = p.id )  and  p.sexo = :sexo";
		//System.out.println( fabricaQueryCount(testQueryComplexa) );
		System.out.println( fabricaQueryCount(test2) );
		
	}

	
}
