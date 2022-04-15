package com.XuanTruong.cooking.reponsitory;

import com.XuanTruong.cooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
    @Query("select p from User p where p.userId = :id")
    User findUsersById(@Param("id")int id);
    @Query("select p from User p where p.email = :email")
    User findUsersByEmail(@Param("email")String email);






}

