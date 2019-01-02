package com.nuaa.project.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:21
 * @Description:
 */
@Entity
@Table(name = "my_users")
public class Users implements java.io.Serializable {
    //唯一标识
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //用户名
    private String name;
    //角色
//    private String role;
    //密码
    private String password;
    //邮箱
    private String email;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    //评论
//    @OneToMany(mappedBy = "content")
//    private Set<Comments> commentsSet = new HashSet<>();

    public Users() {
    }

//    public Set<Comments> getCommentsSet() {
//        return commentsSet;
//    }
//
//    public void setCommentsSet(Set<Comments> commentsSet) {
//        this.commentsSet = commentsSet;
//    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
