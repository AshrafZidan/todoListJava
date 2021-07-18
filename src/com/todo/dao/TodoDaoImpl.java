package com.todo.dao;

import com.todo.Utils.JDBCUtils;
import com.todo.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl  implements  TodoDao {
    private static final String SELECT_ALL_TODOS = "select * from todos where user_id = ?";
    private static final String INSERT_TODOS_SQL = "INSERT INTO todos"
            + "  (title, description, target_date,  is_done , user_id) VALUES " + " (?, ?, ?, ?, ?);";
    private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";

    private static final String SELECT_TODO_BY_ID = "select id,title,description,is_done,target_date, user_id from todos where id =? AND user_id = ?";

    private static final String UPDATE_TODO = "update todos set title = ?, description =?, target_date =?, is_done = ? where id = ? AND user_id = ?;";

    @Override
    public void insetTodo(Todo todo) throws SQLException {

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
            preparedStatement.setString(1, todo.getTitle());
             preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setDate(3, JDBCUtils.getSQLDate(todo.getTargetDate()));
            preparedStatement.setBoolean(4, todo.getStatus());

            preparedStatement.setInt(5, todo.getUser_id());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
          exception.printStackTrace();
        }
    }

    @Override
    public List<Todo> selectAllToDos(int LoggedInUser) {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Todo> todos = new ArrayList<>();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS);) {
            preparedStatement.setString(1,LoggedInUser+"");
             // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                int userId = rs.getInt("user_id");
                todos.add(new Todo(id, title, description, targetDate, isDone, userId));
            }
        } catch (SQLException exception) {
           exception.printStackTrace();
        }

        return todos;

    }

    @Override
    public boolean deleteTodo(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;

    }

    @Override
    public Todo selectTodo(long todoId, int userId) {
        Todo todo = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);) {
            preparedStatement.setLong(1, todoId);
            preparedStatement.setInt(2, userId);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                 String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                int user_id = rs.getInt("user_id");
                todo = new Todo(id, title, description, targetDate, isDone, user_id);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return todo;
    }

    @Override
    public boolean updateTodo(Todo todo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TODO);) {
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getDescription());
             statement.setDate(3, JDBCUtils.getSQLDate(todo.getTargetDate()));
            statement.setBoolean(4, todo.getStatus());
            statement.setLong(5, todo.getId());
            statement.setInt(6, todo.getUser_id());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
