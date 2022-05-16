package com.XuanTruong.cooking.controller;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.message.Sort_Order;
import com.XuanTruong.cooking.message.Status;
import com.XuanTruong.cooking.payload.*;
import com.XuanTruong.cooking.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<DishesDTO> getDishes(@RequestParam(name = "nameDishes",required = true) String nameDishes,
                                     @RequestParam(name = "page",required = false,defaultValue = "0" ) Integer page,
                                     @RequestParam(name = "size",required = false,defaultValue = "2") Integer size,
                                     @RequestParam(name = "sortBy",required = false,defaultValue = "id") String sortBy,
                                     @RequestParam(name = "order",required = false,defaultValue = "ASC") String order
    ){
        Sort sortable = null;
        if (order.equals(Sort_Order.ASC.name())){
            sortable = Sort.by(sortBy).ascending();
        }
        if (order.equals(Sort_Order.DESC.name())) {
            sortable = Sort.by(sortBy).descending();
        }
        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);
        return dishesService.getDishesByName(nameDishes,pageable);
    }
}
