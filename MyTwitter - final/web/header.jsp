<%-- 
    Document   : header.jsp
    Created on : Sep 24, 2015, 6:47:09 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<html>
    <head>
        <meta charset="utf-8">
        <title>&copy; Twitter</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="javascript/main.js" type="text/javascript"></script>
    </head>
    <body>

        <ul>
            <c:if test="${login == 'True'}">
            <li><a href="home.jsp"><i class="fa fa-home"></i> Home</a></li>
            <li><a href="notifications.jsp"><i class="fa fa-bell-o"></i> Notification</a></li>
            <li><a href="profile.jsp"><i class="fa fa-user"></i> My Profile</a></li>
            <li><a href="membership?action=signout" style="position:absolute;right:20px"><i class="fa fa-user"></i> Sign Out</a></li>
            </c:if>
            <c:if test="${login != 'True'}">
            <li><a href="signup.jsp"><i class="fa fa-user"></i> Sign Up</a></li>
            <li><a href="login.jsp" style="position:absolute;right:20px"><i class="fa fa-user"></i> Log In</a></li>
            </c:if>
        </ul>

    </body>
</html>