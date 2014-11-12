package br.com.bulktecnologia.comuns.util;

import java.lang.annotation.Annotation;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.bulktecnologia.modelo.entidade.PessoaEntity;

public class PostgresqlGrantUtils {

	public static void main(String[] args) {
		 try {
			Class[] classes = ClassIntrospectorUtil.getClasses(PessoaEntity.class.getPackage().getName());
			StringBuffer sb = new StringBuffer();
			sb.append("grant all on table ");
			int i=0;
			for (Class c:  classes){
				
				Annotation entity = c.getAnnotation(Entity.class);
				
				
				if (entity!=null){
					String tableName = null;
					Annotation table = c.getAnnotation(Table.class);
					if (table!=null){
						Table t = (Table) table;
						tableName = t.name();
					}
					else{
						tableName = c.getSimpleName();
					}

					if (tableName!=null){
						
						sb.append( tableName );	
						
						if ( (i+1) < classes.length ){
							sb.append(",");
						}
						
					}

				}
				
				i++;
				
			}
			sb.append(" to aplicacao ;");
			
			System.out.println(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
