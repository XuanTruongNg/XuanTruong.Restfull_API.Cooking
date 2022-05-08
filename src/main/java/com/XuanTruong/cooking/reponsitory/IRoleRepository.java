package com.XuanTruong.cooking.reponsitory;

import com.XuanTruong.cooking.entity.Role;
import com.XuanTruong.cooking.entity.key.RoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IRoleRepository extends JpaRepository<Role, RoleKey> {
    @Query("select r.roleName from Role r where r.userId = :userId")
    List<String> getRoleByUserId(@Param("userId")Integer userId);
}
