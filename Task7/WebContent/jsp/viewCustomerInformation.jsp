<jsp:include page="header.jsp" />
<jsp:include page="error-list.jsp" />
    <div class="container">
    
      <div class="page-header">
        <h1>Customer Information</h1>
      </div>
      
    <div class="search-bar">
    <form class="navbar-form navbar-center" role="search">
		<div class="form-group">
    		<input type="text" class="form-control" placeholder="Search">
  		</div>
  		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	</div>

    <div class="bs-example bs-example-tabs">
      <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#account" data-toggle="tab">Account</a></li>
        <li><a href="#transaction" data-toggle="tab">Transaction History</a></li>
      </ul>
      <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="account">
        <h1>ICU's Information</h1>
          <table class="table table-striped">
		<h3>Basic Information</h3>
		<div style="text-align:right">
			<a>Change Password</a>
		</div>
        <colgroup>
          <col class="col-xs-2">
          <col class="col-xs-5">
          <col class="col-xs-1">
        </colgroup>
        <tbody>
          <tr>
            <th>First Name</th>
            <td>I</td>
            <td><a>Edit</a></td>
          </tr>
          <tr>            
          	<th>Last Name</th>
            <td>CU</td>
            <td><a>Edit</a></td>
          </tr>
          <tr>
            <th>Address</th>
            <td>CMU, 15213</td>
            <td><a>Edit</a></td>
          </tr>
          <tr>
            <th>Last Trading Day</th>
            <td>Jan 1, 2014</td>
            <td></td>
          </tr>
          <tr>
            <th>Cash Balance</th>
            <td>$100</td>
            <td></td>
          </tr>
        </tbody>
      </table>
      
	<table class="table table-striped">
		<h3>Funds Information</h3>
        <thead>
          <tr>
            <th>#</th>
            <th>Fund Name</th>
            <th>Fund Price</th>
            <th>Share</th>
            <th>Value</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Apple</td>
            <td>100</td>
            <td>10</td>
            <td>1000</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Google</td>
            <td>800</td>
            <td>20</td>
            <td>16000</td>
          </tr>
        </tbody>
      </table>
        </div>
        <div class="tab-pane fade" id="transaction">
          <table class="table table-striped">
		<h1>ICU's Information</h1>
		
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
        </div>
      </div>
    </div><!-- /example -->

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="/Task7/js/bootstrap.min.js"></script>
  </body>