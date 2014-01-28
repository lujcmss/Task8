<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">

	<div class="page-header">
		<h1>Transition Day</h1>
	</div>
	<jsp:include page="error-list.jsp" />
	<table class="table table-striped">
		<h3>Transition Information</h3>
		<colgroup>
			<col class="col-xs-1">
			<col class="col-xs-3">
			<col class="col-xs-2">
			<col class="col-xs-1">
			<col class="col-xs-2">
			<col class="col-xs-1">
		</colgroup>
		<thead>
			<tr>
				<th>#</th>
				<th>Last Day</th>
				<th>Fund Name</th>
				<th>Fund Ticker</th>
				<th>Price Now</th>
				<th>New Price</th>
			</tr>
		</thead>

		<form class="form-signin" method="post" action="transitionDay.do">
			<tbody>
				<c:set var="count" value="0" />
				<c:forEach var="transitionDayFunds" items="${ transitionDayFunds }">
					<tr>
						<td><label for=${"findId_"}${count}><c:out
									value="${transitionDayFunds.fundId}" /></label></td>
						<td><label for=${"lastDay_"}${count}><c:out
									value="${transitionDayFunds.lastDay}" /></label></td>
						<td><label for=${"fundName_"}${count}><c:out
									value="${transitionDayFunds.fundBean.name}" /></label></td>
						<td><label for=${"fundSymbol_"}${count}><c:out
									value="${transitionDayFunds.fundBean.symbol}" /></label></td>
						<td><label for=${"oldPrice_"}${count}>$<fmt:formatNumber
									type="number" maxFractionDigits="2" minFractionDigits="2"
									value="${transitionDayFunds.oldPrice}" /></label></td>
						<td><input type="text" class="form-control" placeholder="0" value="<fmt:formatNumber
									type="number" maxFractionDigits="2" minFractionDigits="2"
									value="${transitionDayFunds.newPrice}" />"
							required name=${"newPrice_"}${count}></td>
					</tr>
					<c:set var="count" value="${count+1}" />
				</c:forEach>
			</tbody>
	</table>
	<input type="text" class="form-control"
		style="width: 280px; margin: 0 auto;"
		placeholder="New Transition Date (YYYY-MM-DD)" required name="newDate" />
	<p></p>
	<button class="btn btn-lg btn-primary btn-block"
		style="width: 280px; margin: 0 auto;" type="submit" name="button"
		value="Transition">Transition</button>
	</form>



	</form>

</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="_js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="_js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="/Task7/js/bootstrap.min.js"></script>
<script>
	$('#sandbox-container .input-append.date').datepicker({
		format : 22
	});
</script>

</body>
</html>
