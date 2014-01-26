<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <div class="container">
    
      <div class="page-header">
        <h1>Customer Information</h1>
      </div>
    <jsp:include page="error-list.jsp" />
      
    <div class="search-bar">
    <form class="navbar-form navbar-center" method="POST" action="viewCustomerInformation.do">
		<div class="form-group">
    		<input type="text" class="form-control" placeholder="Search user email here" autofocus name="customerEmail">
  		</div>
  		<button type="submit" class="btn btn-default" name="button" value="search">Search</button>
	</form>
	</div>
	
    <div class="bs-example bs-example-tabs">
      <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#account" data-toggle="tab">Account</a></li>
        <li><a href="#transaction" data-toggle="tab">Transaction History</a></li>
      </ul>
      
      <c:if test="${userInfo != null}">
      <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="account">
          <table class="table table-striped">
		<h3><c:out value="${userInfo.firstName}" /> <c:out value="${userInfo.lastName}" />'s Basic Information</h3>
		<div style="text-align:right">
			<a href="resetPassword.do">Reset Password</a>
		</div>
        <colgroup>
          <col class="col-xs-3">
          <col class="col-xs-5">
        </colgroup>
        <tbody>
          <tr>
            <th>First Name</th>
            <td><c:out value="${userInfo.firstName}" /></td>
          </tr>
          <tr>            
          	<th>Last Name</th>
            <td><c:out value="${userInfo.lastName}" /></td>
          </tr>
          <tr>
            <th>Address</th>
            <td><c:out value="${userInfo.addr1}" />,&nbsp;<c:out value="${userInfo.addr2}" /></td>
          </tr>
          <tr>
            <th>Last Trading Day</th>
            <td>
            	<c:choose>
            		<c:when test="${userInfo.lastTradingDay == null}">
            			<c:out value="-" />
            		</c:when>
            		<c:otherwise>
            			<c:out value="${userInfo.lastTradingDay}" />
            		</c:otherwise>
            	</c:choose>
            </td>
          </tr>
        </tbody>
      </table>
      
	<table class="table table-striped">
		<h3><c:out value="${userInfo.firstName}" /> <c:out value="${userInfo.lastName}" />'s Funds Information</h3>
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
           			<c:set var="price" value="${fundInfo.fundPrice}"/>
            		<td><c:out value="${fundInfo.name}"/></td>
            		<td><c:out value="${fundInfo.symbol}"/></td>
            		<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${price}" /></td>
            		<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="3" minFractionDigits="3" value="${share}" /></td>
            		<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${share * price}" /></td>
            		<td></td>
	        	</tr>
			</c:forEach>
        </tbody>
      </table>
        </div>
        <div class="tab-pane fade" id="transaction">
          <table class="table table-striped">
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
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
        	<c:set var="count" value="0" />
			<c:forEach var="historyInfo" items="${ historyInfo }">
				<c:set var="count" value="${ count+1 }" />
				<tr>
					<td>${count}</td>
            		<td><c:out value="${historyInfo.executeDate}"/></td>
            		<td><c:out value="${historyInfo.fundBean.name}"/></td>
            		<td><c:out value="${historyInfo.transactionType}"/></td>
            		<td><c:out value="${historyInfo.status}"/></td>
            		
            		<c:choose>
            			<c:when test="${historyInfo.sharePrice==-1}">
            				<td>-</td>
            			</c:when>
            			<c:otherwise>
            				<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${historyInfo.sharePrice}" /></td>
						</c:otherwise>
					</c:choose>
            		<c:choose>
            			<c:when test="${historyInfo.shares==-1}">
            				<td>-</td>
            			</c:when>
            			<c:otherwise>
            				<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="3" minFractionDigits="3" value="${historyInfo.shares}" /></td>
						</c:otherwise>
					</c:choose>
            		<c:choose>
            			<c:when test="${historyInfo.amount==-1}">
            				<td>-</td>
            			</c:when>
            			<c:otherwise>
            				<td><c:out value=""/><fmt:formatNumber type="number" 
            			maxFractionDigits="2" minFractionDigits="2" value="${historyInfo.amount}" /></td>
						</c:otherwise>
					</c:choose>
            		<td></td>
	        	</tr>
			</c:forEach>
        </tbody>
      </table>
        </div>
      </div>
      </c:if>
    </div><!-- /example -->

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
