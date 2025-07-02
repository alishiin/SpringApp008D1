package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.SubCategoriaCursoControllerV2;
import com.example.SpringApp008D1.model.SubCategoriaCursoModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SubCategoriaCursoAssembler implements RepresentationModelAssembler<SubCategoriaCursoModel, EntityModel<SubCategoriaCursoModel>> {

    @Override
    public EntityModel<SubCategoriaCursoModel> toModel(SubCategoriaCursoModel subcategoria) {
        return EntityModel.of(subcategoria,
                linkTo(methodOn(SubCategoriaCursoControllerV2.class).getSubCategoriaById(subcategoria.getId())).withSelfRel(),
                linkTo(methodOn(SubCategoriaCursoControllerV2.class).listar()).withRel("subcategorias")
        );
    }
}
