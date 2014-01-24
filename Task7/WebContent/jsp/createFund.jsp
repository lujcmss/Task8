<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="container">
    
      <div class="page-header">
        <h1>Create Fund</h1>
      </div>
	  <jsp:include page="error-list.jsp" />
      <form class="form-signin">
        <h4>Fund Name</h4><input type="text" class="form-control" placeholder="Fund Name" required autofocus name="fund">
        <h4>Ticker</h4><input type="text" class="form-control" placeholder="Ticker" required name="ticker">
        <p></p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
      </form>
      
      <table class="table table-striped">
		<h3>Existing Funds</h3>
		
		<colgroup>
          <col class="col-xs-1">
          <col class="col-xs-2">
          <col class="col-xs-3">
     
        </colgroup>
        <thead>
          <tr>
            <th>#</th>
            <th>Fund Name</th>
            <th>Share Price</th>


          </tr>
        </thead>
        <tbody>
			<c:forEach var="funds" items="${ funds }">
				<tr>
            		<td><c:out value="${funds.fundId}"/></td>
            		<td><c:out value="${funds.name}"/></td>
            		<td><c:out value="${funds.symbol}"/></td>
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