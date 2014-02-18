<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<script src="js/alert.js"></script>

<style>
#para1
{

margin-left:139px;
}

</style>
<c:choose>
	<c:when test="${empty success}">
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${success}">


<c:if test="${item == 'Successfully commented to Twitter!'}">
   <h3 id="para1"><b><font color="green">${ item}</font></b></h3>
</c:if>
<c:if test="${item == 'Successed to like!'}">
   <h3 id="para1"><b><font color="green">Liked!</font></b></h3>
</c:if>
<c:if test="${item == 'Successed to dislike!'}">
   <h3 id="para1"><b><font color="red">Disliked!</font></b></h3>
</c:if>

     
     
    <br>
     <br>
		</c:forEach>
	</c:otherwise>
</c:choose>