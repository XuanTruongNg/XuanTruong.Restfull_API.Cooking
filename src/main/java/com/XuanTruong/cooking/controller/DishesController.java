package com.XuanTruong.cooking.controller;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.message.Status;
import com.XuanTruong.cooking.payload.*;
import com.XuanTruong.cooking.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DishesController {
    @Autowired
    IDishesService dishesService;

    @PostMapping("/dishes")
    public DishesResponse createDishes(@RequestBody DishesRequest dishesRequest){
        return dishesService.createDishes(dishesRequest);
    }
    @PutMapping("/dishes/{id}")
    public DishesResponse updateDishes(@RequestBody DishesRequest dishesUpdateRequest){
        return dishesService.updateDishes(dishesUpdateRequest);
    }
    @DeleteMapping("/dishes/{id}")
    public Status deleteDishes(@PathVariable(name = "id") Integer dishesId){
        return dishesService.deleteDishes(dishesId);
    }
    @GetMapping("/dishes")
    public List<DishesDTO> getDishes(@RequestParam String nameDishes){
        return dishesService.getDishesByName(nameDishes);
    }
}
