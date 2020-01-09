package com.timebusker.repository;

import com.timebusker.model.UserEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

    public UserEntity queryByLoginName(String loginName) {
        UserEntity user = new UserEntity();
        try {
            String sql = "SELECT user from UserEntity user where loginName = :loginName";
            Query query = em.createQuery(sql);
            query.setParameter("loginName", loginName);
            user = (UserEntity) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
