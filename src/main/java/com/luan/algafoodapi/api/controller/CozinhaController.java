package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.assembler.CozinhaDTOAssembler;
import com.luan.algafoodapi.api.assembler.CozinhaInputDisassembler;
import com.luan.algafoodapi.api.model.CozinhaDTO;
import com.luan.algafoodapi.api.model.input.CozinhaInput;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.repository.CozinhaRepository;
import com.luan.algafoodapi.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;
	
	@Autowired
	private CozinhaService service;
	
	@Autowired
	private CozinhaDTOAssembler cozinhaDTOAssembler;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@GetMapping
	public Page<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cozinha> cozinahsPage = repository.findAll(pageable);
		
		List<CozinhaDTO> cozinhasDTO =  cozinhaDTOAssembler.toCollectionDto(cozinahsPage.getContent());
		Page<CozinhaDTO> cozinhaDTOPage = new PageImpl<>(cozinhasDTO, pageable, cozinahsPage.getTotalElements());
	
		return cozinhaDTOPage;
	}
	
	//@ResponseStatus(HttpStatus.OK)retornando o status de outro forma
	@GetMapping("/{cozinhaId}")
	public CozinhaDTO findCozinhaById(@PathVariable Long cozinhaId) {
		Cozinha cozinha = service.buscaOuFalha(cozinhaId);
		
		return cozinhaDTOAssembler.toModel(cozinha);
	}
	
	/*retornando com STATUS CREATED 201*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaDTO save(@RequestBody @Valid CozinhaInput cozinhaIdInput) {
		Cozinha cozinhaAtual = cozinhaInputDisassembler.toDomainObject(cozinhaIdInput);
		cozinhaAtual = service.salvar(cozinhaAtual);
		
		return cozinhaDTOAssembler.toModel(cozinhaAtual);
	}
	
	@PutMapping("/{id}")
	public CozinhaDTO update(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
	    Cozinha cozinhaAtual = service.buscaOuFalha(id);
	    cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
	    
	    cozinhaAtual = service.salvar(cozinhaAtual);
	    
	    return cozinhaDTOAssembler.toModel(cozinhaAtual);		
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.excluir(id);
	}

}
