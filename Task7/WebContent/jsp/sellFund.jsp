<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="container"> 
      <div class="page-header">
        <h1>Sell Fund</h1>
      </div>
      <jsp:include page="error-list.jsp" />
    <div class="search-bar">
    <form class="navbar-form navbar-center" method="post"  action="sellFund.do">
		<div class="form-group">
    		<input type="text" class="form-control" placeholder="Fund Name or Ticker" name="fund">
  		</div>
  		<button type="submit" class="btn btn-default" name="button" value="search">Search</button>
	</form>
	
	<table class="table table-striped">
		<h3>Funds Information</h3>
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
            <th>Fund Name</th>
            <th>Fund Ticker</th>
            <th>Share</th>
            <th>Shares to sell</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
 			<c:set var="count" value="0" />
			<c:forEach var="fundInfo" items="${ sellFundInfo }">
				<c:set var="count" value="${ count+1 }" />
				<tr>
				<form class="form-signin" method="post" action="sellFund.do">
           			<td>${count}</td>
           			<c:set var="share" value="${fundInfo.share}"/>
            		<td><label name="fundName"><c:out value="${fundInfo.name}"/></label></td>
            		<td><c:out value="${fundInfo.symbol}"/></td>
            		<td><fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${share}" /></td>
            		<td>
    						<input type="text" class="form-control" placeholder="0" required name="share">
        			</td>
            		<td><button class="btn btn-primary" type="submit" name="button" value="sell">Sell</button></td>
            	</form>
	        	</tr>
			</c:forEach>
        </tbody>
      </table>
	</div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
</html>
