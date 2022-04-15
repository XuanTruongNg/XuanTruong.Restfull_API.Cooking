package com.XuanTruong.cooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "dishesingredient")
public class Dishesingredient {
    @Id
    @Column(name = "ingredientID")
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
