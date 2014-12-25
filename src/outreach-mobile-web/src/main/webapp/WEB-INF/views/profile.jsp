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
<div data-role="page">
    <script id="mobile-datepicker" src="${contextPath}/resources/js/jquery.mobile.datepicker.js"></script>    
    <script src="${contextPath}/resources/js/jquery.ui.datepicker.js"></script> 
    <script src="${contextPath}/resources/js/lov.js"></script>
    <script>
    <!--    
    $(document).on('pageinit',function()
    {   
                var id = ${clientProfile.id};
                $.ajax({
                        type:"GET",
                        url:"../service/client/"+id,
                        data :"",
                        dataType:"json",
                        restful:true,
                        contentType: "application/json",
                        cache:false,
                        timeout:20000,
                        async:true,
                        beforeSend :function(xhr) {
                            //xhr.setRequestHeader('Authorization',bearer);
                        },
                        success:function(data){
                            $("#nameVal").html(data.body[0].FirstName+" "+data.body[0].LastName);
                            $("#dobVal").html(new Date(data.body[0].DOB));
                            $("#idVal").html(data.body[0].id);
                            $("#genderVal").html(data.body[0].Gender);
                            $("#raceVal").html(data.body[0].Race);
                        },
                        error:function(data){
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
            <a href="${contextPath}/ui/editProfile"> 
                <img src="${contextPath}/resources/img/edit.png"  >
            </a>
        </c:when>
        <c:otherwise>
            <img src="${contextPath}/resources/img/edit-dis.png"  >
        </c:otherwise>  
    </c:choose>            
  </div>
  <h4 id='nameVal'>&nbsp;</h4>
  <p id='dobVal'><c:out value="${clientProfile.dob}"/></p>
  <p id="idVal"></p>
  <hr>
  <p id="genderVal"><c:out value="${clientProfile.gender}"/></p>
  <p id="raceVal"><c:out value="${clientProfile.race}"/></p>  
  </div>
  </div>
  
  
  <div class="sresult">
    <ul >
      <li>
         
        <div class="clientmenu">
        <a href="${contextPath}/ui/clientinfo?id=${clientProfile.id}" data-transition="slide"><h4>Client Information</h4></a>
           
        </div>
      </li>
      <li>
         
        <div class="clientmenu">
         <a href="${contextPath}/ui/healthconditions?id=${clientProfile.id}" data-transition="slide"> <h4>Disabilities & Health Conditions </h4> </a>
          
        </div>
      </li>
      <li>
         
        <div class="clientmenu">
          <a href="${contextPath}/ui/household?id=${clientProfile.id}" data-transition="slide"> <h4>Household Information</h4></a>
          
        </div>
      </li>
      <li>
         
        <div class="clientmenu">
          <a href="${contextPath}/ui/contact?id=${clientProfile.id}" data-transition="slide"><h4> Contact</h4></a>
          
        </div>
      </li>
      <li>
         
        <div class="clientmenu">
         <a href="${contextPath}/ui/pathreferral?id=${clientProfile.id}" data-transition="slide"> <h4>Services / PATH Status</h4></a>
          
        </div>
      </li>
    </ul>
  </div>
  
</body>
</html>
