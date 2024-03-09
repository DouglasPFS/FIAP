package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.controller.exception.ControllerNotFoundException;
import com.techchallenge.restaurant.api.findfood.dto.ReservaDTO;
import com.techchallenge.restaurant.api.findfood.entities.Reserva;
import com.techchallenge.restaurant.api.findfood.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repo;

    public Collection<ReservaDTO> findAll() {
        var reservas = repo.findAll();
        return reservas.stream()
                .map(this::toReservaDTO)
                .collect(Collectors.toList());
    }

    public ReservaDTO findById(Long id) {
        var reserva = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Reserva não encontrada!"));
        return toReservaDTO(reserva);
    }

    public ReservaDTO save(ReservaDTO reservaDTO) {
        Reserva reserva = toReserva(reservaDTO);
        reserva = repo.save(reserva);
        return toReservaDTO(reserva);
    }

    public ReservaDTO update(Long id, ReservaDTO reservaDTO) {

        try {
            Reserva buscaReserva = repo.getReferenceById(id);
            buscaReserva.setDataHora(reservaDTO.dataHora());
            buscaReserva.setQtdPessoa(reservaDTO.qtdPessoa());
            buscaReserva.setRestaurante(reservaDTO.restaurante());
            buscaReserva = repo.save(buscaReserva);
            return toReservaDTO(buscaReserva);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Reserva não encontrada!");
        }
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }


    private ReservaDTO toReservaDTO(Reserva reserva) {
        return new ReservaDTO(
                reserva.getId(),
                reserva.getDataHora(),
                reserva.getQtdPessoa(),
                reserva.getRestaurante()

        );
    }

    private Reserva toReserva(ReservaDTO reservaDTO){
        return new Reserva(
                reservaDTO.id(),
                reservaDTO.dataHora(),
                reservaDTO.qtdPessoa(),
                reservaDTO.restaurante()

        );
    }


}
