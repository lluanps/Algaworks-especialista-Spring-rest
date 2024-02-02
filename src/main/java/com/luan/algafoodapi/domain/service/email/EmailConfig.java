package com.luan.algafoodapi.domain.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luan.algafoodapi.core.email.EmailProperties;
import com.luan.algafoodapi.core.email.EmailProperties.Implementacao;
import com.luan.algafoodapi.domain.service.EnvioEmailService;
import com.luan.algafoodapi.domain.service.email.fake.FakeEnvioEmailService;
import com.luan.algafoodapi.domain.service.email.sandbox.SandboxEnvioEmailService;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
    	if (emailProperties.getImpl().equals(Implementacao.FAKE)) {
    		return new FakeEnvioEmailService();
    	} else if (emailProperties.getImpl().equals(Implementacao.SMTP)) {
			return new SmtpEnvioEmailService();
		} else {
			return new SandboxEnvioEmailService();
		}
    }
    
} 