package com.luan.algafoodapi.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.luan.algafoodapi.api.model.mixin.RestauranteMixin;
import com.luan.algafoodapi.domain.model.Restaurante;

@Component
public class JacksonMixinModule extends SimpleModule {
	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
	}
	
}
