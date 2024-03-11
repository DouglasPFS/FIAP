package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.dto.AvaliacaoDTO;
import com.techchallenge.restaurant.api.findfood.entities.Avaliacao;
import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import com.techchallenge.restaurant.api.findfood.repository.AvaliacaoRepository;
import com.techchallenge.restaurant.api.findfood.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void registrarAvaliacao(Long restauranteId, AvaliacaoDTO avaliacaoDTO) {
        Optional<Restaurante> optionalRestaurante = restauranteRepository.findById(restauranteId);
        Avaliacao avaliacao = modelMapper.map(avaliacaoDTO, Avaliacao.class);

        if(optionalRestaurante.isEmpty()){
            throw new EntityNotFoundException("Restaurante não foi encontrada");
        }

        if(avaliacao.getPontuacao() < 0 || avaliacao.getPontuacao() > 5){
            throw new IllegalArgumentException("Pontuação para a reserva precisa ser um valor de 0 a 5");
        }

        avaliacao.setRestaurante(optionalRestaurante.get());
        avaliacaoRepository.save(avaliacao);
    }


    public Collection<AvaliacaoDTO> findAll() {
        return avaliacaoRepository.findAll()
                .stream()
                .map(avaliacao -> modelMapper.map(avaliacao, AvaliacaoDTO.class))
                .collect(Collectors.toList());
    }

}
