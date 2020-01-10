package com.timebusker.repository;

import com.timebusker.model.RoleResourceEntity;
import com.timebusker.model.vo.RolePermissionVO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

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
            logger.error("删除失败！");
        }
        return true;
    }

    public List<RolePermissionVO> findAllPermission() {
        return this.findAllPermission(null);
    }

    public List<RolePermissionVO> findAllPermission(Collection<String> collection) {
        List<RolePermissionVO> list = new ArrayList<>();
        String sql;
        if (collection == null || collection.isEmpty()) {
            sql = "SELECT resource.url,role.symbol from tb_role_resource roleResource " +
                    "join tb_role role on roleResource.role_id = role.id " +
                    "join tb_menu resource on roleResource.resource_id = resource.id " +
                    "group by role.symbol,resource.url order by resource.url,role.symbol asc";
        } else {
            StringBuilder builder = new StringBuilder();
            Iterator<String> iterator = collection.iterator();
            while (iterator.hasNext()) {
                if (builder.toString().length() > 0)
                    builder.append(",");
                else
                    builder.append(" (");
                builder.append("'").append(iterator.next()).append("'");
            }
            builder.append(") ");
            sql = "SELECT resource.url,role.symbol from tb_role_resource roleResource " +
                    "join tb_role role on roleResource.role_id = role.id " +
                    "join tb_menu resource on roleResource.resource_id = resource.id " +
                    "where role.id in " + builder.toString() +
                    "group by role.symbol,resource.url order by resource.url,role.symbol asc";
        }
        try {
            Query query = em.createNativeQuery(sql);
            List<Object[]> res = query.getResultList();
            for (Object[] row : res) {
                RolePermissionVO vo = new RolePermissionVO();
                vo.setUrl(row[0].toString());
                vo.setSymbol(row[1].toString());
                list.add(vo);
            }
        } catch (Exception e) {
            logger.error("查询失败！");
        }
        return list;
    }
}
