package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.controller.exception.ControllerNotFoundException;
import com.techchallenge.restaurant.api.findfood.dto.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import com.techchallenge.restaurant.api.findfood.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = restauranteRepository.save(modelMapper.map(restauranteDTO, Restaurante.class));
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<Restaurante> findRestaurantePorNomeOuLocalizacaoOuTipoDeCozinha(String nome, String localizacao, String tipoDeCozinha) {
        return restauranteRepository.findByNomeIgnoreCaseOrLocalizacaoIgnoreCaseOrTipoCozinhaIgnoreCase(nome, localizacao, tipoDeCozinha);
    }

    // TODO MÉTODOS FINALIZADOS ESTÃO ACIMA

    public Collection<RestauranteDTO> findAll() {
        var restaurantes = repo.findAll();
        return restaurantes.stream()
                .map(this::toRestauranteDTO)
                .collect(Collectors.toList());
    }

    public RestauranteDTO findById(Long id) {
        var restaurante = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Restaurante não encontrado"));
        return toRestauranteDTO(restaurante);
    }

    public RestauranteDTO update(Long id, RestauranteDTO restauranteDTO) {

        try {
            Restaurante buscaRestaurante = repo.getReferenceById(id);
            buscaRestaurante.setNomeRestaurante(restauranteDTO.nomeRestaurante());
            buscaRestaurante.setCapacidade(restauranteDTO.capacidade());
            buscaRestaurante.setLocalizacao(restauranteDTO.localizacao());
            buscaRestaurante.setHorarioFuncionamento(restauranteDTO.horarioFuncionamento());
            buscaRestaurante.setTipoCozinha(restauranteDTO.tipoCozinha());
            buscaRestaurante = repo.save(buscaRestaurante);
            return toRestauranteDTO(buscaRestaurante);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Restaurante não encontrado");
        }
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }

}
