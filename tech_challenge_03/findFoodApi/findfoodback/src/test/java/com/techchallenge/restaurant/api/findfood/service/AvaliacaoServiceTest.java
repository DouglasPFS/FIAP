package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.dto.AvaliacaoDTO;
import com.techchallenge.restaurant.api.findfood.entities.Avaliacao;
import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import com.techchallenge.restaurant.api.findfood.repository.AvaliacaoRepository;
import com.techchallenge.restaurant.api.findfood.repository.RestauranteRepository;
import com.techchallenge.restaurant.api.findfood.service.dados.AvaliacaoServiceDados;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AvaliacaoServiceTest extends AvaliacaoServiceDados {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarAvaliacao() {
        Long restauranteId = 1L;
        AvaliacaoDTO avaliacaoDTO = criarAvaliacaoDto();
        Restaurante restaurante = criarRestaurante();

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));
        when(modelMapper.map(avaliacaoDTO, Avaliacao.class)).thenReturn(criarAvaliacao());

        avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO);

        verify(avaliacaoRepository, times(1)).save(any());
    }

    @Test
    void testRegistrarAvaliacaoRestauranteNaoEncontrado() {
        Long restauranteId = 1L;
        AvaliacaoDTO avaliacaoDTO = criarAvaliacaoDto();

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO));

        verify(avaliacaoRepository, never()).save(any());
    }

    @Test
    void testRegistrarAvaliacaoPontuacaoInvalidaAcimade5() {
        Long restauranteId = 1L;
        AvaliacaoDTO avaliacaoDTO = criarAvaliacaoDto();

        Restaurante restaurante = criarRestaurante();
        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));
        when(modelMapper.map(avaliacaoDTO, Avaliacao.class)).thenReturn(criarAvaliacaoComPontuacaoInvalidaAcima5());

        assertThrows(IllegalArgumentException.class, () -> avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO));

        verify(avaliacaoRepository, never()).save(any());
    }

    @Test
    void testRegistrarAvaliacaoPontuacaoInvalidaAbaixoDe0() {
        Long restauranteId = 1L;
        AvaliacaoDTO avaliacaoDTO = criarAvaliacaoDto();
        avaliacaoDTO.setPontuacao(6);

        Restaurante restaurante = criarRestaurante();
        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));
        when(modelMapper.map(avaliacaoDTO, Avaliacao.class)).thenReturn(criarAvaliacaoComPontuacaoInvalidaAbixo0());

        assertThrows(IllegalArgumentException.class, () -> avaliacaoService.registrarAvaliacao(restauranteId, avaliacaoDTO));

        verify(avaliacaoRepository, never()).save(any());
    }

    @Test
    void testFindAll() {
        when(avaliacaoRepository.findAll()).thenReturn(Collections.emptyList());

        Collection<AvaliacaoDTO> result = avaliacaoService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
