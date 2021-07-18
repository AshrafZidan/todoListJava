package com.todo.Service;

import com.todo.dao.UserDao;
import com.todo.model.User;

public class UserService {
    private UserDao userDao ;
    public UserService(){
        userDao = new UserDao();
    }
    public int registerUser(User user){
        int result = 0;
        result = userDao.registerUser(user);
        return result;
    }

}
