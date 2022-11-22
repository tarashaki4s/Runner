package com.example.dao;

import com.example.entity.CommentImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentImageDao extends JpaRepository<CommentImage, Integer> {
}
