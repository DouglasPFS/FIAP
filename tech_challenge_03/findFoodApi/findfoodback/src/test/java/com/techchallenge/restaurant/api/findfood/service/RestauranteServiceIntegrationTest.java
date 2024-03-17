package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.api.model.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.domain.model.Restaurante;
import com.techchallenge.restaurant.api.findfood.domain.repository.RestauranteRepository;
import com.techchallenge.restaurant.api.findfood.domain.service.RestauranteService;
import com.techchallenge.restaurant.api.findfood.dados.RestauranteDados;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.*;
@SpringBootTest
class RestauranteServiceIntegrationTest extends RestauranteDados {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private RestauranteService restauranteService;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    @DisplayName("Testes de Registro de Restaurante")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class registrarRestaurante {
        @Test
        @Order(1)
        void devePermitirRegistrarRestaurante() {

            // Arrange
            var restauranteDto = criarRestauranteDtoValido();

            // Assert
            assertThatCode(() -> restauranteService.registrarRestaurante(restauranteDto)).doesNotThrowAnyException();
        }

        @Test
        @Order(2)
        void deveLancarExcecaoAoSalvarRestauranteComNomeVazio() {

            // Arrange
            var restauranteDto = criarRestauranteDtoValido();
            restauranteDto.setNome("");

            // Assert
            assertThatThrownBy(() -> restauranteService.registrarRestaurante(restauranteDto))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Inconsistencia nos campos informados.");

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
            var restauranteId = 1L;
            var restauranteDto = criarRestauranteDtoValido();

            // Act
            restauranteService.registrarRestaurante(restauranteDto);
            restauranteDto.setNome("Restaurante Teste 2");

            // Assert
            assertThatCode(() -> restauranteService.registrarRestaurante(restauranteDto)).doesNotThrowAnyException();
        }

        @Test
        @Order(2)
        void deveLancarExcecaoAoAtualizarRestauranteInexistente() {

            // Arrange
            var restauranteId = 100L;
            var restauranteDto = criarRestauranteDtoValido();

            // Assert
            assertThatThrownBy(() -> restauranteService.atualizarRestaurante(restauranteId, restauranteDto))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Restaurante não foi encontrado");

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
            var restauranteId = 1L;
            var restauranteDto = criarRestauranteDtoValido();

            // Act
            restauranteService.registrarRestaurante(restauranteDto);

            // Assert
            assertThatCode(() -> restauranteService.deletarRestaurante(restauranteId)).doesNotThrowAnyException();

            // "org.springframework.dao.DataIntegrityViolationException: could not execute statement [ERROR: update or delete on table "tb_restaurante" violates foreign key constraint "fkfij8rbafgjtwm9knwe98wcut9" on table "tb_avaliacao"
            //  Detalhe: Key (id)=(1) is still referenced from table "tb_avaliacao".] [delete from tb_restaurante where id=?]; SQL [delete from tb_restaurante where id=?]; constraint [fkfij8rbafgjtwm9knwe98wcut9]

        }

        @Test
        @Order(2)
        void deveLancarExcecaoAoDeletarRestauranteInexistente() {

            // Arrange
            Long restauranteId = 100L; // ID inexistente

            // Assert
            assertThatThrownBy(() -> restauranteService.deletarRestaurante(restauranteId))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Restaurante com ID '100' não foi encontrado para exclusão.");

        }
    }

    @Nested
    @DisplayName("Testes de Busca de Restaurante")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class buscarRestaurante{
        @Test
        @Order(1)
        void devePermitirBuscarRestaurantePorNome() throws InterruptedException {

            // Arrange
            var restauranteDto = criarRestauranteDtoValido();

            // Act
            restauranteService.registrarRestaurante(restauranteDto);

            // Assert
            assertThatCode(() -> restauranteService.buscarRestaurantePor(restauranteDto.getNome(), null, null)).doesNotThrowAnyException();

        }
        @Test
        @Order(2)
        void devePermitirBuscarRestaurantePorLocalizacao() throws InterruptedException {

            // Arrange
            var restauranteDto = criarRestauranteDtoValido();

            // Act
            restauranteService.registrarRestaurante(restauranteDto);

            // Assert
            assertThatCode(() -> restauranteService.buscarRestaurantePor(null, restauranteDto.getLocalizacao(), null)).doesNotThrowAnyException();

        }
        @Test
        @Order(3)
        void devePermitirBuscarRestaurantePorTipoCozinha() throws InterruptedException {

            // Arrange
            var restauranteDto = criarRestauranteDtoValido();

            // Act
            restauranteService.registrarRestaurante(restauranteDto);

            // Assert
            assertThatCode(() -> restauranteService.buscarRestaurantePor(null, null , restauranteDto.getTipoCozinha())).doesNotThrowAnyException();

        }
        @Test
        @Order(4)
        void devePermitirBuscarRestaurantesPorId(){

            // Arrange
            var restauranteId = 1L;
            var restauranteDto = criarRestauranteDtoValido();

            // Act
            restauranteService.registrarRestaurante(restauranteDto);

            // Assert
            assertThatCode(() -> restauranteService.buscarRestaurantePorID(restauranteId)).doesNotThrowAnyException();

        }
        @Test
        @Order(5)
        void devePermitirBuscarTodosRestaurantes(){

            // Arrange
            var restauranteId = 1L;
            var restauranteDto = criarRestauranteDtoValido();
            var restaurante = criarRestauranteValido();

            // Act
            restauranteService.registrarRestaurante(restauranteDto);

            // Assert
            assertThatCode(() -> restauranteService.buscarTodosRestaurantes()).doesNotThrowAnyException();

        }
        @Test
        @Order(6)
        void deveLancarExcecaoAoBuscarNomeRestauranteInexistente() {

            // Arrange
            var restauranteNome = "Ifood"; // ID inexistente

            // Assert
            assertThatThrownBy(() -> restauranteService.buscarRestaurantePor(restauranteNome, null, null))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Restaurante com nome '"+restauranteNome+"' não foi encontrado.");
        }
        @Test
        @Order(7)
        void deveLancarExcecaoAoBuscarLocalizacaoRestauranteInexistente() {

            // Arrange
            var restauranteLocalizacao = "Rio de Janeiro";


            // Assert
            assertThatThrownBy(() -> restauranteService.buscarRestaurantePor(null, restauranteLocalizacao, null))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Restaurante com localização '"+restauranteLocalizacao+"' não foi encontrado.");
        }
        @Test
        @Order(8)
        void deveLancarExcecaoAoBuscarTipoCozinhaRestauranteInexistente() {

            // Arrange
            var restauranteTipoCozinha = "Francesa";

            // Assert
            assertThatThrownBy(() -> restauranteService.buscarRestaurantePor(null, null, restauranteTipoCozinha))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Restaurante com tipo de cozinha '"+restauranteTipoCozinha+"' não foi encontrado.");
        }
        @Test
        @Order(9)
        void deveLancarExcecaoAoBuscarTodosRestaurantes() {

            // Assert
            assertThat(restauranteService.buscarTodosRestaurantes()).isEmpty();
        }
    }
}
