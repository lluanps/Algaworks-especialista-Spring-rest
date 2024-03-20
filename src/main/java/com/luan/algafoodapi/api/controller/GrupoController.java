package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.luan.algafoodapi.api.assembler.GrupoDTOAssembler;
import com.luan.algafoodapi.api.assembler.GrupoInputDisassembler;
import com.luan.algafoodapi.api.model.GrupoDTO;
import com.luan.algafoodapi.api.model.input.GrupoInput;
import com.luan.algafoodapi.domain.model.Grupo;
import com.luan.algafoodapi.domain.repository.GrupoRepository;
import com.luan.algafoodapi.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoService service;
	
	@Autowired
	private GrupoRepository repository;
	
	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@GetMapping
	public List<GrupoDTO> listar() {
		List<Grupo> buscarTodosGrupos = repository.findAll(); 
		
		return grupoDTOAssembler.toCollectionDTO(buscarTodosGrupos);
	}
	
	@GetMapping("/{grupoId}")
	public GrupoDTO buscarPorId(@PathVariable Long grupoId) {
		Grupo grupo = service.buscaOuFalha(grupoId);
		
		return grupoDTOAssembler.toModel(grupo);
	}
	
	@PostMapping
	public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		grupo = service.salvar(grupo);
		return grupoDTOAssembler.toModel(grupo);
	}
	
	@PutMapping("/{grupoId}")
	public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = service.buscaOuFalha(grupoId);
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		return grupoDTOAssembler.toModel(service.salvar(grupoAtual));
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		service.excluir(grupoId);
	}
	
}
