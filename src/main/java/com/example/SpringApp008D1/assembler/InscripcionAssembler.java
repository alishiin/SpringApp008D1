package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.InscripcionController;
import com.example.SpringApp008D1.model.InscripcionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InscripcionAssembler implements RepresentationModelAssembler<InscripcionModel, EntityModel<InscripcionModel>> {

    @Override
    public EntityModel<InscripcionModel> toModel(InscripcionModel inscripcion) {
        return EntityModel.of(inscripcion,
                linkTo(methodOn(InscripcionController.class).getInscripcionById(inscripcion.getId())).withSelfRel(),
                linkTo(methodOn(InscripcionController.class).getAllInscripciones()).withRel("inscripciones")
        );
    }
}
