package com.github.elkanuco.fund_transfer.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.github.elkanuco.fund_transfer.dtos.OperationDto;
import com.github.elkanuco.fund_transfer.enums.OperationType;
import com.github.elkanuco.fund_transfer.services.CurrencyExchangeService;
import com.redis.testcontainers.RedisContainer;

import feign.FeignException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class OperationsControllerTest {

	@LocalServerPort
	private int port;

	@MockBean
	private CurrencyExchangeService currencyExchangeService;

	@Container
	private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16.3")//
			.withDatabaseName("testdb")//
			.withUsername("test")//
			.withPassword("test")//
			.withInitScript("dataset/init.sql");//

	@Container
	private static final RedisContainer REDISCONTAINER = new RedisContainer(DockerImageName.parse("redis:7.2.3-alpine"));

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
		registry.add("spring.datasource.username", POSTGRES::getUsername);
		registry.add("spring.datasource.password", POSTGRES::getPassword);
		registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");

		registry.add("spring.redis.host", REDISCONTAINER::getHost);
		registry.add("spring.redis.port", REDISCONTAINER::getFirstMappedPort);
	}

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Test
	@Order(1)
	void process_validWithdraw_inSameCurrency() {
		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.WITHDRAW)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(1l)//
						.currency("JPY")// same currency as the base account to avoid conversion
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(200);//
		// then
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.get("/basiccrud/accounts/1")//
				.then()//
				.statusCode(200)//
				.body("id", is(1), //
						"name", is("Emma Thompson CREDIT JPY"), //
						"balance", is("499900.00")// 500_000-100
				);//
	}

	@Test
	@Order(2)
	void process_validWithdraw_inDifferentCurrency() {
		when(currencyExchangeService.getExchangeRate(any(String.class), any(String.class), any(LocalDate.class)))
				.thenReturn(BigDecimal.valueOf(0.5d));

		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.WITHDRAW)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(1l)//
						.currency("EUR")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(200);//
		// then
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.get("/basiccrud/accounts/1")//
				.then()//
				.statusCode(200)//
				.body("id", is(1), //
						"name", is("Emma Thompson CREDIT JPY"), //
						"balance", is("499850.00")// 499_900.00 - (100*0.5)
				);//
	}

	@Test
	@Order(3)
	void process_invalidWithdraw_insuficientFunds() {
		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.WITHDRAW)//
						.amount(new BigDecimal("500001"))//
						.baseAccountId(1l)//
						.currency("JPY")// same currency as the base account to avoid conversion
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(400)//
				.body(is("Insuficient funds: 499850.00"));// 500_001 > 500_00.00 - 100 - (100*0.5)
	}

	@Test
	@Order(4)
	void process_invalidWithdraw_nonexistantAccount() {
		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.WITHDRAW)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(666l)//
						.currency("JPY")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(404)//
				.body(is("Account not found: 666"));
	}

	@Test
	@Order(5)
	void process_invalidWithdraw_externalExchangeRateServiceThrows() {
		FeignException exception = mock(FeignException.class);
		when(exception.getMessage()).thenReturn("RANDOM ISSUE");
		doThrow(exception).when(currencyExchangeService).getExchangeRate(any(String.class), any(String.class),
				any(LocalDate.class));

		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.WITHDRAW)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(1l)//
						.currency("EUR")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(502)//
				.body(is("Remote service error: RANDOM ISSUE"));//
	}

	@Test
	@Order(6)
	void process_validDeposit_inSameCurrency() {
		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.DEPOSIT)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(1l)//
						.currency("JPY")// same currency as the base account to avoid conversion
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(200);//
		// then
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.get("/basiccrud/accounts/1")//
				.then()//
				.statusCode(200)//
				.body("id", is(1), //
						"name", is("Emma Thompson CREDIT JPY"), //
						"balance", is("499950.00")// 499850.00 + 100
				);//
	}

	@Test
	@Order(7)
	void process_validDeposit_inDifferentCurrency() {
		when(currencyExchangeService.getExchangeRate(any(String.class), any(String.class), any(LocalDate.class)))
				.thenReturn(BigDecimal.valueOf(0.5d));

		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.DEPOSIT)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(1l)//
						.currency("EUR")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(200);//
		// then
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.get("/basiccrud/accounts/1")//
				.then()//
				.statusCode(200)//
				.body("id", is(1), //
						"name", is("Emma Thompson CREDIT JPY"), //
						"balance", is("500000.00")// 499850.00 + 100 + 50
				);//
	}

	@Test
	@Order(8)
	void process_invalidDeposit_nonexistantAccount() {
		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.DEPOSIT)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(666l)//
						.currency("JPY")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(404)//
				.body(is("Account not found: 666"));
	}

	@Test
	@Order(9)
	void process_invalidDeposit_externalExchangeRateServiceThrows() {
		FeignException exception = mock(FeignException.class);
		when(exception.getMessage()).thenReturn("RANDOM ISSUE");
		doThrow(exception).when(currencyExchangeService).getExchangeRate(any(String.class), any(String.class),
				any(LocalDate.class));

		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.DEPOSIT)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(1l)//
						.currency("EUR")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(502)//
				.body(is("Remote service error: RANDOM ISSUE"));//
	}

	// TODO repeat for other fields : this relies on the Validation annotations
	// @OperationDto
	@Test
	@Order(10)
	void process_invalidWithdraw_missingRequestAttributes() {
		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.WITHDRAW)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(null)//
						.currency("JPY")// same currency as the base account to avoid conversion
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(422)//
				.body(is("Base account id is mandatory"));//

	}

	@Test
	@Order(11)
	void process_invalidTransfer_missingTargetAccount() {
		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.TRANSFER)//
						.amount(new BigDecimal("100"))//
						.baseAccountId(1l)//
						.targetAccountId(null)// missing
						.currency("JPY")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(422)//
				.body(is("Missing target account"));//
	}

	@Test
	@Order(12)
	void process_validTransfer() {
		when(currencyExchangeService.getExchangeRate(any(String.class), any(String.class), any(LocalDate.class)))
				.thenReturn(BigDecimal.valueOf(0.5d));

		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.TRANSFER)//
						.amount(new BigDecimal("1000"))//
						.baseAccountId(1l)// JPY - (1000*0.5)
						.targetAccountId(2l)// SGD + (1000*0.5)
						.currency("EUR")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(200);//
		// then
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.get("/basiccrud/accounts/1")//
				.then()//
				.statusCode(200)//
				.body("id", is(1), //
						"name", is("Emma Thompson CREDIT JPY"), //
						"baseCurrency", is(3), // JPY
						"balance", is("499500.00")// 500_000-500
				);//

		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.get("/basiccrud/accounts/2")//
				.then()//
				.statusCode(200)//
				.body("id", is(2), //
						"name", is("Emma Thompson DEBIT SGD"), //
						"baseCurrency", is(12), // SGD
						"balance", is("500500.00")// 500_000+500
				);//
	}

	@Test
	@Order(13)
	void process_invalidTransfer_insufficientFunds() {
		// when
		given()//
				.when()//
				.contentType(ContentType.JSON)//
				.body(OperationDto.builder()//
						.type(OperationType.TRANSFER)//
						.amount(new BigDecimal("500001"))//
						.baseAccountId(1l)// JPY - (1000*0.5)
						.targetAccountId(2l)//
						.currency("JPY")//
						.build())//
				.post("/operations")//
				.then()//
				.statusCode(400)//
				.body(is("Insuficient funds: 499500.00"));// 500_001 > 499_500.00
	}

}
