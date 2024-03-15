package com.techchallenge.restaurant.api.findfood.domain.service;

import com.techchallenge.restaurant.api.findfood.api.model.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.domain.model.Restaurante;
import com.techchallenge.restaurant.api.findfood.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final ModelMapper modelMapper;

    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = restauranteRepository.save(modelMapper.map(restauranteDTO, Restaurante.class));
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<Restaurante> findRestaurantePorNomeOuLocalizacaoOuTipoDeCozinha(String nome, String localizacao, String tipoDeCozinha) {
        return restauranteRepository.findByNomeIgnoreCaseLikeOrLocalizacaoIgnoreCaseLikeOrTipoCozinhaIgnoreCaseLike(nome, localizacao, tipoDeCozinha);
    }

    public Collection<RestauranteDTO> findAll() {
        return restauranteRepository.findAll().stream()
                .map(restaurante -> modelMapper.map(restaurante, RestauranteDTO.class))
                .toList();
    }

    public RestauranteDTO update(Long restauranteId, RestauranteDTO restauranteDTO) {
        Optional<Restaurante> optionalRestaurante = restauranteRepository.findById(restauranteId);

        if(optionalRestaurante.isPresent()) {
            Restaurante restaurante = optionalRestaurante.get();
            modelMapper.map(restauranteDTO, restaurante);

            restaurante = restauranteRepository.save(restaurante);

            return modelMapper.map(restaurante, RestauranteDTO.class);
        } else {
            throw new EntityNotFoundException("Restaurante não foi encontrado");
        }
    }


    public void delete(Long id) {
        restauranteRepository.deleteById(id);
    }

}
