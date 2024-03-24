package com.luan.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.assembler.UsuarioDTOAssembler;
import com.luan.algafoodapi.api.model.UsuarioDTO;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.service.RestauranteService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
@Tag(name = "Restaurante Usuário Responsável")
public class RestauranteUsuarioResponsavelController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	
	@GetMapping
	public CollectionModel<UsuarioDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscaOuFalha(restauranteId);
		
		return usuarioDTOAssembler.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks()
				.add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(RestauranteUsuarioResponsavelController.class)
										.listar(restauranteId)).withSelfRel());	
	}
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.adicionarResponsavel(restauranteId, usuarioId);
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.removerResponsavel(restauranteId, usuarioId);
	}

}
