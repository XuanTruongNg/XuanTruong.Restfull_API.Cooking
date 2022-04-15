package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.DTO.UserDTO;
import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.payload.DishesCreatorResponse;
import com.XuanTruong.cooking.payload.DishesDeleteResponse;
import com.XuanTruong.cooking.payload.DishesRequest;
import com.XuanTruong.cooking.payload.DishesUpdatingResponse;
import com.XuanTruong.cooking.reponsitory.IDishesRepository;
import com.XuanTruong.cooking.reponsitory.IUserRepository;
import com.XuanTruong.cooking.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Override
    public DishesCreatorResponse createDishes(DishesRequest dishesRequest,Integer userId) {
        Dishes dishes = mapper.map(dishesRequest,Dishes.class);
        dishes.setCreatedAt(new Date());
        dishes.setUserId(userId);
        dishes.setStatus(true);
        dishesRepository.save(dishes);
        return new DishesCreatorResponse("ok");
    }

    @Override
    public DishesDeleteResponse deleteDishes(Integer dishesId,Integer userId) {
        Dishes dishes =dishesRepository.findDishesById(dishesId);

        if(dishes == null){
            return new DishesDeleteResponse("not Exist");
        }else{
            if(dishes.getUserId().equals(userId)){
                dishes.setStatus(false);
                dishesRepository.save(dishes);
                return new DishesDeleteResponse("success");
            }
            else{
                return new DishesDeleteResponse("can't delete");
            }
        }
    }

    @Override
    public DishesUpdatingResponse updateDishes(DishesRequest dishesRequest,Integer userId) {
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

    @Override
    public Integer getUserId(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String jwt = bearerToken.substring(7);
        return tokenProvider.getUserIdFromJWT(jwt);
    }
}
