package com.todo.controller;

import com.todo.Service.TodoService;
import com.todo.model.Todo;
import com.todo.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "TodoServlet",value = "/todo")
public class TodoServlet extends HttpServlet {
    private TodoService todoService;

    public void init() {
        todoService = new TodoService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("actionType");
        if (action == null){
            listTodo(request , response);
            return ;
        }

        switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    try {
                        insertTodo(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "delete":
                    try {
                        deleteTodo(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "edit":
                    try {
                        showEditForm(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "update":
                    try {
                        updateTodo(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
            case "list":
                listTodo(request, response);
                break;
            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            todoService.deleteTodo(id);
            response.sendRedirect("todo");

    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String title = request.getParameter("title");
         String description = request.getParameter("description");
        User user = (User) request.getSession().getAttribute("loggedInUser");


        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo newTodo = new Todo(title, description, LocalDate.now(), isDone, user.getId());
        todoService.insertTodo(newTodo);
        response.sendRedirect("todo");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("loggedInUser");
        Todo existingTodo = todoService.selectTodo(id, user.getId());
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);

    }
    private void listTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("loggedInUser");
        List<Todo> listTodo = todoService.selectAllToDos(user.getId());
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("loggedInUser");

        String title = request.getParameter("title");
         String description = request.getParameter("description");
         LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));

        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo updateTodo = new Todo( id , title, description, targetDate, isDone, user.getId());

        todoService.updateTodo(updateTodo);

        response.sendRedirect("todo");
    }


}
