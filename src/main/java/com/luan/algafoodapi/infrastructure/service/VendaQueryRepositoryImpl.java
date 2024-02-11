package com.luan.algafoodapi.infrastructure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.model.StatusPedido;
import com.luan.algafoodapi.domain.model.VendaDiaria;
import com.luan.algafoodapi.domain.model.filter.VendaDiariaFilter;
import com.luan.algafoodapi.domain.service.VendaQueryService;

@Repository
public class VendaQueryRepositoryImpl implements VendaQueryService {

	private static final String DATA_CRIACAO = "dataCriacao";
	private static final String STATUS = "status";

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);

		var functionDateDataCriacao= builder.function("TO_CHAR", String.class,
				root.get("dataCriacao"), 
				builder.literal("yyyy-MM-dd"));

		var selection = builder.construct(VendaDiaria.class, functionDateDataCriacao, builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));

		var predicates = new ArrayList<Predicate>();
		predicates = precondicoesParaFiltro(filtro, builder, root, predicates);

		query.select(selection).where(predicates.toArray(new Predicate[0])).groupBy(root.get(DATA_CRIACAO));

		return manager.createQuery(query).getResultList();
	}

	private ArrayList<Predicate> precondicoesParaFiltro(VendaDiariaFilter filtro, CriteriaBuilder builder, Root<Pedido> root,
			ArrayList<Predicate> predicates) {
		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}

		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(DATA_CRIACAO), filtro.getDataCriacaoInicio()));
		}

		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(DATA_CRIACAO), filtro.getDataCriacaoFim()));
		}

		predicates.add(root.get(STATUS).in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

		return predicates;
	}

}
