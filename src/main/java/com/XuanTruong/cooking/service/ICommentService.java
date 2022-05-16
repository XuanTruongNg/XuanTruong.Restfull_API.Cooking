package com.XuanTruong.cooking.service;

import com.XuanTruong.cooking.DTO.CommentDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICommentService {
    List<CommentDTO> getCommnetByDishesId(Integer dishesId, Pageable pageable);
}
