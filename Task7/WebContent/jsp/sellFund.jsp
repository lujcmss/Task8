<jsp:include page="header.jsp" />
<jsp:include page="error-list.jsp" />
    <div class="container">
    
      <div class="page-header">
        <h1>Sell Fund</h1>
      </div>
      
      <div class="search-bar">
   		<form class="navbar-form navbar-center" role="search">
			<div class="form-group">
    			<input type="text" class="form-control" placeholder="Fund Name or Ticker">
  			</div>
  			<button type="submit" class="btn btn-default">Search</button>
		</form>
	  </div>
	<table class="table table-striped">
		<h3>Funds Information</h3>
		<div style="text-align:right">
			<a>Sell</a>
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
            <th><input type="text" placeholder="0" size="1"/></th>
          </tr>
          <tr>
            <td>2</td>
            <td>Google</td>
            <td>GOOG</td>
            <td>800</td>
            <td>20</td>
            <th><input type="text" placeholder="0" size="1"/></th>
          </tr>
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
