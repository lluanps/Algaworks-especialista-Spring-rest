package com.luan.algafoodapi;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/*
 * executar teste por linha de comando = mvnw test
 * limpa o projeto e em seguida recompila e empacota o código-fonte, = mvnw clean package
 * */

//@SpringBootTest usado apenas a annotation quando a aplicacao estiver levantada
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaTest {
	
	@LocalServerPort//é injetado o numero da porta q foi injetado,
	private int port;
	
	@Autowired
	private Flyway flyway;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		flyway.migrate();
	}
	
	@Test
	public void deveRetornarStatus200QuandoConsultarCozinhas() {
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()// metodo HTTP
		.then()
			.statusCode(HttpStatus.OK.value());//htttp status 200
	}
	
	@Test
	public void deveConter4CozinhasQuandoConsultarCozinhas() {
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()// metodo HTTP
		.then()
			.body("", Matchers.hasSize(4))//verifica a quantidade exata do array do corpo da resposta
			.body("nome", Matchers.hasItems("Tailandesa", "Brasileira"));//verifica se contem os nomes no corpo da resposta
	}
	
	@Test
	public void deveRetornarStatus201QuandoCadastrarCozinhas() {
		RestAssured
		.given()
			.body("{ \"nome\": \"Japonesa\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()// metodo HTTP
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
}
