package com.luan.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.assembler.PedidoDTOAssembler;
import com.luan.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import com.luan.algafoodapi.api.model.PedidoDTO;
import com.luan.algafoodapi.api.model.PedidoResumoDTO;
import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.repository.PedidoRepository;
import com.luan.algafoodapi.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoDTOAssembler pedidoDTOAssembler;
	
	@Autowired
	private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
	
	@GetMapping
	public List<PedidoResumoDTO> listar() {
		List<Pedido> todosPedidos = pedidoRepository.findAll();
		
		return pedidoResumoDTOAssembler.toCollectionDto(todosPedidos);
	}
	
	@GetMapping("/{pedidoId}")
	@ResponseStatus(HttpStatus.OK)
	public PedidoDTO buscarPedidoPorId(@PathVariable Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		
		return pedidoDTOAssembler.toModel(pedido);
	}
	
}
