<jsp:include page="header.jsp" />

    <div class="container">
    
      <div class="page-header">
        <h1>Create Customer Account</h1>
      </div>
      
      <form class="form-signin">
        <input type="text" class="form-control" placeholder="Email" required autofocus>
        <input type="password" class="form-control" placeholder="Password" required>
         <input type="password" class="form-control" placeholder="Confirm Password" required>
        <input type="text" class="form-control" placeholder="First Name" required>
        <input type="text" class="form-control" placeholder="Last Name" required>
        <input type="text" class="form-control" placeholder="Address1" required>
        <input type="text" class="form-control" placeholder="Address2">
        <input type="text" class="form-control" placeholder="City" required>
        <input type="password" class="form-control" placeholder="State" required>
        <input type="text" class="form-control" placeholder="Zip Code" required>
        <p></p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
</html>
