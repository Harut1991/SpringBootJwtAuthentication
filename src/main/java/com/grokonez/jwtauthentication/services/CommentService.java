package com.grokonez.jwtauthentication.services;

import com.grokonez.jwtauthentication.entity.Comment;

public interface CommentService {
    void save(Comment comment);
    Comment findById(long id);
    void delete(Comment comment);
}
