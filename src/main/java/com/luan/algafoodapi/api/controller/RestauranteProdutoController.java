package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.assembler.ProdutoDTOAssembler;
import com.luan.algafoodapi.api.assembler.ProdutoInputDisassembler;
import com.luan.algafoodapi.api.model.ProdutoDTO;
import com.luan.algafoodapi.api.model.input.ProdutoInput;
import com.luan.algafoodapi.domain.model.Produto;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.ProdutoRepository;
import com.luan.algafoodapi.domain.service.ProdutoService;
import com.luan.algafoodapi.domain.service.RestauranteService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoDTOAssembler produtoDTOAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@GetMapping
	public List<ProdutoDTO> listar(@PathVariable Long restauranteId,
			@RequestParam(required = false) boolean incluirInativos) {
		Restaurante restaurante = restauranteService.buscaOuFalha(restauranteId);
		List<Produto> todosProdutos = null;
		
		if (incluirInativos) {
			todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
		} else {
			todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
		
		return produtoDTOAssembler.toCollectionDTO(todosProdutos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO buscarProdutoPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = service.buscaOuFalha(restauranteId, produtoId);
		
		return produtoDTOAssembler.toModel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = restauranteService.buscaOuFalha(restauranteId);
		
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		
		produto = service.salvar(produto);

		return produtoDTOAssembler.toModel(produto);
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produtoAtual = service.buscaOuFalha(restauranteId, produtoId);
		
		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
		produtoAtual = service.salvar(produtoAtual);
		
		return produtoDTOAssembler.toModel(produtoAtual);
	}
	
}
