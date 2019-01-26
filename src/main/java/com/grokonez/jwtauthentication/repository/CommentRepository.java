package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.entity.Comment;
import com.grokonez.jwtauthentication.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
