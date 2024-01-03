package com.luan.algafoodapi.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;

@ControllerAdvice//dentro desse componente é possivel adicionar exceptions handlers
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);//adiciona a causa do erro
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException)rootCause, headers, status, request);
		}
		
		ApiErrorType apiErrorType = ApiErrorType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique se existe erro de sintaxe";
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
		
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		String path = ex.getPath().stream()
//				.map(ref -> ref.getFieldName())//retorna os resultados
//				.collect(Collectors.joining("."));
		
	    String path = joinPath(ex.getPath());
		
		ApiErrorType apiErrorType = ApiErrorType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu valor '%s', que é de um tipo inválido. "
				+ "Corrija e informe o valor compatível com o tipo %s.", 
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();

		return handleExceptionInternal(ex, apiError, headers, status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ApiErrorType apiErrorType = ApiErrorType.ENTIDADE_NAO_ENCONTRADA;
		String detail = e.getMessage();
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
		
//		ApiError apiError = ApiError.builder()
//				.status(status.value())
//				.type("https://algafood.com.br/entidade-nao-encontrada")
//				.title("Entidade não encontrada")
//				.detail(detail)
//				.build();
		
		return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiErrorType apiErrorType = ApiErrorType.NEGOCIO_EXCEPETION;
		String detail = e.getMessage();
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
		
		return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ApiErrorType apiErrorType = ApiErrorType.ENTIDADE_EM_USO;
		String detail = e.getMessage();
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
		
		return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = ApiError.builder()
					.title(status.getReasonPhrase())//getReasonPhrase() retorna a descrição do status
					.status(status.value())
					.build();
		} else if (body instanceof String) {
			body = ApiError.builder()
					.title((String) body)
					.status(status.value())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
	        HttpHeaders headers, HttpStatus status, WebRequest request) {

	    String path = joinPath(ex.getPath());
	    
	    ApiErrorType apiErrorType = ApiErrorType.MENSAGEM_INCOMPREENSIVEL;
	    String detail = String.format("A propriedade '%s' não existe. "
	            + "Corrija ou remova essa propriedade e tente novamente.", path);

	    ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
	    
	    return handleExceptionInternal(ex, apiError, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
	        HttpStatus status, WebRequest request) {
	    
	    if (ex instanceof MethodArgumentTypeMismatchException) {
	        return handleMethodArgumentTypeMismatch(
	                (MethodArgumentTypeMismatchException) ex, headers, status, request);
	    }

	    return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	        MethodArgumentTypeMismatchException ex, HttpHeaders headers,
	        HttpStatus status, WebRequest request) {

	    ApiErrorType apiErrorType = ApiErrorType.PARAMETRO_INVALIDO;

	    String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
	            + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
	            ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

	    ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();

	    return handleExceptionInternal(ex, apiError, headers, status, request);
	}
	
	private ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType apiErrorType
			, String detail) {
		
		return ApiError.builder()
				.status(status.value())
				.type(apiErrorType.getUri())
				.title(apiErrorType.getTitle())
				.detail(detail);
	}
	
	private String joinPath(List<Reference> references) {
	    return references.stream()
	        .map(ref -> ref.getFieldName())
	        .collect(Collectors.joining("."));
	}
	
}
