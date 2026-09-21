package com.brilloestelar.clientes.service;

import com.brilloestelar.clientes.model.Cliente;
import com.brilloestelar.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Long id, Cliente clienteDetails) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setTelefono(clienteDetails.getTelefono());
            cliente.setVehiculo(clienteDetails.getVehiculo());
            cliente.setPlaca(clienteDetails.getPlaca());
            return clienteRepository.save(cliente);
        });
    }

    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
