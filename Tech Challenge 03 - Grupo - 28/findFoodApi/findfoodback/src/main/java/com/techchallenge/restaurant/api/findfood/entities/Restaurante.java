package com.techchallenge.restaurant.api.findfood.entities;

import jakarta.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "tb_restaurante")
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

    public Restaurante(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
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
