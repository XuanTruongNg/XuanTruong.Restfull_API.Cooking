package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.DTO.UserDTO;
import com.XuanTruong.cooking.entity.CustomUserDetails;
import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.message.Status;
import com.XuanTruong.cooking.payload.*;
import com.XuanTruong.cooking.reponsitory.ICommentRepository;
import com.XuanTruong.cooking.reponsitory.IDishesRepository;
import com.XuanTruong.cooking.reponsitory.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    private User getCurrentUser(){
        String currentUserName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(currentUserName);
    }
    private  Boolean checkOwnerAndExisted(Dishes dishes) {
        if (dishes != null && dishes.getUserId().equals(getCurrentUser().getUserId())) {
            return true;
        }
        return false;
    }
    private  DishesDTO makeDishesDto(Dishes dishes){
        User owner = userRepository.getById(dishes.getUserId());
        DishesDTO dishesDTO = mapper.map(dishes,DishesDTO.class);
        UserDTO userDTO = mapper.map(owner,UserDTO.class);
        dishesDTO.setOwner(userDTO);
        return dishesDTO;
    }
    @Override
    public DishesResponse createDishes(DishesRequest dishesRequest) {
        Dishes dishes = mapper.map(dishesRequest,Dishes.class);
        dishes.setCreatedAt(new Date());
        dishes.setUserId(getCurrentUser().getUserId());
        dishes.setStatus(true);
        dishesRepository.save(dishes);

        DishesDTO dishesDTO = mapper.map(dishes,DishesDTO.class);
        DishesResponse dishesResponse = new DishesResponse();
        dishesResponse.setDishesDTO(dishesDTO);
        dishesResponse.setStatus(Status.SUCCESSFULL);
        return dishesResponse;
    }
    @Override
    public Status deleteDishes(Integer dishesId) {
        Dishes dishes = dishesRepository.findDishesById(dishesId);
        if(checkOwnerAndExisted(dishes)){
            dishes.setStatus(false);
            dishesRepository.save(dishes);
            return Status.SUCCESSFULL;
        }else{
            return Status.FAILS;
        }
    }

    @Override
    public DishesResponse updateDishes(DishesRequest dishesUpdateRequest) {
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
        DishesDTO dishesDTO = makeDishesDto(dishes);
        DishesResponse dishesResponse = new DishesResponse();
        dishesResponse.setDishesDTO(dishesDTO);
        dishesResponse.setStatus(Status.SUCCESSFULL);
        return dishesResponse;
    }

    @Override
    public List<DishesDTO> getDishesByName(String name, Pageable pageable) {
        List<Dishes> dishes= dishesRepository.findDishesByName(name,pageable);
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
