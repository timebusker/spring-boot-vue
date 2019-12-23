package com.timebusker.repository;

import com.timebusker.model.UserMenuEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @Description: UserMenuRepository
 * @Author: Administrator
 * @Date: 2019/12/20 13:11
 **/
@Repository
public class UserMenuRepository extends AbstractBaseRepository<UserMenuEntity, String> {

    public UserMenuRepository(EntityManager em) {
        super(UserMenuEntity.class, em);
    }
}
