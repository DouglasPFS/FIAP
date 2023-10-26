package br.com.fiap.techchallenge.domain.model;

import br.com.fiap.techchallenge.domain.enums.Estado;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String logradouro;

    @Column
    private String bairro;

    @Column
    private Estado estado;

    @Column
    private String cidade;

    @Column
    private String cep;
}
