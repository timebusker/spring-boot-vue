package com.timebusker.security;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description: CustomAuthenticationProvider
 * 自定义表单登录意味着表单参数不定且不止账户和密码两项，但 Spring security 的WebAuthenticationDetails默认只会处理用户名和密码信息
 * 自定义表单登录可采用一下三种方式：
 * 1、ajax验证：即在提交账户密码前先把其他参数校验处理好
 * 2、在账户密码校验器，使用filter对额外参数进行校验
 * 3、扩展WebAuthenticationDetails，支持额外参数校验
 * <p>
 * 在CustomAuthenticationDetailsSource中，我们完成了表单参数扩展，下面进行数据验证
 * @Author: Administrator
 * @Date: 2020/1/8 10:22
 **/
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    @Qualifier("simplePasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Kaptcha kaptcha;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        // 获取用户输入的用户名和密码
        String loginName = authentication.getName();
        String password = authentication.getCredentials().toString();
        String code = details.getCode();

        if (!validateCode(code)) {
            throw new KaptchaException();
        }
        // userDetails为数据库中查询到的用户信息
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginName);
        // 如果是自定义AuthenticationProvider，需要手动密码校验
        if (!passwordEncoder.matches(userDetails.getPassword(), password)) {
            throw new BadCredentialsException("密码错误");
        }
        return new UsernamePasswordAuthenticationToken(loginName, password, userDetails.getAuthorities());
    }

    /**
     * 验证码校验
     *
     * @return
     */
    private boolean validateCode(String code) {
        if (StringUtils.isBlank(code))
            return false;
        return kaptcha.validate(code);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 这里不要忘记，和UsernamePasswordAuthenticationToken比较
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        //return true;
    }
}
