package com.techchallenge.restaurant.api.findfood.repository;

import com.techchallenge.restaurant.api.findfood.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
