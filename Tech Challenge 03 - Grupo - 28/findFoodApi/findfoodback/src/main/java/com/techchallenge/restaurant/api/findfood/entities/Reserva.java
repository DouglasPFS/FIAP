package com.techchallenge.restaurant.api.findfood.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_reserva")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dataHora;

    private Long qtdPessoa;

    @ManyToOne
    private Restaurante restaurante;

    public Reserva() {
    }

    public Reserva(Long id, LocalDateTime dataHora, Long qtdPessoa, Restaurante restaurante) {
        this.id = id;
        this.dataHora = dataHora;
        this.qtdPessoa = qtdPessoa;
        this.restaurante = restaurante;
    }

      @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", qtdPessoa=" + qtdPessoa +
                ", restaurante=" + restaurante +
                '}';
    }

}
