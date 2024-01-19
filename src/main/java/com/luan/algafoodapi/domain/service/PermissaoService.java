package com.luan.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.PermissaoNaoEncontradaException;
import com.luan.algafoodapi.domain.model.Permissao;
import com.luan.algafoodapi.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository repository;
	
    public Permissao buscaOuFalha(Long permissaoId) {
        return repository.findById(permissaoId)
            .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }

}
