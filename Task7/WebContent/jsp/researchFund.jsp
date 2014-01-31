<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="any" uri="http://www.anychart.com"%>

<div class="container">

	<div class="page-header">
		<h1>Research Fund</h1>
	</div>
	<jsp:include page="error-list.jsp" />
	<jsp:include page="success-list.jsp" />

	<div class="search-bar">
		<form class="navbar-form navbar-center" method="post"
			action="researchFund.do">
			<div class="form-group">
				<input type="text" class="form-control"
					placeholder="Fund Name or Ticker" name="fundName">
			</div>
			<button type="submit" class="btn btn-default" name="button"
				value="search">Search</button>
		</form>

		<table class="table table-striped" style="text-align:right">
			<c:choose>
				<c:when test="${searched == false}">
					<form method="POST" action="/Task7/jsp/fundsHistoryGraph.jsp" target="_blank">
						<button class="btn btn-primary" type="submit"
							name="button" value="fundsPriceHistory">Price History Chart for All Funds</button>
					</form>
				</c:when>
				<c:otherwise>
					<form method="POST" action="/Task7/jsp/fundHistoryGraph.jsp" target="_blank">
						<button class="btn btn-primary" type="submit"
							name="button" value="fundPriceHistory">Price History Chart for ${fundName}</button>
					</form>
				</c:otherwise>

			</c:choose>
			<colgroup>
				<col class="col-xs-1">
				<col class="col-xs-2">
				<col class="col-xs-1">
				<col class="col-xs-2">
				<col class="col-xs-2">
				<col class="col-xs-2">
				<col class="col-xs-2">
			</colgroup>
			<thead>
				<tr>
					<th style="text-align:right">#</th>
					<th style="text-align:right">Fund Name</th>
					<th style="text-align:right">Ticker</th>
					<th style="text-align:right">Last Day Price</th>
					<th style="text-align:right">Max Price</th>
					<th style="text-align:right">Min Price</th>
					<th style="text-align:right">Avg Price</th>
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
						<td>$<fmt:formatNumber type="number" maxFractionDigits="2"
								minFractionDigits="2" value="${fundInfo.fundMaxPrice}" /></td>
						<td>$<fmt:formatNumber type="number" maxFractionDigits="2"
								minFractionDigits="2" value="${fundInfo.fundMinPrice}" /></td>
						<td>$<fmt:formatNumber type="number" maxFractionDigits="2"
								minFractionDigits="2" value="${fundInfo.fundAvgPrice}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="/Task7/js/bootstrap.min.js"></script>
<script src="/Task7/js/jquery.min.js"></script>
</body>
</html>
