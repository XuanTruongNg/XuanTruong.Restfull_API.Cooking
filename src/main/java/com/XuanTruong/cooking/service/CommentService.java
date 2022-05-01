package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.CommentDTO;
import com.XuanTruong.cooking.DTO.UserDTO;
import com.XuanTruong.cooking.entity.Comment;
import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.reponsitory.ICommentRepository;
import com.XuanTruong.cooking.reponsitory.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ModelMapper mapper;
    @Override
    public List<CommentDTO> getCommnetByDishesId(Integer dishesId) {
        List<Comment> comments = commentRepository.findCommentByDishesId(dishesId);
        List<Integer> userIds = comments.stream().map(Comment::getUserId).collect(Collectors.toList());
        List<User> users = userRepository.findUsersById(userIds);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment : comments){
            CommentDTO commentDTO = mapper.map(comment,CommentDTO.class);
            for (User user: users){
                if( comment.getUserId().equals(user.getUserId())){
                    UserDTO  userDTO = mapper.map(user,UserDTO.class);
                    commentDTO.setOwner(userDTO);
                }
            }
            commentDTOS.add(commentDTO);

        }
        return commentDTOS;
    }
}
