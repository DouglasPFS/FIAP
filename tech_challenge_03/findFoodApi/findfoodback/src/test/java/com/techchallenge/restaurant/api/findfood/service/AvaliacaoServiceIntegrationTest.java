package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.domain.repository.AvaliacaoRepository;
import com.techchallenge.restaurant.api.findfood.domain.service.AvaliacaoService;
import com.techchallenge.restaurant.api.findfood.service.dados.AvaliacaoServiceDados;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AvaliacaoServiceIntegrationTest extends AvaliacaoServiceDados {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    void testRegistrarAvaliacao() {
        var restauranteId = 1L;
        var avaliacaoDTO = criarAvaliacaoDto();

        var contador = avaliacaoRepository.count();
        avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO);
        var contadorAtualizado = avaliacaoRepository.count();

        assertThat(contador + 1).isEqualTo(contadorAtualizado);
    }

    @Test
    void testRegistrarAvaliacaoRestauranteNaoEncontrado() {
        var restauranteId = 0L;
        var avaliacaoDTO = criarAvaliacaoDto();

        assertThatThrownBy(() -> avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Restaurante não foi encontrada");
    }

    @Test
    void testRegistrarAvaliacaoPontuacaoInvalidaAcimade5() {
        var restauranteId = 1L;
        var avaliacaoDTO = criarAvaliacaoDto();
        avaliacaoDTO.setPontuacao(6);

        assertThatThrownBy(() -> avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pontuação para a reserva precisa ser um valor de 0 a 5");
    }

    @Test
    void testRegistrarAvaliacaoPontuacaoInvalidaAbaixoDe0() {
        var restauranteId = 1L;
        var avaliacaoDTO = criarAvaliacaoDto();
        avaliacaoDTO.setPontuacao(-1);

        assertThrows(IllegalArgumentException.class, () -> avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO));
        assertThatThrownBy(() -> avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pontuação para a reserva precisa ser um valor de 0 a 5");
    }

    @Test
    @Order(2)
    void testFindAll() {
        var result = avaliacaoService.findAll();

        assertThat(result).isNotEmpty();
    }
}
