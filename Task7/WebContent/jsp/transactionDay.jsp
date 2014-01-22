<jsp:include page="header.jsp" />
 <jsp:include page="error-list.jsp" />
    
 
 
 
    <div class="container">
    
     <div class="page-header">
        <h1>Transition Day</h1>
      </div>
       <form class="form-signin">
    	<table class="table table-striped">
		<h3>Transition  Information</h3>
		
		<colgroup>
          <col class="col-m-1">
          <col class="col-m-2">
          <col class="col-m-3">
          <col class="col-m-2">
          <col class="col-m-1">
         
        </colgroup>
        <thead>
          <tr>
            <th>#</th>
            <th>Last Transition Date Registered</th>
            <th>Fund Name</th>
            <th>Current Price</th>
            <th >New Price</th>
          
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Jan 10, 2014</td>
            <td>APPL</td>
            <td>100</td>
            <td><input type="text" style="min-width:120px;" class="form-control" placeholder="New Price" required>      </td>
          
          </tr>
          <tr>
            <td>2</td>
            <td>Jan 08, 2014</td>
            <td>GOOG</td>
            <td>100</td>
            <td><input type="text" style="min-width:120px;" class="form-control" placeholder="New Price" required></td>
       
          </tr>
        </tbody>
        
      </table>
      <input type="text" class="form-control" placeholder="New Transition Date" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
           
        </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="_js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="_js/bootstrap.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
   <script>    $('#sandbox-container .input-append.date').datepicker({
    format: 22
    });</script>
    
  </body>
</html>
