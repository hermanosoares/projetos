package br.com.bulktecnologia.comuns.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
@Name("FacesMessageSessionScoped")
@Scope(ScopeType.SESSION)
@AutoCreate
public class FacesMessageSessionScoped implements Serializable {
	
	private List<String> messages = new ArrayList<String>();
	
	public void add(String msg){
		this.messages.add(msg);
	}
	
	
	@Observer(value="org.jboss.seam.beginConversation")  
	public void observeConversationStart() {
		if(messages.size()>0){
			for (String m: this.messages){
				FacesMessages.instance().add(m);
			}
			this.messages.clear();	
		}

	}
	
	
	
}
