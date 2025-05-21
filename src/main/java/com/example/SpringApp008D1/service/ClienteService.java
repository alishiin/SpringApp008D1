package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.ClienteModel;
import com.example.SpringApp008D1.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteModel saveCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public boolean updateCliente(Long id, ClienteModel clienteData) {
        Optional<ClienteModel> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            ClienteModel cliente = clienteOpt.get();
            cliente.setNombre(clienteData.getNombre());
            cliente.setEmail(clienteData.getEmail());
            cliente.setTelefono(clienteData.getTelefono());
            clienteRepository.save(cliente);
            return true;
        }
        return false;
    }

    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
