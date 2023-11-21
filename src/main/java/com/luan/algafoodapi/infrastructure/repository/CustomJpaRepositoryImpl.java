package com.luan.algafoodapi.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.luan.algafoodapi.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>  implements CustomJpaRepository<T, ID> {

	private EntityManager manager;
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		var jpql = "FROM " + getDomainClass().getName();// pega o nome da classe
		
		T entity = manager.createQuery(jpql, getDomainClass())
			.setMaxResults(1)//retorna no maximo um resultado
			.getSingleResult();
		
		return Optional.ofNullable(entity);
	}

}
