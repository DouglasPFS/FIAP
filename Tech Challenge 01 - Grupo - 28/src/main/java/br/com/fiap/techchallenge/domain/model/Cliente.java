package br.com.fiap.techchallenge.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Cliente {

    @Column(nullable = false, length = 14)
    @Id
    private String cnpj;

    @Column(nullable = false, length = 150)
    @NotNull(value = "Razão Social não foi preenchida")
    private String razaoSocial;

    @Column(nullable = true, length = 150)
    private String nomeFantasia;

    @Column(nullable = false)
    @NotNull(value = "Data da fundação não foi preenchida")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataFundacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(nullable = false, length = 60)
    @NotNull(value = "E-mail não foi preenchido")
    private String email;

    @Column(nullable = false, length = 60)
    @NotNull(value = "Nome do responsável não foi preenchido")
    private String nomeResponsavel;

    @Column(nullable = false, length = 60)
    @NotNull(value = "Telefone Principal não foi preenchido")
    private String telefonePrincipal;

    @Column(nullable = true, length = 60)
    private String telefoneSecundario;

    @Column
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime dataCadastro = LocalDateTime.now();

}
