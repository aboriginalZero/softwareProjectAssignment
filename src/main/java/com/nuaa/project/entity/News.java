package com.nuaa.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 17:16
 * @Description:新闻类
 */
//声明每个持久化POJO类都是一个实体Bean
@Entity
//映射到数据库的数据表
@Table(name = "my_news")
//lombok的一款插件，可以免写很多方法
@Data
public class News implements java.io.Serializable {
    //唯一标识，指定表的主键
    @Id
    //设置自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;
}
