package com.luan.algafoodapi.api.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}
	
	//@ResponseStatus(HttpStatus.OK)retornando o status de outro forma
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> findCozinhaById(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRepository.buscar(id);
		
		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	/*retornando com STATUS CREATED 201*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha save(@RequestBody Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> update(@PathVariable Long id, @RequestBody Cozinha obj) {
		Cozinha cozinha = cozinhaRepository.buscar(id);
		
		if (cozinha != null) {
			/*BeanUtils seta os valores, é parecido como o => cozinha.setNome(obj.getNome());
			o terceiro parametro(1,2,3) é usado para ignorar os campos que nao vao ser setados*/
			BeanUtils.copyProperties(obj, cozinha, "id");
			cozinha = cozinhaRepository.salvar(cozinha);
			return ResponseEntity.ok(cozinha);			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Cozinha> delete(@PathVariable Long id) {
		try {
			Cozinha cozinha = cozinhaRepository.buscar(id);
			
			//tratando NullPointException
			if (id != null) {
				cozinhaRepository.remover(cozinha);
				//return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				return ResponseEntity.noContent().build();			
			}

			return ResponseEntity.notFound().build();			
		} 
		catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();//retornar um corpo futuramente
		}
	}

	
}
