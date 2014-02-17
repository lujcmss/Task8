<jsp:include page="header.jsp" />

</div>
</header>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${page=='mostLike'}">
		<h3>Top Liked</h3>
	</c:when>
	<c:when test="${page=='mostDislike'}">
		<h3>Top Disliked</h3>
	</c:when>
	<c:otherwise>
		<h3>Top Commented</h3>
	</c:otherwise>
</c:choose>
<!-- MAIN -->

<form method="POST" action="commentHistory.do">
	<button type="submit" name="button" value="mostComment">most
		comment</button>
	<button type="submit" name="button" value="mostLike">most like
	</button>
	<button type="submit" name="button" value="mostDislike">most
		dislike</button>
</form>

<div id="main">
	<div class="wrapper cf">

		<!-- featured -->

		<!-- Filter container -->
		<div id="filter-container" class="cf">

			<c:forEach var="item" items="${ topBean }">
				<figure>
					<a href="${ item.imageSourceOri }" class=top_up><img
						src="${ item.imageSource }" alt=alt class=image width=300px
						height=300px />
						<h3>"${ item.title }"</h3></a>
					<h3>
						<c:choose>
							<c:when test="${page=='mostLike'}">
								Liked Times:<c:out value="${ item.count }" />
							</c:when>
							<c:when test="${page=='mostDislike'}">
								Disliked Times:<c:out value="${ item.count }" />
							</c:when>
							<c:otherwise>
								Commented Times:<c:out value="${ item.count }" />
							</c:otherwise>
						</c:choose>
					</h3>
					<figcaption>
						<a href="project.html"></a><br>
						<div id="dialog-form" title="Create new user">

							<form method="post" action="commentHistory.do">
								<input type="hidden" name="imageSource"
									value="${ item.imageSource }" />
								<input type="hidden"
									name="imageSourceOri" value="${ item.imageSourceOri }" />
								<input
									type="hidden" name="photoId" value="${item.photoId}" />
								<input
									type="hidden" name="title" value="${item.title}" />
								<fieldset>
									<label for="name">Share Comment!</label> <input type="text"
										size="10" name="comment" id="name"
										class="text ui-widget-content ui-corner-all">
								</fieldset>
								<button type="submit" name="button" value="Comment">
									<img src="/Task8/img/social/twitter_bird.png">
								</button>
								<button id="like" name="button" value="Like">
									<img src="/Task8/img/social/heart.png">
								</button>
								<button id="dislike" name="button" value="Dislike">
									<img src="/Task8/img/social/feed_burner.png">
								</button>
							</form>
						</div>
					</figcaption>
				</figure>
			</c:forEach>
		</div>
		<!-- ENDS Filter container -->
	</div>
	<!-- ENDS WRAPPER -->
</div>
<!-- ENDS MAIN -->
</body>
</html>