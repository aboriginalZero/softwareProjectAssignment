package com.nuaa.project.repository;

import com.nuaa.project.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 19:09
 * @Description:
 */
//将这个接口定义为一个资源库，供整个项目使用
//持久化组件，用于标注数据访问组件
@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {
    @Query("select t from Comments t where t.content like :content")
    Page<Comments> findByContent(@Param("content")String content, Pageable pageable);

    List<Comments> findAllByNewsId(Long id);

    List<Comments> findAllByUsersId(Long id);

    Comments findByContent(String content);
}
