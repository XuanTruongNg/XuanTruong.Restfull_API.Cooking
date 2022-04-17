package com.XuanTruong.cooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dishes")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishesID")
    private Integer id;

    @Column(name = "userID")
    private Integer userId;

    @Column(name = "dishesName")
    private String dishesName;

    @Column(name = "dishesDisc")
    private String dishesDisc;

    @Column(name = "summary")
    private String summary;

    @Column(name = "averageRating")
    private Double averageRating;

    @Column(name = "totalComment")
    private Integer totalComment;

    @Column(name = "mediaUrl")
    private String mediaUrl;

    @Column(name = "mediaName")
    private String mediaName;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "deletedAt")
    private Date deletedAt;

    @Column(name = "status")
    private Boolean status;


}
