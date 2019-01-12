package com.nuaa.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:16
 * @Description:这个表结构的定义是由Spring Security提供的
 * 用来保存令牌,用户名,token和最后登录时间
 */
@Entity
@Table(name = "persistent_logins")
@Data
public class PersistentLogins implements java.io.Serializable{
    @Id
    @Column(name = "series", length = 64, nullable = false)
    private String series;
    @Column(name = "username", length = 64, nullable = false)
    private String username;
    @Column(name = "token", length = 64, nullable = false)
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_used", nullable = false)
    private Date last_used;


}
