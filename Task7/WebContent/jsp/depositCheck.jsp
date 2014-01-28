<jsp:include page="header.jsp" flush="true"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<div class="page-header">
		<h1>Deposit Check</h1>
	</div>
	<jsp:include page="error-list.jsp" />
	<jsp:include page="success-list.jsp" />
	<div class="search-bar">
		<form class="navbar-form navbar-center" method="post"
			action="depositCheck.do">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Customer Email"
					name="customerEmail">
			</div>
			<button type="submit" class="btn btn-default" name="button"
				value="search">Search</button>
		</form>
	</div>

	<table class="table table-striped">
		<h3>User List</h3>
		<colgroup>
			<col class="col-xs-2">
			<col class="col-xs-3">
			<col class="col-xs-2">
			<col class="col-xs-1">
		</colgroup>
		<thead>
			<tr>
				<th>Customer Username</th>
				<th>Customer Name</th>
				<th>Deposit Amount ($)</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="customerList" items="${customerList}">
				<tr>
					<form class="form-signin" method="post" action="depositCheck.do">
						<input type="hidden" name="customerEmail"
							value="${customerList.email}" />
						<td><c:out value="${customerList.email}" /></td>
						<td><c:out value="${customerList.firstName}" />, <c:out
								value="${customerList.lastName}" /></td>
						<td><input type="text" class="form-control" placeholder="0"
							required name="depositAmount"></td>
						<td><button class="btn btn-primary" type="submit"
								name="button" value="deposit">Deposit</button></td>
					</form>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="/Task7/js/bootstrap.min.js"></script>
</body>
</html>