package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.luan.algafoodapi.api.assembler.PedidoDTOAssembler;
import com.luan.algafoodapi.api.assembler.PedidoInputDisassembler;
import com.luan.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import com.luan.algafoodapi.api.model.PedidoDTO;
import com.luan.algafoodapi.api.model.PedidoResumoDTO;
import com.luan.algafoodapi.api.model.input.PedidoInput;
import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.model.Usuario;
import com.luan.algafoodapi.domain.repository.PedidoRepository;
import com.luan.algafoodapi.domain.repository.filter.PedidoFilter;
import com.luan.algafoodapi.domain.service.PedidoService;
import com.luan.algafoodapi.infrastructure.repository.spec.PedidoSpecification;

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
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	/*
	@GetMapping
	public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
		List<Pedido> todosPedidos = pedidoRepository.findAll();
		List<PedidoResumoDTO> pedidoResumoDTOs = pedidoResumoDTOAssembler.toCollectionDto(todosPedidos);
		
		MappingJacksonValue pedidoWrapper = new MappingJacksonValue(pedidoResumoDTOs);
		
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "valorTotal"));//retorna apenas o id e valorTotal
		
		if (StringUtils.isNotBlank(campos)) {
			filterProvider.addFilter("pedidoFilter", 
					SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
		}
		
		pedidoWrapper.setFilters(filterProvider);
		
		return pedidoWrapper;
	}
	*/
	
	@GetMapping
	public List<PedidoResumoDTO> pesquiar(PedidoFilter pedidoFilter) {
		List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecification.usandoFiltro(pedidoFilter));
		
		return pedidoResumoDTOAssembler.toCollectionDto(todosPedidos);
	}
	
	@GetMapping("/{pedidoId}")
	@ResponseStatus(HttpStatus.OK)
	public PedidoDTO buscarPedidoPorId(@PathVariable Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		
		return pedidoDTOAssembler.toModel(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
//	    try {
	        Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

	        // TODO pegar usuário autenticado
//	        novoPedido.setCliente(new Usuario());
//	        novoPedido.getCliente().setId(15L);

	        novoPedido = pedidoService.salvar(novoPedido);

	        return pedidoDTOAssembler.toModel(novoPedido);
//	    } catch (EntidadeNaoEncontradaException e) {
//	        throw new NegocioException(e.getMessage(), e);
//	    }
	}
	
}
