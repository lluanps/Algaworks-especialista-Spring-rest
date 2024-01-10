package com.luan.algafoodapi.api.model.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luan.algafoodapi.domain.model.Restaurante;

public class CozinhaMixin {
	
	@JsonIgnore
	private List<Restaurante> restaurantes;

}
