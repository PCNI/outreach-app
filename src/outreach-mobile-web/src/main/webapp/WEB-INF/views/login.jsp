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
        <script src="${contextPath}/resources/js/jquery.validate.js"></script> 
        <script>
            $(function () {
                $("#login-form").validate({
                    rules: {
                        j_username: {
                            required: true,
                        },
                        j_password: {
                            required: true,
                            minlength: 5
                        }
                    },
                    messages: {
                        j_username: "Please enter a valid user id",
                        j_password: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 5 characters long"
                        }
                    }
                });

            });

        </script>
    </head>

    <body>
        <div data-role="page" id="login">
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
                            <input type="Password" name="j_password" id="Password" placeholder="Password" >
                            <button type="submit" data-role="button" data-transition="slide" class="blubtn mtp40">Submit</button>
                            <!--<input type = "submit" value="Submit" style="background-color: #1B93CF !important" data-role="button"  data-transition="fade" />-->
                            <div class="ui-grid-a mtp20">
                                <div class="ui-block-a">
                                    <label for="Remember">Remember Me</label>
                                    <input type="checkbox" name="favcolor" id="Remember" value="Remember Me" style="visibility: hidden"/>
                                </div>

                                <div class="ui-block-b forgotpass"></div>                                    
                                <div class="ui-grid-a   footerlink">
                                    <p><a  href="#termsandcond" data-rel="popup" data-position-to="window" data-transition="pop">Terms & Conditions</a></p>
                                    <div data-role="popup" id="termsandcond" data-overlay-theme="a" data-theme="a" data-dismissible="false" style="max-width:400px;">
                                            <div data-role="header" data-theme="c" >
                                                <h1 >Terms & Conditions</h1>
                                                </div>
                                            <div role="main" class="ui-content">
                                                   <div class="contscroll">
                                                <b><u>Pathways Community Network Notice of Privacy Practices Certification</u></b><br/>
                                                <b>Pathways Headquarters</b> is part of the Pathways Community Network, a computer network designed to reduce the amount of time and effort it takes for consumers to obtain the human services they need. This agency has policies and procedures to protect confidential information people give us when we talk to them on the phone. This agency's Notice of Privacy Practices is available to you. It describes the types of information that we consider confidential, the ways we protect this information, permitted uses and disclosures of this information, and your rights concerning this information.<br/><br/>
                                                You can pick up a copy of our Notice of Privacy Practices at our offices during normal business hours, or you can download the Notice from our web site. I will also be glad to read the Notice to you now." I have read the above notice in its entirety to the person named below, have given the person the opportunity to have the agency's Notice of
                                                Privacy Practices read in its entirety, and to ask questions about our privacy practices. I have answered all questions to the best of my ability and training.<br/><br/>
                                                <b>Certification Procedure</b><br/>
                                                It is the intention of all Pathways Community Network stakeholder agencies to provide services to all eligible clients regardless of their location when initiating contact with our agencies. The vast majority of Pathways client authorizations are made in person at a participating human services agency. In some cases, however, the client is not able to travel to an agency for a conventional client intake. This may be due to a lack of transportation, geographic distance, or handicap. In these cases, staff at participating agencies may use the Notice of Privacy Practices, utilizing the following procedure:<br/>
                                                <ul><li>Obtain at least three types of identifying information (i.e.: name, date of birth, social security number, mother's maiden name).</li>
                                                <li>Read the Pathways Notice of Privacy Practices Form to the client.</li>
                                                <li>Ask if the client has any questions about what she/he has heard. Answer any questions.</li>
                                                <li>If the client agrees, print the client's identifying information (from list above) in the Agency Use Only area of the form. Place your initials next to the information.</li>
                                                <li>Sign and date the Certification form in the "Certified By" and "Date" spaces and print your name below your signature.
                                                </li></ul>
                                                If you have any questions about this procedure, please contact the Pathways Support Team at 404-639-9933, ext. 308; 866-818-1032 or 
                                                <a href="mailto:support@pcni.org" >support@pcni.org</a><br/>
                                            </div>
												
                                                   <center> <a href="#" class="ui-btn ui-corner-all ui-btn-a blubtn" data-rel="back">Close</a></center>
											
                                                
                                    </div>
                                </div>     
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
