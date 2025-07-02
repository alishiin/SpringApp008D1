package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.ProgresoController;
import com.example.SpringApp008D1.model.ProgresoModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProgresoAssembler implements RepresentationModelAssembler<ProgresoModel, EntityModel<ProgresoModel>> {

    @Override
    public EntityModel<ProgresoModel> toModel(ProgresoModel progreso) {
        return EntityModel.of(progreso,
                linkTo(methodOn(ProgresoController.class).listar()).withRel("progresos"),
                linkTo(methodOn(ProgresoController.class).eliminar(progreso.getId())).withRel("eliminar")
        );
    }
}
