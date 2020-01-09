package com.timebusker.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @Description: AbstractBaseRepository
 * @Author: Administrator
 * @Date: 2019/12/20 10:43
 **/
@NoRepositoryBean
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class AbstractBaseRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    public static Sort sort;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public EntityManager em;

    AbstractBaseRepository(Class<T> clazz, EntityManager em) {
        super(clazz, em);
        this.em = em;
    }

    public AbstractBaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, EntityManager em) {
        super(entityInformation, entityManager);
        this.em = em;
    }
}
