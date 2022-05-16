package com.XuanTruong.cooking.reponsitory;

import com.XuanTruong.cooking.entity.Dishes;
import com.XuanTruong.cooking.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDishesRepository extends JpaRepository<Dishes,Integer> {
    @Query("select p from Dishes p where p.id = :id")
    Dishes findDishesById(@Param("id")Integer id);
    @Query("select p from Dishes p where p.dishesName like %:name%")
    List<Dishes> findDishesByName(@Param("name")String name,Pageable pageable);
}
