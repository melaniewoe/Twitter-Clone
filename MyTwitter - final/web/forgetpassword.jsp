<%-- 
    Document   : forgetpassword
    Created on : Oct 19, 2017, 8:51:29 AM
    Author     : melaniewoe
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@ include file="header.jsp"%>
    <body>  
        <form action="membership" method="post">
        <input type="hidden" name="action" value="forgetPassword">     
            Please enter your email, we will send recovery password:<input name='forgetPasswordEmail' type='email' required><br>
            <div class="form-row">
                <input type="submit" id="btnsubmit" value="Submit" class ="btn">
            </div>
        </form>
    </body>
    <%@ include file = "footer.jsp" %>
</html>
