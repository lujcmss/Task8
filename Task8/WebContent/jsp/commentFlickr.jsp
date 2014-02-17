<jsp:include page="header.jsp" />

</div>
</header>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8" %>


	<jsp:include page="success-list.jsp" />
	<jsp:include page="error-list.jsp" />


<form id="contactForm" width="20%">

	<h3>Search Tag</h3>
	<input type="text" name="tags" id="tags" value="${ SearchTagReturn }"/>

	<h3>Search It!</h3>
	<input type="button" id="theButton"
		onclick="imageFunction(this.form.tags.value)"></input>
</form>

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

<!-- Bootstrap core JavaScript
    ================================================== -->
<jsp:include page="flickrPics.jsp" />
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>