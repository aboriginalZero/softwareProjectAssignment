package com.nuaa.project.repository;

import com.nuaa.project.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 19:13
 * @Description:
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    @Query("select  t from Users t where t.name like :name")
    Page<Users> findByName(@Param("name") String name, Pageable pageRequest);

    Users findById(Long id);

    Users findByName(String name);
}
