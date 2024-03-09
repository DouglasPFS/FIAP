package com.techchallenge.restaurant.api.findfood.controller;


import com.techchallenge.restaurant.api.findfood.dto.AvaliacaoDTO;
import com.techchallenge.restaurant.api.findfood.dto.ReservaDTO;
import com.techchallenge.restaurant.api.findfood.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @GetMapping
    public ResponseEntity<Collection<AvaliacaoDTO>> findAll(){
        var avaliacoes = service.findAll();
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long id){
        var avaliacao = service.findById(id);
        return ResponseEntity.ok(avaliacao);
    }


    @PostMapping
    public ResponseEntity<AvaliacaoDTO> save(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        avaliacaoDTO = service.save(avaliacaoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(avaliacaoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> update(@PathVariable Long id,
                                             @RequestBody AvaliacaoDTO avaliacaoDTO){
        avaliacaoDTO = service.update(id, avaliacaoDTO);
        return ResponseEntity.ok(avaliacaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
