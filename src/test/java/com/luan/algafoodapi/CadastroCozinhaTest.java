package com.luan.algafoodapi;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.repository.CozinhaRepository;
import com.luan.algafoodapi.util.DatabaseCleaner;

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
	
	private static final int COZINHA_ID_INEXISTENTE = 999999999;
	
	@LocalServerPort//é injetado o numero da porta q foi injetado,
	private int port;
	
	@Autowired
	private DatabaseCleaner cleaner;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
//	private Cozinha cozinhaTest;
	private int quantidadeCozinhasCadastradas;
//	private String jsonCorretoCozinhaChinesa;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
//		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
//				"/json/correto/cozinha-chinesa.json");
		
		cleaner.clearTables();
		prepararDados();
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
	public void deveRetornarQuantidadeCorretaDeCozinhasQuandoConsultarCozinhas() {
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()// metodo HTTP
		.then()
			.body("", Matchers.hasSize(quantidadeCozinhasCadastradas));//verifica a quantidade exata do array do corpo da resposta
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

	/*
	@Test
	public void deveRetornarRespostaEStatusCorretosQuandoConsultarCozinhaExistente() {
		RestAssured
		.given()
			.pathParam("cozinhaId", cozinhaTest.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaTest.getNome()));
	}
	*/
	
	@Test
	public void deveRetornarStatus404QuandoConsultarCozinhaInexistente() {
		RestAssured
		.given()
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
		
		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
	}
	
}
