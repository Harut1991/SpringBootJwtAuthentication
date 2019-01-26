package com.grokonez.jwtauthentication.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grokonez.jwtauthentication.entity.Comment;
import com.grokonez.jwtauthentication.entity.Prioritet;
import com.grokonez.jwtauthentication.entity.Task;
import com.grokonez.jwtauthentication.message.request.AssignForm;
import com.grokonez.jwtauthentication.message.request.CommentForm;
import com.grokonez.jwtauthentication.model.Role;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.services.CommentService;
import com.grokonez.jwtauthentication.services.TaskService;
import com.grokonez.jwtauthentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/api/")
@JsonIgnoreProperties({"created_by", "handler"})
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("tasks")
    @PreAuthorize("hasRole('PM')")
    public ResponseEntity<Task> create(@RequestBody Task task){
        User user = this.userService.currentUser();
        return new ResponseEntity<Task>(this.taskService.create(task, user), HttpStatus.OK);
    }


    @GetMapping("tasks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set> getTasks(){
        User user = this.userService.currentUser();
        return new ResponseEntity<Set>(user.getTasks(), HttpStatus.OK);
    }

    @GetMapping("tasks/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<Task> getTasks(@PathVariable("id") Integer id){
        User user = this.userService.currentUser();
        Task task = this.taskService.findById(id);
        if(this.userService.hasAdmin(user) ||
           task.getCreated_by() == user.getId() ||
           user.getTasks().contains(task)){
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        }
        return new ResponseEntity<Task>(new Task(), HttpStatus.OK);
    }


    @DeleteMapping("tasks/{id}")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id){
        User user = this.userService.currentUser();
        Task task = this.taskService.findById(id);
        if(this.userService.hasAdmin(user) || task.getCreated_by() ==
        user.getId()) {
            return new ResponseEntity<String>(this.taskService.delete(id), HttpStatus.OK);
        }
        return new ResponseEntity<String>("The system can`t delete this task .please try again", HttpStatus.OK);
    }

    @PostMapping("tasks/users/add")
    @PreAuthorize("hasRole('PM')")
    public ResponseEntity<User> asingsTask(@RequestBody AssignForm assignForm){
        User user = userService.findById(assignForm.getUser_id());
        Task task = taskService.findById(assignForm.getTask_id());
        user.addTask(task);
        this.userService.save(user);
        return new ResponseEntity<User>(userService.findById(assignForm.getUser_id()), HttpStatus.OK);
    }

    @PutMapping("tasks/changePrioritet/{task_id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> update(@PathVariable("task_id") Integer task_id,@RequestBody Task task_){

        Task task = taskService.findById(task_id);
        User user = this.userService.currentUser();
        if (user.getTasks().contains(task)){
            task.setPrioritet(task_.getPrioritet());
            this.taskService.save(task);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("error", HttpStatus.OK);
    }

    @PostMapping("tasks/comment")
    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<Comment> createComment(@RequestBody CommentForm commentForm){
        User user = this.userService.currentUser();
        Comment comment = new Comment();
        comment.setComment(commentForm.getComment());
        comment.setTask_id(commentForm.getTask_id());
        comment.setUser_id(user.getId());
        commentService.save(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @DeleteMapping("tasks/comment/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Integer id){
        User user = this.userService.currentUser();
        Comment comment = this.commentService.findById(id);
        Task task = this.taskService.findById(comment.getTask_id());
        if(this.userService.hasAdmin(user) || task.getCreated_by() == user.getId() || comment.getUser_id() == user.getId()){
            this.commentService.delete(comment);
            return new ResponseEntity<String>("The comment deleted successfuly", HttpStatus.OK);
        }
        return new ResponseEntity<String>("The system can`t delete this comment .please try again", HttpStatus.OK);
    }
}
