package com.nuaa.project.entity;

import java.util.Date;

/**
 * @Auther: cyw35
 * @Date: 2019/1/2 15:49
 * @Description:具体评论类，方便前后台的数据传输
 */
public class ConcreteComments {
    private String content;
    private String userName;
    private Date createdate;

    public ConcreteComments() {
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public ConcreteComments(String content, String userName, Date createdate) {

        this.content = content;
        this.userName = userName;
        this.createdate = createdate;
    }
}
