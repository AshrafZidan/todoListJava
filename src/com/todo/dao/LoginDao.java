package com.todo.dao;

import com.todo.Utils.JDBCUtils;
import com.todo.model.LoginBean;
import com.todo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao {

    public User Login(LoginBean loginBean) throws ClassNotFoundException{
        User user = null;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from users where username = ? and password = ?");




        ){
        statement.setString(1,loginBean.getUsername());
        statement.setString(2,loginBean.getPassword());

        ResultSet rs =statement.executeQuery();
        System.out.print(rs);
            if(rs.next()){

             String id =    rs.getString("id");
             String fname =    rs.getString("first_name");
            String lname =   rs.getString("last_name");
                String username =   rs.getString("username");

                user = new User(Integer.parseInt(id) , fname , lname , username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }


}
