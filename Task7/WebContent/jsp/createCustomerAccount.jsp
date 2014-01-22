<jsp:include page="header.jsp" />

    <div class="container">
    
      <div class="page-header">
        <h1>Create Customer Account</h1>
      </div>
      <jsp:include page="error-list.jsp" />
      <form class="form-signin" method="post" action="createCustomerAccount.do">
        <input type="text" class="form-control" placeholder="Email" required autofocus name="email">
        <input type="password" class="form-control" placeholder="Password" required name="psw">
         <input type="password" class="form-control" placeholder="Confirm Password" required  name="confirm">
        <input type="text" class="form-control" placeholder="First Name" required name="firstName">
        <input type="text" class="form-control" placeholder="Last Name" required name="lastName">
        <input type="text" class="form-control" placeholder="Address1" required name="addr1">
        <input type="text" class="form-control" placeholder="Address2" name="addr2">
        <input type="text" class="form-control" placeholder="City" required name="city">
        <input type="text" class="form-control" placeholder="State" required name="state">
        <input type="text" class="form-control" placeholder="Zip Code" required name="zipCode">
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
