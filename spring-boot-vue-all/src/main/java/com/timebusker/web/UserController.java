package com.timebusker.web;

import com.timebusker.common.web.ResultVO;
import com.timebusker.model.RoleResourceEntity;
import com.timebusker.model.UserEntity;
import com.timebusker.model.UserRoleEntity;
import com.timebusker.service.UserRoleService;
import com.timebusker.service.UserService;
import com.timebusker.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: UserController
 * @Author: Administrator
 * @Date: 2020/1/7 14:54
 **/
@RestController
@RequestMapping("/user")
public class UserController extends AbstractBaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/list")
    public ResultVO list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        Page<UserEntity> page = userService.queryWithPage(query);
        return ResultVO.ok().put("data", page.getContent())
                .put("totalSize", page.getTotalElements())
                .put("totalPage", page.getTotalPages())
                .put("currentPage", query.getCurrentPage())
                .put("pageSize", query.getPageSize());
    }

    @RequestMapping("/save")
    public ResultVO save(UserEntity user) {
        user.setUpdateTime(LocalDateTime.now());
        userService.save(user);
        return ResultVO.ok();
    }

    @RequestMapping("/delete")
    public ResultVO delete(UserEntity user) {
        userService.delete(user);
        return ResultVO.ok();
    }

    @RequestMapping("/authorized")
    public ResultVO authorized(UserEntity user) {
        Query query = new Query();
        query.put("userId", user.getId());
        List<String> data = userRoleService.query(query);
        return ResultVO.ok().put("data", data);
    }

    @RequestMapping("/auth")
    public ResultVO auth(@RequestBody Map<String, Object> params) {
        List<String> roleIds = (List) params.get("roleIds");
        String userId = (String) params.get("userId");
        if (StringUtils.isBlank(userId) || roleIds.isEmpty())
            return ResultVO.error("请选择角色授权！");
        Set<UserRoleEntity> set = new HashSet<>();
        for (String roleId : roleIds) {
            UserRoleEntity role = new UserRoleEntity();
            UserRoleEntity.UserRoleKey key = new UserRoleEntity.UserRoleKey(userId, roleId);
            role.setIdx(key);
            role.setCreateTime(LocalDateTime.now());
            set.add(role);
        }
        userRoleService.save(set);
        return ResultVO.ok();
    }
}
