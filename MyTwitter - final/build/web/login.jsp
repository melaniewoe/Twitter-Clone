<%-- 
    Document   : login.jsp
    Created on : Sep 24, 2015, 6:44:58 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@ include file="header.jsp"%>
    <body>
        <c:if test="${log_in_error == 'True'}">
            <font color='red'><h1> Wrong account or password !!!</h1></font>
        </c:if>    
        <form action="membership" method="post">
        <input type="hidden" name="action" value="login">     
            Email:<input name='email' type='email' value ="${cookie.email_cookie.getValue()}" required><br>
            Password:<input name='password' type='password' value ="${cookie.pass_cookie.getValue()}" required><br>
                <!-- remember me-->
                <input type="checkbox" name="rememberMe" id="rememberMe" class="rememberCheckBox">Remember me
                <input type="submit" id="btnsubmit" value="Login" class ="btn">
            
        </form>
        <form action="membership" method="post">
            <input type="hidden" name="action" value="gotosignup">
            <input type="submit" value="Click here to register" class ="btn">
        </form>
        <span class="psw"><a href="forgetpassword.jsp">Forget password?</a></span>
    </body>
    <%@ include file = "footer.jsp" %>
</html>
