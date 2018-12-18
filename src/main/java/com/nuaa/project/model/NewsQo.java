package com.nuaa.project.model;

import com.nuaa.project.entity.Comments;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 18:55
 * @Description:
 */
public class NewsQo extends PageQo {
    private Long id;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

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

    public NewsQo() {

    }
}
