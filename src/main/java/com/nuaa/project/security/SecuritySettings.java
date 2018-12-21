package com.nuaa.project.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:15
 * @Description:
 */

//这个注解设定配置参数的前缀部分为securityconfig
@ConfigurationProperties(prefix="securityconfig")
public class SecuritySettings {
//    用来定义退出成功的链接
    private String logoutsuccssurl = "/logout";
//    用来定义允许访问的URL列表
    private String permitall = "/api";
//    用来设定拒绝访问的信息提示链接
    private String deniedpage = "/deny";
//    权限管理规则，是链接地址角色权限的配置列表
    private String urlroles;

    public String getLogoutsuccssurl() {
        return logoutsuccssurl;
    }

    public void setLogoutsuccssurl(String logoutsuccssurl) {
        this.logoutsuccssurl = logoutsuccssurl;
    }

    public String getPermitall() {
        return permitall;
    }

    public void setPermitall(String permitall) {
        this.permitall = permitall;
    }

    public String getDeniedpage() {
        return deniedpage;
    }

    public void setDeniedpage(String deniedpage) {
        this.deniedpage = deniedpage;
    }

    public String getUrlroles() {
        return urlroles;
    }

    public void setUrlroles(String urlroles) {
        this.urlroles = urlroles;
    }
}
