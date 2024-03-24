package com.luan.algafoodapi.core.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(name = "security_auth", 
				type = SecuritySchemeType.OAUTH2, 
				flows = @OAuthFlows(authorizationCode = @OAuthFlow(
						authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
						tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
						scopes = {
								@OAuthScope(name = "READ", description = "read scope"),
								@OAuthScope(name = "WRITE", description = "write scope")
						}
				)))
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
