package com.grokonez.jwtauthentication.dao;

import com.grokonez.jwtauthentication.entity.Comment;
import com.grokonez.jwtauthentication.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ICommentDAO implements CommentDAO {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public void save(Comment comment) {
         commentRepository.save(comment);
    }

    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
