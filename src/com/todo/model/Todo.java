package com.todo.model;

import java.time.LocalDate;

public class Todo {
    private Long id;
    private int user_id;
    private String title;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean status;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    protected Todo() {

    }

    public Todo(long id, String title,  String description, LocalDate targetDate, boolean isDone  , int user_id) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
        this.user_id = user_id;

    }

    public Todo(String title,  String description, LocalDate targetDate, boolean isDone , int user_id) {
        super();
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
