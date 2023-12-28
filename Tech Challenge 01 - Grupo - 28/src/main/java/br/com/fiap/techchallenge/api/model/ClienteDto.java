package br.com.fiap.techchallenge.api.model;

import br.com.fiap.techchallenge.domain.model.Cliente;
import br.com.fiap.techchallenge.domain.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
public class ClienteDto {

    private String cnpj;

    private String razaoSocial;

    private String nomeFantasia;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataFundacao;

    private Endereco endereco;

    private String email;

    private String nomeResponsavel;

    private String telefonePrincipal;

    private String telefoneSecundario;

    public Cliente toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Cliente.class);
    }
}
