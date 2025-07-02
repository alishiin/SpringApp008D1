package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.UsuarioControllerV2;
import com.example.SpringApp008D1.model.UsuarioModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioAssembler implements RepresentationModelAssembler<UsuarioModel, EntityModel<UsuarioModel>> {

    @Override
    public EntityModel<UsuarioModel> toModel(UsuarioModel usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withRel("usuarios")
        );
    }
}
