<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 6/12/2021
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <h1>Login Form</h1>
    <%if (request.getAttribute("LoginError") == "true") {%>
    <div class="alert alert-success center" role="alert">
        <p>${ErrorMsg}</p>
    </div>
    <%}%>
    <form action="<%=request.getContextPath()%>/login" method="post">

        <div class="form-group">
            <label for="uname">User Name:</label> <input type="text"
                                                         class="form-control" id="username" placeholder="User Name"
                                                         name="username" required>
        </div>

        <div class="form-group">
            <label for="uname">Password:</label> <input type="password"
                                                        class="form-control" id="password" placeholder="Password"
                                                        name="password" required>
        </div>


        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>