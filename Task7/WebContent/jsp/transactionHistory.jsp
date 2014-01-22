<jsp:include page="header.jsp" />
<jsp:include page="error-list.jsp" />
    <div class="container">
    
      <div class="page-header">
        <h1>Transaction History</h1>
      </div>
      
	<table class="table table-striped">
		<h3>Transaction Information</h3>
		
		<colgroup>
          <col class="col-xs-1">
          <col class="col-xs-2">
          <col class="col-xs-3">
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
            <th>Share Price</th>
            <th>Shares</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Jan 10, 2014</td>
            <td>APPL</td>
            <td>Buy</td>
            <td>100</td>
            <td>10</td>
            <td>1000</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Jan 08, 2014</td>
            <td>GOOG</td>
            <td>Sell (Pending)</td>
            <td>800</td>
            <td>20</td>
            <td>16000</td>
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
