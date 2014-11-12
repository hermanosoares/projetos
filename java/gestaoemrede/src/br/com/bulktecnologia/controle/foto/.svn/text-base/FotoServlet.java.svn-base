package br.com.bulktecnologia.controle.foto;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.jboss.seam.faces.FacesMessages;

import br.com.bulktecnologia.modelo.entidade.PessoaEntity;
/**
 * Servlet implementation class FotoServlet
 */
public class FotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int tamanho = request.getContentLength();
		String cid = request.getParameter("cid");

		if (tamanho==2713){
			FacesMessages msg = (FacesMessages)resolveObjectInJbossSeamConversation(request, cid, "org.jboss.seam.international.statusMessages",false);
			msg.add("Para capturar a foto, primeiro 'Aceite' o Adobe Flash para ativar a câmera. (CAMERA NÃO ESTÁ ATIVADA).");
		}
		
		if (tamanho>0 && tamanho!=2713){
			byte[] bytes = new byte[tamanho];
			
			InputStream is = request.getInputStream();
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			    offset += numRead;
			}

			PessoaEntity pessoa = (PessoaEntity) resolveObjectInJbossSeamConversation(request, cid, "pessoaEntity",true);
			pessoa.setFoto(bytes);
			
			 // Ensure all the bytes have been read in
			if (offset < bytes.length) {
			    System.out.println("falha processando foto!");
			}
		}
	}
	
	
	
	/**
	 * WorkAround para obter os objetos da conversacao do seam, fora do SMPC.
	 * @param request
	 * @param cid
	 * @param seamComponentName
	 * @author hsoares
	 * @return Object - (proxy)
	 */
	private Object resolveObjectInJbossSeamConversation(HttpServletRequest request, String cid, String seamComponentName, boolean invokeProxyInstance){
		
		HttpSession sessao =  request.getSession(false);
		
		if (sessao!=null && cid!=null){
			 Object o = sessao.getAttribute("org.jboss.seam.CONVERSATION#"+cid+"$"+seamComponentName);
			 
			 if (invokeProxyInstance){
				 return invokeProxyInstanceJbossSeam(o);
			 }
			 else{
				 return o;
			 }
		}

		return null;
	}
	
	
	
	
	
	
	
	private Object invokeProxyInstanceJbossSeam(Object o){
		 try {
				PropertyDescriptor p = PropertyUtils.getPropertyDescriptor(o, "instance");
				Method m = p.getReadMethod();
				m.setAccessible(true);
				Object[] args = new Object[0];
				return m.invoke(o,args);
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	
	
	

}
