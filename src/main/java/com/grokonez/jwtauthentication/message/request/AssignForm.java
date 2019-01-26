package com.grokonez.jwtauthentication.message.request;

import javax.validation.constraints.NotBlank;

public class AssignForm {
    @NotBlank
    private long task_id;

    @NotBlank
    private long user_id;

    public long getTask_id() {
        return task_id;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
