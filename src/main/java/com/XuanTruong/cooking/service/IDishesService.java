package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.payload.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IDishesService {
    DishesCreatorResponse createDishes(DishesRequest dishesRequest);
    DishesDeleteResponse deleteDishes(Integer dishesId);
    DishesUpdatingResponse updateDishes(DishesUpdateRequest dishesUpdateRequestRequest);
    List<DishesDTO> getDishesByName(String name);
    Dishes getDishesById(Integer id);
}
