package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.SesionControllerV2;
import com.example.SpringApp008D1.model.SesionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SesionAssembler implements RepresentationModelAssembler<SesionModel, EntityModel<SesionModel>> {

    @Override
    public EntityModel<SesionModel> toModel(SesionModel sesion) {
        return EntityModel.of(sesion,
                linkTo(methodOn(SesionControllerV2.class).getSesionById(sesion.getId())).withSelfRel(),
                linkTo(methodOn(SesionControllerV2.class).listar()).withRel("sesiones")
        );
    }
}
