package com.luan.algafoodapi.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.luan.algafoodapi.domain.model.Restaurante;

/*adicionado importação estatica indo em preferencias -> java -> editor ->content assist -> favorites -> e adionado o package dessa classe*/
public class RestauranteSpecification {

	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
	}
	
}
