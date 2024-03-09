package com.techchallenge.restaurant.api.findfood.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Entity
@Table(name = "tb_restaurante")
@Getter
@Setter
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeRestaurante;
    private String localizacao;

    private String tipoCozinha;

    private String horarioFuncionamento;

    private int capacidade;


    public Restaurante(Long id, String nomeRestaurante, String localizacao, String tipoCozinha, String horarioFuncionamento, int capacidade) {
        this.id = id;
        this.nomeRestaurante = nomeRestaurante;
        this.localizacao = localizacao;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.capacidade = capacidade;
    }

    public Restaurante() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nomeRestaurante='" + nomeRestaurante + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", tipoCozinha='" + tipoCozinha + '\'' +
                ", horarioFuncionamento='" + horarioFuncionamento + '\'' +
                ", capacidade=" + capacidade +
                '}';
    }
}
