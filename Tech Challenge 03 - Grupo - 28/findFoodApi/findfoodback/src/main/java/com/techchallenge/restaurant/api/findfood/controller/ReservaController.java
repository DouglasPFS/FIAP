package com.techchallenge.restaurant.api.findfood.controller;


import com.techchallenge.restaurant.api.findfood.dto.ReservaDTO;
import com.techchallenge.restaurant.api.findfood.dto.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<Collection<ReservaDTO>> findAll(){
        var reservas = service.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Long id){
        var reserva = service.findById(id);
        return ResponseEntity.ok(reserva);
    }


    @PostMapping
    public ResponseEntity<ReservaDTO> save(@RequestBody ReservaDTO reservaDTO) {
        reservaDTO = service.save(reservaDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(reservaDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> update(@PathVariable Long id,
                                             @RequestBody ReservaDTO reservaDTO){
        reservaDTO = service.update(id, reservaDTO);
        return ResponseEntity.ok(reservaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
