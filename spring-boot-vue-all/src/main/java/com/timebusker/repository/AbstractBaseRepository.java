package com.timebusker.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: AbstractBaseRepository
 * @Author: Administrator
 * @Date: 2019/12/20 10:43
 **/
@NoRepositoryBean
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class AbstractBaseRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public EntityManager em;

    protected Sort sort;

    private Class<T> clazz;

    AbstractBaseRepository(Class<T> clazz, EntityManager em) {
        super(clazz, em);
        this.em = em;
        this.clazz = clazz;
    }

    public AbstractBaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, EntityManager em) {
        super(entityInformation, entityManager);
        this.em = em;
    }

    public Sort setSort() {
        if (sort != null) {
            return sort;
        }
        List<String> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                // 只获取私有封装属性
                String fieldName = field.getName().toLowerCase();
                if (fieldName.contains("name") || fieldName.contains("time")) {
                    list.add(field.getName());
                }
            }
        }
        sort = new Sort(Sort.Direction.ASC, list);
        return sort;
    }

}
