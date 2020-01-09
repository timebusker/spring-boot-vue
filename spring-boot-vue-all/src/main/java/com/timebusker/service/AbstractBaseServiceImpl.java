package com.timebusker.service;

import com.timebusker.common.exception.VueException;
import com.timebusker.model.SystemConfigEntity;
import com.timebusker.repository.AbstractBaseRepository;
import com.timebusker.utils.BeanInvokeUtil;
import com.timebusker.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;

/**
 * @Description: AbstractBaseServiceImpl
 * @Author: Administrator
 * @Date: 2019/12/21 20:03
 **/
public abstract class AbstractBaseServiceImpl<DTO, DAO extends AbstractBaseRepository> implements AbstractBaseService<DTO> {

    public static final Pageable DEFAULT_PAGEABLE = PageRequest.of(Query.DEFAULT_CURRENT_PAGE, Query.DEFAULT_PAGE_SIZE);

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
    public boolean save(Collection<DTO> set) {
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

    @Override
    public Page<DTO> queryWithPage(Query params) {
        Pageable pageable = PageRequest.of(params.getCurrentPage(), params.getPageSize());
        Page<DTO> page = repository.findAll(pageable);
        return page;
    }
}
