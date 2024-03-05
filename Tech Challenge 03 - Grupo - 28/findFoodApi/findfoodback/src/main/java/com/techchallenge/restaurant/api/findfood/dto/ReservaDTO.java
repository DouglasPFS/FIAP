package com.techchallenge.restaurant.api.findfood.dto;

import com.techchallenge.restaurant.api.findfood.entities.Restaurante;

import java.time.LocalDateTime;

public record ReservaDTO(Long id,

                         LocalDateTime dataHora,

                         Long horaSolicitadas,

                         Long qtdPessoa,


                         Restaurante restaurante) {

}
