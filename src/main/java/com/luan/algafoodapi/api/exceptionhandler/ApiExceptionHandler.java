package com.luan.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;

@ControllerAdvice//dentro desse componente é possivel adicionar exceptions handlers
public class ApiExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
		ApiError apiError = ApiError.builder()
				.dataHora(LocalDateTime.now())
				.msg(e.getMessage())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e) {
		ApiError apiError = ApiError.builder()
				.dataHora(LocalDateTime.now())
				.msg(e.getMessage())
				.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException() {
		ApiError apiError = ApiError.builder()
				.dataHora(LocalDateTime.now())
				.msg("O tipo de mídia não é aceito.")
				.build();
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(apiError);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
		ApiError apiError = ApiError.builder()
				.dataHora(LocalDateTime.now())
				.msg(e.getMessage())
				.build();
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(apiError);
	}
	
}
