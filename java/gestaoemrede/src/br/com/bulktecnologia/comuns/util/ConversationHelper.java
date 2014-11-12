package br.com.bulktecnologia.comuns.util;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.FlushModeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.core.ConversationEntries;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.navigation.Pages;

@Name("ConversationHelper")
@Scope(ScopeType.STATELESS)
public class ConversationHelper {

	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * Inicia uma conversação, ou participa de uma caso exista.
	 * e define o Flush para MANUAL.
	 * 
	 */
	public void BeginJoinConversationManual(){
		Integer size = ConversationEntries.getInstance().getConversationEntries().size();
		if (size > 1){
			 Conversation.instance().killAllOthers();
		}
		
		Conversation c = Conversation.instance();
		
		if ( c.begin(true,false) ){
			c.changeFlushMode(FlushModeType.MANUAL);
			log.debug("Workaround executada, Conversation criada: "+c.getId());
			c.killAllOthers();
			Redirect r =  Redirect.instance();
			r.setViewId(Pages.getCurrentViewId());
			r.setConversationPropagationEnabled(true);
			r.execute();
		}
		else{
			log.debug(" somente JOIN na Conversation :" + c.getId());
		}
	}
	
	
	
}
