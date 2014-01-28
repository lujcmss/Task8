<!-- jsp:include page="header.jsp" flush="true"/ -->
<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<div class="page-header">
		<h1>Create Employee Account</h1>
	</div>

	<jsp:include page="error-list.jsp" />

	<form class="form-signin">
		<c:choose>
			<c:when test="${ form == null || form.email == null }">
				<input type="text" class="form-control" placeholder="Username" required
					autofocus name="email">
			</c:when>
			<c:otherwise>
				<input type="text" class="form-control" placeholder="Username"
					value="${form.email}" required autofocus name="email">
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ form == null || form.psw == null }">
				<input type="password" class="form-control" placeholder="Password"
					required name="psw">
			</c:when>
			<c:otherwise>
				<input type="password" class="form-control" placeholder="Password"
					value="${form.psw}" required name="psw">
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ form == null || form.confirm == null }">
				<input type="password" class="form-control"
					placeholder="Password Confirmation" required name="confirm">
			</c:when>
			<c:otherwise>
				<input type="password" class="form-control"
					placeholder="Password Confirmation" value="${form.confirm}"
					required name="confirm">
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ form == null || form.firstName == null }">
				<input type="text" class="form-control" placeholder="First Name"
					required name="firstName">
			</c:when>
			<c:otherwise>
				<input type="text" class="form-control" placeholder="First Name"
					value="${form.firstName}" required name="firstName">
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ form == null || form.lastName == null }">
				<input type="text" class="form-control" placeholder="Last Name"
					required name="lastName">
			</c:when>
			<c:otherwise>
				<input type="text" class="form-control" placeholder="Last Name"
					value="${form.lastName}" required name="lastName">
			</c:otherwise>
		</c:choose>
		<p></p>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
	</form>

</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="/Task7/js/bootstrap.min.js"></script>
</body>
</html>
