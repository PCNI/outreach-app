<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Client Information</title>
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
        <div data-role="page" id="enrollment-info">
            <script src="${contextPath}/resources/js/utils.js"></script> 
            <script>
                $(document).on('pageinit', function () {
                    configDate($(".entryDateClass"));
                    configDate($(".exitDateClass"));

                });
            </script>            
            <div data-role="header" class="mheader">
                <a class="iconbar ui-nodisc-icon " href="#enrollment-info-menu" data-role="button" 
                   data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Client Information</h1>
                <a href="${contextPath}/ui" data-transition="fade"  class="hdright"> 
                    <img src="${contextPath}/resources/img/findicon.jpg"  >
                </a>
            </div>
            <div data-role="panel" id="enrollment-info-menu" data-display="push" class="push-menu">
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
                <form:form action="${contextPath}/ui/enrollment" method="post" modelAttribute="enrollment" id="enrollmentForm">
                    <div class="sresult">

                        <ul class="noborder">
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h4>Client Information</h4>
                                    <input type="hidden" name="personalID.id" value = "${clientProfile.id}"/>   
                                    <input type="hidden" name="id" value = "${enrollment.id}"/>  
                                    <input type="hidden" name="projectID.id" value = "${enrollment.projectID.id}"/>  
                                </div>
                            </li>
                            <li>

                                <div class="clientmenu">
                                    <h5>Entry Date</h5>
                                    <fmt:formatDate value="${enrollment.entryDate}" var="entryDateVar" pattern="MM/dd/yyyy" />
                                    <input type="text" name="entryDate" class="entryDateClass" data-role="date" placeholder="Entry Date" value="${entryDateVar}"/>

                                </div>
                            </li>                            
                            <li>

                                <div class="clientmenu">
                                    <h5>Exit Date</h5>
                                    <fmt:formatDate value="${enrollment.exitDate}" var="exitDateVar" pattern="MM/dd/yyyy" />
                                    <input type="text" name="exitDate" class="exitDateClass" data-role="date" placeholder="Exit Date" value="${exitDateVar}"/>

                                </div>
                            </li>                                                        
                            <li>

                                <div class="clientmenu">
                                    <h5>Disabling Condition</h5>
                                    <form:select path="disablingCondition" name="disablingCondition">
                                        <form:option value="-1" label="- Options to select -" />
                                        <form:options items="${fiveValDKRefused}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>
                                </div>
                            </li>
                            <li>

                                <div class="clientmenu">
                                    <h5>Residence Prior</h5>
                                    <form:select path="residencePrior" name="residencePrior">
                                        <form:option value="-1" label="- Options to select -" />
                                        <form:options items="${residencePriorList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>                                    
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Household ID</h5>
                                    <input  type="text" name="householdID" value="${enrollment.householdID}" placeholder="House Hold ID"/>

                                </div>
                            </li>
                            <li>
                                <div class="clientmenu">
                                    <h5>Relationship to head of household</h5>
                                    <form:select path="relationshipToHoH" name="relationshipToHoH">
                                        <form:option value="-1" label="- Options to select -" />
                                        <form:options items="${relationshipToHoHList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>                                      
                                </div>
                            </li>                            
                            <li class="nobg">
                                <div class="clientmenu">
                                    <h5>Other Residence Prior</h5>
                                    <input  type="text" name="otherResidencePrior" value ="${enrollment.otherResidencePrior}" placeholder="Other Residence Prior"/>
                                </div>
                            </li>  
                            <li>

                                <div class="clientmenu">
                                    <h5>Residence Prior Length of Stay</h5>
                                    <form:select path="residencePriorLengthOfStay" name="residencePriorLengthOfStay">
                                        <form:option value="-1" label="- Options to select -" />
                                        <form:options items="${residencePriorLengthOfStayList}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>                                                                          
                                </div>
                            </li>
                            <li>
                                <div class="clientmenu">
                                    <h5> Continuously Homeless One Year</h5>
                                    <form:select path="continuouslyHomelessOneYear" name="continuouslyHomelessOneYear">
                                        <form:option value="-1" label="- Options to select -" />
                                        <form:options items="${fiveValDKRefused}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>                                         
                                </div>
                            </li>    
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5>Times Homeless Past Three Years</h5>
                                    <input type="number" name="timesHomelessPastThreeYears" value="${enrollment.timesHomelessPastThreeYears}"
                                                placeholder="Enter number of times homeless in last 3 years"/>

                                </div>
                            </li>                            
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5>No. of Months Homeless Past Three Years</h5>
                                    <input type="number" name="monthsHomelessPastThreeYears" value="${enrollment.monthsHomelessPastThreeYears}"
                                                placeholder="Enter number of months homeless past 3 years"/>

                                </div>
                            </li>
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5>No. of Years Homeless</h5>
                                    <input type="number" name="yearsHomeless" value="${enrollment.yearsHomeless}"
                                                placeholder="Enter number of years homeless"/>

                                </div>
                            </li>     
                            <li>
                                <div class="clientmenu">
                                    <h5>Status Documented</h5>
                                    <form:select path="statusDocumented" name="statusDocumented">
                                        <form:option value="-1" label="- Select Status Documented -" />
                                        <form:options items="${fiveValDKRefused}" itemValue="valueString" itemLabel="displayName"/>
                                    </form:select>                                                                             
                                </div>
                            </li>  

                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5>Housing Status</h5>
                                    <form:select path="housingStatus" name="housingStatus">
                                        <form:option value="-1" label="- Options to select -" />
                                        <form:options items="${housingStatusList}" itemValue="valueString" itemLabel="displayName"/>
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
                                       data-direction="reverse" data-transition="slide" 
                                       class="whitebtn mtp20" >Back</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${contextPath}/ui" data-role="button" 
                                       data-direction="reverse" data-transition="slide" 
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
        </div>
    </body>
</html>
