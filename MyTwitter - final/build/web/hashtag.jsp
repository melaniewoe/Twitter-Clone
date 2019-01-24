<%-- 
    Document   : hashtag
    Created on : Dec 03, 2017, 11:15:10 PM
    Author     : macbookair
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="header.jsp"%>
    
    <div class ="container">
        <div class ="row">
            
            <!-- tweeting part -->
            <c:if test ="${login == 'True'}">
                <div class ="col-sm-6">
                    <div class ="panel panel-info">
                        
                        
                    
                        <div class ="panel-body">
                            <c:forEach items ="${twit_list_for_hashtag}" var ="twit_list_h">
                            <div class ="media">
                                <a class="media-left" href="#fake">
                                    <img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
                                </a>
                                <div class ="media-body">
                                    <h4 class ="media-heading">${twit_list_h.get_twitUser()}<small> ${twit_list_h.getTwitDate()}</small></h4>
                                    <p> ${twit_list_h.get_message()} </p>
                                </div>
                            </div> 
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:if>
            
            
            
        </div>
    </div>
  
    <%@ include file = "footer.jsp" %>
</html>
