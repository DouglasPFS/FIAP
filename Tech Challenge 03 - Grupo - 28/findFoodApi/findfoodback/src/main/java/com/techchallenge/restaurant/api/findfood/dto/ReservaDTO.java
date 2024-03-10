package com.techchallenge.restaurant.api.findfood.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ReservaDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHoraInicio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHoraFim;

    private Integer qtdPessoas;

    private String nomeCliente;

    private String emailCliente;

    private String telefoneCliente;

}
