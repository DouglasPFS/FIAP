package com.techchallenge.restaurant.api.findfood.repository;

import com.techchallenge.restaurant.api.findfood.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
