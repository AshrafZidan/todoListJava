<%@ page import="com.todo.model.Todo" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 6/19/2021
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




 <html>
<head>
    <title>User Management Application</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a class="navbar-brand"> Todo
                App</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Todos</a></li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Todos</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/todo?actionType=new"
               class="btn btn-success">Add Todo</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Title</th>
                <th>Target Date</th>
                <th>Todo Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <%
                ArrayList<Todo> todos = (ArrayList<Todo>) request.getAttribute("listTodo");
                System.out.println(todos.get(0).getTitle() + " // jsp --  " + todos.get(0).getId());

            %>

            <c:forEach  items="${listTodo}" var="todo">

                <tr>
                        <td><c:out value="${todo.title}" /></td>
                    <td><c:out value="${todo.targetDate}" /></td>
                    <td><c:out value="${todo.status}" /></td>

                    <td> <a href="<%=request.getContextPath()%>/todo?actionType=edit&id=<c:out value='${todo.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<%=request.getContextPath()%>/todo?actionType=delete&id=<c:out value='${todo.id}' />">Delete</a></td>

                    <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
                              <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>