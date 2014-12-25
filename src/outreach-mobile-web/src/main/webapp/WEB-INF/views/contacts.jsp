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
            <div data-role="header" class="mheader">
                <a class="iconbar ui-nodisc-icon " href="#menu" data-role="button" data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
                <h1>Client</h1>
                <a href="search.html" data-transition="fade"  class="hdright"> <img src="img/hsearch.png"  ></a>
            </div>
            <div data-role="panel" id="menu" data-display="push">
                <div class="ui-panel-inner">
                    <h3>Menu</h3>
                    <ul data-role="listview" class="mainMenu">
                        <li> <a href="search.html" data-transition="slide" ><img src="img/search.png"  > Client Search</a></li>
                        <li><a href="index.html" data-transition="slide" ><img src="img/logout.png"  > Logout</a></li>
                    </ul>
                </div>
            </div>
            <div data-role="main" class="ui-content maincontent">



                <div class="sresult">
                    <ul class="noborder">
                        <li class="nobg">

                            <div class="clientmenu">
                                <h3>Contacts</h3>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <h5>Date </h5>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <h5>Location of Contact</h5>

                            </div>
                        </li>
                        <li class="nobg mtp10">

                            <div class="clientmenu">
                                <h3>Date of Engagement</h3>

                            </div>
                        </li>
                        <li>

                            <div class="clientmenu">
                                <h5>Date</h5>

                            </div>
                        </li>

                    </ul>
                </div>
                <div class="ui-grid-a">



                    <a href="profile.html" data-role="button"  data-direction="reverse" data-transition="slide"  class="blubtn mtp20" >Save</a> 


                </div>

                </body>
                </html>
