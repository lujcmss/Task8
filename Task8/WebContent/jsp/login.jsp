<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

<title>Task8</title>

<!-- Bootstrap core CSS -->
<link href="/Task8/css/bootstrap.css" rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="/Task8/css/signin.css" rel="stylesheet" type="text/css">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<jsp:include page="error-list.jsp" />
	<div class="container">

		<form class="form-signin" method="post" action="login.do">
			<table style="align:center">
				<tr>
					<td>
						<a href="${ TwitterRedirect }">
						<img src="/Task8/images/sign-in-with-twitter-link.png" />
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
