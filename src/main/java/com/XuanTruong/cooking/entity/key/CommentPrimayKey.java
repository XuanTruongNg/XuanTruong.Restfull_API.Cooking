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
public class CommentPrimayKey implements Serializable {
    private Integer commentId;
    private Integer userId;
    private Integer dishesId;

}
