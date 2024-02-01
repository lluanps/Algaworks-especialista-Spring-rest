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
	
	// usando FAKE como padrão para evitar enviar email de vdd
	private Implementacao impl = Implementacao.FAKE;

	
	@NonNull
	private String remetente;
	
	public enum Implementacao {
		SMTP, FAKE
	}

}
