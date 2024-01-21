package com.luan.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.PedidoDTO;
import com.luan.algafoodapi.domain.model.Pedido;

@Component
public class PedidoDTOAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public PedidoDTO toModel(Pedido pedido) {
		return mapper.map(pedido, PedidoDTO.class);
	}
	
	public List<PedidoDTO> toCollectionDto(List<Pedido> pedidos) {
		 return pedidos.stream()
				.map(pedido -> toModel(pedido))
				.collect(Collectors.toList());
	}
	
}
