package com.XuanTruong.cooking.entity;

import com.XuanTruong.cooking.entity.key.DishesIngredientPrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dishesingredient")
@IdClass(DishesIngredientPrimaryKey.class)
public class Dishesingredient implements Serializable {
    @Id
    @Column(name = "ingredientID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;

    @Id
    @Column(name = "dishesID")
    private Integer dishesId;

    @Column(name = "ingredientName")
    private String ingredientName;

    @Column(name = "unit")
    private String unit;

    @Column(name = "amount")
    private Integer amount;

}
