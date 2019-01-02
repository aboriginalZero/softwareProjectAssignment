package com.nuaa.project.config;

import com.nuaa.project.security.*;
import com.nuaa.project.service.CustomUserDetailsService;
import com.nuaa.project.security.LoginSuccessHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableConfigurationProperties(SecuritySettings.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected Log log = LogFactory.getLog(getClass());
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecuritySettings settings;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    //调用自定义的用户认证Service,并且指定了使用密码的加密算法
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        //remember me
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        设置一个使用自定义的登录页面URL
        http    .formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login").successHandler(loginSuccessHandler())
                .and().authorizeRequests()
                .antMatchers("/images/**", "/checkcode", "/scripts/**", "/styles/**").permitAll()
                .antMatchers(settings.getPermitall().split(",")).permitAll()
                .anyRequest().authenticated()
//                防止跨站请求伪造攻击的策略设置
                .and().csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
//                设置自定义的一个登录成功处理器
                .and().logout().logoutSuccessUrl(settings.getLogoutsuccssurl())
                //配置一个拒绝访问的提示链接
                .and().exceptionHandling().accessDeniedPage(settings.getDeniedpage())
//                用来记住用户登录状态的一个配置,86400即一天
                .and().rememberMe().tokenValiditySeconds(86400).tokenRepository(tokenRepository());
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    为了记住用户登陆状态，需要将一个用户的登录令牌等信息保存在数据库中
    //    以下指定连接数据库的数据源
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl jtr = new JdbcTokenRepositoryImpl();
        jtr.setDataSource(dataSource);
        return jtr;
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    //权限管理过滤器
    //在系统启动时就开始工作
    @Bean
    public CustomFilterSecurityInterceptor customFilter() throws Exception {
        CustomFilterSecurityInterceptor customFilter = new CustomFilterSecurityInterceptor();
//        导入权限管理决断器,资源管理器
        customFilter.setSecurityMetadataSource(securityMetadataSource());
        customFilter.setAccessDecisionManager(accessDecisionManager());
        customFilter.setAuthenticationManager(authenticationManager);
        return customFilter;
    }

    //权限管理决断器
    @Bean
    public CustomAccessDecisionManager accessDecisionManager() {
        return new CustomAccessDecisionManager();
    }

    //权限配置资源管理器
    @Bean
    public CustomSecurityMetadataSource securityMetadataSource() {
        return new CustomSecurityMetadataSource(settings.getUrlroles());
    }

    //只要链接地址中包含"/rest"字符串,就将对其忽略CSRF保护策略
    private CsrfSecurityRequestMatcher csrfSecurityRequestMatcher() {
        CsrfSecurityRequestMatcher csrfSecurityRequestMatcher = new CsrfSecurityRequestMatcher();
        List<String> list = new ArrayList<String>();
        //加入需要排除阻止CSRF攻击的链接列表
        list.add("/rest/");
        csrfSecurityRequestMatcher.setExecludeUrls(list);
        return csrfSecurityRequestMatcher;
    }
}
