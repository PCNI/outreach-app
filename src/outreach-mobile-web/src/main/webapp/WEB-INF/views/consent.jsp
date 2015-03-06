<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Consent</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/jquery.mobile.datepicker.css"> 		   
        <script src="${contextPath}/resources/js/jquery.js"></script>
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>      
        <script src="${contextPath}/resources/js/jquery.ui.datepicker.js"></script>        
        <script id="mobile-datepicker" src="${contextPath}/resources/js/jquery.mobile.datepicker.js"></script>		
    </head>

    <body>
        <div data-role="page">
            <script src="${contextPath}/resources/js/jquery.validate.js"></script> 
            <script src="${contextPath}/resources/js/utils.js"></script> 
            <script>
                <!--
                $(document).on('pageinit', function ()
                {
                    configDate($(".dateClassConsent"));
                    $("#consentForm").validate({
                        rules: {
                            name: {
                                required: true,
                            },
                            dob: {
                                required: true,
                                date: true
                            },
                            consent: {
                                required: true
                            }
                        },
                        messages: {
                            name: "Please enter a name",
                            dob: "Please enter your date of birth",
                            consent: "Please agree to our terms and agreements"
                        }
                    });
                });
-->
            </script>


            <div data-role="header" class="mheader">
                <a class="iconbar ui-nodisc-icon " href="#menu" data-role="button" 
                   data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Consent</h1>
                <a href="${contextPath}/ui" data-transition="fade"  class="hdright"> 
                    <img src="${contextPath}/resources/img/findicon.jpg"  >
                </a>
            </div>
            <div data-role="panel" id="menu" data-display="push" class="push-menu">
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
                <h4>HMIS Client Informed Consent Release of Information Authorization</h4>
                <p>Instructions here about expectations with regards to securing informed consent.</p>
                <div class="contscroll">
                    <p>
                        <b>Pathways Community Network Client Authorization Form</b><br/><br/>
                        I also understand that I have the right to refuse to grant this authorization, and that even if I give permission for this agency to access my information in the Pathways system, I can revoke that permission at any time, without penalty. The permission I am giving this agency to view my information and to place information about me in the Pathways system will expire on: <b>01/06/2019</b>.</p>
                    <p>I also understand that under certain circumstances, this agency or Pathways may be legally required to disclose some or all of my confidential information. This may happen if there is any evidence of child abuse, if there is evidence I may harm others or myself, or if a court orders that my information be disclosed.</p>
                    <p>In order to improve services for persons in need, experts may study data from the Pathways system and other sources. As a result, an independent researcher may need to view personal information, such as names and Social Security numbers, to make sure that records are not counted twice. This researcher will remove all personally identifiable information before anyone else examines the data, so that the privacy of those who received services is protected. This procedure is done in accordance with professional standards, under strict government and research institution supervision, and in compliance with all regulations that specifically address those who have received services for mental health, substance abuse, HIV/AIDS, and domestic violence.</p>
                    <p>I understand that <b>Pathways Headquarters</b> (this agency) is part of the Pathways Community Network, a computer network designed to reduce the amount of time and effort it takes for me to obtain the social services I need. This agency has my permission to:
                    <ul><li>Look at information about me in the Pathways system</li>
                        <li>Enter in the system information concerning my situation and need for assistance</li>
                    </ul>
                    <hr style="width:50%"/></p>
                <p>I understand that: Agencies in the Pathways system will keep this information confidential.</p>
                <p>Other agencies will be able to look at this information only if I give each of these agencies my permission, staff at each agency receives regular training on client confidentiality and their legal responsibility to keep my information private, the Pathways system uses passwords and computerized codes to protect my privacy, shared information may include my name, age, gender, marital status, veteran status, address, housing status, and basic information about my goals and the services I receive.</p>
                <p>I can obtain a copy of information about me collected by the Pathways system, except for psychotherapy notes and other information kept private by law.</p>
                <p>I authorize this agency to view my information, and to place information about me in the Pathways system.</p>
                </div>
                <div class="loginform">
                    <div class="ui-field-contain">
                        <form action="${contextPath}/ui/client/info" method="GET" id="consentForm">
                            <input type="text" name="name" id="firstName" 
                                   placeholder="Client Name">
                            <input type="text" data-role="date" id="dob" name="dob" 
                                   class="dateClassConsent" placeholder="Date of Birth"/>

                            <div class="mtp20">
                                <label for="Consents">I have read and understand and 
                                    I consent to the terms and agreements of this app.</label>
                                <input type="checkbox" name="consent" id="Consents" 
                                       value="Consent" style="visibility:hidden"/>
                            </div>
                            <button type="submit" data-transition="fade" data-role="button" 
                                    class="blubtn mtp40" >I Consent</button> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
