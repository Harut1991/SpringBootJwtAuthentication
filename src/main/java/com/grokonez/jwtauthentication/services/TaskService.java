package com.grokonez.jwtauthentication.services;

import com.grokonez.jwtauthentication.entity.Task;
import com.grokonez.jwtauthentication.model.User;

public interface TaskService {
    Task create(Task task, User user);
    Task findById(long id);
    String delete(long id);
    void save(Task task);
}
