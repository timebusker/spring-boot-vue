package com.timebusker.service;

import com.timebusker.common.exception.VueException;
import com.timebusker.repository.AbstractBaseRepository;
import com.timebusker.utils.BeanInvokeUtil;
import com.timebusker.utils.Query;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Set;

/**
 * @Description: AbstractBaseServiceImpl
 * @Author: Administrator
 * @Date: 2019/12/21 20:03
 **/
public abstract class AbstractBaseServiceImpl<DTO, DAO extends AbstractBaseRepository> implements AbstractBaseService<DTO> {

    private DAO repository;

    public void setRepository(DAO repository) {
        this.repository = repository;
    }

    @PostConstruct
    @PreDestroy
    protected abstract void instance();

    @Override
    public boolean save(DTO dto) {
        repository.saveAndFlush(dto);
        return true;
    }

    @Override
    public boolean save(Set<DTO> set) {
        repository.saveAll(set);
        return true;
    }

    @Override
    public boolean update(DTO dto) {
        repository.saveAndFlush(dto);
        return true;
    }

    @Override
    public boolean updateByParams(Query params) {
        if (params.containsKey("id") && StringUtils.isNotBlank(params.get("id").toString())) {
            String id = params.get("id").toString();
            params.remove("id");
            DTO dto = (DTO) repository.getOne(id);
            BeanInvokeUtil.invoke(dto, params);
            repository.saveAndFlush(dto);
        } else {
            throw new VueException("请传入更新对象主键信息！");
        }
        return true;
    }

    @Override
    public boolean delete(DTO dto) {
        repository.delete(dto);
        return true;
    }

    @Override
    public boolean delete(String idx) {
        repository.deleteById(idx);
        return true;
    }

    @Override
    public DTO query(String idx) {
        DTO dto = (DTO) repository.getOne(idx);
        return dto;
    }
}
