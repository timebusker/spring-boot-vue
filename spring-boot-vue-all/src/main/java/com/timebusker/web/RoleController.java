package com.timebusker.web;

import com.timebusker.common.web.ResultVO;
import com.timebusker.model.RoleEntity;
import com.timebusker.model.RoleResourceEntity;
import com.timebusker.model.UserEntity;
import com.timebusker.service.RoleResourceService;
import com.timebusker.service.RoleService;
import com.timebusker.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Description: UserController
 * @Author: Administrator
 * @Date: 2020/1/7 14:54
 **/
@RestController
@RequestMapping("/role")
public class RoleController extends AbstractBaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleResourceService roleResourceService;

    @RequestMapping("/list")
    public ResultVO list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        Page<RoleEntity> page = roleService.queryWithPage(query);
        return ResultVO.ok().put("data", page.getContent())
                .put("totalSize", page.getTotalElements())
                .put("totalPage", page.getTotalPages())
                .put("currentPage", query.getCurrentPage())
                .put("pageSize", query.getPageSize());
    }

    @RequestMapping("/all")
    public ResultVO all() {
        List<RoleEntity> list = roleService.queryAll();
        return ResultVO.ok().put("data", list);
    }

    @RequestMapping("/save")
    public ResultVO save(RoleEntity role) {
        role.setUpdateTime(LocalDateTime.now());
        roleService.save(role);
        return ResultVO.ok();
    }

    @RequestMapping("/delete")
    public ResultVO delete(RoleEntity role) {
        roleService.delete(role);
        return ResultVO.ok();
    }

    @RequestMapping("/auth")
    public ResultVO auth(@RequestBody Map<String, Object> params) {
        List<String> menuIds = (List) params.get("menuIds");
        String roleId = (String) params.get("roleId");
        if (StringUtils.isBlank(roleId) || menuIds.isEmpty())
            return ResultVO.error("请选择角色授权！");
        Set<RoleResourceEntity> set = new HashSet<>();
        for (String menuId : menuIds) {
            RoleResourceEntity resource = new RoleResourceEntity();
            RoleResourceEntity.RoleResourceKey key = new RoleResourceEntity.RoleResourceKey(roleId, menuId);
            resource.setIdx(key);
            resource.setCreateTime(LocalDateTime.now());
            set.add(resource);
        }
        roleResourceService.save(set);
        return ResultVO.ok();
    }

    @RequestMapping("/authorized")
    public ResultVO authorized(RoleEntity role) {
        Query query = new Query();
        query.put("roleId", role.getId());
        List<String> data = roleResourceService.query(query);
        return ResultVO.ok().put("data", data);
    }
}
