package com.timebusker.repository;

import com.timebusker.model.RoleEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @Description: RoleRepository
 * @Author: Administrator
 * @Date: 2019/12/20 13:10
 **/
@Repository
public class RoleRepository extends AbstractBaseRepository<RoleEntity, String> {

    public RoleRepository(EntityManager em) {
        super(RoleEntity.class, em);
    }
}
