package com.luan.algafoodapi.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository{


	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public List<Cozinha> listar() {
		return manager.createQuery("FROM Cozinha", Cozinha.class)
				.getResultList();
	}

	@Transactional
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}

}
