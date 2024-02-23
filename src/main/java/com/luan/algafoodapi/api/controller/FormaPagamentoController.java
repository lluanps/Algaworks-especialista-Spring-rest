package com.luan.algafoodapi.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.luan.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import com.luan.algafoodapi.api.assembler.FormaPagamentoInputDisassembler;
import com.luan.algafoodapi.api.model.FormaPagamentoDTO;
import com.luan.algafoodapi.api.model.input.FormaPagamentoInput;
import com.luan.algafoodapi.api.openapi.FormaPagamentoControllerOpenApi;
import com.luan.algafoodapi.domain.model.FormaPagamento;
import com.luan.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.luan.algafoodapi.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {
	
	@Autowired
	private FormaPagamentoService service;
	
	@Autowired
	private FormaPagamentoRepository repository;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

	@GetMapping
	public ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		OffsetDateTime dataUltimaAtualizacao = repository.getDataUltimaAtualizacao();
		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		List<FormaPagamento> buscaTodos = repository.findAll();
		
		List<FormaPagamentoDTO> formaPagamentoDTOs = formaPagamentoDTOAssembler.toCollectionDto(buscaTodos);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())// habilita o cache para guardar informação por 10 segundos, evitando uma nova requisição durante esse tempote
//				.cacheControl(CacheControl.cachePrivate()())// cachePrivate() -> armazena apenas em cache local
//				.cacheControl(CacheControl.cachePublic())// cachePrivate() -> pode ser armazenada em cache locais e compartilhado
//				.cacheControl(CacheControl.noCache())// se a resposta for em cache, sera necessario fazer uma validacao no servidor, Etag	
//				.cacheControl(CacheControl.noStore())//Não pode ser armazenado em nenhum cache local/compartilhado
				.eTag(eTag)
				.body(formaPagamentoDTOs);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoDTO> buscarPorId(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
	    ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
	    
	    String eTag = "0";
	    
	    OffsetDateTime dataAtualizacao = repository.getDataAtualizacaoById(formaPagamentoId);
	    
	    if (dataAtualizacao != null) {
	        eTag = String.valueOf(dataAtualizacao.toEpochSecond());
	    }
	    
	    if (request.checkNotModified(eTag)) {
	        return null;
	    }
	    
	    FormaPagamento formaPagamento = service.buscaOuFalha(formaPagamentoId);
	    
	    FormaPagamentoDTO formaPagamentoDTO = formaPagamentoDTOAssembler.toModel(formaPagamento);
	    
	    return ResponseEntity.ok()
	            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
	            .eTag(eTag)
	            .body(formaPagamentoDTO);
	}
	
	@PostMapping
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
		formaPagamento = service.salvar(formaPagamento);
		return formaPagamentoDTOAssembler.toModel(formaPagamento);
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public void excluir(@PathVariable Long formaPagamentoId) {
		service.excluir(formaPagamentoId);
	}

}
