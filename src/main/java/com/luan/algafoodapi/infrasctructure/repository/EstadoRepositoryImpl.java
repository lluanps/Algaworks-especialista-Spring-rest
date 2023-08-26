package com.luan.algafoodapi.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Cidade;
import com.luan.algafoodapi.domain.model.Estado;
import com.luan.algafoodapi.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> listar() {
		return manager.createQuery("FROM Cidade", Estado.class)
				.getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	public void remover(Estado estado) {
		estado = buscar(estado.getId());
		manager.remove(estado);
		
	}

}
