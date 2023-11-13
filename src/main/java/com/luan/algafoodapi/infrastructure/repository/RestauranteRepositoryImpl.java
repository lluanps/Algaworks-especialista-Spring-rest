package com.luan.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		var jpql = "FROM Restaurante "
				+ "WHERE nome LIKE :nome AND taxaFrete "
				+ "BETWEEN :taxaInicial AND taxaFinal";
		
		return manager.createQuery(jpql, Restaurante.class)
					.setParameter("taxaFrete", "%" + nome + "%")
					.setParameter("taxaInicial", taxaFreteInicial)
					.setParameter("taxaFinal", taxaFreteFinal)
					.getResultList();
	}
	
}
