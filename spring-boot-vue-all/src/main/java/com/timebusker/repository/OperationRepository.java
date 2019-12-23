package com.timebusker.repository;

import com.timebusker.model.OperationEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @Description: OperationRepository
 * @Author: Administrator
 * @Date: 2019/12/20 13:22
 **/
@Repository
public class OperationRepository extends AbstractBaseRepository<OperationEntity, String> {

    public OperationRepository(EntityManager em) {
        super(OperationEntity.class, em);
    }
}
