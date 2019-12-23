package com.timebusker.repository;

import com.timebusker.model.MenuEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: MenuRepository
 * @Author: Administrator
 * @Date: 2019/12/20 13:10
 **/
@Repository
public class MenuRepository extends AbstractBaseRepository<MenuEntity, String> {

    public MenuRepository(EntityManager entityManager) {
        super(MenuEntity.class, entityManager);
    }

    public List<MenuEntity> queryBySystemIdAndParentId(String systemId, String parentId) {
        List<MenuEntity> list = new ArrayList<>();
        try {
            String sql = "SELECT menu from MenuEntity menu where systemId = :systemId and parentId = :parentId ORDER BY SORT ASC";
            Query query = em.createQuery(sql);
            query.setParameter("systemId", systemId);
            query.setParameter("parentId", parentId);
            list = query.getResultList();
        } catch (Exception e) {
            logger.error("查询失败！");
        }
        return list;
    }
}
