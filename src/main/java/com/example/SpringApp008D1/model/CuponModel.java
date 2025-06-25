package com.example.SpringApp008D1.model;

import com.example.SpringApp008D1.assembler.CuponAssembler;
import jakarta.persistence.*;

@Entity
public class CuponModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private int descuento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getDescuento() { return descuento; }
    public void setDescuento(int descuento) { this.descuento = descuento; }


    public CuponAssembler toAssembler() {
        CuponAssembler assembler = new CuponAssembler();
        assembler.setId(this.id);
        assembler.setCodigo(this.codigo);
        assembler.setDescuento(this.descuento);
        return assembler;
    }

    public static CuponModel fromAssembler(CuponAssembler assembler) {
        CuponModel model = new CuponModel();
        model.setId(assembler.getId());
        model.setCodigo(assembler.getCodigo());
        model.setDescuento(assembler.getDescuento());
        return model;
    }
}
