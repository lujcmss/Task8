<jsp:include page="header.jsp" />

    <div class="container">
    
      <div class="page-header">
        <h1>Change Password</h1>
      </div>
      <jsp:include page="error-list.jsp" />
      <form class="form-signin" method="post" action="changePassword.do">
        <input type="password" class="form-control" placeholder="Old Password" required name="oldpsw">
        <input type="password" class="form-control" placeholder="New Password" required name="newpsw">
        <p></p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Change Password</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>
</html>
