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

    public String crearCupon(CuponModel cupon) {
        cuponRepository.save(cupon);
        return "✅ Cupón agregado correctamente.";
    }

    public List<CuponModel> listarCupones() {
        return cuponRepository.findAll();
    }

    public String eliminarCupon(Long id) {
        Optional<CuponModel> cupon = cuponRepository.findById(id);
        if (cupon.isPresent()) {
            cuponRepository.deleteById(id);
            return "🗑️ Cupón eliminado exitosamente.";
        } else {
            return "❌ Cupón no encontrado.";
        }
    }
}
