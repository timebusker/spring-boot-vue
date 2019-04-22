package com.timebusker.web.common;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.web.ResultVo;
import com.timebusker.web.AbstractBaseController;
import com.timebusker.web.vo.UserVo;
import org.springframework.web.bind.annotation.*;

/**
 * @DESC:AdminLoginController
 * @author:timebusker
 * @date:2019/3/21
 */
@RestController
@RequestMapping("/")
public class AdminLoginController extends AbstractBaseController {

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResultVo login(@RequestBody UserVo vo) {
        System.out.println(JSON.toJSONString(vo));
        vo.setCheckNum(vo.getCheckNum() + System.currentTimeMillis());
        vo.setPassword(vo.getPassword() + System.currentTimeMillis());
        vo.setUserName(vo.getUserName() + System.currentTimeMillis());
        return ResultVo.ok().put("user", vo);
    }
}