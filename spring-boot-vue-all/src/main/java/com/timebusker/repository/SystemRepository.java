package com.timebusker.repository;

import com.timebusker.model.SystemEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @Description: SystemRepository
 * @Author: Administrator
 * @Date: 2019/12/20 13:10
 **/
@Repository
public class SystemRepository extends AbstractBaseRepository<SystemEntity,String> {

    public SystemRepository(EntityManager em) {
        super(SystemEntity.class, em);
    }
}
