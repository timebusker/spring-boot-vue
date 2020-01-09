package com.timebusker.repository;

import com.timebusker.model.SystemConfigEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SystemConfigRepository
 * @Author: Administrator
 * @Date: 2020/1/6 17:21
 **/
@Repository
public class SystemConfigRepository extends AbstractBaseRepository<SystemConfigEntity, String> {

    public SystemConfigRepository(EntityManager entityManager) {
        super(SystemConfigEntity.class, entityManager);
    }

    public List<SystemConfigEntity> queryByConfigType(String configType) {
        List<SystemConfigEntity> list = new ArrayList<>();
        String sql = "SELECT config FROM SystemConfigEntity config WHERE configType=:configType ORDER BY configKey ASC";
        Query query = em.createQuery(sql);
        query.setParameter("configType", configType);
        list = query.getResultList();
        return list;
    }
}
