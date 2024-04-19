package br.com.fiap.techchallenge.domain.service;

import br.com.fiap.techchallenge.domain.model.Cliente;
import br.com.fiap.techchallenge.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    public Cliente getClientePeloCNPJ(String cnpj){
        return clienteRepository
                .findById(cnpj)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public Cliente createCliente(Cliente cliente) {
        if (clienteRepository.findById(cliente.getCnpj()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cliente já existe na base de dados");
        }else{
            return clienteRepository.save(cliente);
        }
    }

    public Cliente updateCliente(Cliente cliente){
        if (clienteRepository.findById(cliente.getCnpj()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }else{
            return clienteRepository.save(cliente);
        }
    }
}
