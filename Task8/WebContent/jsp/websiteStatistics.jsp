<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>

<div class="container">
	<div class="page-header">
		<h1>Website Statistics</h1>
	</div>
	<jsp:include page="success-list.jsp" />
	<jsp:include page="error-list.jsp" />
	
	<table>
		<tr>
			<td><div id="ViewPerDay" style="width: 600px; height: 400px;"></div></td>
		</tr>
		<tr>
			<td><div id="TopCommentUsers" style="width: 600px; height: 400px;"></div></td>
		</tr>
		<tr>
			<td><div id="chart_div"
					style="align: center; width: 700px; height: 300px;"></div></td>
		</tr>
	</table>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<jsp:include page="googleWithWebsite.jsp" />
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>