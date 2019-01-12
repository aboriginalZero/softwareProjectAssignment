package com.nuaa.project.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:21
 * @Description:用户类
 */
@Entity
@Table(name = "my_users")
@Data
public class Users implements java.io.Serializable {
    //唯一标识
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //用户名
    private String name;
    //角色
    private String role;
    //密码
    private String password;
    //邮箱
    private String email;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    public Users() {
    }
}
