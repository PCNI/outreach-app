<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="false"%>
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
        <div data-role="page" id="index">
<script src="${contextPath}/resources/js/utils.js"></script>
        <script src="${contextPath}/resources/js/lov.js"></script>
            <script>
                <!--    
                var enrollmentId;
                var clientId;
                <c:if test="${not empty enrollment}">
                                enrollmentId = ${enrollment.id};
                </c:if>
                var id = ${clientProfile.id};
                $(document).on('pageinit', function ()
                {
                    $("#clientinfo").click(function () {
<c:choose><c:when test="${not empty enrollment}">
                        loadPage('editClientInfo');
</c:when><c:otherwise>
                        loadPage('clientInfo');     
</c:otherwise></c:choose>
                    });
                    
                    $("#healthconditions").click(function () {
                        loadPage('healthconditions')
                    });

                    $("#household").click(function () {
                        loadPage('household');
                    });

                    $("#contact").click(function () {
                        loadPage('contact');
                    });

                    $("#pathreferral").click(function () {
                        loadPage('pathreferral');
                    });
                    
                    loadClientData();

                });
                
                function loadPage(pageId) {
                    window.location.href = "${contextPath}/ui/" + pageId + "?id=" +id + "&enrollmentId=" + enrollmentId;
                }
                

                function loadClientData() {
                    var fdt = OformatStrDate("<c:out value="${clientProfile.dob}"/>");
                    $("#DOB").val(fdt);
                    $("#dobVal").html(fdt);
                    $("#nameVal").html("${clientProfile.firstName} ${clientProfile.middleName} ${clientProfile.lastName}");
                    $("#ID").val('${clientProfile.id}');
                    $("#idVal").val('${clientProfile.id}');
                    $("#SSN").val('${clientProfile.ssn}');
                    $("#FirstName").val('${clientProfile.firstName}');
                    $("#MiddleName").val('${clientProfile.middleName}');
                    $("#LastName").val('${clientProfile.lastName}');
                    $("#NameSuffix").val('${clientProfile.nameSuffix}');
                }  
                    
                $.getJSON('${contextPath}/service/lov/gender/'+checkforEmtpy(${clientProfile.gender}), function(jd) {
                        if(jd.header.count > 0){
                            $("#genderVal").html(jd.body[0].name);
                        }
                    }).fail(function( jqxhr, textStatus, error ) {
                            var err = textStatus + ", " + error;
                            console.log( "Request Failed: " + err );
                                            });
                        
                $.getJSON('${contextPath}/service/lov/race/'+${clientProfile.race}, function(jd) {
                    if(jd.header.count > 0){
                        $("#raceVal").html(jd.body[0].name);
                    }
                }).fail(function( jqxhr, textStatus, error ) {
                                                                var err = textStatus + ", " + error;
                                                                console.log( "Request Failed: " + err );
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
                                    <a href="${contextPath}/ui/editClientProfile?id=<c:out value="${clientProfile.id}"/>" rel="external"> 
                                        <img src="${contextPath}/resources/img/edit.png"  >
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <img src="${contextPath}/resources/img/edit-dis.png"  >
                                </c:otherwise>  
                            </c:choose>            
                        </div>
                        <h4 id='nameVal'>&nbsp;</h4>
                        <p id='dobVal'></p>
                        <p id="idVal"></p>
                        <hr>
                        <p id="genderVal"></p>
                        <p id="raceVal"></p>  
                    </div>
                </div>


                <div class="sresult">
                    <ul >
                        <li>

                            <div class="clientmenu">
                                <a href="javascript:void(0);" id="clientinfo" data-transition="slide" rel="external">
                                    <h4>Client Information</h4></a>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <a href="javascript:void(0)" id="healthconditions" data-transition="slide"  rel="external"> 
                                    <h4>Disabilities & Health Conditions </h4> </a>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <a href="javascript:void(0)" id="household" data-transition="slide"  rel="external"> 
                                    <h4>Household Information</h4></a>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <a href="javascript:void(0)" id="contact" data-transition="slide"  rel="external">
                                    <h4> Contact</h4></a>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <a href="javascript:void(0)" id="pathreferral" data-transition="slide"  rel="external"> 
                                    <h4>Services / PATH Status</h4></a>

                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
                </body>
                </html>
