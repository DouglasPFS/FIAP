package com.techchallenge.restaurant.api.findfood.service;

import com.techchallenge.restaurant.api.findfood.controller.exception.ControllerNotFoundException;
import com.techchallenge.restaurant.api.findfood.dto.AvaliacaoDTO;
import com.techchallenge.restaurant.api.findfood.entities.Avaliacao;
import com.techchallenge.restaurant.api.findfood.repository.AvaliacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repo;

    public Collection<AvaliacaoDTO> findAll() {
        var avaliacoes = repo.findAll();
        return avaliacoes.stream()
                .map(this::toAvaliacaoDTO)
                .collect(Collectors.toList());
    }

    public AvaliacaoDTO findById(Long id) {
        var avaliacao = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Avaliacao não encontrada!"));
        return toAvaliacaoDTO(avaliacao);
    }

    public AvaliacaoDTO save(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = toAvaliacao(avaliacaoDTO);
        avaliacao = repo.save(avaliacao);
        return toAvaliacaoDTO(avaliacao);
    }

    public AvaliacaoDTO update(Long id, AvaliacaoDTO avaliacaoDTO) {

        try {
            Avaliacao buscaAvaliacao = repo.getReferenceById(id);
            buscaAvaliacao.setPontuacao(avaliacaoDTO.pontuacao());
            buscaAvaliacao.setComentario(avaliacaoDTO.comentario());
            buscaAvaliacao.setReserva(avaliacaoDTO.reserva());
            buscaAvaliacao = repo.save(buscaAvaliacao);
            return toAvaliacaoDTO(buscaAvaliacao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Avaliação não encontrada!");
        }
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }


    private AvaliacaoDTO toAvaliacaoDTO(Avaliacao avaliacao) {
        return new AvaliacaoDTO(
                avaliacao.getId(),
                avaliacao.getPontuacao(),
                avaliacao.getComentario(),
                avaliacao.getReserva()
        );
    }

    private Avaliacao toAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        return new Avaliacao(
                avaliacaoDTO.id(),
                avaliacaoDTO.pontuacao(),
                avaliacaoDTO.comentario(),
                avaliacaoDTO.reserva()
        );
    }


}
