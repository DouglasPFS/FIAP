package com.techchallenge.restaurant.api.findfood.dto;

import com.techchallenge.restaurant.api.findfood.entities.Reserva;

public record AvaliacaoDTO(Long id,

                           int pontuacao,

                           String comentario,

                           Reserva reserva

) {

}
