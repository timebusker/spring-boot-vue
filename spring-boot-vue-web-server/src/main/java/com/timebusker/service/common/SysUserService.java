package com.timebusker.service.common;

import com.timebusker.common.web.ResultVo;
import com.timebusker.model.common.SysUser;
import com.timebusker.web.vo.UserVo;

/**
 * @DESC:SysUserService:用户服务接口类
 * @author:timebusker
 * @date:2019/4/23
 */
public interface SysUserService {

    ResultVo doLogin(UserVo vo);

    SysUser userByToken(String token);

    SysUser userByName(String name);
}
