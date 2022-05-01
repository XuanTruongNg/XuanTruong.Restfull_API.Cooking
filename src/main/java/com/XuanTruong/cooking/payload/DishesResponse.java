package com.XuanTruong.cooking.payload;

import com.XuanTruong.cooking.DTO.DishesDTO;
import com.XuanTruong.cooking.message.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishesResponse {
    DishesDTO dishesDTO;
    Status status;
}
