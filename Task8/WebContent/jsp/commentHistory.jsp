<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8" %>

<div class="container">
	<div class="page-header">
		<h1>
			Comment History for : ${ user.screen_name }
		</h1>
	</div>
	<jsp:include page="success-list.jsp" />
	
	<table>
		<tr>
		<c:forEach var="item" items="${ commentHistory }" >
			<td><img src="${ item.imageSource }" /></td>
			<td><c:out value="${ item.date }" /></td>
			<td><c:out value="${ item.comment }" /></td>
		</c:forEach>
		</tr>
	</table>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
</body>
</html>