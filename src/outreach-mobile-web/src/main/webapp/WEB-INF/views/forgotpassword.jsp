<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login - Forgot Password?</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/jquery.mobile.datepicker.css">	
        <script src="${contextPath}/resources/js/jquery.js"></script>
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>    
        <script id="mobile-datepicker" src="${contextPath}/resources/js/jquery.mobile.datepicker.js"></script>    
        <script src="${contextPath}/resources/js/jquery.ui.datepicker.js"></script>  
            <script src="${contextPath}/resources/js/jquery.validate.js"></script> 
            <script>
                <!--
                $(document).on('pageinit', function ()
                {
                    $("#forgotpasswordForm").validate({
                        rules: {
                            email: {
                                required: true,
                                email: true
                            }
                        },
                        messages: {
                            email: "Please provide a valid and registered email address"
                        }
                    });
                });
            -->
            </script>        
    </head>

    <body>
        <div data-role="page" id="loginforgotpassword">
            <div data-role="header" data-theme="e">
                <h1>Forgot Password ?!</h1>
            </div>

            <div data-role="main" class="ui-content">
                <h3>Reset password</h3>
                <form action="${contextPath}/resetpassword" method="get" id="forgotpasswordForm">
                    <div class="sresult">
                        <ul class="noborder">
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5>Enter Email Address</h5>
                                    <input type="email" name="email" placeholder="Email Address"/>
                                </div>
                            </li>
                        </ul>
                    </div>   
                    <div class="ui-grid-a">
                        <div class="ui-block-a">
                            <a href="${contextPath}/login" data-role="button" 
                               data-direction="reverse" data-transition="fade" 
                               class="whitebtn mtp20" >Cancel</a>
                        </div>
                        <div class="ui-block-b">
                            <button type="submit" data-role="button" class="blubtn mtp20" 
                                    data-transition="fade">Submit</button> 
                        </div>
                    </div>
                </form>
            </div>                                
        </div>
    </body>
</html>
