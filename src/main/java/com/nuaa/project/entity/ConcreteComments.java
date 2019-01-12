package com.nuaa.project.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: cyw35
 * @Date: 2019/1/2 15:49
 * @Description:具体评论类，方便前后台的数据传输
 */
@Data
public class ConcreteComments {
    private String content;
    private String userName;
    private Date createdate;


    public ConcreteComments(String content, String userName, Date createdate) {

        this.content = content;
        this.userName = userName;
        this.createdate = createdate;
    }
}
