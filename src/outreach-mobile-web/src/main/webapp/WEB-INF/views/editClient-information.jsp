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

    </head>

    <body>
        <div data-role="page">
            <script src="${contextPath}/resources/js/jquery.js"></script>
            <script src="${contextPath}/resources/js/lov.js"></script>
            <script src="${contextPath}/resources/js/utils.js"></script>
            <script>
                <!--    
                var enrollmentId;
                    msg = "The Client has been updated successfully";
                    methodAjax = "PUT";
                <c:if test="${not empty enrollment}">
                enrollmentId = ${enrollment.id};
                </c:if>
                var id = ${clientProfile.id};
                $(document).on('pageinit', function ()
                {
                <c:choose>
                    <c:when test="${not empty enrollment}">
                    var selectFieldId = $("#DisablingCondition");
                    getLOVWithSelectedValue("fiveValDKRefused", selectFieldId, checkForEmpty(${enrollment.disablingCondition}));

                    selectFieldId = $("#ResidencePrior");
                    getLOVWithSelectedValue("residencePrior", selectFieldId, checkForEmpty(${enrollment.residencePrior}));

                    selectFieldId = $("#ResidencePriorLengthOfStay");
                    getLOVWithSelectedValue("residencePriorLengthOfStay", selectFieldId, checkForEmpty(${enrollment.residencePriorLengthOfStay}));

                    selectFieldId = $("#StatusDocumented");
                    getLOVWithSelectedValue("fiveValDKRefused", selectFieldId, checkForEmpty(${enrollment.statusDocumented}));

                    selectFieldId = $("#ContinuouslyHomelessOneYear");
                    getLOVWithSelectedValue("fiveValDKRefused", selectFieldId, checkForEmpty(${enrollment.continuouslyHomelessOneYear}));

                    selectFieldId = $("#RelationshipToHoH");
                    getLOVWithSelectedValue("relationshipToHoH", selectFieldId, checkForEmpty(${enrollment.relationshipToHoH}));
                    
                    selectFieldId = $("#HousingStatus");
                    getLOVWithSelectedValue("housingStatus", selectFieldId, checkForEmpty(${enrollment.housingStatus}));                    

                    $("#HouseholdID").val('${enrollment.householdID}');
                    $("#ID").val('${enrollment.id}');
                    $("#OtherResidencePrior").val('${enrollment.otherResidencePrior}');
                    $("#TimesHomelessPastThreeYears").val('${enrollment.timesHomelessPastThreeYears}');
                    $("#MonthsHomelessPastThreeYears").val('${enrollment.monthsHomelessPastThreeYears}');
                    $("#YearsHomeless").val('${enrollment.yearsHomeless}');


                    </c:when>
                    <c:otherwise>
                    var selectFieldId = $("#DisablingCondition");
                    getLOV("fiveValDKRefused", selectFieldId);

                    selectFieldId = $("#ResidencePrior");
                    getLOV("residencePrior", selectFieldId);

                    selectFieldId = $("#ResidencePriorLengthOfStay");
                    getLOV("residencePriorLengthOfStay", selectFieldId);

                    selectFieldId = $("#StatusDocumented");
                    getLOV("fiveValDKRefused", selectFieldId);

                    selectFieldId = $("#ContinuouslyHomelessOneYear");
                    getLOV("fiveValDKRefused", selectFieldId);

                    selectFieldId = $("#RelationshipToHoH");
                    getLOV("relationshipToHoH", selectFieldId);
                    
                    selectFieldId = $("#HousingStatus");
                    getLOV("housingStatus", selectFieldId);                    

                    </c:otherwise>
                </c:choose>
                    
                    $('#save').click(function () {
                        var dt = $('#profileForm').serializeObject();
                        arrangeJson(dt);
                        var jsonObj = JSON.stringify(dt);

                        $.ajax({
                            type: methodAjax,
                            url: "../service/enrollment",
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
                                $("#popupDialog #title").html("Success");
                                $("#popupDialog .ui-title").html(msg);
                                $("#successMsg").click();
                                $('#popupDialog').fadeOut(5000, function () {
                                    $(this).remove();
                                    var enId = data.body[0].ID;
                                    var clId = data.body[0].PersonalID.ID;
                                    window.location.href = "${contextPath}/ui/viewProfile?id=" + clId + "&enrollmentId=" + enId;
                                });

                            },
                            error: function (data) {
                                $("#popupDialog").html("Error while saving the information");
                                $("#successMsg").click();
                                $('#popupDialog').fadeOut(5000, function () {
                                    $(this).remove();
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


                    function arrangeJson(data) {
                        var initMatch = /^([a-z0-9]+?)\[/i;
                        var first = /^\[[a-z0-9]+?\]/i;
                        var isNumber = /^[0-9]$/;
                        var bracers = /[\[\]]/g;
                        var splitter = /\]\[|\[|\]/g;

                        for (var key in data) {
                            if (initMatch.test(key)) {
                                data[key.replace(initMatch, '[$1][')] = data[key];
                            }
                            else {
                                data[key.replace(/^(.+)$/, '[$1]')] = data[key];
                            }
                            delete data[key];
                        }


                        for (var key in data) {
                            processExpression(data, key, data[key]);
                            delete data[key];
                        }

                        function processExpression(dataNode, key, value) {
                            var e = key.split(splitter);
                            if (e) {
                                var e2 = [];
                                for (var i = 0; i < e.length; i++) {
                                    if (e[i] !== '') {
                                        e2.push(e[i]);
                                    }
                                }
                                e = e2;
                                if (e.length > 1) {
                                    var x = e[0];
                                    var target = dataNode[x];
                                    if (!target) {
                                        if (isNumber.test(e[1])) {
                                            alert(e[1]);
                                            dataNode[x] = [];
                                        }
                                        else {
                                            dataNode[x] = {}
                                        }
                                    }
                                    processExpression(dataNode[x], key.replace(first, ''), value);
                                }
                                else if (e.length == 1) {
                                    dataNode[e[0]] = value;
                                }
                                else {
                                    alert('This should not happen...');
                                }
                            }
                        }
                    }

                });
-->   
            </script>
            <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>
            <a id='successMsg' href="#popupDialog" data-rel="popup" data-position-to="window"
               data-transition="pop" style='display:none;'></a>
            <div data-role="popup" id="popupDialog" data-overlay-theme="a" data-theme="a" 
                 style="max-width:400px;">
                <div data-role="header" data-theme="c">
                    <h1 id="title"></h1>
                </div>
                <div role="main" class="ui-content">
                    <h3 class="ui-title"></h3>
                </div>                    
            </div>

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
                    <form id="profileForm">
                        <ul class="noborder">
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h4>Client Information</h4>
                                    <input type="hidden" name="PersonalID[ID]" value = "${clientProfile.id}"/>   
                                    <input type="hidden" name="ID" id="ID" />   
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
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5></h5>
                                    <input type="text" name="HouseholdID" id="HouseholdID" 
                                           placeholder="House Hold ID">

                                </div>
                            </li>
                            <li>
                                <div class="clientmenu">
                                    <select name="RelationshipToHoH" id="RelationshipToHoH" >
                                        <option value="-1" selected = "selected">Relationship To HoH</option>
                                    </select>                                  
                                </div>
                            </li>                            
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5></h5>
                                    <input type="text" name="OtherResidencePrior" id="OtherResidencePrior" 
                                           placeholder="Other Residence Prior">

                                </div>
                            </li>  
                            <li>

                                <div class="clientmenu">
                                    <select name="ResidencePriorLengthOfStay" id="ResidencePriorLengthOfStay" >
                                        <option value="-1" selected = "selected">Length of Stay</option>
                                    </select>                                  
                                </div>
                            </li>
                            <li>
                                <div class="clientmenu">
                                    <select name="ContinuouslyHomelessOneYear" id="ContinuouslyHomelessOneYear" >
                                        <option value="-1" selected = "selected">Continuously Homeless One Year</option>
                                    </select>                                  
                                </div>
                            </li>    
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5></h5>
                                    <input type="text" name="TimesHomelessPastThreeYears" id="TimesHomelessPastThreeYears" 
                                           placeholder="No. of times homeless in last 3 years">

                                </div>
                            </li>                            
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5></h5>
                                    <input type="text" name="MonthsHomelessPastThreeYears" id="MonthsHomelessPastThreeYears" 
                                           placeholder="No. of months homeless in last 3 years">

                                </div>
                            </li>
                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5></h5>
                                    <input type="text" name="YearsHomeless" id="YearsHomeless" 
                                           placeholder="Years Homeless">

                                </div>
                            </li>     
                            <li>
                                <div class="clientmenu">
                                    <select name="StatusDocumented" id="StatusDocumented" >
                                        <option value="-1" selected = "selected">Status Documented</option>
                                    </select>                                  
                                </div>
                            </li>                               
               

                            <li class="nobg">

                                <div class="clientmenu">
                                    <h5></h5>
                                    <select name="HousingStatus" id="HousingStatus" >
                                        <option value="-1" selected = "selected">Housing Status</option>
                                    </select>  
                                </div>
                            </li>                      

                        </ul>
                    </form>       
                </div>
                <div class="ui-grid-a">
                    <div class="ui-block-a">
                        <a href="${contextPath}/ui/profile?id=${clientProfile.id}" data-role="button" 
                           data-direction="reverse" data-transition="slide" 
                           class="whitebtn mtp20" >Back</a>

                    </div>

                    <div class="ui-block-b">
                        <button type="button" id="save" data-role="button" class="blubtn mtp20" 
                                data-transition="fade">Save</button> 
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
