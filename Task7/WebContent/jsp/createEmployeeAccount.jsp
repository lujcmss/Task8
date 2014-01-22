<jsp:include page="header.jsp" />
<jsp:include page="error-list.jsp" />
    <div class="container">
    
      <div class="page-header">
        <h1>Create Employee Account</h1>
      </div>
      
      <form class="form-signin">
        <input type="text" class="form-control" placeholder="Email" required autofocus>
        <input type="password" class="form-control" placeholder="Password" required>
             <input type="password" class="form-control" placeholder="Password Confirmation" required>
        <input type="text" class="form-control" placeholder="First Name" required>
        <input type="text" class="form-control" placeholder="Last Name" required>
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
