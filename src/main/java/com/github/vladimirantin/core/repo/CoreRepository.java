package com.github.vladimirantin.core.repo;

import com.github.vladimirantin.core.model.CoreModel;
import com.github.vladimirantin.core.softDelete.event.SoftDeletePublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 15.11.2019
 * Time: 17:17
 */
@NoRepositoryBean
public interface CoreRepository<T extends CoreModel> extends JpaRepository<T, Long> {

    @Override
    @Query("update #{#entityName} e set e.deleted=true where e.id = :id")
    @Transactional
    @Modifying
    void deleteById(@Param("id") Long id);

    @Override
    @Transactional
    default void delete(T entity) {
        SoftDeletePublisher.preSoftDelete(entity);
        deleteById(entity.getId());
        SoftDeletePublisher.postSoftDelete(entity);
    }

    @Override
    @Transactional
    default void deleteAll(Iterable<? extends T> entities) {
        SoftDeletePublisher.preSoftDeleteCollection(entities);
        entities.forEach(entity -> delete(entity));
        SoftDeletePublisher.postSoftDeleteCollection(entities);
    }

    @Override
    @Transactional
    default void deleteInBatch(Iterable<T> entities) {
        SoftDeletePublisher.preSoftDeleteCollection(entities);
        entities.forEach(entity -> delete(entity));
        SoftDeletePublisher.postSoftDeleteCollection(entities);
    }

    @Override
    default void deleteAllInBatch() {
        deleteAll();
    };

    @Override
    @Query("update #{#entityName} e set e.deleted=true")
    @Transactional
    @Modifying
    void deleteAll();


}
