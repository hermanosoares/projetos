package br.com.bulktecnologia.controle.seguranca;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.captcha.Captcha;
import org.jboss.seam.captcha.CaptchaResponse;

@Name("org.jboss.seam.captcha.captcha")
public class MyCaptcha extends Captcha {

	@CaptchaResponse(message="resposta da soma incorreta.")
	public String getResponse() {
		return super.getResponse();
	}
	
}
