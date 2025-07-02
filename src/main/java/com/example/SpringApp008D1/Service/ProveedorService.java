package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.ProveedorModel;
import com.example.SpringApp008D1.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }

    public List<ProveedorModel> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    public Optional<ProveedorModel> getProveedorById(Long id) {
        return proveedorRepository.findById(id);
    }

    public ProveedorModel saveProveedor(ProveedorModel proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public boolean updateProveedor(Long id, ProveedorModel proveedorData) {
        Optional<ProveedorModel> proveedorOpt = proveedorRepository.findById(id);
        if (proveedorOpt.isPresent()) {
            ProveedorModel proveedor = proveedorOpt.get();
            proveedor.setNombre(proveedorData.getNombre());
            proveedor.setContacto(proveedorData.getContacto());
            proveedor.setTelefono(proveedorData.getTelefono());
            proveedorRepository.save(proveedor);
            return true;
        }
        return false;
    }

    public boolean deleteProveedor(Long id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
