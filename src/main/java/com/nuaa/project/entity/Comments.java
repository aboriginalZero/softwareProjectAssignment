package com.nuaa.project.entity;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:19
 * @Description:
 */
@Entity
@Table(name = "my_comments")
public class Comments implements java.io.Serializable{
    //唯一标识
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //内容
    private String content;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    //对应的新闻
    @ManyToOne
    @JoinColumn(name = "news_id")
    @JsonBackReference
    private News news;
    //对应的用户
    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonBackReference
    private Users users;


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

    public Comments() {

    }
}
