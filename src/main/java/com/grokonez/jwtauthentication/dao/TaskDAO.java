package com.grokonez.jwtauthentication.dao;

import com.grokonez.jwtauthentication.entity.Task;
import com.grokonez.jwtauthentication.model.User;

public interface TaskDAO {
    Task create(Task task, User user);
    Task findById(long id);
    String delete(long id);
    void save(Task task);
}
