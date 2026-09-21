package com.brilloestelar.servicios.service;

import com.brilloestelar.servicios.model.Servicio;
import com.brilloestelar.servicios.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String CLIENTES_SERVICE_URL = "http://localhost:8081/api/clientes/";

    public List<Servicio> getAllServicios(String estado, String fecha) {
        if (fecha != null && !fecha.isEmpty()) {
            LocalDate localDate = LocalDate.parse(fecha);
            if (estado != null) {
                return servicioRepository.findByEstadoAndFecha(estado, localDate);
            }
            return servicioRepository.findByFecha(localDate);
        }
        if (estado != null) {
            return servicioRepository.findByEstado(estado);
        }
        return servicioRepository.findAll();
    }

    public Optional<Servicio> getServicioById(Long id) {
        return servicioRepository.findById(id);
    }

    public Servicio createServicio(Servicio servicio) throws Exception {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(
                    CLIENTES_SERVICE_URL + servicio.getClienteId(), String.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                if (servicio.getEstado() == null) {
                    servicio.setEstado("pendiente");
                }
                return servicioRepository.save(servicio);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new Exception("El cliente con ID " + servicio.getClienteId() + " no existe.");
        } catch (Exception e) {
            throw new Exception("Error al comunicar con el Microservicio de Clientes.");
        }
        throw new Exception("Error al crear el servicio.");
    }

    public Optional<Servicio> updateServicio(Long id, Servicio servicioDetails) {
        return servicioRepository.findById(id).map(servicio -> {
            servicio.setTipoTratamiento(servicioDetails.getTipoTratamiento());
            servicio.setEstado(servicioDetails.getEstado());
            servicio.setPrecio(servicioDetails.getPrecio());
            return servicioRepository.save(servicio);
        });
    }

    public boolean deleteServicio(Long id) {
        if (servicioRepository.existsById(id)) {
            servicioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
