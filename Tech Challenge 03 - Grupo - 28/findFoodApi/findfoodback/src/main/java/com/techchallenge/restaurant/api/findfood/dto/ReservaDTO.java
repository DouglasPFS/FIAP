package com.techchallenge.restaurant.api.findfood.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ReservaDTO {

    private Long id;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private Integer qtdPessoas;

}
