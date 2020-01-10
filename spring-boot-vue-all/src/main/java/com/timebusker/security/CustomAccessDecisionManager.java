package com.timebusker.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Description: CustomAccessDecisionManager
 * @Author: Administrator
 * @Date: 2020/1/10 14:57
 **/
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    /**
     * decide 方法是判定是否拥有权限的决策方法，authentication是CustomUserService中循环添加到 GrantedAuthority 对象中的权限信息集合,
     * object 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * configAttributes为MyFilterInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果.
     *
     * @param authentication
     * @param object
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configAttributes || configAttributes.size() <= 0) {
            return;
        }
        ConfigAttribute attribute;
        String needRole;
        for (Iterator<ConfigAttribute> iterator = configAttributes.iterator(); iterator.hasNext(); ) {
            attribute = iterator.next();
            needRole = attribute.getAttribute();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                // authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                if (needRole.equals(authority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("用户操作权限不足！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
