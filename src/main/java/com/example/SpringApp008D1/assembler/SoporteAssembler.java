package com.example.SpringApp008D1.assembler;

import com.example.SpringApp008D1.controller.SoporteControllerV2;
import com.example.SpringApp008D1.model.SoporteModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SoporteAssembler implements RepresentationModelAssembler<SoporteModel, EntityModel<SoporteModel>> {

    @Override
    public EntityModel<SoporteModel> toModel(SoporteModel soporte) {
        return EntityModel.of(soporte,
                linkTo(methodOn(SoporteControllerV2.class).getSoporteById(soporte.getId())).withSelfRel(),
                linkTo(methodOn(SoporteControllerV2.class).listar()).withRel("soportes")
        );
    }
}
