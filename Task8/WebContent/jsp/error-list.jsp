<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>

<c:choose>
	<c:when test="${empty errors}">
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${errors}">
			<p>
       
   <h5><font color="red"><c:out value=" Error  : ${ item}" /></font></h5>
  
			</p>
		
			
			
		</c:forEach>
	</c:otherwise>
</c:choose>
