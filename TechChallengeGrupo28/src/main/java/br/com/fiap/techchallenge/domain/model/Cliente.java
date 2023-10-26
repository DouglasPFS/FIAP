package br.com.fiap.techchallenge.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Cliente {

    @Column
    @Id
    private String cnpj;

    @Column
    private String razaoSocial;

    @Column
    private String nomeFantasia;

    @Column
    private LocalDate dataFundacao;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column
    private String email;

    @Column
    private String nomeResponsavel;

    @Column
    private String telefonePrincipal;

    @Column
    private String telefoneSecundario;

    @Column
    private final LocalDateTime dataCadastro = LocalDateTime.now();

}
