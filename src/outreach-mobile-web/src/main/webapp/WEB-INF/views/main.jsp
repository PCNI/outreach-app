<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Search</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.min.js"></script>
        <script src="${contextPath}/resources/js/jquery.js"></script>
    </head>

    <body>
        <div data-role="page" id="main-page">
            <div data-role="header" class="mheader"> 
                <a class="iconbar ui-nodisc-icon " href="#main-page-menu" data-role="button" data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Search</h1>

            </div>
            <div data-role="panel" id="main-page-menu" data-display="push" class="push-menu">
                <div class="ui-panel-inner">
                    <h3>Menu</h3>
                    <ul data-role="listview" class="mainMenu">
                        <li> <a href="${contextPath}/ui" data-transition="fade" ><img src="${contextPath}/resources/img/search.png"  > Client Search</a></li>
                        <li><a href="${contextPath}/logout" data-transition="fade" ><img src="${contextPath}/resources/img/logout.png"  > Logout</a></li>
                    </ul>
                </div>
            </div>
            <div data-role="main" class="ui-content maincontent">
                <div class="logo"> <img src="${contextPath}/resources/img/logo.jpg" width="265" height="99"> </div>
                    <form:form id="searchForm" name="searchForm"
                               action="${contextPath}/ui/search" method="post" modelAttribute="clientSearchRequest">

                    <div class="loginform">
                        <div class="ui-field-contain">
                            <input type="text" name="firstName" placeholder="First Name">
                            <input type="text" name="lastName" placeholder="Last Name">
                            <input type="text" name="ssn" placeholder="Social Security #">
                            <input type="text" name="personalId" placeholder="Client Key">
                            <div class="ui-grid-a mtp20">
                                <div class="ui-block-a">
                                    <label>Age Range</label>
                                </div>
                            </div>
                            <div class="agerange">
                                <div data-role="rangeslider" data-track-theme="b" data-theme="a">
                                    <div class="ageboxl"><img src="${contextPath}/resources/img/agebox.png" ></div>
                                    <div class="agetxtl"><p>yrs</p></div>
                                    <input name="sAge" id="range-3a" min="0" max="100" value="20" type="range">
                                    <input name="eAge" id="range-3b" min="0" max="100" value="50" type="range">
                                    <div class="agetxtr"><p>yrs</p></div>
                                    <div class="ageboxr"><img src="${contextPath}/resources/img/agebox1.png" ></div>
                                </div>
                            </div>
                            <br/>
                            <button type="submit" data-role="button" data-transition="slide" class="blubtn mtp40">Go</button>
                            <!--<input type = "submit" value="Go" style="background-color: #1B93CF !important" data-role="button"  data-transition="fade" />-->
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </body>
</html>
