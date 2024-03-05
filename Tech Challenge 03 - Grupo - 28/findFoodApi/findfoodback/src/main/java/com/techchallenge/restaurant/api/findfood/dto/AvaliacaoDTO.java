package com.techchallenge.restaurant.api.findfood.dto;

import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import jakarta.persistence.ManyToOne;

public record AvaliacaoDTO(Long id,

                           int pontuacao,

                           String comentario,

                           Restaurante restaurante

) {

}
