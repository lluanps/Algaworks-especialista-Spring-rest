package com.luan.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luan.algafoodapi.api.assembler.FotoProdutoDTOAssembler;
import com.luan.algafoodapi.api.model.FotoProdutoDTO;
import com.luan.algafoodapi.api.model.input.FotoProdutoInput;
import com.luan.algafoodapi.domain.model.FotoProduto;
import com.luan.algafoodapi.domain.model.Produto;
import com.luan.algafoodapi.domain.service.CatalogoFotoProdutoService;
import com.luan.algafoodapi.domain.service.ProdutoService;

@RestController
@RequestMapping("/restaurante/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	
	@Autowired(required = true)
	private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

	@Autowired
	private ProdutoService produtoService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) {
		Produto produto = produtoService.buscaOuFalha(restauranteId, produtoId);
		
		MultipartFile arquivo = fotoProdutoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto);
		return fotoProdutoDTOAssembler.toModel(fotoSalva);
	}
	
}
