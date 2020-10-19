package com.github.vladimirantin.core.audit.impl;

import com.github.vladimirantin.core.audit.AbstractRepository;
import com.github.vladimirantin.core.audit.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Component
public class AuditLogRepo extends AbstractRepository<AuditLog> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Page<AuditLog> findAll(Pageable pageable) {
        Query findAllQuery = em.createNativeQuery("select al.* from audit_log al", AuditLog.class);
        findAllQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        findAllQuery.setMaxResults(pageable.getPageSize());
        List<AuditLog> resultList = findAllQuery.getResultList();
        return new PageImpl(resultList, pageable, count());
    }

    @Override
    public AuditLog save(AuditLog entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Optional<AuditLog> findById(Long id) {
        return Optional.of(em.find(AuditLog.class, id));
    }

    @Override
    public long count() {
        Query countQuery = em.createNativeQuery("select count(al.id) from audit_log al");
        BigInteger count = (BigInteger) countQuery.getSingleResult();
        return count.longValue();
    }
}
