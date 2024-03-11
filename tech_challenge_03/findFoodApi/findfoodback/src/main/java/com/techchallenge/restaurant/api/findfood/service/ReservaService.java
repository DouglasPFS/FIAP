package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.dto.ReservaDTO;
import com.techchallenge.restaurant.api.findfood.entities.Reserva;
import com.techchallenge.restaurant.api.findfood.entities.Restaurante;
import com.techchallenge.restaurant.api.findfood.repository.ReservaRepository;
import com.techchallenge.restaurant.api.findfood.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ReservaDTO reservarMesa(Long restauranteId, ReservaDTO reservaDTO){
        Optional<Restaurante> optionalRestaurante = restauranteRepository.findById(restauranteId);
        if (optionalRestaurante.isEmpty()) {
            throw new EntityNotFoundException("Restaurante não existe");
        }

        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        reserva.setRestaurante(optionalRestaurante.get());

        if(haMesasDisponiveis(reserva)){
            return modelMapper.map(reservaRepository.save(reserva), ReservaDTO.class);
        } else {
            throw new IllegalArgumentException("Não há lugares disponíveis nesse horário para o Restaurante: " + optionalRestaurante.get().getNome());
        }
    }

    private boolean haMesasDisponiveis(Reserva reserva) {
        final int qtdePessoasPorMesa = 4;
        int qtdeTotalDeMesas = reserva.getRestaurante().getQuantidadeTotalDeMesas();

        List<Reserva> listaDeReservas = reservaRepository.findReservasNoIntervaloDaNovaReservaSolicitada(reserva.getRestaurante(), reserva.getDataHoraInicio(), reserva.getDataHoraFim());
        int totalDeMesasReservadas = listaDeReservas.stream()
                .mapToInt(value -> (int) Math.ceil((double)  value.getQtdPessoas() / qtdePessoasPorMesa))
                .sum();

        int qtdeTotalMesasLivres = qtdeTotalDeMesas - totalDeMesasReservadas;
        int mesasNecesariasParaReserva = (int) Math.ceil((double)  reserva.getQtdPessoas() / qtdePessoasPorMesa);

        return qtdeTotalMesasLivres >= mesasNecesariasParaReserva;
    }

    public Collection<ReservaDTO> findAll() {
        return reservaRepository.findAll().stream()
                .map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
                .collect(Collectors.toList());
    }

    public ReservaDTO findById(Long id) {
        var reserva = reservaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada!"));
        return modelMapper.map(reserva, ReservaDTO.class);
    }

    public void delete(Long id) {
        reservaRepository.deleteById(id);
    }

}
