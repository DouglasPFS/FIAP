package com.techchallenge.restaurant.api.findfood.controller;


import com.techchallenge.restaurant.api.findfood.dto.ReservaDTO;
import com.techchallenge.restaurant.api.findfood.dto.RestauranteDTO;
import com.techchallenge.restaurant.api.findfood.entities.Reserva;
import com.techchallenge.restaurant.api.findfood.repository.RestauranteRepository;
import com.techchallenge.restaurant.api.findfood.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/restaurantes")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping("/{restauranteId}/reservarMesa")
    public ResponseEntity<String> reservarMesa(@PathVariable Long restauranteId, @RequestBody ReservaDTO reservaDTO) {
        tableBookingService.bookTable(restaurantId, bookingRequest);
    }

    // TODO MÉTODOS FINALIZADOS ESTÃO ACIMA

    @GetMapping
    public ResponseEntity<Collection<ReservaDTO>> findAll() {
        var reservas = service.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Long id) {
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
                                             @RequestBody ReservaDTO reservaDTO) {
        reservaDTO = service.update(id, reservaDTO);
        return ResponseEntity.ok(reservaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/reserva")
    public ResponseEntity<String> fazerReserva(@RequestBody Reserva reserva) {

        int limitePorMesa = 4;

//        if(reserva.getQtdPessoa() < limitePorMesa) {
//            Mesa mesa = mesaRepository.findById(reserva.getMesaId()).orElse(null);
//            if (mesa != null && mesa.isDisponivelParaReserva()) {
//                mesa.setDisponivelParaReserva(false);
//                mesaRepository.save(mesa);
//                return ResponseEntity.ok("Reserva realizada com sucesso!");
//            } else {
//                return ResponseEntity.badRequest().body("Mesa não disponível para reserva.");
//            }
//
//        }
        return ResponseEntity.ok("Reserva realizada com sucesso!");
    }

}
