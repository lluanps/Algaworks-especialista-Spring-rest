package com.luan.algafoodapi;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.luan.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.service.CozinhaService;

@SpringBootTest
public class CadastroCozinhaIntegrationTests {
	
	@Autowired
	private CozinhaService cozinhaService;

	@Test
	public void deveCadastroCozinhaSucesso() {
	   Cozinha novaCozinha = new Cozinha();
	   novaCozinha.setNome(null);
	   
	   ConstraintViolationException erroEsperado =
	      assertThrows(ConstraintViolationException.class, () -> {
	    	  cozinhaService.salvar(novaCozinha);
	      });
	   
	   assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void testarCadastroCozinhaSemNome() {
	   Cozinha novaCozinha = new Cozinha();
	   novaCozinha.setNome(null);
	   
	   ConstraintViolationException erroEsperado =
	      Assertions.assertThrows(ConstraintViolationException.class, () -> {
	    	  cozinhaService.salvar(novaCozinha);
	      });
	   
	   assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void deveFalharQuandoExcluirCozinhaEmUso() {
		EntidadeEmUsoException erroEsperado =
				assertThrows(EntidadeEmUsoException.class, () -> {
					cozinhaService.excluir(2L);
				});
		
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalharQuandoExcluirCozinhaInexistente() {
		CozinhaNaoEncontradaException erroEsperado = 
				assertThrows(CozinhaNaoEncontradaException.class, () -> {
					cozinhaService.excluir(100L);
				});
		
		assertThat(erroEsperado).isNotNull();
	}
	
}
