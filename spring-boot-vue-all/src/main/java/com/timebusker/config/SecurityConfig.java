package com.timebusker.config;

import com.timebusker.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Description: SecurityConfig 安全控制配置
 * @Author: Administrator
 * @Date: 2020/1/7 14:34
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 不能将自定义登录请求的URL放入忽略URL列表中，否则导致登录请求失败
     */
    private static final String[] IGNORE_PATHS = new String[]{"/img/**", "/css/**", "/js/**", "/libs/**", "/views/**"};

    private static final boolean IS_EXTEND_FORM = true;

    /**
     * 自定义实现用户信息查询
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * 自定义表单扩展器
     */
    @Autowired
    private CustomAuthenticationDetailsSource authenticationDetailsSource;

    /**
     * 自定义校验器
     */
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    /**
     * 注入TOKEN校验
     */
    @Autowired
    private CustomPersistentTokenRepository persistentTokenRepository;
    @Autowired
    private DataSource dataSource;

    /**
     * 设置权限授权控制信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //开启登录配置
                .authorizeRequests()
                // 验证码允许响应所有请求
                .antMatchers("/auth/code").permitAll()
                // 其他接口，登录之后就能访问
                .anyRequest().authenticated()
                // 定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
                .and()
                .formLogin()
                .loginPage("/auth/login").loginProcessingUrl("/auth/process")
                //和表单登录相关的接口统统都直接通过
                .permitAll()
                // 使用自定义authenticationDetailsSource，实现表单扩展，完成表单参数扩展
                .authenticationDetailsSource(authenticationDetailsSource)
                // 自定义登陆用户名和密码参数，默认为username和password
                .usernameParameter("loginName").passwordParameter("password")
                // 登录成功或者失败处理器
                .successHandler(new CustomAuthenticationSuccessHandler()).failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/auth/logout").clearAuthentication(true).deleteCookies("rememberMe").invalidateHttpSession(true).logoutSuccessUrl("/auth/login")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .permitAll()
                .and()
                .httpBasic()
                // 自动登录
                // Cookie存储:以下模式即可
                // 数据库存储:自动登录时，用 Cookie 中的加密串，到数据库中验证，如果通过，自动登录才算通过。
                .and()
                // 开启自动登录，修改参数名(cookie存储)
                // .rememberMe()
                // 使用数据库持久化验证
                .rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(300).userDetailsService(userDetailsService).rememberMeParameter("rememberMe").rememberMeCookieName("rememberMe")
                // 设置session管理
                .and()
                .sessionManagement().invalidSessionUrl("/auth/login")
                // 当达到最大值时，是否保留已经登录的用户
                .maximumSessions(1).maxSessionsPreventsLogin(false).expiredSessionStrategy(new CustomExpiredSessionStrategy());
        // 关闭CSRF跨域
        http.csrf().disable();
    }

    /**
     * 设置账户认证信息:
     * 内存、配置文件、数据库
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        if (IS_EXTEND_FORM) {
            // 扩展表单自定义验证
            auth.authenticationProvider(authenticationProvider);
        } else {
            auth.userDetailsService(userDetailsService).passwordEncoder(simplePasswordEncoder());
        }
    }

    /**
     * 访问拦截忽略设置
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(IGNORE_PATHS);
    }

    /**
     * 设置密码加密策略
     *
     * @return
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置密码加密策略
     * String 继承于CharSequence，也就是说String也是CharSequence类型。
     *
     * @return
     */
    @Bean("simplePasswordEncoder")
    public PasswordEncoder simplePasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence password) {
                return password.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword.toString());
            }
        };
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }
}
