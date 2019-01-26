package com.grokonez.jwtauthentication.dao;

import com.grokonez.jwtauthentication.entity.Comment;

public interface CommentDAO {
    void save(Comment comment);
    Comment findById(long id);
    void delete(Comment comment);
}
