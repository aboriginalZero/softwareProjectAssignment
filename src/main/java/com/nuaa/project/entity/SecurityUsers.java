package com.nuaa.project.entity;

import com.nuaa.project.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:16
 * @Description:安全检验时用到的用户类
 */
public class SecurityUsers extends Users implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUsers(Users users) {
        if (users != null) {
            this.setId(users.getId());
            this.setName(users.getName());
            this.setEmail(users.getEmail());
            this.setPassword(users.getPassword());
            this.setCreatedate(users.getCreatedate());
            this.setRole(users.getRole());
        }
    }

    //    用来取得为用户分配的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        String role = this.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
