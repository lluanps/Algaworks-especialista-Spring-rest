package com.luan.algafoodapi.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.repository.filter.PedidoFilter;

/*adicionado importação estatica indo em preferencias -> java -> editor ->content assist -> favorites -> e adionado o package dessa classe*/
public class PedidoSpecification {

	public static Specification<Pedido> usandoFiltro(PedidoFilter pedidoFilter) {
		return (root, query, builder) -> {
			root.fetch("restaurante");
			root.fetch("cliente");
			root.fetch("cozinha");
			
			var predicates = new ArrayList<Predicate>();
			
			if (pedidoFilter.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), pedidoFilter.getClienteId()));
			}
			
			if (pedidoFilter.getRestaruanteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), pedidoFilter.getRestaruanteId()));
			}
			
			if (pedidoFilter.getDataCriacaoFim() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoInicio()));
			}
			
			if (pedidoFilter.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoFim()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
