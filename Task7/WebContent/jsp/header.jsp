<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Note there is no responsive meta tag here -->

    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>Non-responsive Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/Task7/css/bootstrap.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="/Task7/css/non-responsive.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="/Task7/css/signin.css" rel="stylesheet" type="text/css">
    
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="home.do">Home</a></li>
            
            <!-- customer 
            <li class="dropdown" class="active">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Funds<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="researchFund.do">Research Fund</a></li>
                <li><a href="buyFund.do">Buy Fund</a></li>
                <li><a href="sellFund.do">Sell Fund</a></li>
              </ul>
            </li>
            <li><a href="requestCheck.do">Request Check</a></li>
            <li><a href="transactionHistory.do">Transaction History</a></li>
            -->
            
            <!-- employee-->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Manage Accounts<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li class="dropdown-header">Employee</li>
                <li><a href="createEmployeeAccount.do">Create Employee Account</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Customer</li>
                <li><a href="createCustomerAccount.do">Create Customer Account</a></li>
                <li><a href="viewCustomerInformation.do">Reset Customer Account</a></li>
                <li><a href="viewCustomerInformation.do">View Customer Information</a></li>
              </ul>
            </li>
            <li><a href="depositCheck.do">Deposit Check</a></li>
            <li><a href="createFund.do">Create Fund</a></li>
            <li><a href="transactionDay.do">Transaction Day</a></li>
            
          </ul>
          
          <ul class="nav navbar-nav navbar-right">
            <li><a>Hi, ICU.  Cash: $100</a></li>
            <li><a href="logout.do">Logout</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>