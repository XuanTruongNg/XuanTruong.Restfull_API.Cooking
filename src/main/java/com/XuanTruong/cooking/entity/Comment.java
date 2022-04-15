package com.XuanTruong.cooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "commentID")
    private Integer commentId;

    @Id
    @Column(name = "userID")
    private Integer userId;

    @Id
    @Column(name = "dishesID")
    private Integer dishesId;

    @Column(name = "message")
    private String message;

    @Column(name = "createdAt")
    private java.sql.Timestamp createdAt;

    @Column(name = "updatedAt")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deletedAt")
    private java.sql.Timestamp deletedAt;

    @Column(name = "status")
    private Boolean status;


}
