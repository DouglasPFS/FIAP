package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.controller.exception.ControllerNotFoundException;
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
        int qtdeTotalDeMesas = reserva.getRestaurante().getQuantidadeTotalDeMesas();
        int capacidadeMaximaDePessoas = qtdeTotalDeMesas * 4;

        if (reserva.getQtdPessoas() > capacidadeMaximaDePessoas) {
            return false;
        }

        List<Reserva> listaDeReservas = reservaRepository.findByRestauranteAndTimeRange(reserva.getRestaurante(), reserva.getDataHoraInicio(), reserva.getDataHoraFim());
        int totalDePessoasReservadas = listaDeReservas.stream()
                .mapToInt(Reserva::getQtdPessoas)
                .sum();

        int capacidadeDisponivel = capacidadeMaximaDePessoas - totalDePessoasReservadas;
        return capacidadeDisponivel >= reserva.getQtdPessoas();
    }

    // TODO MÉTODOS FINALIZADOS ESTÃO ACIMA


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
                reserva.getQtdPessoa()
                //reserva.getRestaurante()

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
