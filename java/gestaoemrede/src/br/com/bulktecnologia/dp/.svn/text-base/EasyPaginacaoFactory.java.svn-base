package br.com.bulktecnologia.dp;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;

import br.com.bulktecnologia.controle.jsf.EasyPaginacao;
public class EasyPaginacaoFactory {

	/**
	 * fabrica um easyPaginacao no scope de conversation, com o nome informado.
	 * 
	 * @param easyPaginacaoAliasName
	 */
	public static void factory(String easyPaginacaoAliasName){
		Component c = new Component(EasyPaginacao.class,easyPaginacaoAliasName,ScopeType.CONVERSATION,false,null,null);
		c.outject(new EasyPaginacao(), true);
	}
	
	
	
}
