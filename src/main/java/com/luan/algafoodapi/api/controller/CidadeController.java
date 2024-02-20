package com.luan.algafoodapi.api.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.protocol.RequestContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luan.algafoodapi.api.ResourceURIHelper;
import com.luan.algafoodapi.api.assembler.CidadeDTOAssembler;
import com.luan.algafoodapi.api.assembler.CidadeInputDisassembler;
import com.luan.algafoodapi.api.model.CidadeDTO;
import com.luan.algafoodapi.api.model.input.CidadeInput;
import com.luan.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Cidade;
import com.luan.algafoodapi.domain.repository.CidadeRepository;
import com.luan.algafoodapi.domain.service.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

// SpringFox (3.0.0) foi descontinuada.
@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService service;
	
	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private CidadeDTOAssembler cidadeDTOAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@ApiOperation("Lista as cidades")
	@GetMapping
	public List<CidadeDTO> listar() {
		List<Cidade> findAll = repository.findAll();
		
		return cidadeDTOAssembler.toCollectionDto(findAll);
	}

	@ApiOperation("Busca uma cidade po Id")
	@GetMapping(value = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeDTO cidadeById(@ApiParam(value = "Id de uma cidade")
	@PathVariable Long cidadeId) {
		Cidade cidade = service.buscarOuFalhar(cidadeId);
		
		CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cidade);
		
		cidadeDTO.add(Link.of("http://localhost:8080/cidades/1"));
//		cidadeDTO.add(Link.of("http://localhost:8080/cidades/1", IanaLinkRelations.SELF)); /* https://www.iana.org/assignments/link-relations/link-relations.xhtml */

		cidadeDTO.add(Link.of("http://localhost:8080/cidades", "cidades"));
//		cidadeDTO.add(Link.of("http://localhost:8080/cidades", IanaLinkRelations.COLLECTION));
		
		cidadeDTO.getEstado().add(Link.of("http://localhost:8080/estados/1"));
		
		return cidadeDTO;
	}
	
	@ApiOperation("Cadastra uma cidade")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDTO salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
	@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			cidade = service.salvar(cidade);
			
			CidadeDTO cidadeDTO =  cidadeDTOAssembler.toModel(cidade);
			
			ResourceURIHelper.addUriResponseHeader(cidadeDTO.getId());
			
			return cidadeDTO;
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@ApiOperation("Atualiza uma cidade por Id")
	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@ApiParam("Id de uma cidade") @PathVariable @Valid Long cidadeId,
			@ApiParam(name = "corpo", value = "Representação de uma cidade como novos dados a serem atualizados") @RequestBody CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = service.buscarOuFalhar(cidadeId);
			
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			cidadeAtual =  service.salvar(cidadeAtual);
			
			return cidadeDTOAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@ApiOperation("Exclui uma cidade por Id")
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam("Id de uma cidade") @PathVariable Long cidadeId) {
		service.excluir(cidadeId);	
	}
	
}
