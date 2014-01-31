<jsp:include page="header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">

	<div class="page-header">
		<h1>Transaction History</h1>
	</div>

	<table class="table table-striped" style="text-align:right">
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
				<th style="text-align:right">#</th>
				<th style="text-align:right">Transaction Date</th>
				<th style="text-align:right">Fund Name</th>
				<th style="text-align:right">Operation</th>
				<th style="text-align:right">Status</th>
				<th style="text-align:right">Share Price</th>
				<th style="text-align:right">Shares</th>
				<th style="text-align:right">Total Value</th>
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
