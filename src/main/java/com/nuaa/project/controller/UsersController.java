package com.nuaa.project.controller;

import com.nuaa.project.entity.*;
import com.nuaa.project.model.UsersQo;
import com.nuaa.project.repository.CommentsRepository;
import com.nuaa.project.repository.NewsRepository;
import com.nuaa.project.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 22:59
 * @Description:用户控制器
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    private static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Value("${securityconfig.urlroles}")
    private String urlroles;


    @RequestMapping("/allNews")
    public String allNews(Model model, Principal users) {
        Authentication authentication = (Authentication) users;
        List<String> userroles = new ArrayList<>();
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            userroles.add(ga.getAuthority());
        }

        boolean editrole = false;
        if (!StringUtils.isEmpty(urlroles)) {
            String[] resouces = urlroles.split(";");
            for (String resource : resouces) {
                String[] urls = resource.split("=");
                if (urls[0].indexOf("edit") > 0) {
                    String[] editoles = urls[1].split(",");
                    for (String str : editoles) {
                        str = str.trim();
                        if (userroles.contains(str)) {
                            editrole = true;
                            break;
                        }
                    }
                }
            }
        }

        List<News> newsList = newsRepository.findAll();
        model.addAttribute("editrole", editrole);
        model.addAttribute("username", users.getName());
        model.addAttribute("newsList", newsList);
        return "allNews";
    }

    @RequestMapping("/oneNews/{id}")
    public String oneNews(Model model, Principal users, @PathVariable Long id) {
        ConcreteNews concreteNews = new ConcreteNews();

        News news = newsRepository.findById(id);
//        System.out.println("新闻名称:" + news.getTitle());
        concreteNews.setTitle(news.getTitle());
        concreteNews.setContent(news.getContent());
        concreteNews.setCreatedate(news.getCreatedate());

        List<ConcreteComments> concreteCommentsList = new ArrayList<>();

        List<Comments> commentsList = commentsRepository.findAllByNewsId(id);
        int len = commentsList.size();
//        System.out.println("评论数：" + len);
        for (int i = 0; i < len; i++) {
//            System.out.println("评论内容为：" + commentsList.get(i).getContent());
//            System.out.println("评论用户为：" + commentsList.get(i).getUsers().getName());
            Comments comments = new Comments();
            comments = commentsList.get(i);
            concreteCommentsList.add(new ConcreteComments(comments.getContent(), comments.getUsers().getName(), comments.getCreatedate()));
        }
        concreteNews.setConcreteCommentsList(concreteCommentsList);
        model.addAttribute("concreteNews", concreteNews);
        model.addAttribute("usersName", users.getName());
        return "oneShow";
    }

    @RequestMapping("/{id}")
    public String show(Model model, @PathVariable Long id) {
        Users users = usersRepository.findOne(id);
        model.addAttribute("users", users);
        return "users/show";
    }

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


    @RequestMapping("/new")
    public String create(Model model, Users users) {
        model.addAttribute("users", users);
        return "users/new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Users users) throws Exception {
        users.setCreatedate(new Date());
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        System.out.println("加密前" + users.getPassword());
        users.setPassword(bpe.encode(users.getPassword()));
        System.out.println("加密前" + users.getPassword());
        usersRepository.save(users);
        logger.info("新增->ID=" + users.getId());
        return "1";
    }

    @RequestMapping(value = "/edit/{id}")
    public String update(ModelMap model, @PathVariable Long id) {
        Users users = usersRepository.findOne(id);

        model.addAttribute("users", users);
        return "users/edit";
    }

    @RequestMapping(value = "/updateInfo/{usersName}")
    public String updateInfo(ModelMap model, @PathVariable String usersName) {
        Users users = usersRepository.findByName(usersName);
        System.out.println("用户名为:" + usersName);
        model.addAttribute("users", users);
        return "updateInfo";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public String update(Users users) throws Exception {
        Long id = users.getId();
        Users oldUsers = usersRepository.findOne(id);
        oldUsers.setName(users.getName());
        oldUsers.setEmail(users.getEmail());

        usersRepository.save(oldUsers);
        logger.info("修改->ID=" + oldUsers.getId());
        return "1";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception {
        usersRepository.delete(id);
        logger.info("删除->ID=" + id);
        return "1";
    }

    @RequestMapping("/demo")
    public String index(Model model) {
        List<Users> usersList = usersRepository.findAll();
        model.addAttribute("users", usersList);
        return "users/demo";
    }
}
