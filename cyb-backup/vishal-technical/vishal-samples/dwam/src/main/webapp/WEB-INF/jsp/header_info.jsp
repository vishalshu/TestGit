<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html id="ng-app" xml:lang="en" lang="en" ng-app="dwamApp" xmlns:ng="http://angularjs.org/">

<head>

<!--[if lte IE 8]>
	<script src="scripts/angular/json3.js"></script>
<![endif]-->

<!--[if lte IE 8]>
	<script>
		document.createElement('ng-include');
		document.createElement('ng-pluralize');
		document.createElement('ng-view');
		 
		// Optionally these for CSS
		document.createElement('ng:include');
		document.createElement('ng:pluralize');
		document.createElement('ng:view');
	</script>
<![endif]-->

<!--[if lte IE 8]>
	<script src="scripts/html5shiv.js" type="text/javascript"></script>
<![endif]-->

<!-- HTML5 Shiv -->
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

<!--  <script src="scripts/html5shiv.js"></script> -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Pragma" content="no-cache"/>
<title><fmt:message key="divxWebAccountManager" /></title>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

<!-- JQuery -->
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
<script src='scripts/jquery.validate.js'></script>

<!-- Bootstrap -->
<script src="scripts/bootstrap/bootstrap.min.js"></script>
<script src="scripts/bootstrap/bootstrap-collapse.js"></script>

<!-- AngularJS -->
<script src="scripts/angular/angular.min.js" ng:autobind></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular-resource.min.js"></script>

<!-- Application Boot JS -->
<script src="scripts/dwam-app.js"></script>
<script src="scripts/angular/controllers/device-acct-mgmt.js"></script>
<script src="scripts/angular/controllers/user-acct-mgmt.js"></script>
<script src="scripts/angular/controllers/user-service.js"></script>
<script src="scripts/angular/controllers/device-service.js"></script>

<script src="scripts/angular/angular-ui.min.js"></script>
<script src="scripts/angular/angular-ui-ieshiv.min.js"></script>
<script src="scripts/angular/ng-grid-2.0.3.min.js"></script>
<script src="scripts/angular/json3.min.js"></script>

<!-- Infield Label -->
<script src='scripts/jquery.infieldlabel.js'></script>

<!-- Modernizr 
<script src="scripts/modernizr-2.6.2.js"></script>-->

<!-- For IE polyfills -->
<script src="scripts/jquery.html5support.js"></script>

<script src="scripts/html5ie_fixes.js"></script>

<!-- CSS ================================================== -->
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css">

<!--  <link rel="stylesheet" href="./css/skeleton.css">  -->
<link rel="stylesheet" href="css/angular/angular-ui.css">
<link rel="stylesheet" href="css/angular/ng-grid.css">

<link rel="stylesheet" href="css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap/bootstrap-responsive.css">

<link type="text/css" rel="stylesheet" media="all"	href="css/960/reset.css" />
<link type="text/css" rel="stylesheet" media="all"	href="css/960/debug.css" />
<link type="text/css" rel="stylesheet" media="all"	href="css/nice_menus.css" />
<link type="text/css" rel="stylesheet" media="all"  href="css/menus.css" />
<link type="text/css" rel="stylesheet" media="all"	href="css/typography.css" />
<link type="text/css" rel="stylesheet" media="all"  href="css/style.css" />

<!--Custom stylesheet-->
<link type="text/css" rel="stylesheet" media="all"  href="css/stylesheet.css" />
<link type="text/css" rel="stylesheet" media="all"  href="css/stylesheet-tablet.css" />
<link type="text/css" rel="stylesheet" media="all"	href="css/960/960.css" />
<!--[if IE]><link rel="stylesheet" type="text/css" href="css/all-ie-only.css"/><![endif]-->

<!-- <link rel="stylesheet" href="stylesheets/docs.css"> -->
<!--  <script type="text/javascript" src="scripts/script.js" />  -->
	
</head>
<body>
	<div id="loadingWidget" class="ui-corner-all">
		<img alt="Loading Content" src="images/ajax-loader.gif" />
    </div>