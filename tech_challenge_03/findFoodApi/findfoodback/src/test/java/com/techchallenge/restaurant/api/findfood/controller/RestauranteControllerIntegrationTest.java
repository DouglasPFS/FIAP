package com.techchallenge.restaurant.api.findfood.controller;

import com.techchallenge.restaurant.api.findfood.api.model.AvaliacaoDTO;
import com.techchallenge.restaurant.api.findfood.api.model.RestauranteDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static com.techchallenge.restaurant.api.findfood.dados.RestauranteDados.criarRestauranteDtoValido;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class RestauranteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Nested
    @DisplayName("Testes de Registro de Restaurante")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class registrarRestaurante {
        @Test
        @Order(1)
        void devePermitirRegistrarRestaurante() {

            // Arrange
            Long restauranteId = 1L;
            RestauranteDTO restauranteDTO = criarRestauranteDtoValido();

            given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restauranteDTO)

            // Act
            .when()
                .post("/api/restaurantes/registrarRestaurante")

            // Assert
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(equalTo("Restaurante Registrado com Sucesso"));

        }

        @Test
        @Order(2)
        void deveLancarExcecaoAoSalvarRestauranteComNomeVazio() {

            // Arrange
            Long restauranteId = 1L;
            RestauranteDTO restauranteDTO = criarRestauranteDtoValido();
            restauranteDTO.setNome("");

            given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restauranteDTO)

            // Act
            .when()
                .post("/api/restaurantes/registrarRestaurante")

            // Assert
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE) // Verifica o tipo de conteúdo JSON
                .body("timestamp", notNullValue())
                .body("status", equalTo(400))
                .body("message", equalTo("Inconsistencia nos campos informados."))
                .body("path", equalTo("/api/restaurantes/registrarRestaurante"));
        }
    }

    @Nested
    @DisplayName("Testes de Atualização de Restaurante")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class atualizarRestaurante {
        @Test
        @Order(1)
        void devePermitirAtualizarRestaurantes() {

            // Arrange
            Long restauranteId = 1L;
            RestauranteDTO restauranteDTO = criarRestauranteDtoValido();

            given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restauranteDTO)

            // Act
            .when()
                .put("/api/restaurantes/atualizar/{restauranteId}", restauranteId)

            // Assert
            .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("nome", equalTo(restauranteDTO.getNome()))
                .body("localizacao", equalTo(restauranteDTO.getLocalizacao()))
                .body("tipoCozinha", equalTo(restauranteDTO.getTipoCozinha()))
                .body("horarioFuncionamento", equalTo(restauranteDTO.getHorarioFuncionamento()))
                .body("quantidadeTotalDeMesas", equalTo(restauranteDTO.getQuantidadeTotalDeMesas()));
        }

        @Test
        @Order(2)
        void deveLancarExcecaoAoAtualizarRestauranteInexistente() {

            // Arrange
            Long restauranteId = 100L;
            RestauranteDTO restauranteDTO = criarRestauranteDtoValido();

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(restauranteDTO)

            // Act
            .when()
                .put("/api/restaurantes/atualizar/{restauranteId}", restauranteId)

            // Assert
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE) // Verifica o tipo de conteúdo JSON
                .body("timestamp", notNullValue())
                .body("status", equalTo(404))
                .body("message", equalTo("Restaurante não foi encontrado"))
                .body("path", equalTo("/api/restaurantes/atualizar/100"));

        }
    }

    @Nested
    @DisplayName("Testes de Exclusão de Restaurante")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class deletarRestaurante {
        @Test
        @Order(1)
        void devePermitirDeletarRestaurantes() {

            // Arrange
            Long restauranteId = 100L;
            RestauranteDTO restauranteDTO = criarRestauranteDtoValido();

            given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restauranteDTO)

                // Act
            .when()
                .put("/api/restaurantes/atualizar/{restauranteId}", restauranteId)

                // Assert
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE) // Verifica o tipo de conteúdo JSON
                .body("timestamp", notNullValue())
                .body("status", equalTo(404))
                .body("message", equalTo("Restaurante não foi encontrado"))
                .body("path", equalTo("/api/restaurantes/atualizar/100"));

            //  "org.springframework.dao.DataIntegrityViolationException: could not execute statement [ERROR: update or delete on table "tb_restaurante" violates foreign key constraint "fkfij8rbafgjtwm9knwe98wcut9" on table "tb_avaliacao"
            //  Detalhe: Key (id)=(1) is still referenced from table "tb_avaliacao".] [delete from tb_restaurante where id=?]; SQL [delete from tb_restaurante where id=?]; constraint [fkfij8rbafgjtwm9knwe98wcut9]

        }

        @Test
        @Order(2)
        void deveLancarExcecaoAoDeletarRestauranteInexistente() {

            // Arrange

            // Assert

        }
    }

    @Nested
    @DisplayName("Testes de Busca de Restaurante")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class buscarRestaurante {
        @Test
        @Order(1)
        void devePermitirBuscarRestaurantePorNome() throws InterruptedException {

            // Arrange

            // Act

            // Assert


        }

        @Test
        @Order(2)
        void devePermitirBuscarRestaurantePorLocalizacao() throws InterruptedException {

            // Arrange

            // Act

            // Assert

        }

        @Test
        @Order(3)
        void devePermitirBuscarRestaurantePorTipoCozinha() throws InterruptedException {

            // Arrange

            // Act

            // Assert

        }

        @Test
        @Order(4)
        void devePermitirBuscarRestaurantesPorId() {

            // Arrange

            // Assert

        }

        @Test
        @Order(5)
        void devePermitirBuscarTodosRestaurantes() {

            // Arrange

            // Act

            // Assert

        }

        @Test
        @Order(6)
        void deveLancarExcecaoAoBuscarNomeRestauranteInexistente() {

            // Arrange

            // Assert

        }

        @Test
        @Order(7)
        void deveLancarExcecaoAoBuscarLocalizacaoRestauranteInexistente() {

            // Arrange

            // Assert

        }

        @Test
        @Order(8)
        void deveLancarExcecaoAoBuscarTipoCozinhaRestauranteInexistente() {

            // Arrange

            // Assert

        }

        @Test
        @Order(9)
        void deveLancarExcecaoAoBuscarTodosRestaurantes() {

            Long restauranteId = 2L;

            given()
                .when()
                .get("/api/restaurantes/todos")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Restaurante não foi encontrado"));

        }
    }

}
