package com.timebusker.repository;

import com.timebusker.model.RoleResourceEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: RoleResourceRepository
 * @Author: Administrator
 * @Date: 2020/1/9 15:44
 **/
@Repository
public class RoleResourceRepository extends AbstractBaseRepository<RoleResourceEntity, String> {

    public RoleResourceRepository(EntityManager em) {
        super(RoleResourceEntity.class, em);
    }

    public List<String> findByRoleId(String roleId) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT resource_id from tb_role_resource where role_id= '" + roleId + "' group BY resource_id";
            Query query = em.createNativeQuery(sql);
            list = query.getResultList();
        } catch (Exception e) {
            logger.error("查询失败！");
        }
        return list;
    }

    public boolean deleteByRoleId(String roleId) {
        try {
            String sql = "delete from tb_role_resource where role_id= '" + roleId + "'";
            Query query = em.createNativeQuery(sql);
            query.executeUpdate();
        } catch (Exception e) {
            logger.error("查询失败！");
        }
        return true;
    }
}
