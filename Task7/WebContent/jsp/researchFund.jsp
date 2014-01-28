<jsp:include page="header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">

	<div class="page-header">
		<h1>Research Fund</h1>
	</div>
	<jsp:include page="error-list.jsp" />

	<table class="table table-striped">
		<h3>Funds Information</h3>
		<thead>
			<tr>
				<th>#</th>
				<th>Fund Name</th>
				<th>Fund Ticker</th>
				<th>Last Day Price</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="count" value="0" />
			<c:forEach var="fundInfo" items="${ fundInfo }">
				<c:set var="count" value="${ count+1 }" />
				<tr>
					<td>${count}</td>
					<c:set var="price" value="${fundInfo.fundPrice}" />
					<td><c:out value="${fundInfo.name}" /></td>
					<td><c:out value="${fundInfo.symbol}" /></td>
					<td>$<fmt:formatNumber type="number" maxFractionDigits="2"
							minFractionDigits="2" value="${price}" /></td>
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
