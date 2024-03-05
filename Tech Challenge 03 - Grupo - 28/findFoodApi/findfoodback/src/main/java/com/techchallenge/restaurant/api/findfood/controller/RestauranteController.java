package com.techchallenge.restaurant.api.findfood.controller;


import com.techchallenge.restaurant.api.findfood.dto.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.service.RestauranteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService service;

    @GetMapping
    public ResponseEntity<Collection<RestauranteDTO>> findAll(){
        var restaurantes = service.findAll();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> findById(@PathVariable Long id){
        var restaurante = service.findById(id);
        return ResponseEntity.ok(restaurante);
    }


    @PostMapping
    public ResponseEntity<RestauranteDTO> save(@RequestBody RestauranteDTO restauranteDTO) {
        restauranteDTO = service.save(restauranteDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(restauranteDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> update(@PathVariable Long id,
                                             @RequestBody RestauranteDTO restauranteDTO){
        restauranteDTO = service.update(id, restauranteDTO);
        return ResponseEntity.ok(restauranteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
