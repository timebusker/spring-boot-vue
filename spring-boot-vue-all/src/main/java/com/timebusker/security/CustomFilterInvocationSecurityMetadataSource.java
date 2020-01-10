package com.timebusker.security;

import com.timebusker.model.vo.RolePermissionVO;
import com.timebusker.service.RoleResourceService;
import com.timebusker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description: CustomFilterInvocationSecurityMetadataSource:授权管理
 * @Author: Administrator
 * @Date: 2020/1/10 13:10
 **/
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private HashMap<String, Collection<ConfigAttribute>> map = new HashMap<String, Collection<ConfigAttribute>>();

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine() {
        List<RolePermissionVO> permissions = roleResourceService.getPermission();
        for (RolePermissionVO permission : permissions) {
            ConfigAttribute cfg = new SecurityConfig(permission.getSymbol());
            List<ConfigAttribute> list = new ArrayList<>();
            list.add(cfg);
            map.put(permission.getUrl(), list);
        }
    }

    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
     * 用来判定用户是否有此权限。如果不在权限表中则放行。
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null || map.isEmpty()) {
            loadResourceDefine();
        }
        // object 中包含用户请求的request的信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
