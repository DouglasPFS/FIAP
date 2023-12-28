package br.com.fiap.techchallenge.domain.model;

import br.com.fiap.techchallenge.domain.enums.Estado;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


@Data
@Entity
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 200)
    @NotNull(value = "Logradouro não foi preenchido")
    private String logradouro;

    @Column(nullable = false, length = 100)
    @NotNull(value = "Bairro não foi preenchido")
    private String bairro;

    @Column(nullable = false, length = 19)
    @NotNull(value = "Estado não foi preenchido")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(nullable = false, length = 100)
    @NotNull(value = "Estado não foi preenchido")
    private String cidade;

    @Column(nullable = false, length = 15)
    @NotNull(value = "Cep não foi preenchido")
    private String cep;

    public String getEstado(){
        return this.estado.getSigla();
    }

}
