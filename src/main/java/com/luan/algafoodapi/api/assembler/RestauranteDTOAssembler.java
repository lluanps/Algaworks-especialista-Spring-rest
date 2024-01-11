package com.luan.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.CozinhaDTO;
import com.luan.algafoodapi.api.model.RestauranteDTO;
import com.luan.algafoodapi.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler {

	public RestauranteDTO toModel(Restaurante restaurante) {
		RestauranteDTO dto = new RestauranteDTO();
		
		CozinhaDTO cozinhaDto = new CozinhaDTO();
		cozinhaDto.setId(restaurante.getCozinha().getId());
		cozinhaDto.setNome(restaurante.getCozinha().getNome());
		
		dto.setId(restaurante.getId());
		dto.setNome(restaurante.getNome());
		dto.setTaxaFrete(restaurante.getTaxaFrete());
		dto.setCozinha(cozinhaDto);
		return dto;
	}
	
	public List<RestauranteDTO> toCollectionDto(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
	
}
