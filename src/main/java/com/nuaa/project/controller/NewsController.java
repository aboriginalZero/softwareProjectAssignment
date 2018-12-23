package com.nuaa.project.controller;

import com.nuaa.project.entity.News;
import com.nuaa.project.entity.News;
import com.nuaa.project.model.NewsQo;
import com.nuaa.project.model.UsersQo;
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

/**
 * @Auther: cyw35
 * @Date: 2018/12/19 17:20
 * @Description:
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping("/index")
    public String index(Model model, Principal users) {

        model.addAttribute("users",users);
//        model.addAttribute("news", news);

        model.addAttribute("newnews", true);
        model.addAttribute("editnews", true);
        model.addAttribute("deletenews", true);

        return "news/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Page<News> getList(NewsQo newsQo) {
        try {
            Pageable pageable = new PageRequest(newsQo.getPage(), newsQo.getSize(), new Sort(Sort.Direction.ASC, "id"));
            return newsRepository.findByTitle(newsQo.getTitle() == null ? "%" : "%" + newsQo.getTitle() + "%", pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/{id}")
    public String show(Model model, @PathVariable Long id) {
        News news = newsRepository.findOne(id);
        model.addAttribute("news", news);
        return "news/show";
    }

    @RequestMapping("/new")
    public String create(Model model, News news) {
        model.addAttribute("news", news);
        return "news/new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(News news) throws Exception {
//        news.setCreatedate(new Date());
        newsRepository.save(news);
        logger.info("新增->ID=" + news.getId());
        return "1";
    }

    @RequestMapping(value = "/edit/{id}")
    public String update(ModelMap model, @PathVariable Long id) {
        News news = newsRepository.findOne(id);

        model.addAttribute("news", news);
        return "news/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public String update(News news) throws Exception {
        newsRepository.save(news);
        logger.info("修改->ID=" + news.getId());
        return "1";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception {
        newsRepository.delete(id);
        logger.info("删除->ID=" + id);
        return "1";
    }

}
