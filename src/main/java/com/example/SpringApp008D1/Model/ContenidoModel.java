package com.example.SpringApp008D1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContenidoModel {
    private String idContenido;
    private String nombre;
    private String descripcion;
    private Date fechacreacion;
    private String estado;
    private String categoria;
    private String formato;

}
