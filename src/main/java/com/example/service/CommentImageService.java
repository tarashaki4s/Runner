package com.example.service;

import com.example.entity.CommentImage;

import java.util.List;

public interface CommentImageService {
    public List<CommentImage> findAll();

    public List<CommentImage> findByCommentId(int id);

    public CommentImage update(CommentImage commentImage);

    public void deleteById(int id);

    public CommentImage create(CommentImage commentImage);
}
