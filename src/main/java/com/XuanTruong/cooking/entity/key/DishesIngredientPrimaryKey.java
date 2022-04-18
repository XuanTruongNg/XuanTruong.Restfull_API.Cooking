package com.XuanTruong.cooking.entity.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DishesIngredientPrimaryKey implements Serializable {
    private Integer ingredientId;
    private Integer dishesId;

}
