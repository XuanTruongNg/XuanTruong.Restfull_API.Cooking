package com.XuanTruong.cooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dishes")
@AllArgsConstructor
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishes_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "dishes_name")
    private String dishesName;

    @Column(name = "dishes_disc")
    private String dishesDisc;

    @Column(name = "summary")
    private String summary;

    @Nullable
    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "total_comment")
    private Integer totalComment;

    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "media_name")
    private String mediaName;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "status")
    private Boolean status;

}
