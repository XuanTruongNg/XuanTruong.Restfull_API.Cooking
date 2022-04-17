package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.DTO.UserDTO;
import com.XuanTruong.cooking.entity.CustomUserDetails;
import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.payload.*;
import com.XuanTruong.cooking.reponsitory.IDishesRepository;
import com.XuanTruong.cooking.reponsitory.IUserRepository;
import com.XuanTruong.cooking.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;
public class DishesService implements IDishesService{
    @Autowired
    ModelMapper mapper;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    IDishesRepository dishesRepository;
    @Autowired
    IUserRepository userRepository;

    private Integer getCurrentUserId(){
        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserName = userDetails.getUsername();
        return userRepository.findUsersIdByUserName(currentUserName);
    }
    private  Boolean checkOwnerAndExisted(Dishes dishes) {
        if (dishes == null) {
            return false;
        } else {
            if (dishes.getUserId().equals(getCurrentUserId())) {
                return true;
            } else {
                return false;
            }
        }
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
        if(checkOwnerAndExisted(dishes)){;
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
        return null;
    }

    @Override
    public Dishes getDishesById(Integer id) {

        return null;
    }
    public  DishesDTO getDishesDTOById(Integer id){
        Dishes dishes = dishesRepository.getById(id);
        User owner = userRepository.getById(dishes.getUserId());
        DishesDTO dishesDTO = mapper.map(dishes,DishesDTO.class);
        UserDTO userDTO = mapper.map(owner,UserDTO.class);
        dishesDTO.setOwner(userDTO);
        return dishesDTO;
    }

//    @Override
//    public Integer getUserId(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        String jwt = bearerToken.substring(7);
//        return tokenProvider.getUserIdFromJWT(jwt);
//    }
}
