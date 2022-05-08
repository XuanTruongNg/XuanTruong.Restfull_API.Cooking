package com.XuanTruong.cooking.entity.key;


import lombok.*;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoleKey implements Serializable {
    private Integer roleId;
    private Integer userId;
}
