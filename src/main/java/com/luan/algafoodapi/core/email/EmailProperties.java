package com.luan.algafoodapi.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {
	
	private Implementacao impl;

	private Sandbox sandbox = new Sandbox();
	
	@NonNull
	private String remetente;
	
	public enum Implementacao {
		SMTP, FAKE, SANDBOX
	}
	
	@Getter
	@Setter
	public class Sandbox {
		private String destinatario;
	}

}
