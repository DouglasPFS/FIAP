package com.techchallenge.restaurant.api.findfood.controller;

import com.techchallenge.restaurant.api.findfood.api.model.ReservaDTO;
import com.techchallenge.restaurant.api.findfood.dados.ReservaDados;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservaControllerIntegrationTest extends ReservaDados {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void deveReservarMesaComSucesso() {
        Long restauranteId = 1L;
        ReservaDTO reservaDTO = criarReservaDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reservaDTO)
        .when()
                .post("/api/restaurantes/{restauranteId}/reservarMesa", restauranteId)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("nomeCliente", equalTo(reservaDTO.getNomeCliente()))
                .body("emailCliente", equalTo(reservaDTO.getEmailCliente()))
                .body("telefoneCliente", equalTo(reservaDTO.getTelefoneCliente()))
                .body("qtdPessoas", equalTo(reservaDTO.getQtdPessoas()));
    }

    @Test
    void deveRetornarTodasAsReservasComSucesso() {
        Long restauranteId = 1L;

        given()
        .when()
                .get("/api/restaurantes/{restauranteId}/reservas", restauranteId)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @Disabled
    //TODO REFAZER TESTE
    void deveRetornarReservaPorIdComSucesso() {
        Long reservaId = 1L;

        given()
        .when()
                .get("/api/restaurantes/reserva/{reservaId}", reservaId)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void testExcluirReserva() {
        given()
                .when()
                .delete("/api/restaurantes/reserva/{reservaId}", 1L) // Assuming reservaId is 1L
                .then()
                .statusCode(HttpStatus.OK.value());
        // Add more assertions based on the response body if needed
    }
}
