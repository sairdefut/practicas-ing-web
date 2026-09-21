package com.brilloestelar.clientes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String vehiculo;
    private String placa;

    public Cliente() {}

    public Cliente(String nombre, String telefono, String vehiculo, String placa) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.vehiculo = vehiculo;
        this.placa = placa;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getVehiculo() { return vehiculo; }
    public void setVehiculo(String vehiculo) { this.vehiculo = vehiculo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
}
