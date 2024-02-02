package com.luan.algafoodapi.domain.service.email.sandbox;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.luan.algafoodapi.core.email.EmailProperties;
import com.luan.algafoodapi.domain.service.email.SmtpEnvioEmailService;

public class SandboxEnvioEmailService extends SmtpEnvioEmailService {
	
	@Autowired
	private EmailProperties emailProperties;
	
    @Override
    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
    	MimeMessage message = super.criarMimeMessage(mensagem);
    	
    	MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
    	messageHelper.setTo(emailProperties.getSandbox().getDestinatario());
    	
    	return message;
    }
	
}
