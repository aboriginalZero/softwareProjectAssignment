package com.nuaa.project.service;

import com.nuaa.project.entity.SecurityUsers;
import com.nuaa.project.entity.Users;
import com.nuaa.project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 18:52
 * @Description:自定义的用户认证
 */
//Spring Boot 将这些类纳入Spring容器中管理
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    //返回自定义的Security-User,通过它来完成用户的身份认证
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = userRepository.findByName(userName);
        if (users == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        return new SecurityUsers(users);
    }
}
