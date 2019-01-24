<%-- 
    Document   : notification
    Created on : Oct 22, 2017, 5:28:49 PM
    Author     : macbookair
--%>

<html>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="header.jsp"%>
    
    <div center>
                    <div class ="panel panel-info">
                            <c:forEach items ="${notification_list}" var ="notif_list">
                            <div class ="media">
                                <a class="media-left" href="#fake">
                                    <img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
                                </a>
                                <div class ="media-body">
                                    <h4 class ="media-heading">${notif_list.get_twitUser()}<p> mentioned you in a new tweet.</p>
                                        <small>${notif_list.getTwitDate()}</small>
                                </div>
                            </div> 
                            </c:forEach>
                            <c:forEach items ="${notification_list_for_follow}" var ="notif_list_ff">
                            <div class ="media">
                                <a class="media-left" href="#fake">
                                    <img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
                                </a>
                                <div class ="media-body">
                                    <h4 class ="media-heading">${notif_list_ff.getuserID()}<p> followed you.</p>
                                        <small>${notif_list_ff.getfollowDate()}</small>
                                </div>
                            </div> 
                            </c:forEach>
                    </div>
    </div>
    
  
    <%@ include file = "footer.jsp" %>
</html>
