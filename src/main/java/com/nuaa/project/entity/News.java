package com.nuaa.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:16
 * @Description:
 */
@Entity
@Table(name = "my_news")
public class News implements java.io.Serializable{
    //唯一标识
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    //评论
//    @OneToMany(mappedBy = "content")
//    private Set<Comments> commentsSet = new HashSet<>();

    public News() {
    }


//    public Set<Comments> getCommentsSet() {
//        return commentsSet;
//    }
//
//    public void setCommentsSet(Set<Comments> commentsSet) {
//        this.commentsSet = commentsSet;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
