package com.timebusker.repository;

import com.timebusker.model.UserRoleEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> findByUserId(String userId) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT role_id from tb_user_role where user_id= '" + userId + "' group BY role_id";
            Query query = em.createNativeQuery(sql);
            list = query.getResultList();
        } catch (Exception e) {
            logger.error("查询失败！");
        }
        return list;
    }

    public boolean deleteByUserId(String userId) {
        try {
            String sql = "delete from tb_user_role where user_id= '" + userId + "'";
            Query query = em.createNativeQuery(sql);
            query.executeUpdate();
        } catch (Exception e) {
            logger.error("查询失败！");
        }
        return true;
    }
}
