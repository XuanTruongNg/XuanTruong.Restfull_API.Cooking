package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.payload.DishesCreatorResponse;
import com.XuanTruong.cooking.payload.DishesDeleteResponse;
import com.XuanTruong.cooking.payload.DishesRequest;
import com.XuanTruong.cooking.payload.DishesUpdatingResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface IDishesService {
    DishesCreatorResponse createDishes(DishesRequest dishesRequest,Integer userId);
    DishesDeleteResponse deleteDishes(Integer dishesId,Integer userId);
    DishesUpdatingResponse updateDishes(DishesRequest dishesRequest,Integer userId);
    List<DishesDTO> getDishesByName(String name);
    Dishes getDishesById(Integer id);
    Integer getUserId(HttpServletRequest request);
}
