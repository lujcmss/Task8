<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="container">
     <c:choose>
     <c:when test="${requestScope.user!=null}">
   
      <div class="page-header">
        <h1>Sell Fund</h1>
      </div>
      <jsp:include page="error-list.jsp" />
      <div class="search-bar">
   		<form class="navbar-form navbar-center" role="search">
			<div class="form-group" method="post" name="sellFund.do">
    			<input type="text" class="form-control" placeholder="Fund Name or Ticker">
  			</div>
  			<button type="submit" class="btn btn-default">Search</button>
		</form>
	  </div>
	<form class="navbar-form navbar-center" method="post" action="sellFund.do">
	<table class="table table-striped">
		<h3>Funds Information</h3>
		<div style="text-align:right">
			  			<button type="submit" class="btn btn-default">Sell</button>

		</div>
		
		<colgroup>
          <col class="col-xs-1">
          <col class="col-xs-3">
          <col class="col-xs-2">
          <col class="col-xs-1">
          <col class="col-xs-1">
          <col class="col-xs-1">
        </colgroup>
        <thead>
          <tr>
            <th>#</th>
            <th>Fund Name</th>
            <th>Fund Ticker</th>
            <th>Fund Price</th>
            <th>Share</th>
            <th>Share to Sell</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Apple</td>
            <td>APPL</td>
            <td>100</td>
            <td>10</td>
            <th><input type="text" placeholder="0" size="1" name="shares"/></th>
          </tr>
          <tr>
            <td>2</td>
            <td>Google</td>
            <td>GOOG</td>
            <td>800</td>
            <td>20</td>
            <th><input type="text" placeholder="0" size="1" name="shares"/></th>
          </tr>
         <c:if test="${sessionScope.userFundList!=null}">
		<c:forEach items="${sessionScope.userFundList}"  var="oneFund">
		<tr>
            <td>${oneFund.fundId}</td>
            <td>${oneFund.name}</td>
            <td>${oneFund.symbol}</td>
            <td>${oneFund.fundPrice}</td>  --//we need find price, but it's not in fund bean( it was stored in another table with 1 to N relation to fund N)-->
            <td>${oneFund.sellAmount}</td> --//we need the amount of share this customer can sell
            <th><input type="text" placeholder="0" size="1" name="shares"/></th>
          </tr>
          </c:forEach>
	</c:if>

        </tbody>
      </table>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
    </c:when>
    	<c:otherwise>
        			<div>
        			<br>
        			<br>
        			<br>
        			You must login first!<br>
        			<a href="login.jsp">LOGIN</a>
        			</div>
      </c:otherwise>
      </c:choose>
    
  </body>
</html>
