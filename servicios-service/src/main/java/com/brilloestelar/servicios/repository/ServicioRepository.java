package com.brilloestelar.servicios.repository;

import com.brilloestelar.servicios.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByEstado(String estado);
    List<Servicio> findByFecha(LocalDate fecha);
    List<Servicio> findByEstadoAndFecha(String estado, LocalDate fecha);
}
