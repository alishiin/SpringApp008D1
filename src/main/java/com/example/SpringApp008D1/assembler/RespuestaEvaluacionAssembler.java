package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.RespuestaEvaluacionControllerV2;
import com.example.SpringApp008D1.model.RespuestaEvaluacionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RespuestaEvaluacionAssembler implements RepresentationModelAssembler<RespuestaEvaluacionModel, EntityModel<RespuestaEvaluacionModel>> {

    @Override
    public EntityModel<RespuestaEvaluacionModel> toModel(RespuestaEvaluacionModel respuesta) {
        return EntityModel.of(respuesta,
                linkTo(methodOn(RespuestaEvaluacionControllerV2.class).getRespuestaById(respuesta.getId())).withSelfRel(),
                linkTo(methodOn(RespuestaEvaluacionControllerV2.class).getAllRespuestas()).withRel("respuestas")
        );
    }
}
