<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
	<c:when test="${empty success}">
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${success}">
			<p>
				<c:out value=" Success!  : ${ item}" />
			</p>
		</c:forEach>
	</c:otherwise>
</c:choose>