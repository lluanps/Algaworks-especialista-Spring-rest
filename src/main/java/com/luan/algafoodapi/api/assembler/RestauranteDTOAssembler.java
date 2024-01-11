package com.luan.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.RestauranteDTO;
import com.luan.algafoodapi.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler {
	
	@Autowired
	private ModelMapper mapper;//O objetivo do ModelMapper é tornar o mapeamento de objetos fácil, determinando automaticamente como um modelo de objeto mapeia para outro, com base em convenções, da mesma forma que um humano faria

	public RestauranteDTO toModel(Restaurante restaurante) {
		return mapper.map(restaurante, RestauranteDTO.class);
	}
	
	public List<RestauranteDTO> toCollectionDto(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
	
}
