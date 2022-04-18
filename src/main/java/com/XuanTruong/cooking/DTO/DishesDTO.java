package com.XuanTruong.cooking.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishesDTO {
    private Integer id;

    private Integer userId;

    private UserDTO owner;

    private String dishesName;

    private String dishesDisc;

    private String summary;

    private Double averageRating;

    private Integer totalComment;

    private List<CommentDTO> comments;

    private String mediaUrl;

    private String mediaName;

}
