package com.todo.Service;

import com.todo.dao.TodoDaoImpl;
import com.todo.model.Todo;

import java.sql.SQLException;
import java.util.List;

public class TodoService {
    private TodoDaoImpl todoDao ;


    public TodoService(){
        todoDao = new TodoDaoImpl();
    }

    public List<Todo> selectAllToDos(int LoggedInUser){
     List<Todo>  list ;
    list=   todoDao.selectAllToDos(LoggedInUser);
      return list;
    }

    public void insertTodo(Todo newTodo) throws SQLException {
        todoDao.insetTodo(newTodo);
    }
    public boolean deleteTodo(int id) throws SQLException {
        boolean res =  todoDao.deleteTodo(id);
        return res;
    }

    public Todo selectTodo(long id, int id1) {
        Todo todo = null;
            todo = todoDao.selectTodo(id , id1);
        return  todo;
    }

    public boolean updateTodo(Todo  updateTodo) throws SQLException {
        boolean isUpdated = false;
        isUpdated =  todoDao.updateTodo(updateTodo);
        return isUpdated;
    }
}
