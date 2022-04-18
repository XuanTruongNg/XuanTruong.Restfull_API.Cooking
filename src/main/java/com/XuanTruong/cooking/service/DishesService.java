package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.CommentDTO;
import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.DTO.UserDTO;
import com.XuanTruong.cooking.entity.Comment;
import com.XuanTruong.cooking.entity.CustomUserDetails;
import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.payload.*;
import com.XuanTruong.cooking.reponsitory.ICommentRepository;
import com.XuanTruong.cooking.reponsitory.IDishesRepository;
import com.XuanTruong.cooking.reponsitory.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class DishesService implements IDishesService{
    @Autowired
    ModelMapper mapper;
    @Autowired
    IDishesRepository dishesRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ICommentRepository commentRepository;

    private Integer getCurrentUserId(){
        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserName = userDetails.getUsername();
        return userRepository.findUsersIdByUserName(currentUserName);
    }
    private  Boolean checkOwnerAndExisted(Dishes dishes) {
        if (dishes != null && dishes.getUserId().equals(getCurrentUserId())) {
            return true;
        }
        return false;
    }
    private  DishesDTO makeDishesDto(Dishes dishes){
        User owner = userRepository.getById(dishes.getUserId());
        // get list comment of this dishes
        List<Comment> comments = commentRepository.findCommentByDishesId(dishes.getId());
        List<CommentDTO> commentDTOS = new ArrayList<CommentDTO>();
        for( Comment comment:comments){
            String userName = userRepository.findUsersNameById(comment.getUserId());// list username?
            CommentDTO commentDTO = mapper.map(comment,CommentDTO.class);
            commentDTO.setUserName(userName);
            commentDTOS.add(commentDTO);
        }
        DishesDTO dishesDTO = mapper.map(dishes,DishesDTO.class);
        UserDTO userDTO = mapper.map(owner,UserDTO.class);
        dishesDTO.setComments(commentDTOS);
        dishesDTO.setOwner(userDTO);
        return dishesDTO;
    }
    @Override
    public DishesCreatorResponse createDishes(DishesRequest dishesRequest) {
        Dishes dishes = mapper.map(dishesRequest,Dishes.class);
        dishes.setCreatedAt(new Date());
        dishes.setUserId(getCurrentUserId());
        dishes.setStatus(true);
        dishesRepository.save(dishes);
        return new DishesCreatorResponse("ok");
    }
    @Override
    public DishesDeleteResponse deleteDishes(Integer dishesId) {
        Dishes dishes = dishesRepository.findDishesById(dishesId);
        if(checkOwnerAndExisted(dishes)){
            dishes.setStatus(false);
            dishesRepository.save(dishes);
            return new DishesDeleteResponse("oki");
        }else{
            return new DishesDeleteResponse("false");
        }
    }

    @Override
    public DishesUpdatingResponse updateDishes(DishesUpdateRequest dishesUpdateRequest) {
        Dishes dishes = dishesRepository.findDishesById(dishesUpdateRequest.getId());
        if(checkOwnerAndExisted(dishes)){
            dishes.setDishesName(dishesUpdateRequest.getDishesName());
            dishes.setDishesDisc(dishesUpdateRequest.getDishesDisc());
            dishes.setSummary(dishesUpdateRequest.getSummary());
            dishes.setMediaUrl(dishesUpdateRequest.getMediaUrl());
            dishes.setMediaName(dishesUpdateRequest.getMediaName());
            dishes.setUpdatedAt(new Date());
            dishesRepository.save(dishes);
        }
        return null;
    }

    @Override
    public List<DishesDTO> getDishesByName(String name) {
        List<Dishes> dishes= dishesRepository.findDishesByName(name);
        List<DishesDTO> dishesDTOS = new ArrayList<DishesDTO>();
        for(Dishes dishes1: dishes){
            dishesDTOS.add(makeDishesDto(dishes1));
        }
        return dishesDTOS;
    }

    @Override
    public DishesDTO getDisheDTOById(Integer id) {
        Dishes dishes = dishesRepository.getById(id);
        return makeDishesDto(dishes);
    }


}
