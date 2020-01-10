package com.timebusker.security;

import com.timebusker.model.RoleEntity;
import com.timebusker.model.UserEntity;
import com.timebusker.repository.RoleRepository;
import com.timebusker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: CustomUserDetailsService
 * @Author: Administrator
 * @Date: 2020/1/7 15:22
 **/
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        UserEntity user = userRepository.queryByLoginName(loginName);
        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 添加权限
        List<RoleEntity> permissions = roleRepository.queryByUserId(user.getId());
        for (RoleEntity permission : permissions) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getSymbol());
            authorities.add(grantedAuthority);
        }
        // 返回UserDetails实现类
        return new User(user.getLoginName(), user.getPassword(), authorities);
    }
}
