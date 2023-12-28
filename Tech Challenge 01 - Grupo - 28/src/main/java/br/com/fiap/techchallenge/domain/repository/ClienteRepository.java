package br.com.fiap.techchallenge.domain.repository;

import br.com.fiap.techchallenge.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
