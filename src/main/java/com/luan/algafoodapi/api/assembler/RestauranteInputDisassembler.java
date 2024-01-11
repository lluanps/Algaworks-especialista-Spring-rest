package com.luan.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.input.RestauranteInputDTO;
import com.luan.algafoodapi.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {
	
	@Autowired
	private ModelMapper mapper;

	public Restaurante toDomainObjetct(RestauranteInputDTO restauranteInput) {
		return mapper.map(restauranteInput, Restaurante.class);
	}
}
