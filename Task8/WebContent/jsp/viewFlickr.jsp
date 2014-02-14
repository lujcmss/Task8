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

	<div class="container" onload="imageFunction();">
		<h3>Get the Latitude and Longitude of a Point</h3>




		<form>
			Latitude:<input size="15" type="text" id="latbox" name="lat" value="">
			Longitude:<input size="15" type="text" id="lonbox" name="lon"
				value="">
			<table style="align: center">
				<tr>
					<td><input type="text" name="tags" id="tags"
						placeholder="Search for Tags" /></td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>




			<input type="button" id="theButton"
				onclick="imageFunction(this.form.lat.value,this.form.lon.value,this.form.tags.value)"
				value="search" />
		</form>

		<input type="button" value="Clear / Reset" id="reset"
			onclick="reset()">


		<h1>Latitude and Longitude of a Point</h1>
		<div id="wrapper" style="margin: 5px">
			<div id="map" style="width: 800px; height: 450px"></div>
		</div>
	</div>
	
	<div class="clase1"></div>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<jsp:include page="googleWithFlickr.jsp" />
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>