<jsp:include page="header.jsp" />
    <div class="container">
    
      <div class="page-header">
        <h1>Request Check</h1>
      </div>
    <jsp:include page="error-list.jsp" />
      <form class="form-signin" method="POST" action="requestCheck.do">
      	<label>Please input the request amount ($)</label>
        <input type="text" class="form-control" placeholder="Request Amount" required autofocus name="requestAmount">
        <p></p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Request</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
</html>
