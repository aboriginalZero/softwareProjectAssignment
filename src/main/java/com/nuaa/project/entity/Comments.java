package com.nuaa.project.entity;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;
/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:19
 * @Description:评论类
 */
//声明每个持久化POJO类都是一个实体Bean
@Entity
//声明每个持久化POJO类都是一个实体Bean
@Table(name = "my_comments")
//lombok的一款插件，可以免写很多方法，如 Get,Set,toString等
@Data
public class Comments implements java.io.Serializable{
    //唯一标识，指定表的主键
    @Id
    //设置自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //内容
    private String content;
    //创建时间
    //格式化日期格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    //对应的新闻
    //定义多对一的关系
    @ManyToOne
    //用news_id列来存储
    @JoinColumn(name = "news_id")
    //防止关系对象的递归访问
    @JsonBackReference
    private News news;
    //对应的用户
    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonBackReference
    private Users users;

}
