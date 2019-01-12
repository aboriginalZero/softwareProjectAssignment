package com.nuaa.project.controller;

import com.nuaa.project.entity.Comments;
import com.nuaa.project.entity.News;
import com.nuaa.project.entity.Users;
import com.nuaa.project.model.CommentsQo;
import com.nuaa.project.repository.CommentsRepository;
import com.nuaa.project.repository.NewsRepository;
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

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: cyw35
 * @Date: 2018/12/19 22:44
 * @Description:评论控制器
 */
@Controller
@RequestMapping("/comments")
public class CommentsController {

    private static Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UsersRepository usersRepository;


    @RequestMapping("/index")
    public String index(Model model, Principal users) {

        model.addAttribute("users", users);
        model.addAttribute("newcomments", true);
        model.addAttribute("editcomments", true);
        model.addAttribute("deletecomments", true);

        return "comments/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Page<Comments> getList(CommentsQo commentsQo) {
        try {
            Pageable pageable = new PageRequest(commentsQo.getPage(), commentsQo.getSize(), new Sort(Sort.Direction.ASC, "id"));
            return commentsRepository.findByContent(commentsQo.getContent() == null ? "%" : "%" + commentsQo.getContent() + "%", pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/{id}")
    public String show(Model model, @PathVariable Long id) {
        Comments comments = commentsRepository.findOne(id);
        model.addAttribute("comments", comments);
        return "comments/show";
    }

    @RequestMapping(value = "/myComments/{usersName}")
    public String myComments(Model model,@PathVariable String usersName) {
        Users users = usersRepository.findByName(usersName);
        List<Comments> commentsList = commentsRepository.findAllByUsersId(users.getId());
        model.addAttribute("commentsList", commentsList);
        return "myComments";
    }

    @RequestMapping(value = "/newComments")
    @ResponseBody
    public String newComments(String content, String title, String usersName) {
        System.out.println("内容是：" + content);
        Comments comments = new Comments();
        comments.setContent(content);
        comments.setCreatedate(new Date());
        comments.setUsers(usersRepository.findByName(usersName));
        comments.setNews(newsRepository.findByTitle(title).get(0));

        commentsRepository.save(comments);
        logger.info("新增->ID=" + comments.getId());
        return "1";
    }

    @RequestMapping("/new")
    public String create(Model model, Comments comments) {
        List<Users> usersList = usersRepository.findAll();
        List<News> newsList = newsRepository.findAll();

        model.addAttribute("usersList", usersList);
        model.addAttribute("newsList", newsList);
        model.addAttribute("comments", comments);
        return "comments/new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Comments comments) throws Exception {
//        comments.setCreatedate(new Date());
        commentsRepository.save(comments);
        logger.info("新增->ID=" + comments.getId());
        return "1";
    }

    @RequestMapping(value = "/edit/{id}")
    public String update(ModelMap model, @PathVariable Long id) {
        Comments comments = commentsRepository.findOne(id);

        List<Users> usersList = usersRepository.findAll();
        List<News> newsList = newsRepository.findAll();

        model.addAttribute("usersList", usersList);
        model.addAttribute("newsList", newsList);
        model.addAttribute("comments", comments);
        return "comments/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public String update(Comments comments) throws Exception {
        commentsRepository.save(comments);
        logger.info("修改->ID=" + comments.getId());
        return "1";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception {
        commentsRepository.delete(id);
        logger.info("删除->ID=" + id);
        return "1";
    }

}
