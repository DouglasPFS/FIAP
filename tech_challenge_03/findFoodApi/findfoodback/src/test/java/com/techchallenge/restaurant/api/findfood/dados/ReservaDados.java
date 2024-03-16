package com.techchallenge.restaurant.api.findfood.dados;

import com.techchallenge.restaurant.api.findfood.api.model.ReservaDTO;

import java.time.LocalDateTime;

public class ReservaDados {

    public ReservaDTO criarReservaDto(){
        return ReservaDTO.builder()
                .id(1L)
                .nomeCliente("João da Silva")
                .emailCliente("joao.silva@gmail.com")
                .dataHoraInicio(LocalDateTime.now())
                .dataHoraFim(LocalDateTime.now().plusHours(2))
                .telefoneCliente("15 99332-3456")
                .qtdPessoas(5)
                .build();
    }
}
