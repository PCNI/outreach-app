<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Client</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.mobile-1.4.5.min.css">
<script src="${contextPath}/js/jquery.js"></script>
<script src="${contextPath}/js/jquery.mobile-1.4.5.js"></script>
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
  
    <h3>Disabilities and Health Conditions</h3>
   <div class="hcaccord" data-role="collapsibleset" data-inset="false" data-iconpos="right">
    <div data-role="collapsible">
      <h3 class="bgarrow">Physical Disability <span>Yes</span></h3>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="33%"  class="btmboder bgarrowrt"><p>02/15/2014</p></td>
    <td colspan="2"  class="btmboder borderl padlt15 bgarrow"><p>Physical Disability</p></td>
    </tr>
  <tr>
    <td colspan="2" class="btmboder"><p class="tmini padrt10">Expected to be of long-continued
      and indefinite duration and substantially
      impairs ability live independently</p></td>
    <td width="33%" align="right"  class="btmboder bgarrow"><p class="tmini padrt10 padrt20">No</p></td>
  </tr>
  <tr>
    <td colspan="2"  class="btmboder"><p class="tmini padrt10">Documentation of the disability and  
      severity of the file</p></td>
    <td width="33%" align="right"  class="btmboder"><form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form></td>
  </tr>
  <tr>
    <td colspan="2"><p class="tmini padrt10">Currently receiving treatment or services  
      with this facility</p></td>
    <td width="33%" align="right"> 
    <form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form>
    
    </td>
  </tr>
</table>

    </div>
    <div data-role="collapsible">
        <h3>Development Disability</h3>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="33%"  class="btmboder bgarrowrt"><p>02/15/2014</p></td>
    <td colspan="2"  class="btmboder borderl padlt15 bgarrow"><p>Development Disability</p></td>
    </tr>
  <tr>
    <td colspan="2" class="btmboder"><p class="tmini padrt10">Expected to substantially impair ability to live ndependently</p></td>
    <td width="33%" align="right"  class="btmboder bgarrow"><p class="tmini padrt10 padrt20">No</p></td>
  </tr>
  <tr>
    <td colspan="2"  class="btmboder"><p class="tmini padrt10">Documentation of the disability and 
severity of the file</p></td>
    <td width="33%" align="right"  class="btmboder"><form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form></td>
  </tr>
  <tr>
    <td colspan="2"><p class="tmini padrt10">Currently receiving treatment or services 
with this facility</p></td>
    <td width="33%" align="right"> 
    <form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form>
    
    </td>
  </tr>
</table>
    </div>
    <div data-role="collapsible">
        <h3>Chronic Health Condition</h3>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="33%"  class="btmboder bgarrowrt"><p>02/15/2014</p></td>
    <td colspan="2"  class="btmboder borderl padlt15 bgarrow"><p>Chronic Health Condition</p></td>
    </tr>
  <tr>
    <td colspan="2" class="btmboder"><p class="tmini padrt10">Expected to be of long-continued
and indefinite duration and substantially 
impairs ability live independently</p></td>
    <td width="33%" align="right"  class="btmboder bgarrow"><p class="tmini padrt10 padrt20">No</p></td>
  </tr>
  <tr>
    <td colspan="2"  class="btmboder"><p class="tmini padrt10">Documentation of the disability and 
severity of the file</p></td>
    <td width="33%" align="right"  class="btmboder"><form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form></td>
  </tr>
  <tr>
    <td colspan="2"><p class="tmini padrt10">Currently receiving treatment or services 
with this facility</p></td>
    <td width="33%" align="right"> 
    <form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form>
    
    </td>
  </tr>
</table>
    </div>
    <div data-role="collapsible">
        <h3>Mental Health Problem </h3>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="33%"  class="btmboder bgarrowrt"><p>02/15/2014</p></td>
    <td colspan="2"  class="btmboder borderl padlt15 bgarrow"><p>Mental Health Condition</p></td>
    </tr>
  <tr>
    <td colspan="2" class="btmboder"><p class="tmini padrt10">Expected to be of long-continued
and indefinite duration and substantially 
impairs ability live independently</p></td>
    <td width="33%" align="right"  class="btmboder bgarrow"><p class="tmini padrt10 padrt20">No</p></td>
  </tr>
  <tr>
    <td colspan="2"  class="btmboder"><p class="tmini padrt10">Documentation of the disability and 
severity of the file</p></td>
    <td width="33%" align="right"  class="btmboder"><form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form></td>
  </tr>
  <tr>
    <td colspan="2"><p class="tmini padrt10">Currently receiving treatment or services 
with this facility</p></td>
    <td width="33%" align="right"> 
    <form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form>
    
    </td>
  </tr>
</table>
    </div>
    <div data-role="collapsible">
        <h3>Substance Abuse Problem  </h3>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="33%"  class="btmboder bgarrowrt"><p>02/15/2014</p></td>
    <td colspan="2"  class="btmboder borderl padlt15 bgarrow"><p>Substance Abuse</p></td>
    </tr>
  <tr>
    <td colspan="2" class="btmboder"><p class="tmini padrt10">Expected to be of long-continued
and indefinite duration and substantially 
impairs ability live independently</p></td>
    <td width="33%" align="right"  class="btmboder bgarrow"><p class="tmini padrt10 padrt20">No</p></td>
  </tr>
  <tr>
    <td colspan="2"  class="btmboder"><p class="tmini padrt10">Documentation of the disability and 
severity of the file</p></td>
    <td width="33%" align="right"  class="btmboder"><form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form></td>
  </tr>
  <tr>
    <td colspan="2"><p class="tmini padrt10">Currently receiving treatment or services 
with this facility</p></td>
    <td width="33%" align="right"> 
    <form>
    
    <select name="flip-3" id="flip-3" data-role="flipswitch" data-mini="true">
        <option value="off">No</option>
        <option value="on">Yes</option>
    </select>
</form>
    
    </td>
  </tr>
</table>
    </div>
</div>
  
     
  </div>
  <div class="ui-grid-a">
      <div class="ui-block-a">
       <a href="profile.html" data-role="button"  data-direction="reverse" data-transition="slide"  class="whitebtn mtp20" >Back</a>
        
      </div>

      <div class="ui-block-b">
        <a href="profile.html" data-role="button"  data-direction="reverse" data-transition="slide"  class="blubtn mtp20" >Save</a> 
         
      </div>
    </div>
  
</body>
</html>
