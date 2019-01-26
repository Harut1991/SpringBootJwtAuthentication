package com.grokonez.jwtauthentication.services;

import com.grokonez.jwtauthentication.dao.CommentDAO;
import com.grokonez.jwtauthentication.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICommentService implements CommentService {
    @Autowired
    private CommentDAO commentDAO;

    @Override
    public void save(Comment comment) {
         commentDAO.save(comment);
    }

    @Override
    public Comment findById(long id) {
        return commentDAO.findById(id);
    }

    @Override
    public void delete(Comment comment) {
        commentDAO.delete(comment);
    }
}
