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
    <link rel="stylesheet" href="${contextPath}/resources/css/jquery.mobile.datepicker.css">	
    <script src="${contextPath}/resources/js/jquery.js"></script>
    <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>    
    <script id="mobile-datepicker" src="${contextPath}/resources/js/jquery.mobile.datepicker.js"></script>    
    <script src="${contextPath}/resources/js/jquery.ui.datepicker.js"></script>  
    <script src="${contextPath}/resources/js/utils.js"></script>  
    </head>

    <body>
        <div data-role="page">
            <div data-role="header" class="mheader">
                <h1>Login</h1>
            </div>
            <div data-role="main" class="ui-content maincontent">
                <div class="logo"> <img src="${contextPath}/resources/img/logo.jpg" width="265" height="99"> </div>
                <div class="loginform">
                    <form name="login-form" id="login-form" class="login-form" action="j_spring_security_check" method="post">
                        <c:if test="${not empty message}">
                            <p style="color: red !important; text-align: center;">${message}</p>
                        </c:if>
                        <div class="ui-field-contain">
                            <input type="text" name="j_username" id="UserID" placeholder="User ID">
                            <input type="Password" name="j_password" id="Password" placeholder="Password">
                            <button type="submit" data-role="button" data-transition="slide" class="blubtn mtp40">Submit</button>
                            <!--<input type = "submit" value="Submit" style="background-color: #1B93CF !important" data-role="button"  data-transition="fade" />-->
                            <div class="ui-grid-a mtp20">
                                <div class="ui-block-a">
                                    <label for="Remember">Remember Me</label>
                                    <input type="checkbox" name="favcolor" id="Remember" value="Remember Me">
                                </div>

                                <div class="ui-block-b forgotpass">
                                    <a href="#">Forgot Password</a>
                                </div>
                            </div>

                            <div class="ui-grid-a   footerlink">
                                <p><a href="#">Terms & Conditions</a></p>
                                <p><a href="#">Pathways LLC</a></p>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
    </body>
</html>
