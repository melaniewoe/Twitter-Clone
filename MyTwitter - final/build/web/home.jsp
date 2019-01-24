<%-- 
    Document   : home.jsp
    Created on : Sep 24, 2015, 6:47:02 PM
    Author     : xl
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
                   <a href="#"><img class="img-thumbnail" alt="" src="http://placehold.it/100x100"></a> 
                   ${user.fullName} ${user.email}
                   <div class ="row">
                       <div class="col-xs-3">
                            <h5>
                                <small>Tweets</small>
				<b href="#">${user.twitNumber}</b>
                                <small>Following</small>
				<b href="#">${following_number}</b>
                                <small>Follower</small>
				<b href="#">${follower_number}</b>
                            </h5>
                       </div>
                   </div>
                </div> 
                <!-- trend table -->
                <div class ="panel panel-default">
                    <div class ="panel-heading">
                        <div class ="panel-title">
                            TRENDING TOPIC
                        </div>  
                    </div>
                    <div class="panel-body">
                        <ul class="list-unstyled">
                            <c:forEach items="${hashtag_list}" var="h_list">
                                <form action="membership" method="post">
                                    <input type="hidden" name="action" value="find_hashtag"/>
                                    <input type="hidden" name="hashtagText" value="${h_list.gethashtagText()}"/>
                                    <input type="submit" value="${h_list.gethashtagText()}"/>
                                </form>
                                <li><b href="hashtag.jsp">${h_list.gethashtagText()} </b></li><br>
                            </c:forEach>
                        </ul>
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
                        <form action="membership" method ="post">
                            <input type="hidden" name="action" value="post_tweet"/>
                            <div class ="form-group has-feedback">
                                <input type="text" id ="twit_caption" name="twit_caption" class ="form-control">
                            </div>
                            <button class="" type="submit" name="post_tweet" value="post" aria-label="right Align">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"> </span> Tweet
                            </button>
                            </div>
                        </form>
                        
                        
                    
                        <div class ="panel-body">
                            <c:forEach items ="${twit_list_for_profile}" var ="twit">
                            <div class ="media">
                                <a class="media-left" href="#fake">
                                    <img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
                                </a>
                                <div class ="media-body">
                                    <h4 class ="media-heading">${twit.get_twitUser()}<small> ${twit.getTwitDate()}</small></h4>
                                    <p> ${twit.get_message()} </p>
                                    <ul class="nav nav-pills nav-pills-custom">
                                        <c:if test = "${twit.emailAddress == email}">
                                        <form action="membership" method="post">
                                            <input type="hidden" name="action" value="deleteTwit"/>
                                            <input type="hidden" name="twitID" value="${twit.gettwitID()}"/>
                                            <input type="submit" value="delete"/>
                                        </form>
                                        </c:if>
                                    </ul>  
                                </div>
                            </div> 
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:if>
            
            <!-- third column -->
            <div class="col-sm-3">
                <div class="panel panel-default panel-custom">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Who to follow
                            <small><a href="#">View all</a></small>
			</h3>
                    </div>
                    <div class ="panel-body">
                        <c:forEach items="${user_list}" var="users_list">
                        <div class ="media">
                            <div class="media-left">
                                <img src="http://placehold.it/32x32" alt="" class="media-object img-rounded">
                            </div>
                            <div class="media-body">
                            <h4 class="media-heading">${users_list.fullName}</h4>
                            <small>${users_list.email}</small>
                            <form action="membership" method="post">
                                        <input type="hidden" name="action" value="follow"/>
                                        <input type="hidden" name="followedUser" value="${users_list.email}"/>
                                        +
                                        <span class="glyphicon glyphicon-user"></span>

                                        <input type="submit" value="follow"/>
                                    </form>
                            <c:forEach items="${follow_list}" var ="fl">
                                <c:choose>
                                <c:when test = "${fl.followedUser == users_list.email}">
                            
                                
                                    <form action="membership" method="post">
                                        <input type="hidden" name="action" value="unfollow"/>
                                        <input type="hidden" name="followedUser" value="${users_list.email}"/>
                                        +
                                        <span class="glyphicon glyphicon-user"></span>

                                        <input type="submit" value="unfollow"/>
                                    </form>
                                </c:when>
                                <c:when test = "${fl.followedUser != users_list.email}">
                                    
                                    <form action="membership" method="post">
                                        <input type="hidden" name="action" value="follow"/>
                                        <input type="hidden" name="followedUser" value="${users_list.email}"/>
                                        +
                                        <span class="glyphicon glyphicon-user"></span>

                                        <input type="submit" value="follow"/>
                                    </form>
                                </c:when>
                                </c:choose>
                            </c:forEach> 
                            
                        </div>
                        </c:forEach>
                        
                    </div>
                </div>
            </div>
            
        </div>
    </div>
  
    <%@ include file = "footer.jsp" %>
</html>
