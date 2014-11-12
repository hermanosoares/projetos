package br.com.bulktecnologia.comuns.util;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.modelo.enums.TipoOperacao;
@Name("enumHelper")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class EnumHelper {
	
	private Logger log = Logger.getLogger(EnumHelper.class);
	
	@In
	private FacesMessages facesMessages;	
	
	
	
	
	public void trataAddRemoveEnum(TipoOperacao operacao,Collection colecao,Boolean aceitaDuplicado,Class tipoEnum,Object enumInstance){
		
		String valor = null;
		
		if (enumInstance!=null){
			valor = enumInstance.toString();
		}
		
		if (colecao==null){
			colecao = new ArrayList();
		}	
		
		if (valor!=null){

			if ( valor.equalsIgnoreCase("todos") ){
				TipoOperacao.values();
				Enum[] values;
				try {
					values = (Enum[])tipoEnum.getEnumConstants();

					for (Enum e: values){
						if ( !e.toString().equalsIgnoreCase("todos") ){

							if (TipoOperacao.Adicionar.equals(operacao) ){
								if (aceitaDuplicado){
									colecao.add(e);	 
								}
								else{
									if ( colecao.contains(e) ){
										facesMessages.add(e+" já adicionado!");

									}
									else{
										colecao.add(e);	 
									}
								}
							}
							else{
								if (TipoOperacao.Remover.equals(operacao) ){
									colecao.remove(e);
								}
							}

						}
					}
					enumInstance = null;
				}
				catch (Exception e) {
					log.fatal(e);
				}
			}
			else{
				Enum item = Enum.valueOf(tipoEnum, valor);
				
				if ( TipoOperacao.Adicionar.equals(operacao) ){
					if (aceitaDuplicado){
						colecao.add(item);	
					}
					else{
						if ( colecao.contains(item) ){
							facesMessages.add(valor+" já adicionado!");
						}
						else{
							colecao.add(item);
						}
					}
				}
				else{
					if ( TipoOperacao.Remover.equals(operacao) ){
						colecao.remove(item);
					}
				}
				
				enumInstance = null;
			}
		}
		else{
			facesMessages.add("primeiro selecione algum registro !.");
		}
				
	}
	
	
	
	
	
	
	
	
	
}	

