package com.timebusker.repository;

import com.timebusker.model.MenuPointEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @Description: MenuPointRepository
 * @Author: Administrator
 * @Date: 2019/12/20 13:10
 **/
@Repository
public class MenuPointRepository extends AbstractBaseRepository<MenuPointEntity, String> {

    public MenuPointRepository(EntityManager entityManager) {
        super(MenuPointEntity.class, entityManager);
    }

}