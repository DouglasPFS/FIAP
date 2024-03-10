package com.techchallenge.restaurant.api.findfood.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class RestauranteDTO {

    private Long id;

    @NotNull(value = "O nome não foi preenchido")
    private String nome;

    @NotNull(value = "A localizacao não foi preenchida")
    private String localizacao;

    @NotNull(value = "O tipoCozinha não foi preenchido")
    private String tipoCozinha;

    @NotNull(value = "O horarioFuncionamento não foi preenchido")
    private String horarioFuncionamento;

    @NotNull(value = "A quantidadeTotalDeMesas não foi preenchida")
    private Integer quantidadeTotalDeMesas;

}

