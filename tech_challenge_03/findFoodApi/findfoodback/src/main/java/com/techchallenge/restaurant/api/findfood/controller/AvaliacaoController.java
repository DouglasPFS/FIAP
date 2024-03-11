package com.techchallenge.restaurant.api.findfood.controller;

import com.techchallenge.restaurant.api.findfood.dto.AvaliacaoDTO;
import com.techchallenge.restaurant.api.findfood.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/restaurantes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @PostMapping("/{restauranteId}/avaliar")
    public ResponseEntity<String> registrarAvaliacao(@PathVariable Long restauranteId, @RequestBody AvaliacaoDTO avaliacaoDTO) {
        service.registrarAvaliacao(restauranteId, avaliacaoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Avaliação feita com sucesso");
    }

    @GetMapping("/{restauranteId}/avaliacoes")
    public ResponseEntity<Collection<AvaliacaoDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

}
