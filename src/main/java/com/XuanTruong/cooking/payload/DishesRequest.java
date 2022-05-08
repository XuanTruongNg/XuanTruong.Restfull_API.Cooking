package com.XuanTruong.cooking.payload;

import com.XuanTruong.cooking.DTO.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DishesRequest {
    private Integer id;
    private String dishesName;
    private String dishesDisc;
    private String summary;
    private String mediaUrl;
    private String mediaName;
}
