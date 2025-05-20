package com.example.SpringApp008D1.Model;

<<<<<<< HEAD
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
=======
public class UsuarioModel {
    private Long id;
    private String nombre;
    private String email;

    public UsuarioModel() {}

    public UsuarioModel(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
>>>>>>> 793816a (Agregando controller , model , service , repository sus respectivos atributos y sus relaciones...)
}
