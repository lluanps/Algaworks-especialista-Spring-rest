package com.luan.algafoodapi.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Pagamento;
import com.luan.algafoodapi.domain.repository.PagamentoRepository;

@Repository
public class PagamentoRepositoryImpl implements PagamentoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public List<Pagamento> listar() {
		return manager.createQuery("FROM Pagamento", Pagamento.class)
				.getResultList();
	}

	@Transactional
	@Override
	public Pagamento buscar(Long id) {
		return manager.find(Pagamento.class, id);
	}

	@Transactional
	@Override
	public Pagamento salvar(Pagamento pagamento) {
		return manager.merge(pagamento);
	}

	@Transactional
	@Override
	public void remover(Pagamento pagamento) {
		pagamento = buscar(pagamento.getId());
		manager.remove(pagamento);
		
	}

}
