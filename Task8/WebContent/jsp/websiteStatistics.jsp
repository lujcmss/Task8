<jsp:include page="header.jsp" />

</div>
</header>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>

<jsp:include page="success-list.jsp" />
<jsp:include page="error-list.jsp" />

<div id="myCarousel" class="carousel slide" data-ride="carousel" style="margin-left:139px;">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" >
		<div class="item active">
			<div id="ViewPerDay" style="width: 600px; height: 400px;" ></div>
		</div>
		<div class="item">
			<div id="TopCommentUsers" style="width: 600px; height: 400px;" ></div>
		</div>
	</div>
	<a class="left carousel-control" href="#myCarousel" data-slide="prev"><span
		class="glyphicon glyphicon-chevron-left"></span></a> <a
		class="right carousel-control" href="#myCarousel" data-slide="next"><span
		class="glyphicon glyphicon-chevron-right"></span></a>
</div>
<!-- /.carousel -->
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<jsp:include page="googleWithWebsite.jsp" />
<!-- Placed at the end of the document so the pages load faster -->
<script src="/Task8/js/jquery-2.1.0.min.js"></script>
<script src="/Task8/js/bootstrap.min.js"></script>
<script src="/Task8/js/docs.min.js"></script>
</body>
</html>