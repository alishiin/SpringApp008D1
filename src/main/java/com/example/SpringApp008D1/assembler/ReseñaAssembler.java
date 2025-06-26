package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.ReseñaController;
import com.example.SpringApp008D1.model.ReseñaModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReseñaAssembler implements RepresentationModelAssembler<ReseñaModel, EntityModel<ReseñaModel>> {

    @Override
    public EntityModel<ReseñaModel> toModel(ReseñaModel reseña) {
        return EntityModel.of(reseña,
                linkTo(methodOn(ReseñaController.class).listar()).withRel("resenas"),
                linkTo(methodOn(ReseñaController.class).eliminar(reseña.getId())).withRel("eliminar")
        );
    }
}
