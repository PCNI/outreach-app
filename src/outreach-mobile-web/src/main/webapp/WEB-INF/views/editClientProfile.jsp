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
        <link rel="stylesheet" href="${contextPath}/resources/css/jquery.mobile.datepicker.css">	
        <script src="${contextPath}/resources/js/jquery.js"></script>
			
    </head>

    <body>
        <div data-role="page" id="one">
            <div data-role="header" class="mheader">        
		<script src="${contextPath}/resources/js/lov.js"></script>  
		<script src="${contextPath}/resources/js/utils.js"></script>  
		<script>
                    <!--
                    $(document).on('pageinit', function ()
                    {
                        //profile edit starts

                        $(".dateClass").datepicker({yearRange: "1930:2010"});
                        
                        var yearRange = $(".dateClass").datepicker("option", "yearRange");
                        $(".dateClass").datepicker("option", "yearRange", "1930:2010");

                        var changeYear = $(".dateClass").datepicker("option", "changeYear");
                        $(".dateClass").datepicker("option", "changeYear", true);

                        $(".dateClass").datepicker({dateFormat: "yy-mm-dd"});

                        var dateFormat = $(".dateClass").datepicker("option", "dateFormat");
                        $(".dateClass").datepicker("option", "dateFormat", "yy-mm-dd");

                        loadClientData();

                        var selectFieldId = $("#NameDataQuality");
                        getLOVWithSelectedValue("nameDataQuality", selectFieldId, checkForEmpty(${clientProfile.nameDataQuality}));

                        selectFieldId = $("#SSNDataQuality");
                        getLOVWithSelectedValue("ssnDataQuality", selectFieldId, checkForEmpty(${clientProfile.sSNDataQuality}));

                        selectFieldId = $("#Race");
                        getLOVWithSelectedValue("race", selectFieldId, checkForEmpty(${clientProfile.race}));

                        selectFieldId = $("#Ethnicity");
                        getLOVWithSelectedValue("ethnicity", selectFieldId, checkForEmpty(${clientProfile.ethnicity}));

                        selectFieldId = $("#DOBDataQuality");
                        getLOVWithSelectedValue("dobDataQuality", selectFieldId, checkForEmpty(${clientProfile.dobDataQuality}));

                        selectFieldId = $("#Gender");
                        getLOVWithSelectedValue("gender", selectFieldId, checkForEmpty(${clientProfile.gender}));

                        selectFieldId = $("#VeteranStatus");
                        getLOVWithSelectedValue("fiveValDKRefused", selectFieldId, checkForEmpty(${clientProfile.veteranStatus}));

                        $.getJSON('${contextPath}/service/lov/gender/'+${clientProfile.gender}, function(jd) {
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

                        $('#updateProfile').click(function () {
                            var jsonObj = JSON.stringify($('#profileForm').serializeObject());
                            $.ajax({
                                type: "PUT",
                                url: "../service/client",
                                data: jsonObj,
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
                                    if (data.header.count == 1) {
                                        $("#popupDialog .status").html("Success");
                                        $("#popupDialog .ui-title").html("The Client has been updated successfully");
                                        $("#successMsg").click();
                                        $("#popupDialog").fadeOut(5000, function () {
                                            $(this).remove();
                                            var enId = data.body[0].ID;
                                            window.location.href = "${contextPath}/ui/profile?id=" + enId;
                                        });
                                    } else if (data.header.count == 0) {
                                        $("#popupDialog .status").html("Sorry!");
                                        $("#popupDialog .ui-title").html(data.header[0].message);
                                        $("#successMsg").click();
                                        $("#popupDialog").fadeOut(5000, function () {
                                            $(this).remove();
                                            window.location.href = "${contextPath}/ui/"
                                        });
                                    }
                                },
                                error: function (data) {
                                    $("#popupDialog .status").html("Sorry!");
                                    $("#popupDialog .ui-title").html("The Client could not be added at this time.");
                                    $("#successMsg").click();
                                    $("#popupDialog").fadeOut(5000, function () {
                                        $(this).remove();
                                        window.location.href = "${contextPath}/ui/";
                                    });
                                }
                            });
                            return false;
                        });

                        $.fn.serializeObject = function ()
                        {
                            var obj = {};
                            var profileFieldArray = this.serializeArray();
                            $.each(profileFieldArray, function () {
                                if (obj[this.name] !== undefined) {
                                    if (!obj[this.name].push) {
                                        obj[this.name] = [obj[this.name]];
                                    }
                                    obj[this.name].push(this.value || '');
                                } else {
                                    obj[this.name] = this.value || '';
                                }
                            });
                            return obj;
                        };

                        // profile ends
                    });


                    function loadClientData() {
                        var fdt = OformatStrDate("<c:out value="${clientProfile.dob}"/>");;
                        $("#DOB").val(fdt);
                        $("#dobVal").html(fdt);
                        $("#ID").val('${clientProfile.id}');
                        $("#SSN").val('${clientProfile.ssn}');
                        $("#FirstName").val('${clientProfile.firstName}');
                        $("#MiddleName").val('${clientProfile.middleName}');
                        $("#LastName").val('${clientProfile.lastName}');
                        $("#NameSuffix").val('${clientProfile.nameSuffix}');
                    }


-->
                </script>     
                <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>	
                <script src="${contextPath}/resources/js/jquery.ui.datepicker.js"></script> 
                <script id="mobile-datepicker" src="${contextPath}/resources/js/jquery.mobile.datepicker.js"></script>  			
  	

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
                <a id='successMsg' href="#popupDialog" data-rel="popup" data-position-to="window"
                   data-transition="pop" style='display:none;'></a>
                <div data-role="popup" id="popupDialog" data-overlay-theme="a" data-theme="a" 
                     style="max-width:400px;">
                    <div data-role="header" data-theme="c">
                        <h1 class="status"></h1>
                    </div>
                    <div role="main" class="ui-content">
                        <h3 class="ui-title"></h3>
                    </div>                    
                </div>                
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
                                    <a href="${contextPath}/ui/editClientProfile?id=<c:out value="${clientProfile.id}"/>" rel="external"> 
                                        <img src="${contextPath}/resources/img/edit.png"  >
                                    </a>
                        </div>
                        <h4 id="nameVal"><c:out value="${clientProfile.firstName}"/> <c:out value="${clientProfile.middleName}"/> <c:out value="${clientProfile.lastName}"/>&nbsp;</h4>
                        <p id="dobVal"></p>
                        <p id="idVal"><c:out value="${clientProfile.id}"/></p>
                        <hr>
                        <p id="genderVal"><c:out value="${clientProfile.gender}"/></p>
                        <p id="raceVal"><c:out value="${clientProfile.race}"/></p>

                    </div>
                </div>
                <form action="" method="post" id="profileForm">
                    <div class="sresult">
                        <ul class="noborder">
                            <li class="nobg">
                                <div class="clientmenu">
                                    <input type="text" name="ID" 
                                           id="ID" placeholder="Client ID" readonly="true">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <select name="NameDataQuality" id="NameDataQuality" 
                                            placeholder="Name Data Quality">
                                        <option value="-1" selected = "selected">
                                            Name Data Quality</option>
                                    </select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu"> 
                                    <input type="text" name="FirstName" id="FirstName" placeholder="First Name">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <input type="text" name="MiddleName" id="MiddleName" placeholder="Middle Name">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <input type="text" name="LastName" id="LastName" placeholder="Last Name">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu"> 
                                    <input type="text" name="NameSuffix" id="NameSuffix" placeholder="Suffix">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <select name="Gender" id="Gender" placeholder="Gender">
                                        <option value="-1" selected = "selected">Gender</option>
                                    </select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <select name="SSNDataQuality" id="SSNDataQuality" placeholder="SSN Quality">
                                        <option value="-1" selected = "selected">SSN Quality</option>
                                    </select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">             
                                    <input type="text" name="SSN" id="SSN" placeholder="SSN">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <select name="DOBDataQuality" id="DOBDataQuality" placeholder="DOB Type">
                                        <option value="-1" selected = "selected">DOB Type</option>      
                                    </select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <input type="text" name="DOB" class="dateClass" data-role="date" id="DOB" 
                                           value='' placeholder="DOB">
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <select name="Race" id="Race" placeholder="Race">
                                        <option value="-1" selected = "selected">Race</option>    
                                    </select>
                                </div>
                            </li>
                            <li class="nobg">
                                <div class="clientmenu">
                                    <select name="Ethnicity" id="Ethnicity" placeholder="Ethnicity">
                                        <option value="-1" selected = "selected">Ethnicity</option>
                                    </select>
                                </div>
                            </li>
                            <li>

                                <div class="clientmenu">
                                    <select name="VeteranStatus" id="VeteranStatus">
                                        <option value="-1" selected = "selected">Select Veteran Status</option>
                                    </select>                                

                                </div>
                            </li>

                        </ul>
                    </div> 
                    <div class="ui-grid-a">
                        <div class="ui-block-a">
                            <a href='${contextPath}/ui/profile?id=<c:out value="${clientProfile.id}"/>' data-role="button" class="whitebtn mtp20" >Back</a>
                        </div>
                        <div class="ui-block-b">
                            <button type="button" id="updateProfile" data-role="button" class="blubtn mtp20" 
                                    data-transition="fade">Save</button> 
                        </div>
                    </div>

                </form>  




            </div>
        </div><!-- /page popup -->
    </body>
</html>
