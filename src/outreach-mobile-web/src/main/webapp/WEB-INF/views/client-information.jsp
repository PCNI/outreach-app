<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Client</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">

        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <script src="${contextPath}/resources/js/jquery.js"></script>
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>
    </head>

    <body>
        <div data-role="page">
            <script id="mobile-datepicker" src="${contextPath}/resources/js/jquery.mobile.datepicker.js"></script>    
            <script src="${contextPath}/resources/js/jquery.ui.datepicker.js"></script> 
            <script src="${contextPath}/resources/js/lov.js"></script>
            <script>
                <!--    
            $(document).on('pageinit', function ()
                {
                    var id = ${clientProfile.id};
                    $.ajax({
                        type: "GET",
                        url: "../service/client/" + id,
                        data: "",
                        dataType: "json",
                        restful: true,
                        contentType: "application/json",
                        cache: false,
                        timeout: 20000,
                        async: true,
                        beforeSend: function (xhr) {
                            //xhr.setRequestHeader('Authorization',bearer);
                        },
                        success: function (data) {
                            $("#nameVal").html(data.body[0].FirstName + " " + data.body[0].LastName);
                            $("#dobVal").html(new Date(data.body[0].DOB));
                            $("#idVal").html(data.body[0].id);
                            $("#genderVal").html(data.body[0].Gender);
                            $("#raceVal").html(data.body[0].Race);
                        },
                        error: function (data) {
                            alert("Error connecting to the server");
                        }
                    });
                });
-->   
            </script>

            <div data-role="header" class="mheader">
                <a class="iconbar ui-nodisc-icon " href="#menu" data-role="button" 
                   data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Client</h1>
                <a href="${contextPath}/ui" data-transition="fade"  class="hdright"> 
                    <img src="${contextPath}/resources/img/findicon.jpg"  >
                </a>
            </div>
            <div data-role="panel" id="menu" data-display="push">
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
                <div class="sresult">
                    <ul class="noborder">
                        <li class="nobg">

                            <div class="clientmenu">
                                <h4>Client Information</h4>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <select name="VeteranStatus" id="VeteranStatus">
                                    <option value="-1" selected = "selected">Veteran Status</option>
                                </select>                                

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <select name="DisablingCondition" id="DisablingCondition">
                                    <option value="-1" selected = "selected">Disabling Condition</option>
                                </select>                                 

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <select name="ResidencePrior" id="ResidencePrior">
                                    <option value="-1" selected = "selected">Residence Prior to Project Entry</option>
                                </select>                                

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <select name="ResidencePriorLengthOfStay" id="ResidencePriorLengthOfStay" >
                                    <option value="-1" selected = "selected">Length of Stay</option>
                                </select>                                  
                            </div>
                        </li>
                        <li class="nobg">

                            <div class="clientmenu">
                                <h5></h5>
                                <input type="text" name="NoOfMonthsHomeless" id="NoOfMonthsHomeless" 
                                       placeholder="No. of months homeless in last 3 years">

                            </div>
                        </li>
                    </ul>
                </div>
                <div class="ui-grid-a">
                    <div class="ui-block-a">
                        <a href="profile.html" data-role="button" data-direction="reverse" data-transition="slide" class="whitebtn mtp20" >Back</a>

                    </div>

                    <div class="ui-block-b">
                        <a href="profile.html" data-role="button" data-direction="reverse" data-transition="slide" class="blubtn mtp20" >Save</a> 

                    </div>
                </div>
            </div>
    </body>
</html>
