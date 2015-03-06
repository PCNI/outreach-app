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
                <div data-role="page" id="error-info">
            <script>
                $(document).on('pageinit', function () {
                    var msg = "Error processing the request."
                    //alert("Action has been completed successfully");
                    $("#errorHref").click(function () {
                        $("#errorDialog #errorStatus ").html("Bad Request");
                        $("#errorDialog #errorMsg").html(msg);
                    });
                    $("#errorHref").click();
                });
            </script>    
            <a id='errorHref' href="#errorDialog" data-rel="popup" data-position-to="window"
               data-transition="pop" style='display:none;'></a>
            <div data-role="popup" id="errorDialog" data-overlay-theme="b" data-theme="a" 
                 style="max-width:400px;">
                <div data-role="header" data-theme="c">
                    <h1 id="errorStatus" style="color: red !important;">Bad Request !</h1>
                </div>
                <div role="main" class="ui-content">
                    <h3 class="clientmenu" id="errorMsg"></h3>
                       <a href="${contextPath}/ui" class=" blubtn mtp40 ui-btn ui-shadow ui-corner-all"   data-role="button" 
                               data-transition="slide" >Ok</a>
    
                </div>
            </div>        
        </div>
    </body>
</html>
