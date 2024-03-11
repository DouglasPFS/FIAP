package com.techchallenge.restaurant.api.findfood.domain.repository;

import com.techchallenge.restaurant.api.findfood.domain.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
