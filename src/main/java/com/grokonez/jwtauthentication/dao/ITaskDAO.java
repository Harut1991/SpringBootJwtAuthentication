package com.grokonez.jwtauthentication.dao;

import com.grokonez.jwtauthentication.entity.Task;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.TaskRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ITaskDAO implements TaskDAO {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Task create(Task task, User user) {
        task.setCreated_by(user.getId());
        return taskRepository.save(task);
    }

    @Override
    public Task findById(long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public String delete(long id) {
         taskRepository.delete(this.findById(id));
         return "Task deleted successfuly";
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }
}
