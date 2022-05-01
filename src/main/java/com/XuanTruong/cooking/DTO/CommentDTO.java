package com.XuanTruong.cooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Integer commentId;
    private UserDTO owner;
    private Integer dishesId;
    private String message;
}
