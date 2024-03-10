package com.techchallenge.restaurant.api.findfood.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reserva")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private Integer qtdPessoas;

    @ManyToOne
    private Restaurante restaurante;

    private String nomeCliente;

    private String emailCliente;

    private String telefoneCliente;

}
