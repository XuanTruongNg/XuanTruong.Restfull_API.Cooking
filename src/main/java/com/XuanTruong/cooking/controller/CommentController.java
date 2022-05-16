package com.XuanTruong.cooking.controller;

import com.XuanTruong.cooking.DTO.CommentDTO;
import com.XuanTruong.cooking.message.Sort_Order;
import com.XuanTruong.cooking.service.ICommentService;
import com.sun.mail.util.ASCIIUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.XuanTruong.cooking.message.Sort_Order.ASC;

@RestController
public class CommentController {
    @Autowired
    ICommentService commentService;
    @GetMapping("/comment")
    public List<CommentDTO> getComments(@RequestParam(name ="dishesId",required = true ) Integer dishesId,
                                        @RequestParam(name = "page",required = false,defaultValue = "0" ) Integer page,
                                        @RequestParam(name = "size",required = false,defaultValue = "5") Integer size,
                                        @RequestParam(name = "sortBy",required = false,defaultValue = "id") String sortBy,
                                        @RequestParam(name = "order",required = false,defaultValue = "ASC") String order

    ){
        Sort sortable = null;
        if (order.equals(Sort_Order.ASC.name())){
            sortable = Sort.by(sortBy).ascending();
        }
        if (order.equals(Sort_Order.DESC.name())) {
            sortable = Sort.by(sortBy).descending();
        }
        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);
        return commentService.getCommnetByDishesId(dishesId,pageable);
    }


}
