package com.techchallenge.restaurant.api.findfood.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dataHora;

    private Long horasSolicitadas;

    private Long qtdPessoa;

    @ManyToOne
    private Restaurante restaurante;

    public Reserva() {
    }

    public Reserva(Long id, LocalDateTime dataHora, Long horasSolicitadas, Long qtdPessoa, Restaurante restaurante) {
        this.id = id;
        this.dataHora = dataHora;
        this.horasSolicitadas = horasSolicitadas;
        this.qtdPessoa = qtdPessoa;
        this.restaurante = restaurante;
    }

    public Long getHorasSolicitadas() {
        return horasSolicitadas;
    }

    public void setHorasSolicitadas(Long horasSolicitadas) {
        this.horasSolicitadas = horasSolicitadas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getQtdPessoa() {
        return qtdPessoa;
    }

    public void setQtdPessoa(Long qtdPessoa) {
        this.qtdPessoa = qtdPessoa;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
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
                ", horasSolicitadas=" + horasSolicitadas +
                ", qtdPessoa=" + qtdPessoa +
                ", restaurante=" + restaurante +
                '}';
    }
}
