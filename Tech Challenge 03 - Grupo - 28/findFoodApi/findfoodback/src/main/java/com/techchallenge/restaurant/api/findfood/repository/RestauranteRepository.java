package com.techchallenge.restaurant.api.findfood.repository;

import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByNomeIgnoreCaseOrLocalizacaoIgnoreCaseOrTipoCozinhaIgnoreCase(String nome,
                                                                                      String tipoCozinha,
                                                                                      String localizacao);
}
