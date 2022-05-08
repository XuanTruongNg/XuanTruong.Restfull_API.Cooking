package com.XuanTruong.cooking;

import com.XuanTruong.cooking.entity.Comment;
import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.reponsitory.ICommentRepository;
import com.XuanTruong.cooking.reponsitory.IRoleRepository;
import com.XuanTruong.cooking.security.JwtTokenProvider;
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

    @Test
    void contextLoads() {
        Claims claims = jwtTokenProvider.getBodyJWT("eyJhbGciOiJIUzUxMiJ9.eyJST0xFIjpbIlVTRVIiXSwic3ViIjoieHVhbnRydW9uZzIxOTIwMDEiLCJpYXQiOjE2NTIwMTY3MDQsImV4cCI6MTY1MjYyMTUwNH0.nk0r4GoOoQIPV3ymxXNg-k4te7P5aGItg5KrJ14tmc3Ph_P94XGy7OqfcSk17S_D-6bZcPUsAea0wekYALJdXg");
        System.out.println(claims.getSubject());

    }

}
