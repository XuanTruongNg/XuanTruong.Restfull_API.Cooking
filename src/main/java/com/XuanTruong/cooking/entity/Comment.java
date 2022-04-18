package com.XuanTruong.cooking.entity;

import com.XuanTruong.cooking.entity.key.CommentPrimayKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comment")
@IdClass(CommentPrimayKey.class)
public class Comment implements Serializable {
    @Id
    @Column(name = "commentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "deletedAt")
    private Date deletedAt;

    @Column(name = "status")
    private Boolean status;

}
