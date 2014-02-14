<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8" %>

<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="utf-8"> 

<!-- Note there is no responsive meta tag here -->

<link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

<title>Task8</title>

<!-- Bootstrap core CSS -->
<link href="/Task8/css/bootstrap.css" rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="/Task8/css/non-responsive.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template -->
<link href="/Task8/css/signin.css" rel="stylesheet" type="text/css">

<!-- jsp:include page="flickrPics.jsp" / -->
<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">T-F</a>
			</div>
			<div class="navbar-collapse collapse">

				<ul class="nav navbar-nav">
					<c:choose>
						<c:when test="${curPage=='home.do'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="home.do">Home</a>
					</li>

					<c:choose>
						<c:when test="${curPage=='viewFlickr.do'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="viewFlickr.do">Flick &amp; Map</a>
					</li>

					<c:choose>
						<c:when test="${curPage=='commentFlickr.do'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="commentFlickr.do">Search &amp; Tweet</a>
					</li>
					
					<c:choose>
						<c:when test="${curPage=='commentHistory.do'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="commentHistory.do">Popular Pictures</a>
					</li>
					
					<c:choose>
						<c:when test="${curPage=='websiteStatistics.do'}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="websiteStatistics.do">Statistics</a>
					</li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a> Hello ${user.screen_name} </a></li>
					<li><a href="logout.do">Logout</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>