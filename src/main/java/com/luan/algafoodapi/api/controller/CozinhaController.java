package com.luan.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
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
	
	@GetMapping
	public List<Cozinha> listar() {
		return repository.findAll();
	}
	
	//@ResponseStatus(HttpStatus.OK)retornando o status de outro forma
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> findCozinhaById(@PathVariable Long id) {
		Optional<Cozinha> cozinha = repository.findById(id);
		
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	/*retornando com STATUS CREATED 201*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha save(@RequestBody Cozinha cozinha) {
		return service.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> update(@PathVariable Long id, @RequestBody Cozinha obj) {
		Optional<Cozinha> cozinhaAtual = repository.findById(id);
		
		if (cozinhaAtual.isPresent()) {
			/*BeanUtils seta os valores, é parecido como o => cozinha.setNome(obj.getNome());
			o terceiro parametro(1,2,3) é usado para ignorar os campos que nao vao ser setados*/
			BeanUtils.copyProperties(obj, cozinhaAtual.get(), "id");
			Cozinha cozinhaSalva = service.salvar(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaSalva);			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.excluir(id);
	}

}
