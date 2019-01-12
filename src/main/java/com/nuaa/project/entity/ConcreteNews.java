package com.nuaa.project.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Auther: cyw35
 * @Date: 2019/1/2 15:47
 * @Description:具体新闻类，便于前后台交互
 */
@Data
public class ConcreteNews {
    private String title;
    private String content;
    private Date createdate;
    private List<ConcreteComments> concreteCommentsList;

    public ConcreteNews(String title, String content, Date createdate, List<ConcreteComments> concreteCommentsList) {
        this.title = title;
        this.content = content;
        this.createdate = createdate;
        this.concreteCommentsList = concreteCommentsList;
    }

    public ConcreteNews() {
    }
}
