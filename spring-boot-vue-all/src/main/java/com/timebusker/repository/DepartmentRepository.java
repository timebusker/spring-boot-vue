package com.timebusker.repository;

import com.timebusker.model.DepartmentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @Description: DepartmentRepository
 * @Author: Administrator
 * @Date: 2019/12/20 11:07
 **/
@Repository
public class DepartmentRepository extends AbstractBaseRepository<DepartmentEntity, String> {

    public DepartmentRepository(EntityManager entityManager) {
        super(DepartmentEntity.class, entityManager);
    }
}
