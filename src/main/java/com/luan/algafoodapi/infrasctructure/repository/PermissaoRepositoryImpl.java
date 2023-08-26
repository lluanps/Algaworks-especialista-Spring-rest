package com.luan.algafoodapi.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.model.Permissao;
import com.luan.algafoodapi.domain.repository.PermissaoRepository;

@Service
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public List<Permissao> listar() {
		return manager.createQuery("FROM Permissao", Permissao.class)
				.getResultList();
	}

	@Transactional
	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Transactional
	@Override
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Transactional
	@Override
	public void remover(Permissao permissao) {
		permissao = buscar(permissao.getId());
		manager.remove(permissao);
		
	}

}
