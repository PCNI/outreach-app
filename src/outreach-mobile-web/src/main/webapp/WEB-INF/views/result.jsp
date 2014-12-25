<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Result</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <script src="${contextPath}/resources/js/jquery.js"></script>
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>
    </head>

    <body>
        <div data-role="page">
            <div data-role="header" class="mheader"> 
                <a class="iconbar ui-nodisc-icon " href="#menu" data-role="button" data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Result</h1>
                <button style="width:80px; height:40px; background:transparent; border:none; text-shadow:none; text-shadow:none; box-shadow:none; color:#fff;"  class="ui-btn-icon-right ui-nodisc-icon"
                        onclick="javascript:window.location.href='${contextPath}/ui'">Cancel</button>  
            </div>
            <div data-role="panel" id="menu" data-display="push">
                <div class="ui-panel-inner">
                    <h3>Menu</h3>
                    <ul data-role="listview" class="mainMenu">
                        <li> <a href="${contextPath}/ui" data-transition="fade" >
                                <img src="${contextPath}/resources/img/search.png"  > Client Search</a></li>
                        <li><a href="${contextPath}/logout" data-transition="fade" >
                                <img src="${contextPath}/resources/img/logout.png"  > Logout</a></li>
                    </ul>
                </div>
            </div>    
            <div data-role="main" class="ui-content maincontent">
                <div class="sresult">
                    <ul data-role="listview">
                        <c:forEach items="${clientList}" var="clientList">
                            <li>
                                <a href="${contextPath}/ui/viewProfile?id=${clientList.id}" data-transition="slide"> 
                                    <div class="userimg">
                                        <c:choose>
                                            <c:when test="${not empty clientList.imageUrl}">
                                                <img src="${clientList.imageUrl}" width="109" height="109">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${contextPath}/resources/img/userimg3.jpg" width="109" height="109">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="userdesc">
                                        <h4><c:out value="${clientList.firstName}"/>&nbsp;<c:out value="${clientList.lastName}"/>
                                            <c:out value="${clientList.age}"/></h4>
                                        <h5><c:out value="${clientList.ssn}"/></h5>
                                    </div>
                                </a>
                            </li>
                        </c:forEach> 
                    </ul>
                </div>
                <a href="${contextPath}/ui/addClientConsent" data-transition="fade" data-role="button" class="blubtn mtp40" >Add New User</a> </div>
    </body>
</html>
