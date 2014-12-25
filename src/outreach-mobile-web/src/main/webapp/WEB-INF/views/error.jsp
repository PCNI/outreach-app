<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <script src="${contextPath}/resources/js/jquery.js"></script>
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>
    </head>

    <body>
        <div data-role="page">
            <div data-role="header" class="mheader">
                <h1>Login</h1>
            </div>
            <div data-role="main" class="ui-content maincontent">
                <div class="logo"> <img src="${contextPath}/resources/img/logo.jpg" width="265" height="99"> </div>
                <div class="loginform">
                    <c:if test="${not empty message}">
                        <p style="color: red !important; text-align: center;">${message}</p>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
