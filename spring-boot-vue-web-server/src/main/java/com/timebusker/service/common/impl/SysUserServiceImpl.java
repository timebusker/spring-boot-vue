package com.timebusker.service.common.impl;

import com.timebusker.common.redis.RedisService;
import com.timebusker.common.web.ResultVo;
import com.timebusker.mapper.common.SysUserMapper;
import com.timebusker.model.common.SysUser;
import com.timebusker.service.cache.SysUserKey;
import com.timebusker.service.common.SysUserService;
import com.timebusker.utils.MD5Utils;
import com.timebusker.web.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;

/**
 * @DESC:SysUserServiceImpl
 * @author:timebusker
 * @date:2019/4/23
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public ResultVo doLogin(UserVo vo) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", vo.getUserName());
        criteria.andEqualTo("password", MD5Utils.MD5Encode(vo.getPassword()));
        SysUser user = sysUserMapper.selectOneByExample(example);
        if (user != null) {
            return ResultVo.ok().put("user", user);
        } else {
            criteria = example.createCriteria();
            criteria.andEqualTo("username", vo.getUserName());
            user = sysUserMapper.selectOneByExample(example);
            if (user == null) {
                return ResultVo.error("用户不存在，请检查账户输入！");
            } else {
                return ResultVo.error("登录密码错误，请重新输入！");
            }
        }
    }

    @Override
    public SysUser userByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SysUser user = redisService.get(SysUserKey.TOKEN, token, SysUser.class);
        return user;
    }

    @Override
    public SysUser userByName(String name) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", name);
        SysUser user = sysUserMapper.selectOneByExample(example);
        return user;
    }
}
