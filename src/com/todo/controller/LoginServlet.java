package com.todo.controller;

import com.todo.Service.LoginService;
import com.todo.model.LoginBean;
import com.todo.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authentcate(request,response);
    }

    private void authentcate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();

        loginBean.setUsername(username);
        loginBean.setPassword(password);
        loginService = new LoginService();

       User user= loginService.authentcate(loginBean);
        if (user != null){
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("/todo");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
//            dispatcher.forward(request, response);
        }else {

            request.setAttribute("LoginError","true");
            request.setAttribute("ErrorMsg","Check UserName or Password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
            dispatcher.forward(request, response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("LoginError","false");
//        request.setAttribute("ErrorMsg","");
        response.sendRedirect("login/login.jsp");
    }
}
