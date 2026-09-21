package com.brilloestelar.servicios.controller;

import com.brilloestelar.servicios.model.Servicio;
import com.brilloestelar.servicios.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getAllServicios(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String fecha) {
        return servicioService.getAllServicios(estado, fecha);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        return servicioService.getServicioById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createServicio(@RequestBody Servicio servicio) {
        try {
            Servicio savedServicio = servicioService.createServicio(servicio);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedServicio);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Long id, @RequestBody Servicio servicioDetails) {
        return servicioService.updateServicio(id, servicioDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        if (servicioService.deleteServicio(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
