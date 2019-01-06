package com.nuaa.project.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 19:07
 * @Description:
 */
public class UsersQo extends PageQo{
    private Long id;
    private String name;
    private String password;
    private String email;
    private String role;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public UsersQo() {

    }
}
