package com.luan.algafoodapi.domain.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.core.email.EmailProperties;
import com.luan.algafoodapi.domain.service.EnvioEmailService;
import com.luan.algafoodapi.domain.service.email.exception.EmailException;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailProperties emailProperties;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
			mimeMessageHelper.setFrom(emailProperties.getRemetente());
			mimeMessageHelper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			mimeMessageHelper.setSubject(mensagem.getAssunto());
			mimeMessageHelper.setText(mensagem.getCorpo(), true);
			
			javaMailSender.send(mimeMessage);
			
		} catch (Exception e) {
			throw new EmailException("Não foi possivel enviar e-mail", e);
		}
	}

}
