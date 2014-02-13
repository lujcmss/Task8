<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8" %>

<div class="container">
	<div class="page-header">
		<h1>
			Search for Tags and Comment
		</h1>
	</div>
	<jsp:include page="success-list.jsp" />
	
	<form method="POST" action="commentFlickr.do">
		<table style="align:center">
			<tr>
				<td>
					<input type="text" name="tags" placeholder="Search for Tags" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="button" value="Search Tags" />
				</td>
			</tr>
		</table>
	</form>
	<h4>
		Here's top 10 images for : <label id="tags"><c:out value="${ SearchTagReturn }" /></label>
	</h4>
	<div id="imagecontainer">
	</div>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<jsp:include page="flickrPics.jsp" />
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>