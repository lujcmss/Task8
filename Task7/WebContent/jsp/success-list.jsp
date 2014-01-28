<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
	<c:when test="${empty success}">
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${success}">
		
				<h5><font color="green"><c:out value=" Success!  : ${ item}" /></font></h5>
		
		</c:forEach>
	</c:otherwise>
</c:choose>