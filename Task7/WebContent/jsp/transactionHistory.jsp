<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <th>Transaction Id</th>
            <th>Transaction Date</th>
            <th>Fund Name</th>
            <th>Operation</th>
            <th>Status</th>
            <th>Share Price</th>
            <th>Shares</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
			<c:forEach var="historyInfo" items="${ historyInfo }">
				<tr>
           			<td><c:out value="${historyInfo.transactionId}"/></td>
            		<td><c:out value="${historyInfo.executeDate}"/></td>
            		<td><c:out value="${historyInfo.fundBean.name}"/></td>
            		<td><c:out value="${historyInfo.transactionType}"/></td>
            		<td><c:out value="${historyInfo.status}"/></td>
            		<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${historyInfo.sharePrice}" /></td>
            		<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="3" minFractionDigits="3" value="${historyInfo.shares}" /></td>
            		<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${historyInfo.amount}" /></td>
            		<td></td>
	        	</tr>
			</c:forEach>
        </tbody>
      </table>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
</html>
