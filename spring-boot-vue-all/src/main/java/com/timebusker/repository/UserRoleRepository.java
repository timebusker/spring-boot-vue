package com.timebusker.repository;

import com.timebusker.model.UserRoleEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @Description: UserRoleRepository
 * @Author: Administrator
 * @Date: 2019/12/20 13:11
 **/
@Repository
public class UserRoleRepository extends AbstractBaseRepository<UserRoleEntity, String> {

    public UserRoleRepository(EntityManager em) {
        super(UserRoleEntity.class, em);
    }
}
