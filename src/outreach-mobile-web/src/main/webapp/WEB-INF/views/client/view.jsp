<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Client</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.min.js"></script>
        <script src="${contextPath}/resources/js/jquery.js"></script>
    </head>

    <body>
        <div data-role="page" id="client-view">
       
            <div data-role="header" class="mheader">
                <a class="iconbar ui-nodisc-icon " href="#client-view-menu" data-role="button" 
                   data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Client</h1>
                <a href="${contextPath}/ui" data-transition="fade"  class="hdright"> 
                    <img src="${contextPath}/resources/img/findicon.jpg"  >
                </a>
            </div>
            <div data-role="panel" id="client-view-menu" data-display="push" class="push-menu">
                <div class="ui-panel-inner">
                    <h3>Menu</h3>
                    <ul data-role="listview" class="mainMenu">
                        <li> <a href="${contextPath}/ui" data-transition="fade" >
                                <img src="${contextPath}/resources/img/search.png"> Client Search</a></li>
                        <li><a href="${contextPath}/logout" data-transition="fade" >
                                <img src="${contextPath}/resources/img/logout.png"> Logout</a></li>
                    </ul>
                </div>
            </div>    
            <div data-role="main" class="ui-content maincontent">

                <div class="cvcard">
                    <div class="cvardid">
                        <div class="cameraicon"><img src="${contextPath}/resources/img/camera.png" width="30" height="25"></div>
                            <c:choose>
                                <c:when test="${not empty clientProfile.imageUrl}">
                                <img src="${clientProfile.imageUrl}" width="243" height="227">
                            </c:when>
                            <c:otherwise>
                                <img src="${contextPath}/resources/img/userimg3.jpg" width="243" height="227">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="cvarddesc">
                        <div class="editbtn">
                            <c:choose>
                                <c:when test="${editable == 'true'}">      
                                    <a href="${contextPath}/ui/client/info/${clientProfile.id}"/> 
                                    <img src="${contextPath}/resources/img/edit.png"  >
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <img src="${contextPath}/resources/img/edit-dis.png"  >
                                </c:otherwise>  
                            </c:choose>            
                        </div>
                        <h4>${clientProfile.firstName}&nbsp;${clientProfile.middleName}&nbsp;${clientProfile.lastName}</h4>
                        <fmt:formatDate value="${clientProfile.dob}" var="dateString" pattern="MM/dd/yyyy" />
                        <p>${dateString}</p>
                        <p>${clientProfile.id}</p>
                        <hr>
                        <p>${gender}</p>
                        <p>${race}</p>  
                        <p>${ethnicity}</p>  
                    </div>
                </div>


                <div class="sresult">
                    <ul >
                        <li>

                            <div class="clientmenu">
                                <a class="ui-link" href="${contextPath}/ui/enrollment/${clientProfile.id}" data-transition="slide">
                                    <h4>Client Information</h4></a>

                            </div>
                        </li>
                        <li>
                            <div class="clientmenu">
                                <a class="ui-link" href="${contextPath}/ui/disability/${clientProfile.id}" data-transition="slide"> 
                                    <h4>Disabilities & Health Conditions </h4> </a>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <a class="ui-link" href="${contextPath}/ui/doe/${clientProfile.id}" data-transition="slide"> 
                                    <h4>Date of Engagement</h4></a>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <a class="ui-link" href="${contextPath}/ui/services/${clientProfile.id}" data-transition="slide">
                                    <h4>Services</h4></a>
                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <a class="ui-link" href="${contextPath}/ui/pathstatus/${clientProfile.id}" data-transition="slide">
                                    <h4>PATH Status</h4></a>
                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <a class="ui-link" href="${contextPath}/ui/referralsource/${clientProfile.id}" data-transition="slide">
                                    <h4>Referral Sources</h4></a>
                            </div>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
