<%-- 
    Document   : updateprofile
    Created on : Nov 10, 2017, 2:13:42 PM
    Author     : macbookair
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@ include file="header.jsp"%>
    <body>
        Update your profile
        <form action="membership" method="post">
        <input type="hidden" name="action" value="update_profile">   
            Full Name: ${user.fullName}  <input name='fullName' type='text' placeholder="New Full Name"><br>
            Email: ${user.email} <input name='email' type='email' placeholder="New Email"><br>
            Password: ${user.password}   <input name='password' type='password' placeholder="New Password"><br>
            <input type="submit" id="btnsubmit" value="Submit" class ="btn">
        </form>
    </body>
    <%@ include file = "footer.jsp" %>
</html>