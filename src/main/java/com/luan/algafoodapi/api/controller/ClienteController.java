package com.luan.algafoodapi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//controller feito para testar configuração de multiplos grupos de documentação no projeto
@RestController
public class ClienteController {
	
	@GetMapping("clientes/pedidos")
	public void getCliente() {
	}

}
