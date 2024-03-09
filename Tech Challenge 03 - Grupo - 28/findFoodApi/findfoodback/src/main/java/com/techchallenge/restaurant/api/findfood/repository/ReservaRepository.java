package com.techchallenge.restaurant.api.findfood.repository;

import com.techchallenge.restaurant.api.findfood.entities.Reserva;
import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByRestauranteAndTimeRange(Restaurante restaurante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);
}
