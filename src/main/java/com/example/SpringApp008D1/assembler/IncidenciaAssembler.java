package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.IncidenciaControllerV2;
import com.example.SpringApp008D1.model.IncidenciaModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class IncidenciaAssembler implements RepresentationModelAssembler<IncidenciaModel, EntityModel<IncidenciaModel>> {

    @Override
    public EntityModel<IncidenciaModel> toModel(IncidenciaModel incidencia) {
        return EntityModel.of(incidencia,
                linkTo(methodOn(IncidenciaControllerV2.class).getIncidenciaById(incidencia.getId())).withSelfRel(),
                linkTo(methodOn(IncidenciaControllerV2.class).getAllIncidencias()).withRel("incidencias")
        );
    }
}
