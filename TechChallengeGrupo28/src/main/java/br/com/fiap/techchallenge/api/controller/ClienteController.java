package br.com.fiap.techchallenge.api.controller;

import br.com.fiap.techchallenge.api.model.ClienteDto;
import br.com.fiap.techchallenge.domain.model.Cliente;
import br.com.fiap.techchallenge.domain.service.ClienteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{cnpj}")
    public Cliente getClientePorCNPJ(@PathVariable("cnpj") String cnpj) {
        return clienteService.getClientePeloCNPJ(cnpj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente addCliente(@RequestBody @NotNull ClienteDto clienteDto) {
         return clienteService.createCliente(clienteDto.toEntity());
    }

    @PutMapping("/{cnpj}")
    public Cliente updateCliente(@RequestBody @NotNull ClienteDto clienteDto, @PathVariable("cnpj") String cnpj){
        return clienteService.updateCliente(clienteDto.toEntity());
    }


}
