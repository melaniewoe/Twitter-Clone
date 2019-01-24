<%-- 
    Document   : signup.jsp
    Created on : Sep 28, 2016
    Author     : melanie woe
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<!DOCTYPE html>
<html>
<body>
    <c:if test="${email_exists == 'True'}">
        <font color='red'><h1> Email exists. Log in? </h1></font>
    </c:if>
    <div class="container-signup">
        <h1>Join Twitter Today.</h1>
        <p><i>${message}</i></p>
        <form id = "formId" action="membership" method="post" onsubmit="return validateForm()">        
            <input type="hidden" name="action" value="signup">    
            <div id="divError" class="notVisible"></div>
            <div class="form-row">
                <label id="lbfullName">Full name:</label>
                <input type="text" id ="tbfullName" name ="fullName" class="TextCSS" placeholder="Full Name" value="<c:out value='${user.fullName}'/>">
                <span id="tbfullName_error" class="notVisible">*</span>
            </div>
            <br>
            <div class="form-row">
                <label id="lbemail">Email:</label>
                <input type="email" id="tbemail" name="email" class="TextCSS" placeholder="Email address" value="<c:out value = '${user.email}'/>">
                <span id="tbemail_error" class="notVisible">*</span>
            </div>
            <br>
            <div class="form-row">
                <label id="lbpassword">Password:</label>
                <input type="password" id="tbpassword" name="password" class="TextCSS" placeholder="Password" value="<c:out value = '${user.password}'/>">
                <span id="tbpassword_error" class="notVisible">*</span>
            </div>
            <br>
            <div class="form-row">
                <label id="lbconfirmPassword">Confirm Password:</label>
                <input type="password" id="tbconfirmPassword" name="confirmPassword" class="TextCSS" placeholder="Password" value="<c:out value = '${user.confirmPassword}'/>">
                <span id="tbconfirmPassword_error" class="notVisible">*</span>
            </div>
            <br>
            <div class="form-row">
                <label id="lbDateofBirth">Date of Birth:</label>
                <input type="date" id="tbDateofBirth" name="DateofBirth" class="TextCSS" placeholder="DoB">
                <span id="tbDateofBirth_error" class="notVisible">*</span>
            </div>
            <br>
            
            <div class="form-row">
                <select onChange="DisplayTextBox();" id="securityQuestion">
                    <option value="Select one question">Select one question</option>
                    <option value="What was your first pet?">What was your first pet?</option>
                    <option value="What was your first car?">What was your first car?</option>
                    <option value="What was your first school?">what was your first school?</option>
                </select>
                <input type="text" id = "tbsecurityQuestion" name = "securityQuestion" class="notVisible" value="<c:out value = '${user.securityQuestion}'/>">
            </div>
                   
            <div class="form-row">
                <input type="submit" id="btnsubmit" value="SIGN UP" class ="btn">
            </div>
            
        </form>
            
        
        
        
    </div>
</body>
<c:import url="footer.jsp"/>
</html>