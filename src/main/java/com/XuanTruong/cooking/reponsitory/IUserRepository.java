package com.XuanTruong.cooking.reponsitory;

import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
    @Query("select p from User p where p.userId = :id")
    User findUsersById(@Param("id")int id);
    @Query("select p from User p where p.email = :email")
    User findUsersByEmail(@Param("email")String email);
    @Query("select p.userId from User p where p.userName = :name")
    Integer findUsersIdByUserName(@Param("name")String name);
    @Query("select p.userName from User p where p.userId = :id")
    String findUsersNameById(@Param("id")int id);
    @Query("select u from User u where u.userId in (:usersId)")
    List<User> findUsersById(@Param("usersId")List<Integer> usersId);



}

