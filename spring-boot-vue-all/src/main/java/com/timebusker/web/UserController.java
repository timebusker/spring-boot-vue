package com.timebusker.web;

import com.timebusker.common.web.ResultVO;
import com.timebusker.model.UserEntity;
import com.timebusker.service.UserService;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

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

}
