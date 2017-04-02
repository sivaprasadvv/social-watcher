<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title><tiles:insertAttribute name="title-content" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Start: JQuery Table Tools css added on 27 June 2013 -->
<link
	href="${pageContext.request.contextPath}/resources/js/TableTools/media/css/TableTools_JUI.css"
	rel="stylesheet">
	<link
	href="${pageContext.request.contextPath}/resources/js/TableTools/media/css/TableTools.css"
	rel="stylesheet">
<!-- End: JQuery Table Tools css added on 27 June 2013 -->

<!-- Le styles -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/commons.css"
	rel="stylesheet">	
<tiles:insertAttribute name="custom-css" />	
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.css" rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le favicon and touch icons -->
<link rel="shortcut icon"
	href="http://twitter.github.com/bootstrap/assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
	<tiles:insertAttribute name="navbar-content" />
	<div class="container">
		<tiles:insertAttribute name="main-content" />
		<!-- <hr>
		<footer>
			<p>© Social Watcher 2013</p>
		</footer> -->
	</div>
	<tiles:insertAttribute name="footer-content" />
	<!-- Javascript-->
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-1.8.0.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<%-- <script
		src="${pageContext.request.contextPath}/resources/js/jquery.fancybox-1.3.1.js"></script> --%>
	<script type="text/javascript">
			var application_context = '${pageContext.request.contextPath}';
			var username = '${userContext.loginname}';
			var role = '${userContext.role}';
			var loading_msg='<h5><img src='+application_context+"/resources/img/loading.gif" +'/> Loading data...</h5>';
			var deleting_msg='<h5><img src='+application_context+"/resources/img/loading.gif" +'/> Deleting...</h5>';
			var reviewing_msg='<h5><img src='+application_context+"/resources/img/loading.gif" +'/> Updating your Review...</h5>';
			var commenting_msg='<h5><img src='+application_context+"/resources/img/loading.gif" +'/> Loading Data to enter your Comment...</h5>';
	</script>
	<tiles:insertAttribute name="custom-js" />	
</body>
</html>