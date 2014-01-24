<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <div class="container">
	<c:choose>
	<c:when test="${userType=='Employee'}">
	  <div class="page-header">
        <h1>Welcome, <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /></h1>
      </div>
      	<table class="table table-striped">
		<h3>Basic Information</h3>
		<div style="text-align:right">
			<a href="changePassword.do">Change Password</a>
			<a>&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="editInfo.do">Edit Information</a>
		</div>
        <colgroup>
          <col class="col-xs-3">
          <col class="col-xs-5">
        </colgroup>
        <tbody>
          <tr>
            <th>First Name</th>
            <td><c:out value="${user.firstName}" /></td>
          </tr>
          <tr>            
          	<th>Last Name</th>
            <td><c:out value="${user.lastName}" /></td>
          </tr>
        </tbody>
      </table>
	</c:when>
	<c:otherwise>
      <div class="page-header">
        <h1><c:out value="${user.firstName}" /> <c:out value="${user.lastName}" />'s Information</h1>
      </div>

	<table class="table table-striped">
		<h3>Basic Information</h3>
		<div style="text-align:right">
			<a href="changePassword.do">Change Password</a>
			<a>&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="editInfo.do">Edit Information</a>
		</div>
        <colgroup>
          <col class="col-xs-3">
          <col class="col-xs-5">
        </colgroup>
        <tbody>
          <tr>
            <th>First Name</th>
            <td><c:out value="${user.firstName}" /></td>
          </tr>
          <tr>            
          	<th>Last Name</th>
            <td><c:out value="${user.lastName}" /></td>
          </tr>
          <tr>
            <th>Address</th>
            <td><c:out value="${user.addr1}" />,&nbsp;<c:out value="${user.addr2}" /></td>
          </tr>
          <tr>
            <th>Last Trading Day</th>
            <td>
            	<c:choose>
            		<c:when test="${user.lastTradingDay == null}">
            			<c:out value="-" />
            		</c:when>
            		<c:otherwise>
            			<c:out value="${user.lastTradingDay }" />
            		</c:otherwise>
            	</c:choose>
            </td>
          </tr>
          <tr>
            <th>Cash Balance</th>
            <td>$<fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${user.cash}" /></td>
          </tr>
        </tbody>
      </table>
      
	<table class="table table-striped">
		<h3>Funds Information</h3>
        <thead>
          <tr>
            <th>#</th>
            <th>Fund Name</th>
            <th>Fund Ticker</th>
            <th>Fund Price</th>
            <th>Share</th>
            <th>Value</th>
          </tr>
        </thead>
        <tbody>
			<c:forEach var="fundInfo" items="${ fundInfo }">
				<tr>
           			<td>${fundInfo.fundId}</td>
           			<c:set var="share" value="${fundInfo.share}"/>
            		<td><c:out value="${fundInfo.name}"/></td>
            		<td><c:out value="${fundInfo.symbol}"/></td>
            		<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${share}" /></td>
            		<td></td>
	        	</tr>
			</c:forEach>
        </tbody>
      </table>
    </c:otherwise>
	</c:choose>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
</html>