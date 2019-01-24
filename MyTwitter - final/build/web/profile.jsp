<%-- 
    Document   : profile
    Created on : Nov 08, 2017, 12:34:43 AM
    Author     : macbookair
--%>

<!DOCTYPE html>

<html>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="header.jsp"%>
    
    <div class ="container">
        <div class ="row">
            <div class ="col-sm-3">
                <div class ="panel panel-default">
                    <h1>My Profile 
                        <a href="updateprofile.jsp">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </a>
                    </h1>
                   <a href="#"><img class="img-thumbnail" alt="" src="http://placehold.it/100x100"></a> 
                   ${user.fullName} ${user.email}
                   <div class ="row">
                       <div class="col-xs-3">
                            <h5>
                                <small>Tweets</small>
				<b href="#">${user.twitNumber}</b>
                            </h5>
                       </div>
                   </div>
                </div>  
            </div>
            
            <!-- tweeting part -->
            <c:if test ="${login == 'True'}">
                <div class ="col-sm-6">
                    <div class ="panel panel-info">
                        <div class ="panel-heading">
                        <div class ="media">
                            <a class ="media-left">
                                <img alt="" class="media-object img-rounded" src="http://placehold.it/35x35">
                            </a>
                        </div>
                        <form method ="post">
                            <div class ="form-group has-feedback">
                                <input type="text" id ="twit_caption" name="twit_caption" class ="form-control">
                            </div>
                            <button class="" type="submit" name="post_tweet" value="post" aria-label="right Align">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"> </span> Tweet
                            </button>
                            </div>
                        </form>
                        
                        
                    
                        <div class ="panel-body">
                            <c:forEach items ="${twit_list_for_profile}" var ="twit_list_fp">
                            <div class ="media">
                                <a class="media-left" href="#fake">
                                    <img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
                                </a>
                                <div class ="media-body">
                                    <h4 class ="media-heading">${twit_list_fp.get_twitUser()}<small> ${twit_list_fp.getTwitDate()}</small></h4>
                                    <p> ${twit_list_fp.get_message()} </p>
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

