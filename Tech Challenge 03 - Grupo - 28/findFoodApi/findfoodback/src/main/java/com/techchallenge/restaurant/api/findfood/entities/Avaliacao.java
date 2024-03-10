package com.techchallenge.restaurant.api.findfood.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_avaliacao")
@Getter
@Setter
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int pontuacao;

    private String comentario;

    @ManyToOne
    private Restaurante restaurante;

    private String nomeCliente;

    private String emailCliente;

    private String telefoneCliente;

}

