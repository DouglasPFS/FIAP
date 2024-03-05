package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.controller.exception.ControllerNotFoundException;
import com.techchallenge.restaurant.api.findfood.dto.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import com.techchallenge.restaurant.api.findfood.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repo;

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

    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = toRestaurante(restauranteDTO);
        restaurante = repo.save(restaurante);
        return toRestauranteDTO(restaurante);
    }

    public RestauranteDTO update(Long id, RestauranteDTO restauranteDTO) {

        try {
            Restaurante buscaRestaurante = repo.getReferenceById(id);
            buscaRestaurante.setNomeRestaurante(restauranteDTO.nomeRestaurante());
            buscaRestaurante.setTipoCozinha(restauranteDTO.tipoCozinha());
            buscaRestaurante.setLocalizacao(restauranteDTO.localizacao());
            buscaRestaurante.setHorarioFuncionamento(restauranteDTO.horarioFuncionamento());
            buscaRestaurante.setCapacidade(restauranteDTO.capacidade());
            buscaRestaurante = repo.save(buscaRestaurante);
            return toRestauranteDTO(buscaRestaurante);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Restaurante não encontrado");
        }
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }


    private RestauranteDTO toRestauranteDTO(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getNomeRestaurante(),
                restaurante.getTipoCozinha(),
                restaurante.getLocalizacao(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getCapacidade()

        );
    }


    private Restaurante toRestaurante(RestauranteDTO restauranteDTO){
        return new Restaurante(
                restauranteDTO.id(),
                restauranteDTO.nomeRestaurante(),
                restauranteDTO.tipoCozinha(),
                restauranteDTO.localizacao(),
                restauranteDTO.horarioFuncionamento(),
                restauranteDTO.capacidade()



        );
    }


}
