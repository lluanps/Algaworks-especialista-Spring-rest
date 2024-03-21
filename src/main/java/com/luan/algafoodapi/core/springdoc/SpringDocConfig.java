package com.luan.algafoodapi.core.springdoc;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {

	/*Login: luan, senha:123*/
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
					.info(new Info().title("Algafood API")
							.version("v1")
							.description("Rest API do Algafood")
							.license(new License()
									.name("Apache 2.0")
									.url("http://springdoc.com")
									))
					.externalDocs(new ExternalDocumentation()
							.description("Github - Luan Pinheiro da Silva")
							.url("https://github.com/lluanps")
					);
	}
	
	/*
	@Bean
	public GroupedOpenApi groupedOpenApi() {
		return GroupedOpenApi.builder()
				.group("Algafood API")
				.pathsToMatch("/**")
				.addOpenApiCustomiser(openApi -> {
					openApi.info(new Info()
							.title("Algafood API")
							.version("v1")
							.description("Rest API do Algafood")
							.license(new License()
									.name("Apache 2.0")
									.url("http://springdoc.com")
									))
							.externalDocs(new ExternalDocumentation()
									.description("Github - Luan Pinheiro da Silva")
									.url("https://github.com/lluanps")
					);
				})
				.build();
	}
	
	@Bean
	public GroupedOpenApi groupedOpenApiCliente() {
		return GroupedOpenApi.builder()
				.group("Algafood API Cliente")
				.pathsToMatch("/clientes/**")
				.addOpenApiCustomiser(openApi -> {
					openApi.info(new Info()
							.title("Algafood API Cliente")
							.version("v1")
							.description("Rest API do Algafood Cliente")
							.license(new License()
									.name("Apache 2.0")
									.url("http://springdoc.com")
									))
							.externalDocs(new ExternalDocumentation()
									.description("Github - Luan Pinheiro da Silva")
									.url("https://github.com/lluanps")
					);
				})
				.build();
	}
	*/

}
