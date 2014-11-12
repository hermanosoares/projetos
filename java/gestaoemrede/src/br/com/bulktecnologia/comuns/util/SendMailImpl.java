package br.com.bulktecnologia.comuns.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.async.Asynchronous;

import br.com.bulktecnologia.comuns.ApplicationConstants;

@Name("sendMail")
@AutoCreate
public class SendMailImpl implements SendMail{
	
	private String mailSMTPServer;
	private String mailSMTPServerPort;
	
	
	
	
	public SendMailImpl() { //Para o GMAIL 
		mailSMTPServer = "smtp.gmail.com";
		mailSMTPServerPort = "465";
	}
	
	
	
	
	 
	SendMailImpl(String mailSMTPServer, String mailSMTPServerPort) { //Para outro Servidor
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
	}
	
	@Asynchronous
	public void enviaMsgRedefinicaoSenha(String nome,String emailPrincipal, String emailSecundario,String login, String novoPassword, String ip){
		
		String from = "Nova Senha - Gestão em Rede<noreply@gestaoemrede.com.br>";
		
		String subject = "Redefinição de Senha - Gestão Em Rede";
		
		String body = " Prezado "+nome+" ,\n foi solicitado uma redefinição de senha para seu acesso ao Gestão em Rede." +
					  "\n Solicitado na Data : "+new Date() + " do terminal (IP/host) : "+ ip +
					  "\n sua nova senha de acesso é: "+novoPassword+
					  "\n(observe que as letras estão em minúsculo)." +
					  "\n " +
					  "\n http://www.gestaoemrede.com.br "+
					  "\n Equipe Gestão Em Rede.";
		
		this.sendMail(from, emailPrincipal, emailSecundario,subject, body);
		
	}
	
	
	public void sendMail(String from, String to,String copia,String subject, String message) {
		
		Properties props = new Properties();

                // quem estiver utilizando um SERVIDOR PROXY descomente essa parte e atribua as propriedades do SERVIDOR PROXY utilizado
                
/*                props.setProperty("proxySet","true");
                props.setProperty("socksProxyHost","192.168.155.1"); // IP do Servidor Proxy
                props.setProperty("socksProxyPort","1080");  // Porta do servidor Proxy
*/                

		props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
		props.put("mail.smtp.auth", "true"); //ativa autenticacao
		props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
		props.put("mail.debug", "false");
		props.put("mail.smtp.port", mailSMTPServerPort); //porta
		props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		//Cria um autenticador que sera usado a seguir
		SimpleAuth auth = null;
		auth = new SimpleAuth ("noreply@gestaoemrede.com.br",ApplicationConstants.Parametros.pwdNoReply);

		//Session - objeto que ira realizar a conexão com o servidor

		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(false); //Habilita o LOG das ações executadas durante o envio do email

		//Objeto que contém a mensagem
		Message msg = new MimeMessage(session);

		try {
			//Setando o destinatário
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			if (!StringUtils.isBlank(copia)){
				msg.setRecipient(Message.RecipientType.BCC, new InternetAddress(copia));
			}
			
			//Setando a origem do email
			msg.setFrom(new InternetAddress(from));
			//Setando o assunto
			msg.setSubject(subject);
			//Setando o conteúdo/corpo do email
			msg.setContent(message,"text/plain");

		} catch (Exception e) {
			System.out.println(">> Erro: Completar Mensagem");
			e.printStackTrace();
		}
		
		//Objeto encarregado de enviar os dados para o email
		Transport tr;
		try {
			tr = session.getTransport("smtp"); //define smtp para transporte
			
			 
			tr.connect(mailSMTPServer, "noreply@gestaoemrede.com.br", ApplicationConstants.Parametros.pwdNoReply);
			msg.saveChanges(); // don't forget this
			//envio da mensagem
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(">> Erro: Envio Mensagem");
			e.printStackTrace();
		}

	}
}

//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
class SimpleAuth extends Authenticator {
	public String username = null;
	public String password = null;


	public SimpleAuth(String user, String pwd) {
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication (username,password);
	}
	
} 
