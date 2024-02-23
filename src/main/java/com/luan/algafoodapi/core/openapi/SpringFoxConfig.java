package com.luan.algafoodapi.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luan.algafoodapi.api.exceptionhandler.ApiError;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)//adiciona restrições de validação de propriedades do modelo da documentação
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();
			
		return new Docket(DocumentationType.OAS_30)
				.select()
//				.apis(RequestHandlerSelectors.any())// seleciona tudo
				.apis(RequestHandlerSelectors.basePackage("com.luan.algafoodapi.api"))// seleciona pacote
				.paths(PathSelectors.any())
				.build()
			.useDefaultResponseMessages(false)// desabilita codigo de Status 400
			.globalResponses(HttpMethod.GET, globalGetResponseMessages())
			.globalResponses(HttpMethod.POST, globalPostResponseMessages())
			.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
			.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
			.additionalModels(typeResolver.resolve(ApiError.class))// add um modelo na documentação
			.ignoredParameterTypes(ServletWebRequest.class)
			.apiInfo(apiInfo())
			.tags(new Tag("Cidades", "Gerencia as cidades", 0),
				new Tag("Grupos", "Gerencia as grupos de usuário"),
				new Tag("Cozinhas", "Gerencia as cozinhas"),
				new Tag("Forma Pagamento", "Gerencia as cozinhas formas de pagamento"),
				new Tag("Pedido", "Gerencia os pedidos"),
				new Tag("Restaurante", "Gerencia os restaurantes"));
	}

	private List<Response> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
					.description("Requisição inválida")
			        .representation(MediaType.APPLICATION_JSON)
			        	.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
					.description("Erro interno do sistema")
			        .representation(MediaType.APPLICATION_JSON)
			        	.apply(getProblemaModelReference())
					.build()
				);
	}

	private List<Response> globalPutResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
					.description("Requisição inválida")
			        .representation(MediaType.APPLICATION_JSON)
		        		.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
					.description("Erro interno do sistema")
			        .representation(MediaType.APPLICATION_JSON)
		        		.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
					.description("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
					.description("Requisição recusado, pois o formato da requisição não é aceito")
			        .representation(MediaType.APPLICATION_JSON)
		        		.apply(getProblemaModelReference())
					.build()
				);
	}

	private List<Response> globalPostResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
					.description("Erro interno do sistema")
			        .representation(MediaType.APPLICATION_JSON)
		        		.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
					.description("Requisição inválida")
			        .representation(MediaType.APPLICATION_JSON)
		        		.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
					.description("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseBuilder()
					.code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
					.description("Requisição recusado, pois o formato da requisição não é aceito")
			        .representation(MediaType.APPLICATION_JSON)
			        	.apply(getProblemaModelReference())
					.build()
				);
	}

	private List<Response> globalGetResponseMessages() {
		  return Arrays.asList(
		      new ResponseBuilder()
		          .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
		          .description("Erro interno do Servidor")
		          .representation(MediaType.APPLICATION_JSON)
		          	.apply(getProblemaModelReference())
		          .build(),
		      new ResponseBuilder()
		          .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
		          .description("Recurso não possui representação que pode ser aceita pelo consumidor")
		          .build()
		  );
		}
	
	/* http://localhost:8080/swagger-ui/index.html */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Algafood API")
				.description("API Aberta para clientes e restaurantes")
				.version("1")
				.contact(new Contact("Luan Pinheiro da Silva", "https://www.linkedin.com/in/lluanps/", "lluanps@gmail.com"))
				.build();
	}
	
	//usado para corrigir erro ao adicionar @ApiModelProperty no OffsetDateTime
	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}

	//Usado para referenciar modelo de representação de problema com códigos de status de erro
	private Consumer<RepresentationBuilder> getProblemaModelReference() {
	    return r -> r.model(m -> m.name("ApiError")
	            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
	                    q -> q.name("ApiError").namespace("com.luan.algafoodapi.api.exceptionhandler")))));
	}
	
}