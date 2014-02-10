<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8" %>

<div class="container">
	<div class="page-header">
		<h1>
			Top tags from twitter
		</h1>
	</div>
	<jsp:include page="success-list.jsp" />
	
	<form method="POST" action="viewFlickr.do">
		<table style="align:center">
			<tr>
				<td>
					<input type="text" name="tags" placeholder="Search for Tags" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="button" value="Search Tags"/>
				</td>
			</tr>
		</table>
	</form>
	<h4>
		<label id="tags"><c:out value="${ SearchTagReturn }" /></label>
	</h4>
	<form id="imagecontainer" method="get" action="">
		<input style="display: none" name="urlClicked" />
	</form>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<jsp:include page="flickrPics.jsp" />
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="/Task8/js/bootstrap.min.js"></script>
</body>
</html>