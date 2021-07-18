package com.todo.dao;

import com.todo.model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDao {

    void insetTodo(Todo todo) throws SQLException;

    List<Todo> selectAllToDos(int LoggedInUser);
    boolean deleteTodo(int id) throws SQLException;
    Todo selectTodo(long todoId,int userId);
    boolean updateTodo(Todo todo) throws SQLException;


}
