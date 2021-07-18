package com.todo.dao;

import com.todo.Utils.JDBCUtils;
import com.todo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {


    public int registerUser(User user){

        String INSERT_USERS_SQL = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ? , ? , ?)" ;

        int result = 0;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1 , user.getFirstName());
            preparedStatement.setString(2 , user.getLastName());
            preparedStatement.setString(3 , user.getUsername());
            preparedStatement.setString(4 , user.getPassword());

            result = preparedStatement.executeUpdate();

        }catch (SQLException e){
        e.printStackTrace();
        }

        return result;

    }
}
