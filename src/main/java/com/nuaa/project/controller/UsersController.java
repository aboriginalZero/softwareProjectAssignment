package com.nuaa.project.controller;

import com.nuaa.project.entity.Users;
import com.nuaa.project.model.UsersQo;
import com.nuaa.project.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 22:59
 * @Description:
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    private static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersRepository usersRepository;


    @RequestMapping("/index")
    public String index(Model model, Users users) {
        model.addAttribute("users", users);

        model.addAttribute("newusers", true);
        model.addAttribute("editusers", true);
        model.addAttribute("deleteusers", true);

        return "users/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Page<Users> getList(UsersQo usersQo) {
        try {
            Pageable pageable = new PageRequest(usersQo.getPage(), usersQo.getSize(), new Sort(Sort.Direction.ASC, "id"));
            return usersRepository.findByName(usersQo.getName() == null ? "%" : "%" + usersQo.getName() + "%", pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/{id}")
    public String show(Model model, @PathVariable Long id) {
        Users users = usersRepository.findOne(id);
        model.addAttribute("users", users);
        return "users/show";
    }

    @RequestMapping("/new")
    public String create(Model model,Users users){
        model.addAttribute("users",users);
        return "users/new";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Users users) throws Exception{
        users.setCreatedate(new Date());
        usersRepository.save(users);
        logger.info("新增->ID="+users.getId());
        return "1";
    }

    @RequestMapping(value="/edit/{id}")
    public String update(ModelMap model,@PathVariable Long id){
        Users users = usersRepository.findOne(id);

        model.addAttribute("users",users);
        return "users/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public String update(Users users) throws Exception{
        usersRepository.save(users);
        logger.info("修改->ID="+ users.getId());
        return "1";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception{
        usersRepository.delete(id);
        logger.info("删除->ID="+id);
        return "1";
    }

    @RequestMapping("/demo")
    public String index(Model model) {
        List<Users> usersList = usersRepository.findAll();
        model.addAttribute("users", usersList);
        return "users/demo";
    }
}
