<jsp:include page="header.jsp" />

    <div class="container">
    
      <div class="page-header">
        <h1>Deposit Check</h1>
      </div>
      
    <div class="search-bar">
    <form class="navbar-form navbar-center" role="search">
		<div class="form-group">
    		<input type="text" class="form-control" placeholder="Customer Id">
  		</div>
  		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	</div>
	
      <form class="form-signin">
        <input type="text" class="form-control" placeholder="Deposit Amount" required autofocus>
        <p></p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Deposit</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
</html>