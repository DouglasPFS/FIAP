package com.techchallenge.restaurant.api.findfood.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class AvaliacaoDTO {

    @NotNull(value = "A pontuação não foi preenchida")
    private Integer pontuacao;

    @NotNull(value = "O comentário não foi preenchido")
    private String comentario;

    @NotNull(value = "O Nome do Cliente não foi preenchido")
    private String nomeCliente;

    private String emailCliente;

    private String telefoneCliente;
}