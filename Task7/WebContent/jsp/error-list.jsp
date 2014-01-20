
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:choose>
    <c:when test="${empty errors}">

			
				
    </c:when>
    <c:otherwise>
   
				<c:forEach var="item" items="${errors}">
			
        				<p>
        					<c:out value=" Error  : ${ item}" />
        					
        					
                
            
        				</p>
            
        			
   				
   			</c:forEach>
				
    </c:otherwise>
</c:choose>