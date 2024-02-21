package com.luan.algafoodapi.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)//adiciona restrições de validação de propriedades do modelo da documentação
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
//				.apis(RequestHandlerSelectors.any())// seleciona tudo
				.apis(RequestHandlerSelectors.basePackage("com.luan.algafoodapi.api"))// seleciona pacote
				.build()
			.apiInfo(apiInfo())
			.tags(new Tag("Cidades", "Gerencia as cidades", 0));
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
	
}