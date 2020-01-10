package com.timebusker.service;

import com.timebusker.model.RoleResourceEntity;
import com.timebusker.model.vo.RolePermissionVO;
import com.timebusker.repository.RoleResourceRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description: RoleResourceService
 * @Author: Administrator
 * @Date: 2020/1/9 15:46
 **/
@Service
public class RoleResourceService extends AbstractBaseServiceImpl<RoleResourceEntity, RoleResourceRepository> {

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    @Override
    protected void instance() {
        this.setRepository(roleResourceRepository);
    }

    public List<String> query(Query params) {
        List<String> list = new ArrayList<>();
        list = roleResourceRepository.findByRoleId(params.get("roleId").toString());
        return list;
    }

    @Override
    @Transactional
    public boolean save(Collection<RoleResourceEntity> collection) {
        roleResourceRepository.deleteByRoleId(collection.iterator().next().getIdx().getRoleId());
        return super.save(collection);
    }

    public List<RolePermissionVO> getPermission() {
        List<RolePermissionVO> list = roleResourceRepository.findAllPermission();
        return list;
    }

    public List<RolePermissionVO> getPermission(Set<String> roleIds) {
        List<RolePermissionVO> list = roleResourceRepository.findAllPermission(roleIds);
        return list;
    }
}
