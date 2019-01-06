package com.nuaa.project.repository;

import com.nuaa.project.entity.News;
import com.nuaa.project.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Auther: cyw35
 * @Date: 2018/12/18 18:52
 * @Description:
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("select t from News t where t.title like :title")
    Page<News> findByTitle(@Param("title") String title, Pageable pageable);

    @Query("select t from News t where t.title like :title")
    List<News> findByTitle(@Param("title") String title);



    News findById(Long id);
}
