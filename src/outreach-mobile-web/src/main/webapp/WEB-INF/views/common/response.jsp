<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page session="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Response</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.min.js"></script>
        <script src="${contextPath}/resources/js/jquery.js"></script>        

    </head>
    <body>
        <div data-role="page" id="responseID">
            <script>
                $(document).on('pageinit', function () {
                    var msg = "Request updated."
                    msg = "${message}";
                <c:choose>
                    <c:when test="${not empty responseCode && responseCode == '200'}">
                    //alert("Action has been completed successfully");
                    $("#responseHref").click(function () {
                        $("#responseDialog #responseStatus ").html("Success");
                        $("#responseDialog #responseMsg").html(msg);
                    });
                    $("#responseHref").click();
                    </c:when>
                    <c:when test="${not empty responseCode && responseCode == '500'}">
                    $("#responseHref").click(function () {
                        $("#responseDialog #responseStatus ").html("Error!");
                        $("#responseDialog #responseMsg").html(msg);
                    });
                    $("#responseHref").click();
                    </c:when>
                </c:choose>

                });
            </script>    
            <a id='responseHref' href="#responseDialog" data-rel="popup" data-position-to="window"
               data-transition="pop" style='display:none;'></a>
            <div data-role="popup" id="responseDialog" data-overlay-theme="b" data-theme="a" 
                 style="max-width:400px;">
                <div data-role="header" data-theme="c">
                    <h1 id="responseStatus"></h1>
                </div>
                <div role="main" class="ui-content">
                    <h3 class="clientmenu" id="responseMsg"></h3>
                       <a href="${contextPath}/${nextPage}" class=" blubtn mtp40 ui-btn ui-shadow ui-corner-all"   data-role="button" 
                               data-transition="slide" >Ok</a>
                  </div>
            </div>
        </div>


    </body>
</html>
