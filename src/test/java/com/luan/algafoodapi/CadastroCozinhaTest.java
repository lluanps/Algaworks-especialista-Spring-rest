package com.luan.algafoodapi;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/*
 * executar teste por linha de comando = mvnw test
 * limpa o projeto e em seguida recompila e empacota o código-fonte, = mvnw clean package
 * */

//@SpringBootTest usado apenas a annotation quando a aplicacao estiver levantada
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaTest {
	
	@LocalServerPort//é injetado o numero da porta q foi injetado,
	private int port;
	
	@Test
	public void deveRetornarStatus200QuandoConsultarCozinhas() {
		RestAssured
		.given()
			.basePath("/cozinhas")
//			.port(8080) caso o teste for realizado com a aplicação levantada usar o numero da porta
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());//htttp status 200
	}
	
	@Test
	public void deveConter3CozinhasQuandoConsultarCozinhas() {
		RestAssured
		.given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(3))//verifica a quantidade exata do array do corpo da resposta
			.body("nome", Matchers.hasItems("Chilena", "Brazileira"));//verifica se contem os nomes no corpo da resposta
	}
	
}
