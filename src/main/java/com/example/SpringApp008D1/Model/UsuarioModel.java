package com.example.SpringApp008D1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class UsuarioModel {
    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private String rolUsuario;
}
