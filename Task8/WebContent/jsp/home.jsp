<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
	<div class="page-header">
		<h1>
			Welcome,
			<c:out value="${user.firstName}" />
			<c:out value="${user.lastName}" />
		</h1>
	</div>
	<jsp:include page="success-list.jsp" />
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="/Task8/js/bootstrap.min.js"></script>
</body>
</html>