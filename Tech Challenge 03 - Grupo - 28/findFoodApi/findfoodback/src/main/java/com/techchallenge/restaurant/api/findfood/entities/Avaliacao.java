package com.techchallenge.restaurant.api.findfood.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

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

    @OneToOne
    private Reserva reserva;


    public Avaliacao(Long id, int pontuacao, String comentario, Reserva reserva) {
        this.id = id;
        this.pontuacao = pontuacao;
        this.comentario = comentario;
        this.reserva = reserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", pontuacao=" + pontuacao +
                ", comentario='" + comentario + '\'' +
                ", reserva=" + reserva +
                '}';
    }
}

