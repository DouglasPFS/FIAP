package com.techchallenge.restaurant.api.findfood.controller;

import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestauranteControllerIntegrationTest {

    @Nested
    @DisplayName("Testes de Registro de Restaurante")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class registrarRestaurante {
        @Test
        @Order(1)
        void devePermitirRegistrarRestaurante() {

            // Arrange

            // Assert

        }

        @Test
        @Order(2)
        void deveLancarExcecaoAoSalvarRestauranteComNomeVazio() {

            // Arrange

            // Assert

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

            // Act

            // Assert
        }

        @Test
        @Order(2)
        void deveLancarExcecaoAoAtualizarRestauranteInexistente() {

            // Arrange

            // Assert

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

            // Act

            // Assert

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
                    .get("/api/restaurantes/{restauranteId}/avaliacoes", restauranteId)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("path", equalTo("/api/restaurantes/2/avaliacoes"))
                    .body("message", equalTo("Restaurante não foi encontrada"));

        }
    }

}
