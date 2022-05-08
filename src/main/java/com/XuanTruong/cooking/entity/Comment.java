package com.XuanTruong.cooking.entity;

import com.XuanTruong.cooking.entity.key.CommentPrimayKey;
import lombok.AllArgsConstructor;

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
@AllArgsConstructor
public class Comment implements Serializable {
    @Id
    @Column(name = "comment_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "dishes_id")
    private Integer dishesId;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "status")
    private Boolean status;

}
