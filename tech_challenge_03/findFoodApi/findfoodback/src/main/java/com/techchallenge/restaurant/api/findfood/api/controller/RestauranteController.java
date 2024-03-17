package com.techchallenge.restaurant.api.findfood.api.controller;

import com.techchallenge.restaurant.api.findfood.api.model.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.domain.model.Restaurante;
import com.techchallenge.restaurant.api.findfood.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
@AllArgsConstructor
public class RestauranteController {

    private final RestauranteService service;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody RestauranteDTO restauranteDTO) {
        service.registrarRestaurante(restauranteDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Restaurante Registrado com Sucesso");
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Restaurante>> findRestaurantes(@RequestParam(name = "nome", required = false, defaultValue = "") String nome,
                                                              @RequestParam(name = "localizacao", required = false, defaultValue = "") String localizacao,
                                                              @RequestParam(name = "tipoCozinha", required = false, defaultValue = "") String tipoCozinha) {
        var restaurante = service.buscarRestaurantePor(nome, localizacao, tipoCozinha);
        return ResponseEntity.ok(restaurante);
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> findAll() {
        var restaurantes = service.buscarTodosRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDTO> update(@PathVariable Long restauranteId, @RequestBody RestauranteDTO restauranteDTO) {
        restauranteDTO = service.atualizarRestaurante(restauranteId, restauranteDTO);
        return ResponseEntity.ok(restauranteDTO);
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Void> delete(@PathVariable Long restauranteId) {
        service.deletarRestaurante(restauranteId);
        return ResponseEntity.noContent().build();
    }
}
