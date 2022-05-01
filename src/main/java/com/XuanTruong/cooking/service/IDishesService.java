package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.message.Status;
import com.XuanTruong.cooking.payload.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IDishesService {
    DishesResponse createDishes(DishesRequest dishesRequest);
    Status deleteDishes(Integer dishesId);
    DishesResponse updateDishes(DishesRequest dishesUpdateRequestRequest);
    List<DishesDTO> getDishesByName(String name);
    DishesDTO getDisheDTOById(Integer id);
}
