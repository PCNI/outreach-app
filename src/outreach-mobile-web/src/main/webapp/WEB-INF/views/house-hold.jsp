<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/><!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Client</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/styles.css">
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/jquery.mobile-1.4.5.min.css">
        <script src="${contextPath}/js/jquery.js"></script>
        <script src="${contextPath}/js/jquery.mobile-1.4.5.js"></script>
        <script>
            function modify_qty(val) {
                var qty = document.getElementById('qty').value;
                var new_qty = parseInt(qty, 10) + val;

                if (new_qty < 0) {
                    new_qty = 0;
                }

                document.getElementById('qty').value = new_qty;
                return new_qty;
            }
        </script>
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
                                <h3>Household</h3>
                            </div>
                        </li>
                        <li>
                            <div class="clientmenu">
                                <h5>Relationship to head of household </h5>
                            </div>
                        </li>
                        <li class="nobg">
                            <div class="clientmenu">
                                <h5 class="locationicon"><img src="img/location.png" width="25" height="36"> Client Location</h5>
                            </div>
                        </li>
                        <li class="nopad nobg">
                            <div class="ui-grid-a">
                                <div class="ui-block-a">
                                    <div class="  ui-bar-a">
                                        <p>Number of adults in the household?</p>
                                        <div class="ui-grid-a">
                                            <div class="ui-block-a leftcounter">
                                                <div class="ui-bar ui-bar-a">
                                                    <div class="box countbox">
                                                        <label for="qty"> Male </label>
                                                        <button id="up" onclick="modify_qty(1)"><img src="img/top-arrow.png" width="24" height="13"> </button>
                                                        <input id="qty" value="0" />
                                                        <button id="down" onclick="modify_qty(-1)"><img src="img/bottom-arro.png" width="24" height="13"> </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="ui-block-b rightcounter">
                                                <div class="ui-bar ui-bar-a">
                                                    <div class="box countbox">
                                                        <label for="qty"> Female </label>
                                                        <button id="up" onclick="modify_qty(1)"><img src="img/top-arrow.png" width="24" height="13"> </button>
                                                        <input id="qty1" value="0" />
                                                        <button id="down" onclick="modify_qty(-1)"> <img src="img/bottom-arro.png" width="24" height="13"></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div class="  ui-bar-a">
                                        <p>Number of children in the household?</p>
                                        <div class="ui-grid-a">
                                            <div class="ui-block-a leftcounter">
                                                <div class="ui-bar ui-bar-a">
                                                    <div class="box countbox">
                                                        <label for="qty"> Male </label>
                                                        <button id="up" onclick="modify_qty(1)"><img src="img/top-arrow.png" width="24" height="13"> </button>
                                                        <input id="qty2" value="0" />
                                                        <button id="down" onclick="modify_qty(-1)"><img src="img/bottom-arro.png" width="24" height="13"> </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="ui-block-b rightcounter">
                                                <div class="ui-bar ui-bar-a">
                                                    <div class="box countbox">
                                                        <label for="qty"> Female </label>
                                                        <button id="up" onclick="modify_qty(1)"><img src="img/top-arrow.png" width="24" height="13"> </button>
                                                        <input id="qty3" value="0" />
                                                        <button id="down" onclick="modify_qty(-1)"> <img src="img/bottom-arro.png" width="24" height="13"></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <p>&nbsp;</p>
                        </li>
                        <li class="nobg mtp10">
                            <div class="clientmenu">
                                <h3>Housing Status</h3>
                            </div>
                        </li>
                        <li>
                            <div class="clientmenu">
                                <h5>Homeless & at-risk of homelessness status</h5>
                            </div>
                        </li>
                        <li class="nobg mtp10">
                            <div class="clientmenu">
                                <h3>Household Members</h3>
                            </div>
                        </li>
                        <li  class="nobg">
                            <div class="ui-grid-a">
                                <div class="ui-block-a">
                                    <div class=" ">Oliver, John / 22<br>
                                        465678</div>
                                </div>
                                <div class="ui-block-b">
                                    <div class=" txtright ">Nephew</div>
                                </div>
                            </div>
                            <div class="ui-grid-a">
                                <div class="ui-block-a">
                                    <div class=" ">John, Nancy / 43<br>
                                        234023</div>
                                </div>
                                <div class="ui-block-b">
                                    <div class=" txtright">Spouse</div>
                                </div>
                            </div>
                            <div class="ui-grid-a">
                                <div class="ui-block-a">
                                    <div class=" ">Trenton, Sammy / 24<br>
                                        567456</div>
                                </div>
                                <div class="ui-block-b">
                                    <div class="txtright ">Child</div>
                                </div>
                            </div>
                            <div class="ui-grid-a">
                                <div class="ui-block-a">
                                    <div class=" ">Elis, Deborah / 21<br>
                                        578345</div>
                                </div>
                                <div class="ui-block-b">
                                    <div class=" txtright">Child</div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="ui-grid-a">
                    <div class="ui-block-a"> <a href="profile.html" data-role="button"  data-direction="reverse" data-transition="slide"  class="whitebtn mtp20" >Back</a> </div>
                    <div class="ui-block-b"> <a href="profile.html" data-role="button"  data-direction="reverse" data-transition="slide"  class="blubtn mtp20" >Save</a> </div>
                </div>
                </body>
                </html>
