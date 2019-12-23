package com.timebusker.repository;

import com.timebusker.model.UserEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @Description: UserRepository
 * @Author: Administrator
 * @Date: 2019/12/20 13:10
 **/
@Repository
public class UserRepository extends AbstractBaseRepository<UserEntity, String> {

    public UserRepository(EntityManager em) {
        super(UserEntity.class, em);
    }
}
