package com.github.vladimirantin.core.service;

import com.github.vladimirantin.core.model.CoreModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 10.06.2019
 * Time: 17:26

 * Abstraction for Spring service
 * @param <R> - Repository type
 * @param <E> - Entity type
 */
public abstract class CoreModelService<R extends JpaRepository<E, Long>,E extends CoreModel> {

    /**
     * Logger for SubClass
     */
    Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * Inject repository
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected R repo;

    /**
     * Find all for object type
     * @param pageable - Parameters for pagination
     * @return - One page of type E
     */
    public Page<E> findAll(Pageable pageable) {
        log.info("Find all (entity: {}, repo: {})", getEntityName(), getRepoName());
        return repo.findAll(pageable);
    }


    /**
     * Find one by id
     * @param id - Object id
     * @return - One object of type E
     */
    public E findOne(long id){
        log.info("Find one by id: {} (entity: {}, repo: {})", id, getEntityName(), getRepoName());
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    /**
     * Create or Update object
     * @param entity - Object to save
     * @return - Saved object
     */
    public E save(E entity){
        log.info("Save one (entity: {}, repo: {})", getEntityName(), getRepoName());
        return repo.save(entity);
    }

    /**
     * Delete one object
     * @param id - Object id
     */
    public void delete(long id) {
        log.info("Delete one by id: {} (entity: {}, repo: {})", id, getEntityName(), getRepoName());
        repo.deleteById(id);
    }


    /**
     * Get Class name of entity
     * @return - class name
     */
    @SuppressWarnings("unchecked")
    protected String getEntityName() {
        return ((Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]).getSimpleName();
    }


    /**
     * Get Class name of repository
     * @return - class name
     */
    @SuppressWarnings("unchecked")
    protected String getRepoName() {
        return ((Class<R>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}
