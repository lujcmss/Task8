<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

<title>Carnegie Financial Services</title>

<!-- Bootstrap core CSS -->
<link href="/Task7/css/bootstrap.css" rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="/Task7/css/signin.css" rel="stylesheet" type="text/css">

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
			<h2 class="form-signin-heading">Please sign in</h2>
			<c:choose>
				<c:when test="${email == null}">
					<input type="text" class="form-control" placeholder="Username"
						required autofocus name="email">
				</c:when>
				<c:otherwise>
					<input type="text" class="form-control" placeholder="Username"
						value="${email}" required autofocus name="email">
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${password == null}">
					<input type="password" class="form-control" placeholder="Password"
						required name="psw">
				</c:when>
				<c:otherwise>
					<input type="password" class="form-control" placeholder="Password"
						value="${password}" required name="psw">
				</c:otherwise>
			</c:choose>
			<br />
			<c:choose>
				<c:when test="${remember == null}">
					<input type="checkbox" name="remember" value="remember" /> Remember Password
        			</c:when>
				<c:otherwise>
					<input type="checkbox" name="remember" value="remember"
						checked="checked" /> Remember Password
        			</c:otherwise>
			</c:choose>

			<div class="row">

				<div class="col-lg-6">
					<div class="input-group">
						<span class="input-group-addon"> <c:choose>
								<c:when test="${userType == null}">
									<input type="radio" name="userType" value="Employee"> Login as Employee
       				<input type="radio" name="userType" value="Customer"> Login as Customer
       			</c:when>
								<c:when test="${userType == 'Employee'}">
									<input type="radio" name="userType" value="Employee"
										checked="checked"> Login as Employee
       				<input type="radio" name="userType" value="Customer"> Login as Customer
       			</c:when>
								<c:otherwise>
									<input type="radio" name="userType" value="Employee"> Login as Employee
       				<input type="radio" name="userType" value="Customer"
										checked="checked"> Login as Customer
        		</c:otherwise>
							</c:choose>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
