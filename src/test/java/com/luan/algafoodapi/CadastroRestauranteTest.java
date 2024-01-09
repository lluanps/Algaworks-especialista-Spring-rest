package com.luan.algafoodapi;

import static org.assertj.core.api.Assertions.contentOf;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.CozinhaRepository;
import com.luan.algafoodapi.domain.repository.RestauranteRepository;
import com.luan.algafoodapi.util.DatabaseCleaner;
import com.luan.algafoodapi.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner cleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	private String jsonRestauranteCorreto;
	private String jsonRestauranteComCozinhaInexistente;
	private String jsonRestauranteSemFrete;
	
    private Restaurante RestaurantenewYorkBarbecue;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		
		jsonRestauranteCorreto = ResourceUtils.getContentFromResource(
				"/json/correto/restaurante-new-york-barbecue.json.json");
		
		jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource(
				"/json/correto/restaurante-new-york-barbecue-com-cozinha-inexistente.json");
		
		jsonRestauranteSemFrete = ResourceUtils.getContentFromResource(
				"/json/correto/restaurante-new-york-barbecue-sem-frete.json");
		
		cleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatus200QuandoConsultarRestaurante() {
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus201QuandoCadastrarRestauranteCorreto() {
		RestAssured
		.given()
			.body(jsonRestauranteCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus400QuandoCadastrarCozinhaInexistente() {
		RestAssured
		.given()
			.body(jsonRestauranteComCozinhaInexistente)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deveRetornarStatus404QuandoCadastrarRestauranteSemCozinha() {
		
	}
	
    private void prepararDados() {
        Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);
        
        RestaurantenewYorkBarbecue = new Restaurante();
        RestaurantenewYorkBarbecue.setNome("Burger Top");
        RestaurantenewYorkBarbecue.setTaxaFrete(new BigDecimal(10));
        RestaurantenewYorkBarbecue.setCozinha(cozinhaAmericana);
        restauranteRepository.save(RestaurantenewYorkBarbecue);
        
        Restaurante comidaMineiraRestaurante = new Restaurante();
        comidaMineiraRestaurante.setNome("Comida Mineira");
        comidaMineiraRestaurante.setTaxaFrete(new BigDecimal(10));
        comidaMineiraRestaurante.setCozinha(cozinhaBrasileira);
        restauranteRepository.save(comidaMineiraRestaurante);
    }

}
