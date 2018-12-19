package com.nuaa.project;

import com.nuaa.project.entity.Comments;
import com.nuaa.project.entity.News;
import com.nuaa.project.entity.Users;
import com.nuaa.project.repository.CommentsRepository;
import com.nuaa.project.repository.NewsRepository;
import com.nuaa.project.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 18:52
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})

public class MysqlTest {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    NewsRepository newsRepository;

    @Test
    public void insertSomeData(){
        //建立外键的要先删除
        commentsRepository.deleteAll();
        usersRepository.deleteAll();
        newsRepository.deleteAll();


        News news = new News();
        news.setTitle("新闻标题");
        news.setContent("新闻内容");
        newsRepository.save(news);
        Assert.notNull(news.getId());

        Users users = new Users();
        users.setName("abc");
//        这边加密处理还没做
        users.setPassword("abc");
        users.setEmail("350446550@qq.com");
        usersRepository.save(users);
        Assert.notNull(users.getId());

        Users users1 = new Users();
        users1.setName("cyw");
//        这边加密处理还没做
        users1.setPassword("cyw");
        users1.setEmail("847681488@qq.com");
        usersRepository.save(users1);
        Assert.notNull(users1.getId());

        Comments comments = new Comments();
        comments.setContent("评论内容");
//        comments.setUsers(comments);
        comments.setNews(news);
        commentsRepository.save(comments);
        Assert.notNull(comments.getId());

        Comments comments1 = commentsRepository.findByContent("评论内容");
        Assert.notNull(comments1);

        Users users2 = usersRepository.findByName("cyw");
        Assert.notNull(users2);

        comments1.setUsers(users2);
        commentsRepository.save(comments1);
    }
}
