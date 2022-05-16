package com.XuanTruong.cooking.reponsitory;

import com.XuanTruong.cooking.entity.Comment;
import com.XuanTruong.cooking.entity.key.CommentPrimayKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, CommentPrimayKey> {
    @Query("select c from Comment c where c.dishesId = :dishesId ")
    List<Comment> findCommentByDishesId(@Param("dishesId")Integer dishesId, Pageable pageable);
}
