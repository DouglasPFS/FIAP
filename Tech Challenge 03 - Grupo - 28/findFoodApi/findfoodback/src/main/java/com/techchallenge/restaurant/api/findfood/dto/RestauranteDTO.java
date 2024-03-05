package com.techchallenge.restaurant.api.findfood.dto;

public record RestauranteDTO(Long id,

                             String nomeRestaurante,
                             String localizacao,

                             String tipoCozinha,

                             String horarioFuncionamento,
                             int capacidade

) {

}
