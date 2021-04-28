package com.github.vladimirantin.core.web.rest;

import com.github.vladimirantin.core.model.CoreModel;
import com.github.vladimirantin.core.service.CoreModelService;
import com.github.vladimirantin.core.web.DTO.CoreDTO;
import com.github.vladimirantin.core.web.mapper.CoreMapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 28.04.2021
 * Time: 16:43
 */
public class ExtendedRestController<SERVICE extends CoreModelService, MAPPER extends CoreMapperImpl,DTO extends CoreDTO, ENTITY extends CoreModel>
extends CoreRestController<SERVICE, MAPPER, DTO, ENTITY>{


    @SuppressWarnings("unchecked")
    @PatchMapping("/{id}/{propertyName}")
    public ResponseEntity patch(@PathVariable long id, @PathVariable String propertyName, @RequestBody DTO dto) throws InvocationTargetException, IllegalAccessException {
        ENTITY entity = (ENTITY) service.findOne(id);
        entity.setReflectValue(propertyName, dto.getReflectValue(propertyName));
        entity = (ENTITY) service.save(entity);
        return ResponseEntity.ok(mapper.toDto(entity));
    }

}
