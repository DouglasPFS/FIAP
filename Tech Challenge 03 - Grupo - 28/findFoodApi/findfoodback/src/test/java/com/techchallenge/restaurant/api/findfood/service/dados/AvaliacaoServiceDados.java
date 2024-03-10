package com.techchallenge.restaurant.api.findfood.service.dados;

import com.techchallenge.restaurant.api.findfood.entities.Restaurante;

public class AvaliacaoServiceDados {

    public Restaurante criarRestaurante(){
        return Restaurante.builder()
                .id(1L)
                .nome("Outback São Paulo")
                .localizacao("Avenida Paulista")
                .horarioFuncionamento("18h00 Até 23h00")
                .tipoCozinha("Carnes")
                .build();
    }
}
