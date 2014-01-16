<jsp:include page="header.jsp" />

    <div class="container">
    
      <div class="page-header">
        <h1>Create Fund</h1>
      </div>
	
      <form class="form-signin">
        <input type="text" class="form-control" placeholder="Fund Name" required autofocus>
        <input type="text" class="form-control" placeholder="Ticker" required>
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
          <tr>
            <td>1</td>
            <td>APPL</td>
            <td>123</td>

          </tr>
          <tr>
            <td>2</td>
            <td>GOOG</td>
            <td>131</td>

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