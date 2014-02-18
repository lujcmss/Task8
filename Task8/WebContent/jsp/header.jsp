<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html class="no-js">

<head>
<meta charset="utf-8" />
<title>Geo-WoW</title>

<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<link rel="stylesheet" media="all" href="/Task8/css/style.css" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv=content-type content="text/html; charset=UTF-8">
<!-- Adding "maximum-scale=1" fixes the Mobile Safari auto-zoom bug: http://filamentgroup.com/examples/iosScaleBug/ -->

<!-- JS -->
<script src="/Task8/js/jquery-2.1.0.min.js"></script>
<script src="/Task8/js/custom.js"></script>
<script src="/Task8/js/tabs.js"></script>
<script src="/Task8/js/css3-mediaqueries.js"></script>
<script src="/Task8/js/jquery.columnizer.min.js"></script>

<!-- Isotope -->
<script src="/Task8/js/jquery.isotope.min.js"></script>

<!-- Lof slider -->
<script src="/Task8/js/jquery.easing.js"></script>
<script src="/Task8/js/lof-slider.js"></script>
<link rel="stylesheet" href="/Task8/css/lof-slider.css" media="all" />
<!-- ENDS slider -->

<!-- Tweet -->
<link rel="stylesheet" href="/Task8/css/jquery.tweet.css" media="all" />
<script src="/Task8/js/tweet/jquery.tweet.js"></script>
<!-- ENDS Tweet -->

<!-- superfish -->
<link rel="stylesheet" media="screen" href="/Task8/css/superfish.css" />
<script src="/Task8/js/superfish-1.4.8/js/hoverIntent.js"></script>
<script src="/Task8/js/superfish-1.4.8/js/superfish.js"></script>
<script src="/Task8/js/superfish-1.4.8/js/supersubs.js"></script>
<!-- ENDS superfish -->

<!-- prettyPhoto -->
<script src="/Task8/js/prettyPhoto/js/jquery.prettyPhoto.js"></script>
<link rel="stylesheet" href="/Task8/js/prettyPhoto/css/prettyPhoto.css"
	media="screen" />
<!-- ENDS prettyPhoto -->

<!-- poshytip -->
<link rel="stylesheet"
	href="/Task8/js/poshytip-1.1/src/tip-twitter/tip-twitter.css" />
<link rel="stylesheet"
	href="/Task8/js/poshytip-1.1/src/tip-yellowsimple/tip-yellowsimple.css" />
<script src="/Task8/js/poshytip-1.1/src/jquery.poshytip.min.js"></script>
<!-- ENDS poshytip -->

<!-- JCarousel -->
<script type="text/javascript" src="/Task8/js/jquery.jcarousel.min.js"></script>
<link rel="stylesheet" media="screen" href="/Task8/css/carousel.css" />
<!-- ENDS JCarousel -->

<!-- GOOGLE FONTS -->
<link href="http://fonts.googleapis.com/css?family=Voltaire"
	rel='stylesheet' type='text/css'>

<!-- modernizr -->
<script src="/Task8/js/modernizr.js"></script>

<!-- SKIN -->
<link rel="stylesheet" media="all" href="/Task8/css/skin.css" />

<!-- Less framework -->
<link rel="stylesheet" media="all" href="/Task8/css/lessframework.css" />

<!-- flexslider -->
<link rel="stylesheet" href="/Task8/css/flexslider.css">
<script src="/Task8/js/jquery.flexslider.js"></script>

<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<style class="cssdeck">
.container {
	width: 1000px;
	height: 500px;
	padding: 3px;
	border: 1px solid gray;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-moz-box-sizing: border-box;
	background: black;
}

.image-slider-wrapper {
	overflow: hidden;
}

#image_slider {
	position: relative;
	overflow: hidden;
	height: 500px;
	padding: 0;
	left: 0;
}

#image_slider li {
	position: relative;
	max-width: 100%;
	float: left;
	list-style: none;
	left: 0;
}

.image {
	position: relative;
	width: 100%; /* for IE 6 */
}

h2 {
	position: absolute;
	top: 200px;
	left: 0;
	width: 100%;
	color: white;
}

h2 span {
	color: white;
	font: bold 24px/45px Helvetica, Sans-Serif;
	letter-spacing: 2px;
	background: rgb(0, 0, 0); /* fallback color */
	background: rgba(0, 0, 0, 0.7);
	padding: 10px;
}

h2 span.spacer {
	padding: 10px;
}
</style>

<style>
html,body {
	height: 100%;
	margin: 0px;
	padding: 0px;
}

#map-canvas {
	height: 100%;
	margin: 50px;
	padding: 0px;
}

.controls {
	margin-top: 16px;
	border: 1px solid transparent;
	border-radius: 2px 0 0 2px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	height: 32px;
	outline: none;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input {
	background-color: #fff;
	padding: 0 11px 0 13px;
	width: 400px;
	font-family: Roboto;
	font-size: 15px;
	font-weight: 300;
	text-overflow: ellipsis;
}

#pac-input:focus {
	border-color: #4d90fe;
	margin-left: -1px;
	padding-left: 14px; /* Regular padding-left + 1. */
	width: 401px;
}

.pac-container {
	font-family: Roboto;
}

#type-selector {
	color: #fff;
	background-color: #4d90fe;
	padding: 5px 11px 0px 11px;
}

#type-selector label {
	font-family: Roboto;
	font-size: 13px;
	font-weight: 300;
}

#target {
	width: 345px;
}
</style>

<style>
body {
	font-size: 62.5%;
}

label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
</head>


<body class="home">

	<!-- HEADER -->
	<header>
		<div class="wrapper cf">

			<div id="logo">
				<a href="home.do"><img src="/Task8/img/logo.png" alt="Simpler"></a>
			</div>

			<!-- nav -->
			<ul id="nav" class="sf-menu">
				<c:choose>
					<c:when test="${curPage=='home.do'}">
						<li class="current-menu-item">
					</c:when>
					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="home.do">Home</a>
				</li>

				<c:choose>
					<c:when test="${curPage=='viewFlickr.do'}">
						<li class="current-menu-item">
					</c:when>
					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="viewFlickr.do">Flick &amp; Map</a>
				</li>

				<c:choose>
					<c:when test="${curPage=='commentFlickr.do'}">
						<li class="current-menu-item">
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
				<a href="commentHistory.do">WoW-Pics</a>
				<ul>
					<li><a href="commentHistory.do?page=share">Most Shared</a></li>
					<li><a href="commentHistory.do?page=like">Most Liked</a></li>
					<li><a href="commentHistory.do?page=dislike">Most
							Commented</a></li>
				</ul>
				</li>

				<c:choose>
					<c:when test="${accessToken==null}">
						<li><a href="login.do">Login</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="logout.do">Logout</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
			<div id="combo-holder"></div>
			<!-- ends nav -->