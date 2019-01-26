package com.grokonez.jwtauthentication.services;

import com.grokonez.jwtauthentication.dao.TaskDAO;
import com.grokonez.jwtauthentication.entity.Task;
import com.grokonez.jwtauthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ITaskService implements TaskService{
    @Autowired
    private TaskDAO taskDao;

    @Override
    public Task create(Task task, User user) {
        return taskDao.create(task, user);
    }

    @Override
    public Task findById(long id) {
        return taskDao.findById(id);
    }

    @Override
    public String delete(long id) { return taskDao.delete(id); }

    @Override
    public void save(Task task) {
         taskDao.save(task);
    }
}
