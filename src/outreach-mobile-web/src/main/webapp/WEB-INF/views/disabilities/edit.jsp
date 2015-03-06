<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <script src="${contextPath}/js/jquery.js"></script>
        <script src="${contextPath}/js/jquery.mobile-1.4.5.js"></script>
<script src="${contextPath}/resources/js/jquery.validate.js"></script> 
    </head>

    <body>
        <div data-role="page" id="disabilities">
            <div data-role="header" class="mheader">
                <a class="iconbar ui-nodisc-icon " href="#menu" data-role="button" data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Client</h1>
                <a href="${contextPath}/ui" data-transition="fade" class="hdright"> <img src="${contextPath}/resources/img/hsearch.png"  ></a>
            </div>
            <div data-role="panel" id="menu" data-display="push" class="push-menu">
                <div class="ui-panel-inner">
                    <h3>Menu</h3>
                    <ul data-role="listview" class="mainMenu">
                      <li> <a href="${contextPath}/ui" data-transition="fade" >
                                <img src="${contextPath}/resources/img/search.png"  > Client Search</a></li>
                        <li><a href="${contextPath}/logout" data-transition="fade" >
                                <img src="${contextPath}/resources/img/logout.png"  > Logout</a></li>
                    </ul>
                </div>
            </div>
            <div data-role="main" class="ui-content maincontent">
                <form:form action="${contextPath}/ui/disability" method="post" modelAttribute="disabilitiesListWrapper" id="disabilitiesForm">
                    <div class="sresult">
                        <h3>Disabilities and Health Conditions</h3>
                        <div class="hcaccord" data-role="collapsibleset" data-inset="false" data-iconpos="right">
                            <c:forEach items="${disabilitiesListWrapper.disabilitiesList}" var="x" varStatus="itemCount">
                                <div data-role="collapsible">
                                    <h3 class="bgarrow">${disabilityTypeMap[x.disabilityType]}</h3>
                                    <form:input type="hidden" path="disabilitiesList[${itemCount.index}].id" value = "${x.id}"/>
                                    <form:input type="hidden" path="disabilitiesList[${itemCount.index}].disabilityType" value = "${x.disabilityType}"/>
                                    <form:input type="hidden" path="disabilitiesList[${itemCount.index}].projectEntryID.id" value = "${enrollment.id}"/>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td colspan="3" class="btmboder"><p class="tmini padrt10">
                                                <ul class="noborder">
                                                    <li>
                                                        <div class="clientmenu">
                                                            <h5>Disability Response</h5>
                                                            <form:input type="number" path="disabilitiesList[${itemCount.index}].disabilityResponse" cssClass="disabilityResponseClass" placeholder="Number" value = "${x.disabilityResponse}"/>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="clientmenu">
                                                            <h5>Indefinite and Impairs?</h5>
                                                            <form:select path="disabilitiesList[${itemCount.index}].indefiniteAndImpairs" >
                                                                <form:option value="-1" label="- Select Indefinite and Impairs -" />
                                                                <form:options items="${fiveValDKRefused}" itemValue="valueString" itemLabel="displayName"/>
                                                            </form:select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="clientmenu">
                                                            <h5>Documentation on File?</h5>
                                                            <form:select path="disabilitiesList[${itemCount.index}].documentationOnFile" >
                                                                <form:option value="-1" label="- Select Documentation on File -" />
                                                                <form:options items="${noYes}" itemValue="valueString" itemLabel="displayName"/>
                                                            </form:select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="clientmenu">
                                                            <h5>Receiving Services?</h5>
                                                            <form:select path="disabilitiesList[${itemCount.index}].receivingServices" >
                                                                <form:option value="-1" label="- Select Receiving Services -" />
                                                                <form:options items="${fiveValDKRefused}" itemValue="valueString" itemLabel="displayName"/>
                                                            </form:select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="clientmenu">
                                                            <h5>PATH How Confirmed?</h5>
                                                            <form:select path="disabilitiesList[${itemCount.index}].pATHHowConfirmed" >
                                                                <form:option value="-1" label="- Select PATH How Confirmed -" />
                                                                <form:options items="${pathHowConfirmed}" itemValue="valueString" itemLabel="displayName"/>
                                                            </form:select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="clientmenu">
                                                            <h5>PATH SMI Information</h5>
                                                            <form:select path="disabilitiesList[${itemCount.index}].pATHSMIInformation" >
                                                                <form:option value="-1" label="- Select PATH SMI Information -" />
                                                                <form:options items="${pathSMIInformation}" itemValue="valueString" itemLabel="displayName"/>
                                                            </form:select>
                                                        </div>
                                                    </li>
                                                </ul>    
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </c:forEach>
                        </div>
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
