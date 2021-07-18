package com.todo.Service;

import com.todo.dao.LoginDao;
import com.todo.model.LoginBean;
import com.todo.model.User;

import javax.servlet.RequestDispatcher;

public class LoginService {

    private LoginDao loginDao;

    public User authentcate(LoginBean loginBean){
        loginDao = new LoginDao();
        User user = null;
        try {
            user = loginDao.Login(loginBean);
            if (user != null){

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  user;
    }
}
