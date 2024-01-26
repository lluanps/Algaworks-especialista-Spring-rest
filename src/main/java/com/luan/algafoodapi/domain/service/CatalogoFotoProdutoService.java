package com.luan.algafoodapi.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.model.FotoProduto;
import com.luan.algafoodapi.domain.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto) {
		Long restauranteId = fotoProduto.getRestauranteId();
		Long produtoId = fotoProduto.getProduto().getId();
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		
		if (fotoExistente.isPresent()) {
			produtoRepository.delete(fotoExistente.get());
		}
		
		return produtoRepository.save(fotoProduto);
	}
	
}
