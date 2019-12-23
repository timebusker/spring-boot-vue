package com.timebusker.service;

import com.timebusker.model.OperationEntity;
import com.timebusker.repository.OperationRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: OperationLogService
 * @Author: Administrator
 * @Date: 2019/12/20 14:23
 **/
@Service
public class OperationLogService extends AbstractBaseServiceImpl<OperationEntity, OperationRepository> {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    protected void instance() {
        this.setRepository(operationRepository);
    }

    @Override
    public OperationEntity queryByParams(Query params) {
        return null;
    }

    @Override
    public List<OperationEntity> query(Query params) {
        return null;
    }

    @Override
    public Page<OperationEntity> query(Query params, Pageable pageable) {
        return null;
    }
}
