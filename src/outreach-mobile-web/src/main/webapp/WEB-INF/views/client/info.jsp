<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Client</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">

        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.min.js"></script>
        <script src="${contextPath}/resources/js/jquery.js"></script>
        <link rel="stylesheet" href="${contextPath}/resources/css/jquery.mobile.datepicker.css">	
        <script src="${contextPath}/resources/js/jquery.ui.datepicker.js"></script> 
        <script src="${contextPath}/resources/js/jquery.mobile.datepicker.js"></script>

    </head>
    <body>
        <div data-role="page" id="client-info">
            <script src="${contextPath}/resources/js/utils.js"></script>
            <script>
                $(document).on('pageinit', function () {
                    configDate($(".dateClass"));
                    function readImage(input) {
                        if (input.files && input.files[0]) {
                            var FR = new FileReader();
                            FR.onload = function (e) {
                                $('#imgUsrPreview').attr("src", e.target.result);
                                $('#imageDataBase').val(e.target.result.split(',')[1]);
                            };
                            FR.readAsDataURL(input.files[0]);
                        }
                    }

                    $("#imageFileBrowser").change(function () {
                        readImage(this);
                    });

                });
            </script>
            <div data-role="header" class="mheader">        
                <a class="iconbar ui-nodisc-icon " href="#client-info-menu" data-role="button" 
                   data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Client</h1>
                <a href="${contextPath}/ui" data-transition="fade"  class="hdright"> 
                    <img src="${contextPath}/resources/img/findicon.jpg"  >
                </a>
            </div>
            <div data-role="panel" id="client-info-menu" data-display="push" class="push-menu">
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
                                <img id="imgUsrPreview" src="${clientProfile.imageUrl}" width="243" height="227">
                            </c:when>
                            <c:otherwise>
                                <img id="imgUsrPreview" src="${contextPath}/resources/img/userimg3.jpg" width="243" height="227">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="cvarddesc">
                        <div class="editbtn"><img src="${contextPath}/resources/img/edit-dis.png"  ></div>
                        <h4>${clientProfile.firstName}&nbsp;${clientProfile.middleName}&nbsp;${clientProfile.lastName}</h4>
                        <fmt:formatDate value="${clientProfile.dob}" var="dateString" pattern="MM/dd/yyyy" />
                        <p><c:out value="${dateString}"/></p>
                        <p><c:out value="${clientProfile.id}"/></p>
                        <hr>
                        <p><c:out value="${gender}"/></p>
                        <p><c:out value="${race}"/></p>
                        <p><c:out value="${ethnicity}"/></p>

                    </div>
                </div>
                <form:form action="${contextPath}/ui/client" method="POST" modelAttribute="clientProfile">
                    <div class="sresult">
                        <ul class="noborder">
                            <li class="nobg">
                                <div class="clientmenu">
                                    <input type="hidden" name="id" value="${clientProfile.id}">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Upload Client Photo</h5>
                                    <input type="file" name="file" class="form-control" id="imageFileBrowser"/>
                                    <input type="hidden" name="imageData" id="imageDataBase">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Select Project</h5>
                                    <form:select path="projectID" name="projectID">
                                        <form:option value="-1" label="- Select Project -" />
                                        <form:options items="${projectList}" itemValue="id" itemLabel="projectName"/>
                                    </form:select>
                                </div>
                            </li>                               
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Name Data Quality</h5>
                                    <form:select path="nameDataQuality" name="nameDataQuality">
                                        <form:option value="-1" label="- Select Name Data Quality -" />
                                        <form:options items="${NameDataQualityList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu"> 
                                    <h5>First Name</h5>
                                    <input type="text" name="firstName" placeholder="First Name" value="${clientProfile.firstName}">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Middle Name</h5>
                                    <input type="text" name="middleName" placeholder="Middle Name" value="${clientProfile.middleName}">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Last Name</h5>
                                    <input type="text" name="lastName" placeholder="Last Name" value="${clientProfile.lastName}">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu"> 
                                    <h5>Name Suffix</h5>
                                    <input type="text" name="nameSuffix" placeholder="Suffix" value="${clientProfile.nameSuffix}">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Gender</h5>
                                    <form:select path="gender" name="gender">
                                        <form:option value="-1" label="- Select Gender -" />
                                        <form:options items="${GenderList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>SSN Data Quality</h5>
                                    <form:select path="sSNDataQuality" name="sSNDataQuality">
                                        <form:option value="-1" label="- Select SSN Data Quality -" />
                                        <form:options items="${SSNDataQualityList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">        
                                    <h5>SSN</h5>
                                    <input type="text" name="ssn" placeholder="SSN" value="${clientProfile.ssn}">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Date of Birth Data Quality</h5>
                                    <form:select path="dobDataQuality" name="dobDataQuality">
                                        <form:option value="-1" label="- Select DOB Data Quality -" />
                                        <form:options items="${DobDataQualityList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Date of Birth</h5>
                                    <fmt:formatDate value="${clientProfile.dob}" var="dateString1" pattern="MM/dd/yyyy" />
                                    <input type="text" name="dob" class="dateClass" data-role="date" placeholder="DOB" value="${dateString1}">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Race</h5>
                                    <form:select path="race" name="race">
                                        <form:option value="-1" label="- Select Race -" />
                                        <form:options items="${RaceList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Ethnicity</h5>
                                    <form:select path="ethnicity" name="ethnicity">
                                        <form:option value="-1" label="- Select Ethnicity -" />
                                        <form:options items="${EthnicityList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>
                                </div>
                            </li>
                            <li>

                                <div class="clientmenu">
                                    <h5>Veteran Status</h5>
                                    <form:select path="veteranStatus" name="veteranStatus">
                                        <form:option value="-1" label="- Select Veteran Status -" />
                                        <form:options items="${fiveValDKRefused}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>
                                </div>
                            </li>

                        </ul>
                    </div> 
                    <div class="ui-grid-a">
                        <div class="ui-block-a">
                            <c:choose>
                                <c:when test="${not empty clientProfile.id}">
                                    <a href="${contextPath}/ui/client/${clientProfile.id}" data-role="button" 
                                       data-direction="reverse" data-transition="fade" 
                                       class="whitebtn mtp20" >Back</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${contextPath}/ui" data-role="button" 
                                       data-direction="reverse" data-transition="fade" 
                                       class="whitebtn mtp20" >Back</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="ui-block-b">
                            <button type="submit" data-role="button" class="blubtn mtp20" 
                                    data-transition="fade">Save</button> 
                        </div>
                    </div>
                </form:form>
            </div>
        </div><!-- /page -->
    </body>
</html>
