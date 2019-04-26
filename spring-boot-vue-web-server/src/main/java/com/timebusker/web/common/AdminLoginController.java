package com.timebusker.web.common;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.web.ResultVo;
import com.timebusker.constant.CommonKey;
import com.timebusker.service.cache.SysUserKey;
import com.timebusker.service.common.SysUserService;
import com.timebusker.utils.MD5Utils;
import com.timebusker.web.AbstractBaseController;
import com.timebusker.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @DESC:AdminLoginController
 * @author:timebusker
 * @date:2019/3/21
 */
@RestController
@RequestMapping("/")
public class AdminLoginController extends AbstractBaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVo login(@RequestBody UserVo vo) {
        ResultVo resultVo = sysUserService.doLogin(vo);
        if ("0".equals(resultVo.get("code").toString())) {
            token = MD5Utils.MD5Encode(resultVo.get("user"));
            addCookie(CommonKey.COOKIE_NAME_TOKEN, token);
            redisService.set(SysUserKey.TOKEN, token, resultVo.get("user"));
        }
        return resultVo;
    }
}