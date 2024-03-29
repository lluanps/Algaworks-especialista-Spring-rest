package com.luan.algafoodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luan.algafoodapi.api.model.EnderecoDTO;
import com.luan.algafoodapi.api.model.input.ItemPedidoInput;
import com.luan.algafoodapi.domain.model.Endereco;
import com.luan.algafoodapi.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
//		modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
//					.addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);

		var enderecoToEnderecoDTOTypeMap =  modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
		
		enderecoToEnderecoDTOTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoDTOdest, value) -> enderecoDTOdest.getCidade().setEstado(value));
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		return modelMapper;
//		return new ModelMapper();
	}

}
