package br.com.bulktecnologia.controle.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.comuns.ApplicationConstants;
import br.com.bulktecnologia.comuns.util.SQLHelper;
import br.com.bulktecnologia.modelo.dao.BaseDAO;

@Name("easyPaginacao")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class EasyPaginacao extends BaseDAO implements Serializable{
	
	
	private Long currentPosition = 0L;
	
	private Long countRegistros = 0L;
	
	private Long pageSelecionadaAnterior;
	private Long pageSelecionada = 1L;
	
	public void processaPaginaSelecionada(){
		
		if (pageSelecionada!=null){
			
			int diferenca = pageSelecionada.intValue() - pageSelecionadaAnterior.intValue();
			
			if (diferenca<0){
				diferenca = pageSelecionadaAnterior.intValue() - pageSelecionada.intValue();
			}

				for (int i=1;i<=diferenca;i++){
					if (pageSelecionada>pageSelecionadaAnterior){
						if (i==(pageSelecionada)){
							break;
						}
						this.proximo();	
					}
					else{
						this.anterior();
					}
				}
		}

	}
	
	
	@Create
	public void create(){
	}
	
	/**
	 * devolve a quantidade de registros existentes na tabela.
	 * 
	 * @return Long
	 */
	public Long getCount() {
		return countRegistros;
	}
	
	
	public void primeiro(){
		this.currentPosition = 0L;
		this.pageSelecionada = 1L;
	}
	
	public void primeiroComboNavegacao(){
		this.pageSelecionada = 1L;
	}
	
	
	public void ultimo(){
		 this.currentPosition = (getCount() - new Long( ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA));
	}
	
	public void ultimoComboNavegacao(){
		 this.pageSelecionada = new Integer(getNumPaginas().size()).longValue();
	}
	
	
	public void proximo(){
		currentPosition = currentPosition +  ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA;
	}
	
	public void proximoComboNavegacao(){
		this.pageSelecionada++;
	}
	
	
	public void anterior(){
		
		currentPosition = currentPosition -  ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA;
		if (currentPosition < 0){
			currentPosition = 0L;
		}
	}

	public void anteriorComboNavegacao(){
		this.pageSelecionada--;
	}
	
	
	public Boolean getExisteUltimo(){
		
		if ( (this.getCount()-this.getCurrentPosition())> ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA){
			return Boolean.TRUE;
		}
		else{
			return false;
		}
		
	}	

	
	public Boolean getExisteProximo(){
		
		Long qtdeRegistros = getCount();
		Long restante = qtdeRegistros - currentPosition;
		
		if (restante<= ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA){
			return Boolean.FALSE;
		}
		
		if (qtdeRegistros > currentPosition){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	public Boolean getExisteAnterior(){
		if (currentPosition==0){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}



	public Integer getCurrentPosition() {
		return currentPosition.intValue();
	}


	public Integer getMaxResults() {
		return  ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA;
	}


	public List executaHQLQueryPaginada(Map<String, Object> parametros,String sql,String orderByClause){
		
		String sqlCount = null;
		
		if ( parametros.containsKey(ApplicationConstants.Parametros.NAMEDQUERY_SELECT_COUNT) ){
			 String namedQueryName= (String)parametros.get(ApplicationConstants.Parametros.NAMEDQUERY_SELECT_COUNT);
			 sqlCount = resolveHQLFromNamedQuery(namedQueryName);
		}
		else{
			if ( parametros.containsKey(ApplicationConstants.Parametros.QUERY_SELECT_COUNT) ){
				sqlCount = (String)parametros.get(ApplicationConstants.Parametros.QUERY_SELECT_COUNT);
			}
			else{
				sqlCount = SQLHelper.fabricaQueryCount(sql);
			}
				
		}
		 
		Query q1 =  this.getEm().createQuery(sqlCount);
		configuraParametrosQuery(q1,parametros);
		countRegistros = (Long)q1.getSingleResult();
		
		if (countRegistros == 0){
			return new ArrayList();
		}
		
		if ( !StringUtils.isBlank(orderByClause) ){
			int ultimaPosicao = (sql.length());
			StringBuffer sb = new StringBuffer(sql);
			sb.insert(ultimaPosicao,orderByClause);
			sql = sb.toString();
		}
		
		Query q2 =  this.getEm().createQuery(sql);
		configuraParametrosQuery(q2,parametros);
		q2.setFirstResult(getCurrentPosition());
		q2.setMaxResults(ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA);
		List resultado = q2.getResultList();
		
		return resultado;
	}
	
	
	
	public List executaNamedQueryPaginada(Map<String, Object> parametros,String namedQuery,String orderByClause){
		String hql = resolveHQLFromNamedQuery(namedQuery);
		return this.executaHQLQueryPaginada(parametros, hql, orderByClause);
	}
	
	
	private String resolveHQLFromNamedQuery(String namedQuery){
		Session session = (Session)this.getEm().getDelegate();
		
		org.hibernate.Query q = session.getNamedQuery(namedQuery);
		
		String hql = q.getQueryString();
		return hql;
	}
	
	
	public Boolean getRedenrizaNavegacaoUsabilidade(){
		if (this.getCount()>ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * numero de paginas paginado com no ApplicationConstants.Parametros.MAX_PAGINAS_COMBO
	 * @return
	 */
	public List<Long> getNumPaginasPaginado(){
		
		ArrayList<Long> pageForward = new ArrayList<Long>();
		ArrayList<Long> pagePrevious = new ArrayList<Long>();
		
		Double pageCount = Double.parseDouble(this.getCount().toString()) / ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA;
		Double totalPageCount = Math.ceil(pageCount);
		int totalPaginasSelectCount = totalPageCount.intValue();
		
		int pageAtual = this.pageSelecionada.intValue();
		int pageCountTempProximo = pageAtual;

		
		if (this.pageSelecionada>1){
			if (this.pageSelecionada<=ApplicationConstants.Parametros.MAX_PAGINAS_COMBO){
				Long temp = pageSelecionada;
				while (temp!=1){
					pagePrevious.add(--temp);	
				}
			}
			else{
				Long inicioPrevious = this.pageSelecionada - ApplicationConstants.Parametros.MAX_PAGINAS_COMBO;
					for (int i=1;i<=ApplicationConstants.Parametros.MAX_PAGINAS_COMBO;i++){
						pagePrevious.add(inicioPrevious);
						inicioPrevious++;
					}
			}
		}
		
		for (int i=1;i<=ApplicationConstants.Parametros.MAX_PAGINAS_COMBO;i++){
			if (pageCountTempProximo>=1){
				if (pageCountTempProximo<=totalPaginasSelectCount){
					pageForward.add(Long.parseLong(""+pageCountTempProximo));
					pageCountTempProximo++;
				}
			}
		}
		
		pagePrevious.addAll(pageForward);
		Collections.sort(pagePrevious);
		return pagePrevious;
	}

	/**
	 * numero de paginas real com base no select count
	 * @return
	 */
	public List<Long> getNumPaginas(){
		
		ArrayList<Long> saida = new ArrayList<Long>();
		
		Double pageCount = Double.parseDouble(this.getCount().toString()) / ApplicationConstants.Parametros.MAX_RESULTADO_QUERY_JPA;
		Double totalPageCount = Math.ceil(pageCount);
		int realPageCount = totalPageCount.intValue();
		
		for (int i=1;i<=realPageCount;i++){
			saida.add(Long.parseLong(""+i));
		}
		
		return saida;
	}
	
	
	public Long getPageSelecionada() {
		return pageSelecionada;
	}

	public void setPageSelecionada(Long pageSelecionada) {
		this.pageSelecionadaAnterior = this.pageSelecionada;
		this.pageSelecionada = pageSelecionada;
	}
	
	
}
