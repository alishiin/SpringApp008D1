package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.CuponModel;
import com.example.SpringApp008D1.repository.CuponRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuponService {

    private final CuponRepository cuponRepository;

    public CuponService(CuponRepository cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    public CuponModel crearCupon(CuponModel cupon) {
        return cuponRepository.save(cupon);
    }

    public List<CuponModel> listarCupones() {
        return cuponRepository.findAll();
    }

    public Optional<CuponModel> obtenerCuponPorId(Long id) {
        return cuponRepository.findById(id);
    }

    public boolean eliminarCupon(Long id) {
        if (cuponRepository.existsById(id)) {
            cuponRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<CuponModel> actualizarCupon(Long id, CuponModel cuponActualizado) {
        Optional<CuponModel> cuponExistente = cuponRepository.findById(id);
        if (cuponExistente.isPresent()) {
            CuponModel cupon = cuponExistente.get();
            cupon.setCodigo(cuponActualizado.getCodigo());
            cupon.setDescuento(cuponActualizado.getDescuento());
            CuponModel actualizado = cuponRepository.save(cupon);
            return Optional.of(actualizado);
        } else {
            return Optional.empty();
        }
    }
}
