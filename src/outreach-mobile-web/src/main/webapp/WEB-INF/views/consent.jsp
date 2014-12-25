<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Consent</title>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/css/jquery.mobile.datepicker.css"> 		   
        <script src="${contextPath}/resources/js/jquery.js"></script>
        <script src="${contextPath}/resources/js/jquery.mobile-1.4.5.js"></script>      
	
    </head>

    <body>
    <div data-role="page">
	<script id="mobile-datepicker" src="${contextPath}/resources/js/jquery.mobile.datepicker.js"></script>	
	<script src="${contextPath}/resources/js/jquery.ui.datepicker.js"></script>	
	<script>
		<!--    
		$(document).on('pageinit',function()
		{
			$( ".dateClass").datepicker({
					yearRange: "1930:2010"
			});

			var yearRange = $( ".dateClass" ).datepicker( "option", "yearRange" );
			$( ".dateClass" ).datepicker( "option", "yearRange", "1930:2010" );

			var changeYear = $( ".dateClass" ).datepicker( "option", "changeYear" );
			$( ".dateClass" ).datepicker( "option", "changeYear", true );			
			
			$( ".dateClass").datepicker({   
				dateFormat: "mm/dd/yy"
			});
				
			var dateFormat = $( ".dateClass" ).datepicker( "option", "dateFormat" );
			// Setter
			$( ".dateClass" ).datepicker( "option", "dateFormat", "mm/dd/yy" );


		   $("#iConsent").click(function(){
			 var clientName = $("#firstName").val();
			 var dob = $("#dob").val();
			 if(clientName.length === 0 || dob.length === 0){// && consent == false
				alert("Please fill in the details and read the terms and agree. ");
				return false;
			 }else{
				if(!$("#Remember").is(":checked")){
					alert("We require your consent before you proceed.");
					return false;
				}
			 }
			return true;
		   });    
		});
		
		-->
	</script>
		
        <div data-role="header" class="mheader">
			<a class="iconbar ui-nodisc-icon " href="#menu" data-role="button" 
			   data-theme="c" data-rel="back" data-inline="true" data-icon="bars"> &nbsp; </a>
			<h1>Consent</h1>
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
                <h4>HMIS Client Informed Consent Release of Information Authorization</h4>
                <p>Instructions here about expectations with regards to securing informed consent.</p>
                <div class="contscroll">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                    Cras risus urna, mattis sed lacinia eu, tempus vitae eros. 
                    Praesent porttitor in velit vitae aliquet. Mauris a odio sed dolor 
                    vehicula congue sit amet sed justo. Aliquam tincidunt dictum nisl, 
                    sit amet tempus felis feugiat eget. Etiam gravida tincidunt dui sit 
                    amet tempor. Etiam sagittis metus orci, eget egestas turpis imperdiet et. 
                    Fusce vitae ligula non turpis pellentesque luctus. Vestibulum ante 
                    ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;
                    Nullam nec turpis iaculis sem placerat fringilla. Aenean id molestie odio. 
                    Aliquam et dictum neque. Etiam gravida tincidunt dui sit amet tempor. 
                    Etiam sagittis metus orci, eget egestas turpis imperdiet et. Fusce vitae 
                    ligula non turpis pellentesque luctus. Vestibulum ante ipsum primis in faucibus 
                    orci luctus et ultrices posuere cubilia Curae; Nullam nec turpis iaculis 
                    sem placerat fringilla. Aenean id molestie odio. Aliquam et dictum neque.
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras risus urna, 
                    mattis sed lacinia eu, tempus vitae eros. Praesent porttitor in velit vitae aliquet. 
                    Mauris a odio sed dolor vehicula congue sit amet sed justo. Aliquam tincidunt 
                    dictum nisl, sit amet tempus felis feugiat eget. Etiam gravida tincidunt dui 
                    sit amet tempor. Etiam sagittis metus orci, eget egestas turpis imperdiet et. 
                    Fusce vitae ligula non turpis pellentesque luctus. Vestibulum ante ipsum primis in 
                    faucibus orci luctus et ultrices posuere cubilia Curae; Nullam nec turpis iaculis 
                    sem placerat fringilla. Aenean id molestie odio. Aliquam et dictum neque. 
                    Etiam gravida tincidunt dui sit amet tempor. Etiam sagittis metus orci, 
                    eget egestas turpis imperdiet et. Fusce vitae ligula non turpis 
                    pellentesque luctus. Vestibulum ante ipsum primis in faucibus orci luctus et 
                    ultrices posuere cubilia Curae; Nullam nec turpis iaculis sem placerat fringilla.
                    Aenean id molestie odio. Aliquam et dictum neque.
                </div>
                <div class="loginform">
                    <div class="ui-field-contain">
                        <form action="${contextPath}/ui/profileForm" method="post" id="consentForm">
                            <input type="text" name="firstName" id="firstName" 
                                   placeholder="Client Name">
                            <input type="text" data-role="date" id="dob" name="dob" 
                                   class="dateClass" placeholder="Date of Birth"/>

                            <div class="ui-grid-a mtp20">
                                <label for="Remember">I have read and understand and 
                                    I consent to the terms and agreements of this app.</label>
                                <input type="checkbox" name="consent" id="Remember" value="Consent">
                            </div>
                            <button type="submit" data-transition="fade" data-role="button" id="iConsent" 
                                    class="blubtn mtp40" >I Consent</button> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
