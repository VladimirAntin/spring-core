package com.github.vladimirantin.core.web.rest;

import com.github.vladimirantin.core.model.CoreModel;
import com.github.vladimirantin.core.service.CoreModelService;
import com.github.vladimirantin.core.web.DTO.CoreDTO;
import com.github.vladimirantin.core.web.mapper.CoreMapperImpl;
import com.github.vladimirantin.core.web.utils.HttpParams;
import com.github.vladimirantin.core.web.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 10.06.2019
 * Time: 19:09
 *
 * Abstraction for Spring boot REST controller
 * @param <SERVICE> - Service
 * @param <MAPPER> - Mapper
 * @param <DTO> - Data Transfer Object
 * @param <ENTITY> - Entity
 */
public abstract class CoreRestController<SERVICE extends CoreModelService, MAPPER extends CoreMapperImpl,DTO extends CoreDTO, ENTITY extends CoreModel> {

    /**
     * Inject service
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected SERVICE service;

    /**
     * Inject mapper
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected MAPPER mapper;

    /**
     * Find All entities for type ENTITY
     * @param pageable - pagerequest
     * @param params - Parameters for pagination and default params
     * @return - One page of type DTO
     */
    @SuppressWarnings("unchecked")
    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable, HttpParams params) {
        Page<ENTITY> entities = service.findAll(pageable);
        if (params.getBody()== HttpParams.BodyType.array) {
            return ResponseEntity
                    .ok()
                    .headers(PaginationUtil.generatePaginationHttpHeaders(entities))
                    .body(mapper.toMinimalDTO(entities.getContent()));
        } else {
            return ResponseEntity
                    .ok()
                    .headers(PaginationUtil.generatePaginationHttpHeaders(entities))
                    .body(PaginationUtil.generateBody(mapper.toMinimalDTO(entities.getContent()),entities));
        }
    }

    /**
     * Find one entity for type ENTITY
     * @param id - id of ENTITY
     * @return - Found DTO
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.findOne(id)));
    }

    /**
     * Create one object of type ENTITY
     * @param dto - Object from request
     * @return - Created object (DTO)
     */
    @SuppressWarnings("unchecked")
    @PostMapping
    public ResponseEntity create(@RequestBody DTO dto) {
        if(dto.getId()!=0){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(service.save(mapper.toEntity(dto))));
    }

    /**
     * Update one object of type ENTITY
     * @param id - id of ENTITY
     * @param dto - Object from request
     * @return - Updated object (DTO)
     */
    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody DTO dto) {
        if(id!=dto.getId()){
            return ResponseEntity.badRequest().build();
        }
        ENTITY entity = (ENTITY) service.save(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDto(entity));
    }

    /**
     * Delete ENTITY by id
     * @param id - Id of ENTITY
     * @return - Status code if everything is ok
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable long id) {
        ENTITY entity = (ENTITY) service.findOne(id);
        service.delete(entity);
        return ResponseEntity.noContent().build();
    }


}
