package com.XuanTruong.cooking.entity;

import com.XuanTruong.cooking.entity.key.RoleKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(RoleKey.class)
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_name")
    private String roleName;

}
