<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="container">
    
      <div class="page-header">
        <h1>Buy Fund</h1>
      </div>
      <jsp:include page="error-list.jsp" />
    <div class="search-bar">
    <form class="navbar-form navbar-center" method="post"  action="buyFund.do">
		<div class="form-group">
    		<input type="text" class="form-control" placeholder="Fund Name or Ticker" name="fund">
  		</div>
  		<button type="submit" class="btn btn-default" name="search">Search</button>
	</form>
	
	  <form class="form-signin" method="post" action="buyFund.do">
        <input type="text" class="form-control" placeholder="Purchase Amount" required autofocus name="amount">
        <p></p>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="buy">Buy</button>
      </form>
	</div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
</html>
