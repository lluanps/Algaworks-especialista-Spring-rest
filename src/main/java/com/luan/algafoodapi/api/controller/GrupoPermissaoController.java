package com.luan.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.assembler.PermissaoDTOAssembler;
import com.luan.algafoodapi.api.model.PermissaoDTO;
import com.luan.algafoodapi.api.openapi.GrupoPermissaoControllerOpenApi;
import com.luan.algafoodapi.domain.model.Grupo;
import com.luan.algafoodapi.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler;
	
	@GetMapping
	public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.buscaOuFalha(grupoId);
		
		return permissaoDTOAssembler.toCollectionDto(grupo.getPermissoes());
	}
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.associar(grupoId, permissaoId);
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarGrupo(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.desassociar(grupoId, permissaoId);
	}

}
