package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.dto.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import com.techchallenge.restaurant.api.findfood.repository.RestauranteRepository;
import com.techchallenge.restaurant.api.findfood.utils.RestauranteFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class RestauranteServiceIT {

    private final ModelMapper modelMapper = new ModelMapper();
    @Mock
    private RestauranteRepository restauranteRepository;
    @InjectMocks
    private RestauranteServiceInpl restauranteService;
    AutoCloseable mock;
    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        restauranteService = new RestauranteServiceInpl(restauranteRepository);
    }
    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }
    @Test
    void devePermitirRegistrarRestaurante(){

        // Arrange
        RestauranteDTO restauranteDto = RestauranteFixture.criarRestauranteDtoValido();
        Restaurante restaurante = modelMapper.map(restauranteDto, Restaurante.class);  // Use the initialized modelMapper
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);

        //Act
        var restauranteRegistrado = restauranteService.save(restauranteDto);

        // Assert
        assertThat(restauranteRegistrado)
            .isInstanceOf(RestauranteDTO.class)
            .isNotNull();
    }

}
