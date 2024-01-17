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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.assembler.UsuarioComSenhaInput;
import com.luan.algafoodapi.api.assembler.UsuarioDTOAssembler;
import com.luan.algafoodapi.api.assembler.UsuarioInput;
import com.luan.algafoodapi.api.assembler.UsuarioInputDisassembler;
import com.luan.algafoodapi.api.model.UsuarioDTO;
import com.luan.algafoodapi.api.model.input.SenhaInput;
import com.luan.algafoodapi.domain.model.Usuario;
import com.luan.algafoodapi.domain.repository.UsuarioRepository;
import com.luan.algafoodapi.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@GetMapping
	public List<UsuarioDTO> listar() {
		List<Usuario> findAll = repository.findAll();
		
		return usuarioDTOAssembler.toCollectionDto(findAll);
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscarPorId(@PathVariable Long usuarioId) {
		Usuario usuario = service.buscaOuFalha(usuarioId);
		
		return usuarioDTOAssembler.toModel(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);
		usuario = service.salvar(usuario);
		
		return usuarioDTOAssembler.toModel(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuarioAtual = service.buscaOuFalha(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = service.salvar(usuarioAtual);
		
		return usuarioDTOAssembler.toModel(usuarioAtual);
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
		service.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
	
}
