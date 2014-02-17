<jsp:include page="header.jsp" />

</div>
</header>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>

<jsp:include page="success-list.jsp" />
<jsp:include page="error-list.jsp" />

<form id="contactForm" width="20%">
	<h3>Latitude:</h3>
	<input size="7" width="20%" type="text" id="latbox" name="lat" value="${ lat }"
		class="form-poshytip">
	<h3>Longitude:</h3>
	<input size="7" type="text" id="lonbox" name="lon" value="${ lon }">

	<h3>Search Tag</h3>
	<input type="text" name="tags" id="tags" value="${ mapTag }"/>

	<h3>Search It!</h3>
	<input type="button" id="theButton"
		onclick="imageFunction(this.form.lat.value,this.form.lon.value,this.form.tags.value)"></input>
</form>

<input id="pac-input" class="controls" type="text"
	placeholder="Search Box">
<div id="map-canvas"></div>

<!-- MAIN -->
<div id="main">
	<div class="wrapper cf">
		<!-- Filter container -->
		<div id="filter-container" class="cf"></div>
		<!-- ENDS Filter container -->
	</div>
	<!-- ENDS featured -->
</div>
<!-- ENDS MAIN -->

<jsp:include page="googleWithFlickr.jsp" />
</body>
</html>