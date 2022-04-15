package com.XuanTruong.cooking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DishesRequest {
    private String dishesName;
    private String dishesDisc;
    private String summary;
    private String mediaUrl;
    private String mediaName;
}
