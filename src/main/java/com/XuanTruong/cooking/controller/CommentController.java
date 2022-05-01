package com.XuanTruong.cooking.controller;

import com.XuanTruong.cooking.DTO.CommentDTO;
import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    ICommentService commentService;
    @GetMapping("/comment/{dishesId}")
    public List<CommentDTO> getComments(@PathVariable(name="dishesId") Integer dishesId){
        return commentService.getCommnetByDishesId(dishesId);
    }

}
