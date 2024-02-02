package com.luan.algafoodapi.domain.service.email.fake;

import com.luan.algafoodapi.domain.service.email.SmtpEnvioEmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {
	
    @Override
    public void enviar(Mensagem mensagem) {
        /* Foi necessário alterar o modificador de acesso do método processarTemplate da classe pai(SmtpEnvioEmailService) para "protected", para poder chamar aqui*/
        String corpo = processarTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }

}
