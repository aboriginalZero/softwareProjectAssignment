package com.nuaa.project.model;

import com.nuaa.project.entity.News;
import com.nuaa.project.entity.Users;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 18:57
 * @Description:
 */
public class CommentsQo extends PageQo {
    private Long id;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdate;
    private News news;
    private Users users;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public CommentsQo() {

    }
}
