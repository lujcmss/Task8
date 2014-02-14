<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>

<div class="container">
	<div class="page-header">
		<h1>Google Map with Flicker</h1>
	</div>
	<jsp:include page="success-list.jsp" />
	<jsp:include page="error-list.jsp" />

	<form method="POST" action="viewFlickr.do">
		<table style="align: center">
			<tr>
				<td><input type="text" name="tags"
					placeholder="Search for Tags" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="button" value="Search Tags" /></td>
			</tr>
		</table>
	</form>
	<h4>
		Tags : <label id="tags"><c:out value="${ SearchTagReturn }" /></label>
	</h4>
	
	<table align="center">
		<tr valign="top">
			<td style="width: 50%;">
				<div id="map_div" style="width: 400px; height: 300;"></div>
			</td>
			<td style="width: 50%;">
				<div id="table_div"></div>
			</td>
		</tr>

	</table>
	<div class="clase1"></div>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<jsp:include page="googleWithFlickr.jsp" />
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>