package br.com.bulktecnologia.controle.listener;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.navigation.Pages;

@Name("ConversationListener")
public class ConversationListener{

	private Logger log = Logger.getLogger(this.getClass());
	private String noConversationViewId;
	
	@Observer(value="org.jboss.seam.endConversation")
	public void observeConversationEnd() {
		log.debug("CONVERSATION Ended");
		
	}

	@Observer(value="org.jboss.seam.beginConversation")
	public void observeConversationStart() {
		log.debug("CONVERSATION Started");		
	}

	
	@Observer("org.jboss.seam.noConversation")
	public void noConversation(){
		this.noConversationViewId = Pages.getCurrentViewId();
	}
	
	public String getNoConversationViewId(){
		return this.noConversationViewId;
	}
	
}
