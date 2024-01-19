package com.luan.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.exception.GrupoNaoEncontradoException;
import com.luan.algafoodapi.domain.model.Grupo;
import com.luan.algafoodapi.domain.model.Permissao;
import com.luan.algafoodapi.domain.repository.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository repository;

	@Autowired
	private PermissaoService permissaoService;
	
	public Grupo buscaOuFalha(Long grupoId) {
		return repository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return repository.save(grupo);
	}

	@Transactional
	public void excluir(Long grupoId) {
		try {
			repository.deleteById(grupoId); 
			repository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Grupo de código %d não pode ser removido, pois está em uso", grupoId));
		}
	}

	@Transactional
	public void associar(Long grupoId, Long permissaoId) {
		Grupo grupo = buscaOuFalha(grupoId);
		Permissao permissao = permissaoService.buscaOuFalha(permissaoId);
		
		grupo.associar(permissao);
	}
	
	@Transactional
	public void desassociar(Long grupoId, Long permissaoId) {
		Grupo grupo = buscaOuFalha(grupoId);
		Permissao permissao = permissaoService.buscaOuFalha(permissaoId);
		
		grupo.desassociarGrupo(permissao);
	}
	
}
