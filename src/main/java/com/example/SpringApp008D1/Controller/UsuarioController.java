package com.example.SpringApp008D1.Controller;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
@RestController

public class UsuarioController {
=======
import com.example.SpringApp008D1.Model.UsuarioModel;
import com.example.SpringApp008D1.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

>>>>>>> 793816a (Agregando controller , model , service , repository sus respectivos atributos y sus relaciones...)
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
<<<<<<< HEAD
    public List<Usuario> listar() {
=======
    public List<UsuarioModel> listar() {
>>>>>>> 793816a (Agregando controller , model , service , repository sus respectivos atributos y sus relaciones...)
        return service.listar();
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
    public Usuario obtener(@PathVariable Long id) {
=======
    public UsuarioModel obtener(@PathVariable Long id) {
>>>>>>> 793816a (Agregando controller , model , service , repository sus respectivos atributos y sus relaciones...)
        return service.obtener(id);
    }

    @PostMapping
<<<<<<< HEAD
    public Usuario crear(@RequestBody Usuario usuario) {
=======
    public UsuarioModel crear(@RequestBody UsuarioModel usuario) {
>>>>>>> 793816a (Agregando controller , model , service , repository sus respectivos atributos y sus relaciones...)
        return service.crear(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/existe/{id}")
    public boolean existe(@PathVariable Long id) {
        return service.existe(id);
    }
<<<<<<< HEAD

=======
>>>>>>> 793816a (Agregando controller , model , service , repository sus respectivos atributos y sus relaciones...)
}
