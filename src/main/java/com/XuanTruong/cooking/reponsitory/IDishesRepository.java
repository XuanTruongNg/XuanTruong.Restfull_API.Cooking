package com.XuanTruong.cooking.reponsitory;

import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishesRepository extends JpaRepository<Dishes,Integer> {
    @Query("select p from Dishes p where p.userId = :id")
    Dishes findDishesById(@Param("id")Integer id);
}
