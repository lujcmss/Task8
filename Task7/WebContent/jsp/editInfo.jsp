<jsp:include page="header.jsp" flush="true"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<div class="page-header">
		<h1>Create Customer Account</h1>
	</div>
	<jsp:include page="error-list.jsp" />
	<jsp:include page="success-list.jsp" />

	<form class="form-signin" method="post" action="editInfo.do">
		<input type="text" class="form-control" placeholder="First Name"
			required name="firstName" value="${user.firstName}"> <input
			type="text" class="form-control" placeholder="Last Name" required
			name="lastName" value="${user.lastName}">
		<c:if test="${userType=='Customer'}">
			<input type="text" class="form-control" placeholder="Address1"
				required name="addr1" value="${user.addr1}">
			<input type="text" class="form-control" placeholder="Address2"
				name="addr2" value="${user.addr2}">
			<input type="text" class="form-control" placeholder="City" required
				name="city" value="${user.city}">
			<input type="text" class="form-control" placeholder="State" required
				name="state" value="${user.state}">
			<input type="text" class="form-control" placeholder="Zip Code"
				required name="zipCode" value="${user.zip}">
		</c:if>
		<p></p>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Edit</button>
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
