package com.github.vladimirantin.core.repo;

import com.github.vladimirantin.core.model.CoreModel;
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
        deleteById(entity.getId());
    }
}
