package com.brilloestelar.servicios.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    private LocalDate fecha;
    
    @Column(name = "tipo_tratamiento")
    private String tipoTratamiento;
    
    private String estado;
    private Double precio;

    public Servicio() {
        this.fecha = LocalDate.now();
        this.estado = "pendiente";
    }

    public Servicio(Long clienteId, String tipoTratamiento, String estado, Double precio) {
        this.clienteId = clienteId;
        this.fecha = LocalDate.now();
        this.tipoTratamiento = tipoTratamiento;
        this.estado = estado != null ? estado : "pendiente";
        this.precio = precio;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTipoTratamiento() { return tipoTratamiento; }
    public void setTipoTratamiento(String tipoTratamiento) { this.tipoTratamiento = tipoTratamiento; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}
