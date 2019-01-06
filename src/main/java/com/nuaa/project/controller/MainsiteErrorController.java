package com.nuaa.project.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: cyw35
 * @Date: 2018/12/19 22:44
 * @Description:错误处理控制器
 */
@Controller
public class MainsiteErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "403";
    }

    @Override
    public String getErrorPath() {
        return "403";
    }

    @RequestMapping(value="/deny")
    public String deny(){
        return "deny";
    }
}
