package com.XuanTruong.cooking;

import com.XuanTruong.cooking.DTO.CommentDTO;
import com.XuanTruong.cooking.entity.Comment;
import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.reponsitory.ICommentRepository;
import com.XuanTruong.cooking.reponsitory.IRoleRepository;
import com.XuanTruong.cooking.security.JwtTokenProvider;
import com.XuanTruong.cooking.service.CommentService;
import io.jsonwebtoken.Claims;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootTest
class CookingApplicationTests {
    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    CommentService commentService;

    @Test
    void contextLoads() {

    }

}
