package com.XuanTruong.cooking.controller;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.payload.DishesCreatorResponse;
import com.XuanTruong.cooking.payload.DishesDeleteResponse;
import com.XuanTruong.cooking.payload.DishesRequest;
import com.XuanTruong.cooking.payload.DishesUpdatingResponse;
import com.XuanTruong.cooking.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DishesController {
    @Autowired
    IDishesService dishesService;

    @PostMapping("/dishes")
    public DishesCreatorResponse createDishes(@RequestBody DishesRequest dishesRequest, HttpServletRequest request){
        return dishesService.createDishes(dishesRequest,dishesService.getUserId(request));
    }
    @PutMapping("/dishes/{id}")
    public DishesUpdatingResponse updateDishes(@RequestBody DishesRequest dishesRequest,HttpServletRequest request ){
        return dishesService.updateDishes(dishesRequest,dishesService.getUserId(request));
    }
    @DeleteMapping("/dishes/{id}")
    public DishesDeleteResponse deleteDishes(@PathVariable(name = "id") Integer dishesId,HttpServletRequest request){
        return dishesService.deleteDishes(dishesId,dishesService.getUserId(request));
    }
    @GetMapping("/dishes")
    public List<DishesDTO> getDishes(@RequestParam String nameDishes){
        return dishesService.getDishesByName(nameDishes);
    }
}
