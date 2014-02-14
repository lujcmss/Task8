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
	<jsp:include page="error-list.jsp" />
	
	<h4>
		Here's most popular pictures in our website:
	</h4>
	<table>
		<c:forEach var="item" items="${ topComment }" >
			<form method="POST" action="commentHistory.do">
				<tr>
					<td><a class="top_up" href="${ item.imageSourceOri }"><img src="${ item.imageSource }" /></a></td>
					<td>Commented times: <c:out value="${ item.count }" /></td>
					<td><input type="text" name="comment" /></td>
					<td><input type="submit" name="button" value="Comment" /></td>
				</tr>
			</form>
		</c:forEach>
	</table>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript"
	src="http://gettopup.com/releases/latest/top_up-min.js"></script>
</body>
</html>