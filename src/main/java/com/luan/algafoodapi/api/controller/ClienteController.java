package com.luan.algafoodapi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

//controller feito para testar configuração de multiplos grupos de documentação no projeto
@RestController
@Tag(name = "Cliente")
public class ClienteController {
	
	@GetMapping("clientes/pedidos")
	public void getCliente() {
	}

}