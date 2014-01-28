<!-- jsp:include page="header.jsp" flush="true"/ -->
<%@ include file="header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">

	<div class="page-header">
		<h1>Transaction History</h1>
	</div>

	<table class="table table-striped">
		<h3>Transaction Information</h3>
		<jsp:include page="error-list.jsp" />
		<colgroup>
			<col class="col-xs-1">
			<col class="col-xs-2">
			<col class="col-xs-3">
			<col class="col-xs-2">
			<col class="col-xs-2">
			<col class="col-xs-1">
			<col class="col-xs-1">
			<col class="col-xs-2">
		</colgroup>
		<thead>
			<tr>
				<th>#</th>
				<th>Transaction Date</th>
				<th>Fund Name</th>
				<th>Operation</th>
				<th>Status</th>
				<th>Share Price</th>
				<th>Shares</th>
				<th>Total Value</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="count" value="0" />
			<c:forEach var="historyInfo" items="${ historyInfo }">
				<c:set var="count" value="${ count+1 }" />
				<tr>
					<td>${count}</td>
					<td><c:out value="${historyInfo.executeDate}" /></td>
					<td><c:out value="${historyInfo.fundBean.name}" /></td>
					<td><c:out value="${historyInfo.transactionType}" /></td>
					<td><c:out value="${historyInfo.status}" /></td>

					<c:choose>
						<c:when test="${historyInfo.sharePrice==-1}">
							<td>-</td>
						</c:when>
						<c:otherwise>
							<td>$<fmt:formatNumber type="number" maxFractionDigits="2"
									minFractionDigits="2" value="${historyInfo.sharePrice}" /></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${historyInfo.shares==-1}">
							<td>-</td>
						</c:when>
						<c:otherwise>
							<td><fmt:formatNumber type="number" maxFractionDigits="3"
									minFractionDigits="3" value="${historyInfo.shares}" /></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${historyInfo.amount==-1}">
							<td>-</td>
						</c:when>
						<c:otherwise>
							<td>$<fmt:formatNumber type="number" maxFractionDigits="2"
									minFractionDigits="2" value="${historyInfo.amount}" /></td>
						</c:otherwise>
					</c:choose>
					<td></td>
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
