package com.nuaa.project.controller;

import com.nuaa.project.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: cyw35
 * @Date: 2018/12/29 11:22
 * @Description:
 */
@Controller
@RequestMapping("/ordinary")
public class OrdinaryUsersController {
    private static Logger logger = LoggerFactory.getLogger(OrdinaryUsersController.class);

    @Autowired
    private UsersRepository usersRepository;

    @Value("${securityconfig.urlroles}")
    private String urlroles;

    @RequestMapping("/index")
    public String index(Model model, Principal users) {
        Authentication authentication = (Authentication) users;
        List<String> userroles = new ArrayList<>();
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            userroles.add(ga.getAuthority());
        }

        boolean newusers = false, editusers = false, deleteusers = false;
        if (!StringUtils.isEmpty(urlroles)) {
            String[] resouces = urlroles.split(";");
            for (String resource : resouces) {
                String[] urls = resource.split("=");
                if (urls[0].indexOf("new") > 0) {
                    String[] newroles = urls[1].split(",");
                    for (String str : newroles) {
                        str = str.trim();
                        if (userroles.contains(str)) {
                            newusers = true;
                            break;
                        }
                    }
                } else if (urls[0].indexOf("edit") > 0) {
                    String[] editoles = urls[1].split(",");
                    for (String str : editoles) {
                        str = str.trim();
                        if (userroles.contains(str)) {
                            editusers = true;
                            break;
                        }
                    }
                } else if (urls[0].indexOf("delete") > 0) {
                    String[] deleteroles = urls[1].split(",");
                    for (String str : deleteroles) {
                        str = str.trim();
                        if (userroles.contains(str)) {
                            deleteusers = true;
                            break;
                        }
                    }
                }
            }
        }

        model.addAttribute("users", users);

        model.addAttribute("newusers", newusers);
        model.addAttribute("editusers", editusers);
        model.addAttribute("deleteusers", deleteusers);

        return "ordinary/index";
    }

}
