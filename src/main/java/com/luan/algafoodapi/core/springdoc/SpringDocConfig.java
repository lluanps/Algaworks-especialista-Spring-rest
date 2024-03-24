package com.luan.algafoodapi.core.springdoc;

import java.util.Arrays;

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
import io.swagger.v3.oas.models.tags.Tag;

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
					)
					.tags(Arrays.asList(
							new Tag().name("Cidades").description("Gerencia as cidades"),
							new Tag().name("Usuário").description("Gerencia os usuarios"),
							new Tag().name("Usuário Grupo").description("Gerencia os grupos de usuários"),
							new Tag().name("Restaurante").description("Gerencia os restaurantes"),
							new Tag().name("Restaurante Usuário Responsável").description("Gerencia os usuarios responsável pelo restaurante"),
							new Tag().name("Restaurante Produto").description("Gerencia os produtos do restaurante"),
							new Tag().name("Fluxo Pedido").description("Gerencia o fluxo dos pedidos do restaurante"),
							new Tag().name("Grupo").description("Gerencia os grupos"),
							new Tag().name("Grupo Permissao").description("Gerencia as permissões do grupo"),
							new Tag().name("Estado").description("Gerencia os estados"),
							new Tag().name("Cozinha").description("Gerencia as cozinhas"),
							new Tag().name("Pedido").description("Gerencia os pedidos"),
							new Tag().name("Forma Pagamento").description("Gerencia as formas de pagamento do restaurante"),
							new Tag().name("Estatistica").description("Gerencia as estatisticas do restaurante"),
							new Tag().name("Cliente").description("usado para testes"),
							new Tag().name("Restaurante Produto Foto").description("Gerencia as fotos do produto do restaurante"),
							new Tag().name("Restaurante Forma Pagamento").description("Gerencia as formas de pagamento do restaurante")
							));
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
