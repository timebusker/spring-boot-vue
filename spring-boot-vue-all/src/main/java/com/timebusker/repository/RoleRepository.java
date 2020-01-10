package com.timebusker.repository;

import com.timebusker.model.RoleEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    public List<RoleEntity> queryByUserId(String userId) {
        List<RoleEntity> list = new ArrayList<>();
        try {
            String sql = "SELECT role FROM RoleEntity role " +
                    "join UserRoleEntity roleUser on role.id=roleUser.idx.roleId " +
                    "join UserEntity user on user.id=roleUser.idx.userId where user.id =:userId";
            Query query = em.createQuery(sql);
            query.setParameter("userId", userId);
            list = query.getResultList();
        } catch (Exception e) {
            logger.error("查询失败！");
        }
        return list;
    }
}
