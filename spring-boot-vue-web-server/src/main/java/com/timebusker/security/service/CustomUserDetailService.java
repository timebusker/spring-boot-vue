package com.timebusker.security.service;

import com.timebusker.model.common.SysUser;
import com.timebusker.security.utils.CustomPasswordEncoder;
import com.timebusker.service.common.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @DESC:CustomUserDetailService：用户信息校验接口
 * @author:timebusker
 * @date:2019/4/26
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser user = sysUserService.userByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("not found user!");
        }

        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
        authorities.add(new SimpleGrantedAuthority("ROLE_*"));
        User userDetails = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), authorities);
        return userDetails;
    }
}
